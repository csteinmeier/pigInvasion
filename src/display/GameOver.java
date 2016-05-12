package display;

import Utility.GameObject;
import main.GameState;
import main.HighscoreState;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameOver extends GameObject
{
    private int height;
    private int width;
    private int textWidth ;
    private int textHeight;
    private Color transparent;
    private Font fontGameOver;
    private boolean isGameOver;
    private static final String GAME_OVER = "_Game Over";
    private static final String TEXT_YEAH = "YEAH, neuer Highscore! \nTrag dich ein!";
    private static final String TEXT_NOO = "NOOO! \nLeider nicht in der \nBestenliste :(";
    private TextField namenseingabe;
    private GameContainer container;
    private StateBasedGame game;
    private Font fontNormal;
    private boolean newName;
    private boolean fertig=false;
    private int punkte;
    private Shape button;
    
    public GameOver(int height, int width, Font fontGameOver, Font fontNormal, GameContainer container, StateBasedGame g) 
    {
        this.isGameOver = false;
        this.height = height;
        this.width = width;
        this.fontGameOver = fontGameOver;
        transparent = new Color(Color.black);
        transparent.a = 0.8f;
        this.container = container;
        this.fontNormal = fontNormal;
        this.game = g;
        this.namenseingabe = new TextField(container,fontNormal, 200,550,450,45);
        button = new Rectangle(660, 550, 110, 45);
    }
    
    
    @Override
    public void draw(Graphics g) {
        g.setColor(transparent);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.white);
        g.setFont(fontGameOver);
        g.drawString(GAME_OVER, 500, 100);
        g.setFont(fontNormal);
        if (newName)
        {
            g.drawString(TEXT_YEAH, 200, 450);
            namenseingabe.setBorderColor(transparent);
            namenseingabe.setBackgroundColor(Color.darkGray);
            namenseingabe.setTextColor(Color.magenta);
            namenseingabe.setMaxLength(15);
            namenseingabe.render(container, g);
        }
        else
        {
            g.drawString(TEXT_NOO, 200, 450);
        }
        g.drawString("Okay", 662, 552);
        g.drawRoundRect(660, 550, 110, 45, 10);
    }
    
    @Override 
    public void update(int delta) 
    {
        if (button.contains(container.getInput().getMouseX(), container.getInput().getMouseY())
           && container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON))
        {
            fertig = true;
        }
        if(container.getInput().isKeyPressed(Input.KEY_ENTER) || fertig)
        {
            HighscoreState.getHighscoreList().add(new HighscoreEntry(namenseingabe.getText(),punkte));
            game.enterState(HighscoreState.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    }
    
    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setNewHighscore(boolean b, int punkte) 
    {
        this.newName = b;
        this.punkte = punkte;
    }
}
