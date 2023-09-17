import controller.GameController;
import models.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TikTacToeGame {
    public static void main(String[] args) {

        System.out.println("Welcome to TIK-TAC-TOE game");
        Scanner sc = new Scanner(System.in);

        System.out.println("What is the dimension of the game?");
        int dimension = sc.nextInt();

        System.out.println("How many players will play?");
        int noOfPlayers = sc.nextInt();

        List<Player> playerList = new LinkedList<>();
        System.out.println("Will there be any BOT? y/n");
        String isBot = sc.next();

        if(isBot.equals("y")){
            noOfPlayers-=1;

            System.out.println("Enter the name of Bot: ");
            String botName = sc.next();

            System.out.println("Enter the symbol of the bot: ");
            String botSymbol = sc.next();

            playerList.add(new BOT(botSymbol.charAt(0),botName, BotDifficultyLevel.EASY));

        }

        for(int i = 0; i < noOfPlayers; i++) {
            System.out.println("What is the name of the player: " + (i+1));
            String name = sc.next();

            System.out.println("What is the symbol for player: " + i+1);
            String symbol = sc.next(); //Assumption : Single character.

            Player player = new Player(symbol.charAt(0), name, PlayerType.HUMAN);
            playerList.add(player);
        }

//        Game game = Game.getBuilder()
//                .setDimension(dimension)
//                .setPlayers(playerList)
//                .build();
        GameController gameController = new GameController();

        Game game = gameController.createGame(dimension, playerList);
        gameController.setGameStatus(game,GameStatus.PROGRESS);

        while(gameController.getGameStatus(game) == GameStatus.PROGRESS) {
            System.out.println("Current Board: ");
            gameController.displayBoard(game);

            System.out.println("Do you want to undo ? y/n");
            String input = sc.next();

            if (input.equals("y")) {
                gameController.undo(game);
            } else {
                gameController.executeNextMove(game);
            }

        }

        if(gameController.getGameStatus(game) == GameStatus.ENDED) {
            // someone has won
            System.out.println("Winning Player: " +
                    gameController.getWinnerName(game));
        } else {
            System.out.println("Game has drawn");
        }

    }
}
