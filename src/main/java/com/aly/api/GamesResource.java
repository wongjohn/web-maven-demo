package com.aly.api;

import com.aly.api.config.ResourceConfig;
import com.aly.domain.Games;
import com.aly.service.GamesService;
import com.aly.util.HttpHeaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * 游戏资源类。
 * 进行操作时，需要用户登陆，需要从HTTP请求头中获取用户认证信息，所以需要继承BaseConfigResource。
 */
@RestController
@RequestMapping(value = "/api", headers="Authorization")
public class GamesResource extends ResourceConfig {
    /**
     * 获取全部游戏。
     * @return 全部游戏
     */
    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public List<Games> getAllGames() {
        return gamesService.getAllGames();
    }

    /**
     * 创建新游戏。
     * @param games 游戏内容
     * @return 刚刚创建的游戏
     */
    @RequestMapping(value = "/games", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public Games createGames(@Valid @RequestBody Games games, HttpServletRequest request,HttpServletResponse response) {
        gamesService.insert(games);
        HttpHeaderBuilder.location(request, response, games.getGameId());
        return games;
    }

    /**
     * 根据游戏Id，获取游戏信息。
     * @param id 游戏Id
     * @return 游戏详细信息
     */
    @RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
    public Games getGamesById(@PathVariable Integer id) {
        return gamesService.getGamesById(id);
    }

    /**
     * 修改游戏。
     * @param id 游戏Id
     * @param games 游戏内容
     * @return 修改后的游戏
     */
    @RequestMapping(value = "/games/{id}", method = RequestMethod.POST)
    public Games updateGames(@PathVariable Integer id, @Valid @RequestBody Games games) {
        games.setGameId(id);
        gamesService.updateByPrimaryKey(games);
        return games;
    }

    /**
     * 删除游戏。
     * @param id 游戏Id
     */
    @RequestMapping(value = "/games/{id}", method = RequestMethod.DELETE)
    public void deleteGames(@PathVariable Integer id) {
        gamesService.deleteByPrimaryKey(id);
    }

    private GamesService gamesService;

    @Autowired
    public void setGamesService(GamesService gamesService) {
        this.gamesService = gamesService;
    }
}
