package com.aly.api;

import com.aly.api.config.ResourceConfig;
import com.aly.domain.Recruits;
import com.aly.service.RecruitsService;
import com.aly.util.HttpHeaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * 人事招聘。
 * 进行操作时，需要用户登陆，需要从HTTP请求头中获取用户认证信息，所以需要继承BaseConfigResource。
 */
@RestController
@RequestMapping(value = "/api", headers="Authorization")
public class RecruitsResource extends ResourceConfig {
    /**
     * 获取全部人事招聘。
     * @return 全部人事招聘
     */
    @RequestMapping(value = "/recruits", method = RequestMethod.GET)
    public List<Recruits> getAllRecruits() {
        return recruitsService.getAllRecruits();
    }

    /**
     * 创建新人事招聘。
     * @param recruits 人事招聘内容
     * @return 刚刚创建的人事招聘
     */
    @RequestMapping(value = "/recruits", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public Recruits createRecruits(@Valid @RequestBody Recruits recruits, HttpServletRequest request,HttpServletResponse response) {
        recruitsService.insert(recruits);
        HttpHeaderBuilder.location(request, response, recruits.getRecruitId());
        return recruits;
    }

    /**
     * 根据人事招聘Id，获取招聘信息。
     * @param id 招聘Id
     * @return 招聘详细信息
     */
    @RequestMapping(value = "/recruits/{id}", method = RequestMethod.GET)
    public Recruits getRecruitsById(@PathVariable Integer id) {
        return recruitsService.getRecruitsById(id);
    }

    /**
     * 修改人事招聘。
     * @param id 人事招聘Id
     * @param recruits 人事招聘内容
     * @return 修改后的人事招聘
     */
    @RequestMapping(value = "/recruits/{id}", method = RequestMethod.POST)
    public Recruits updateRecruits(@PathVariable Integer id, @Valid @RequestBody Recruits recruits) {
        recruits.setRecruitId(id);
        recruitsService.updateByPrimaryKey(recruits);
        return recruits;
    }

    /**
     * 删除人事招聘。
     * @param id 人事招聘Id
     */
    @RequestMapping(value = "/recruits/{id}", method = RequestMethod.DELETE)
    public void deleteRecruits(@PathVariable Integer id) {
        recruitsService.deleteByPrimaryKey(id);
    }

    private RecruitsService recruitsService;

    @Autowired
    public void setRecruitsService(RecruitsService recruitsService) {
        this.recruitsService = recruitsService;
    }
}
