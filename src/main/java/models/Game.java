package models;

import exceptions.InvalidGameDimensionException;
import strategy.winStrategy.GameWinningStrategy;
import strategy.winStrategy.OrderOneGameWinningStrategy;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus status;
    private int nextPlayerIndex;

    private Player winningPlayer;

    public Player getWinner() {
        return winningPlayer;
    }

    public void setWinner(Player winner) {
        this.winningPlayer = winner;
    }

    private GameWinningStrategy gameWinningStrategy;

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }


    public static Builder getBuilder() {
        return new Builder();
    }

    public void makeNextMove() {
        Player playerWhoMoveItis = players.get(nextPlayerIndex);
        System.out.println("It is " + playerWhoMoveItis.getName() + "'s turn");

        Move move = playerWhoMoveItis.decideMove(board);

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (board.getBoard().get(row).get(col).getState()
                .equals(CellState.EMPTY)) {
            //Move is valid.
            board.applyMove(move);
            moves.add(move);

            // check the winner
            if (gameWinningStrategy.checkWinner(board, move)) {
                status = GameStatus.ENDED;
                winningPlayer = playerWhoMoveItis;
            }

            nextPlayerIndex +=1;
            nextPlayerIndex %= players.size();
        } else {
            // throw exception
        }
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }


        public Game build(){
            try{
                isValid();
            }catch (InvalidGameDimensionException e){
                System.out.println("Error has occured");
                return null;
            }

            Game game = new Game();
            game.setBoard(new Board(dimension));
            game.setPlayers(players);
            game.setMoves(new LinkedList<>());
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));

            return game;
        }

        private boolean isValid() throws InvalidGameDimensionException {
            if(dimension<3){
                throw new InvalidGameDimensionException("Dimension of game can not be less than 3");
            }
            return true;
        }

    }
}
