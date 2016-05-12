package main;

import Utility.HSComparator;
import display.HighscoreEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class HighscoreState extends BasicGameState
{
    public static final int ID = 4;
    private static final int BOX_X = 70;
    private static final int BOX_Y = 70;
    private static final int BOX_WIDTH = 700;
    private static final int BOX_HEIGHT = 550;    
    private static final String HIGHSCORE = "_Highscore";
    private static List<HighscoreEntry> highscoreList = new ArrayList<>();
    
    private StateBasedGame game;
    private Image hintergrund;
    private Font font;
    private Color transpBlack;
    private Color transpGreen;
    private int textWidth2;
    private int textWidth1;
    
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException 
    {
        this.game = game;
        this.hintergrund = new Image("res/img/menu.jpg");
        this.font = new AngelCodeFont("res/fonts/mittel_gruen.fnt", new Image("res/fonts/mittel_gruen.png"));
        transpBlack = new Color(Color.black);
        transpGreen = new Color(Color.green);
        transpBlack.a = 0.8f;
        transpGreen.a = 0.4f;
        highscoreList.add(new HighscoreEntry("Frau Hoppel",1000));
        highscoreList.add(new HighscoreEntry("Frau Hoppel",900));
        highscoreList.add(new HighscoreEntry("Frau Hoppel",800));
        highscoreList.add(new HighscoreEntry("Frau Hoppel",700));
        highscoreList.add(new HighscoreEntry("Frau Hoppel",600));
        highscoreList.add(new HighscoreEntry("Frau Hoppel",500));
        highscoreList.add(new HighscoreEntry("Frau Hoppel",400));
        highscoreList.add(new HighscoreEntry("Frau Hoppel",300));
        highscoreList.add(new HighscoreEntry("Frau Hoppel",200));
        highscoreList.add(new HighscoreEntry("Frau Hoppel",100));
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
    {
        hintergrund.draw();
        drawTable(g);
        g.setColor(Color.black);
        int zeilenhoehe = 40;
        if (highscoreList.size()>10)
        {
            sortiereNeu();
        }
        for (int i=0; i<highscoreList.size(); i++)
        {
            g.drawString(""+(i+1),BOX_X+20,BOX_Y+60+zeilenhoehe*(i+1));
            HighscoreEntry entry = highscoreList.get(i);
            g.drawString(entry.getName(), BOX_X+20+textWidth1, BOX_Y+60+zeilenhoehe*(i+1));
            g.drawString(""+entry.getPunkte(), BOX_X+20+textWidth1+textWidth2, BOX_Y+60+zeilenhoehe*(i+1));
        }
    }
    
    private void drawTable(Graphics g)
    {
        g.setColor(transpBlack);
        g.fillRoundRect(BOX_X, BOX_Y, BOX_WIDTH, BOX_HEIGHT, 10);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(HIGHSCORE,BOX_X+10,BOX_Y+10);
        g.setColor(transpGreen);
        g.fillRoundRect(BOX_X+10, BOX_Y+60, BOX_WIDTH-20, BOX_HEIGHT-70, 10);
        g.setColor(Color.black);
        String leer = "999";
        String name = "Name              ";
        textWidth1= font.getWidth(leer);
        textWidth2= font.getWidth(name);
        g.drawString(name,BOX_X+20+textWidth1,BOX_Y+60);
        g.drawString("Score", BOX_X+20+textWidth1+textWidth2, BOX_Y+60);
        g.drawLine(BOX_X+5+textWidth1, BOX_Y+65, BOX_X+5+textWidth1, BOX_Y+BOX_HEIGHT-15);
        g.drawLine(BOX_X+10+textWidth1+textWidth2, BOX_Y+65, BOX_X+10+textWidth1+textWidth2, BOX_Y+BOX_HEIGHT-15);
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
    {
        Input input = container.getInput();
        if(container.getInput().isKeyPressed(Input.KEY_ESCAPE))
        {
            game.enterState(MenuState.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    }
        
    public static List<HighscoreEntry> getHighscoreList() {
        return highscoreList;
    }

    public static void setHighscoreList(List<HighscoreEntry> highscoreList) {
        highscoreList = highscoreList;
    }

    private void sortiereNeu() 
    {
        Comparator<HighscoreEntry> comp = new HSComparator();
        Collections.sort(highscoreList,comp);
        highscoreList.remove(highscoreList.size()-1);
    }
}
