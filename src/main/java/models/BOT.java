package models;

import strategy.botStrategy.BotPlayingStrategy;
import strategy.botStrategy.EasyBotPlayingStrategy;

public class BOT extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    // we can use factory to assign the strategy based on diffculty level
    private BotPlayingStrategy botPlayingStrategy;


    public BOT(char ch, String botName, BotDifficultyLevel easy) {
        super(ch,botName,PlayerType.BOT);
        this.botDifficultyLevel=easy;
        this.botPlayingStrategy = new EasyBotPlayingStrategy();

    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public Move decideMove(Board board) {
        return botPlayingStrategy.decideMove(this, board);
    }
}
