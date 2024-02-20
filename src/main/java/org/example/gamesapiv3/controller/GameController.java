package org.example.gamesapiv3.controller;

import org.example.gamesapiv3.Service.GameService;
import org.example.gamesapiv3.model.Data;
import org.example.gamesapiv3.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    GameService gameService;

    @GetMapping("/{id}")
    Game getGameById(@PathVariable int id){
        return Data.games.stream()
                .filter(game -> id == game.getId())
                .findAny()
                .orElse(null);
    }

    @DeleteMapping("/{id}")
     boolean DeleteGameById(@PathVariable int id){
        Game foundGame = Data.games.stream()
                .filter(game -> id == game.getId())
                .findAny()
                .orElse(null);
        if(foundGame != null) {
            Data.games.remove(foundGame);
            return true;
        }
        return false;
    }

    @PutMapping()
    boolean updateGameById(@RequestBody Game updGame) {
        for (Game game : Data.games) {
            if (updGame.getId() == game.getId()){
                Data.games.set(Data.games.indexOf(game), updGame);
                return true;
            }
        }
        return false;
    }

//    @GetMapping()
//    List<Game> getAllGames(){
//        return Data.games;
//    }
    @GetMapping()
    List<Game> getAllGames(){
        return gameService.getAllGames();
    }

    @PostMapping()
    void createGame(@RequestBody Game game) {
        Data.games.add(game);
    }
}

