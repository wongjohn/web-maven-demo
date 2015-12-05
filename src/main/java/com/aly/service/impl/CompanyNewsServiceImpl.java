package com.aly.service.impl;

import com.aly.dao.CompanyNewsMapper;
import com.aly.domain.CompanyNews;
import com.aly.service.CompanyNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司动态服务。
 */
@Service("companyNewsService")
public class CompanyNewsServiceImpl implements CompanyNewsService {
    @Override
    public int deleteByPrimaryKey(Integer newsId) {
        return companyNewsMapper.deleteByPrimaryKey(newsId);
    }
    /**公司动态之间需要通过previous_id、next_id进行关联，所以在插入、修改时，需要特别注意。*/
    @Override
    public int insert(final CompanyNews record) {
        CompanyNews companyNewsWithEmptyPreviousId = getPreviousCompanyNewsAndSetLinkInfo(record);

        int insertResult = companyNewsMapper.insert(record);

        updatePreviousCompanyNewsInfoWithLinkInfo(record, companyNewsWithEmptyPreviousId);

        return insertResult;
    }

    private void updatePreviousCompanyNewsInfoWithLinkInfo(CompanyNews record, CompanyNews companyNewsWithEmptyPreviousId) {
        if(null != companyNewsWithEmptyPreviousId) {
            companyNewsWithEmptyPreviousId.setPreviousId(record.getNewsId());
            companyNewsWithEmptyPreviousId.setPreviousShortcut(record.getShortcut());
            companyNewsWithEmptyPreviousId.setPreviousTitle(record.getNewsTitle());
            companyNewsMapper.updateByPrimaryKey(companyNewsWithEmptyPreviousId);
        }
    }

    private CompanyNews getPreviousCompanyNewsAndSetLinkInfo(CompanyNews record) {
        CompanyNews companyNewsWithEmptyPreviousId = companyNewsMapper.selectByEmptyPreviousId();
        if(null != companyNewsWithEmptyPreviousId) {
            record.setNextId(companyNewsWithEmptyPreviousId.getNewsId());
            record.setNextShortcut(companyNewsWithEmptyPreviousId.getShortcut());
            record.setNextTitle(companyNewsWithEmptyPreviousId.getNewsTitle());
        }
        return companyNewsWithEmptyPreviousId;
    }

    @Override
    public int insertSelective(CompanyNews record) {
        CompanyNews companyNewsWithEmptyPreviousId = getPreviousCompanyNewsAndSetLinkInfo(record);

        int insertResult = companyNewsMapper.insertSelective(record);

        updatePreviousCompanyNewsInfoWithLinkInfo(record, companyNewsWithEmptyPreviousId);
        return insertResult;
    }
    @Override
    public CompanyNews selectByPrimaryKey(Integer newsId) {
        return companyNewsMapper.selectByPrimaryKey(newsId);
    }
    @Override
    public int updateByPrimaryKeySelective(CompanyNews record) {
        return companyNewsMapper.updateByPrimaryKeySelective(record);
    }
    @Override
    public int updateByPrimaryKey(CompanyNews record) {
        return companyNewsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CompanyNews> getAllCompanyNews() {
        return companyNewsMapper.getAllCompanyNews();
    }

    @Override
    public CompanyNews getCompanyNewsById(Integer id) {
        return companyNewsMapper.selectByPrimaryKey(id);
    }

    @Override
    public CompanyNews getComanyNewsByShortcut(String shortcut) {
        return companyNewsMapper.getCompanyNewsByShortcut(shortcut);
    }

    private CompanyNewsMapper companyNewsMapper;

    @Autowired
    public void setCompanyNewsMapper(CompanyNewsMapper companyNewsMapper) {
        this.companyNewsMapper = companyNewsMapper;
    }

}
