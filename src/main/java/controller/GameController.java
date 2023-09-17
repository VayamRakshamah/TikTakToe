package controller;

import models.Game;
import models.GameStatus;
import models.Player;

import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player> players) {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .build();
    }
    public void undo(Game game) {

    }


    public GameStatus getGameStatus(Game game) {
        return game.getStatus();
    }

    public void setGameStatus(Game game, GameStatus gameStatus) {
        game.setStatus(gameStatus);
    }

    public String getWinnerName(Game game) {
        return game.getWinner().getName();
    }

    public void executeNextMove(Game game) {
        game.makeNextMove();
    }

    public void displayBoard(Game game) {
        game.getBoard().displayBoard();
    }

}

