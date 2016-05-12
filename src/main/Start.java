package main;

import display.HighscoreEntry;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Start extends StateBasedGame {

    public Start() 
    {
	super("Invasion!");
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException 
    {
        addState(new MenuState());
        addState(new GameState());
        addState(new HighscoreState());
        addState(new PauseState());
    }
    
    public static void main(String[] argv) 
    {
        try 
        {
            AppGameContainer container = new AppGameContainer(new Start());
            container.setDisplayMode(1024, 768, false);
            container.setClearEachFrame(false);
            container.setMinimumLogicUpdateInterval(25);
            container.setShowFPS(false);
            container.start();
        } 
        catch (SlickException e) {
                e.printStackTrace();
        }
    }
}
