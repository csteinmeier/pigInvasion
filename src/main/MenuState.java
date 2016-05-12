package main;

import display.MenuEntry;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class MenuState extends BasicGameState
{
    public static final int ID = 2;
    private static final int BOX_X = 70;
    private static final int BOX_Y = 70;
    private Font font;
    private StateBasedGame game;
    private Image hintergrund;
    private MenuEntry starten;
    private MenuEntry beenden;
    private MenuEntry highscore;
    private MenuEntry settings;
    

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException 
    {
        this.game = game;
        font = new AngelCodeFont("res/fonts/mittel_gruen.fnt", new Image("res/fonts/mittel_gruen.png"));
        hintergrund = new Image("res/img/menu.jpg");
        starten = new MenuEntry(BOX_X+25, BOX_Y+50,"START",1,font);
        settings = new MenuEntry(BOX_X+25, BOX_Y+100,"SETTINGS",3,font);
        highscore = new MenuEntry(BOX_X+25, BOX_Y+150,"HIGHSCORE",4,font);
        beenden = new MenuEntry(BOX_X+25, BOX_Y+200,"EXIT",0,font);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
    {
        hintergrund.draw();
        Color transparent = new Color(Color.black);
        transparent.a = 0.8f;
        g.setColor(transparent);
        g.fillRoundRect(BOX_X, BOX_Y, 300, 300, 10);
        starten.draw(g);
        settings.draw(g);
        highscore.draw(g);
        beenden.draw(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
    {
        Input input = container.getInput();
        if (starten.pruefeKollision(input.getMouseX(), input.getMouseY()) && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
        {
            game.enterState(GameState.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
        if (settings.pruefeKollision(input.getMouseX(), input.getMouseY()) && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
        {
            //TODO: settings 
        }
        if (highscore.pruefeKollision(input.getMouseX(), input.getMouseY()) && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
        {
            game.enterState(HighscoreState.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
        if (beenden.pruefeKollision(input.getMouseX(), input.getMouseY()) && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
        {
            container.exit();
        }
    }
}
