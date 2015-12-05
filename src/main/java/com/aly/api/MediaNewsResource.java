package com.aly.api;

import com.aly.api.config.ResourceConfig;
import com.aly.domain.MediaNews;
import com.aly.service.MediaNewsService;
import com.aly.util.HttpHeaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * 媒体动态。
 * 进行操作时，需要用户登陆，需要从HTTP请求头中获取用户认证信息，所以需要继承BaseConfigResource。
 */
@RestController
@RequestMapping(value = "/api/news", headers="Authorization")
public class MediaNewsResource extends ResourceConfig {

    /**
     * 获取全部媒体动态。
     * @return 全部媒体动态
     */
    @RequestMapping(value = "/media", method = RequestMethod.GET)
    public List<MediaNews> getAllMediaNews() {
        return mediaNewsService.getAllMediaNews();
    }

    /**
     * 创建新媒体动态。
     * @param mediaNews 媒体动态内容
     * @return 刚刚创建的媒体动态
     */
    @RequestMapping(value = "/media", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public MediaNews createMediaNews(@Valid @RequestBody MediaNews mediaNews, HttpServletRequest request,HttpServletResponse response) {
        mediaNewsService.insert(mediaNews);
        HttpHeaderBuilder.location(request, response, mediaNews.getNewsId());
        return mediaNews;
    }

    /**
     * 根据媒体动态Id，获取媒体动态信息。
     * @param id 媒体动态ID
     * @return 媒体动态详细信息
     */
    @RequestMapping(value = "/media/{id}", method = RequestMethod.GET)
    public MediaNews getMediaNewsById(@PathVariable Integer id) {
        return mediaNewsService.getMediaNewsById(id);
    }

    /**
     * 修改媒体动态。
     * @param id 媒体动态Id
     * @param mediaNews 媒体动态内容
     * @return 修改后的媒体动态
     */
    @RequestMapping(value = "/media/{id}", method = RequestMethod.POST)
    public MediaNews updateMediaNews(@PathVariable Integer id, @Valid @RequestBody MediaNews mediaNews) {
        mediaNews.setNewsId(id);
        mediaNewsService.updateByPrimaryKey(mediaNews);
        return mediaNews;
    }

    /**
     * 删除媒体动态。
     * @param id 媒体动态Id
     */
    @RequestMapping(value = "/media/{id}", method = RequestMethod.DELETE)
    public void deleteMediaNews(@PathVariable Integer id) {
        mediaNewsService.deleteByPrimaryKey(id);
    }

    private MediaNewsService mediaNewsService;

    @Autowired
    public void setMediaNewsService(MediaNewsService mediaNewsService) {
        this.mediaNewsService = mediaNewsService;
    }
}
