package com.aly.controller;

import com.aly.domain.Games;
import com.aly.service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 旗下游戏.
 */
@Controller
public class GamesController {
    @RequestMapping("/games")
    public String games(Model model) {
        List<Games> allGames = gamesService.getAllGames();
        List<Games> onlineGames = new ArrayList<>(), casualGames = new ArrayList<>();
        for(Games game : allGames) {
            if("网络游戏".equals(game.getGameType())) {
                onlineGames.add(game);
            } else {
                casualGames.add(game);
            }
        }
        model.addAttribute("onlineGames", onlineGames);
        model.addAttribute("casualGames", casualGames);
        return "games";
    }

    private GamesService gamesService;

    @Autowired
    public void setGamesService(GamesService gamesService) {
        this.gamesService = gamesService;
    }
}
