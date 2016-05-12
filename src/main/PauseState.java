package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class PauseState extends BasicGameState
{
    public static final int ID = 5;
    private static final String PAUSE = "_Pause";
    private StateBasedGame game;
    private Image hintergrund;
    private AngelCodeFont font;
    
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException 
    {
        this.game = game;
        this.hintergrund = new Image("res/img/background.jpg");
        this.font = new AngelCodeFont("res/fonts/gross_gruen.fnt", new Image("res/fonts/gross_gruen.png"));
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
    {
        hintergrund.draw();
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(PAUSE, 500, 100);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
    {
        if(container.getInput().isKeyPressed(Input.KEY_P))
        {
            game.enterState(GameState.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    }
    
}
