package main;

import Utility.Flugbahn;
import Utility.Weapon;
import display.Damage;
import display.GameOver;
import display.HighscoreEntry;
import display.Score;
import entities.Drop;
import entities.Enderman;
import entities.Munition;
import entities.Pig;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class GameState extends BasicGameState
{
    //const
    public static final int ID = 1;
    private final int MAX_DMG = 3;
    private final int PUNKTE_ABSCHUSS = 2;
    private final int PUNKTE_PORK = 5;
    //res
    private Image hintergrund;
    private Image pigImg;
    private Image enderImg;
    private Image pearlImg;
    private Image dmgImg;
    private Image porkImg;
    private Image dirtImg;
    private Image cobbleImg;
//    private Sound schuss;
//    private Music ambient;
    private Font normal;
    private Font gameover;
    //display
    private Score punkteAnzeige;
    private GameOver ende;
    private Damage dmg;
    //entities
    private Pig pig;
    private Munition pearl;
    private Munition cobble;
    private Enderman ender;
    private List<Munition> munition = new ArrayList<>();
    private List<Weapon> shots = new ArrayList<>();
    private List<Drop> drops = new ArrayList<>();
 
    private StateBasedGame game;
    private boolean paused;
    private int pearls;
    private int cobbles;

    @Override 
    public void enter(GameContainer container, StateBasedGame game)
    {
        if (!paused)
        {
            punkteAnzeige = new Score(20,20,normal);
            dmg = new Damage(40,80,dmgImg);
            ende = new GameOver(container.getHeight(),container.getWidth(),gameover,normal,container, game);
            ender = new Enderman(400,700,container.getInput(),enderImg);
            pig = new Pig(400,100,pigImg);
            paused = false;
        }
    }
    
    @Override 
    public void leave(GameContainer container, StateBasedGame game)
    {
        if (!paused)
        {
            ende.setGameOver(false);
            punkteAnzeige.reset();
            pig.reset();
            ender.reset();
            dmg.reset();
            for (Weapon w:shots)
            {
                w.verschwinde();
            }
            for (Drop d : drops)
            {
                d.verschwinde();
            }
            for (Munition m : munition)
            {
                m.verschwinde();
            }
            paused = false;
        }
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException 
    {
        this.game = game;
        hintergrund = new Image("res/img/background.jpg");
        pigImg = new Image("res/img/pigheadsmall.jpg");
        enderImg = new Image("res/img/enderhead.jpg");
        pearlImg = new Image("res/img/enderpearl.png");
        dmgImg = new Image("res/img/pigdmg.jpg");
        porkImg = new Image("res/img/pork.png");
        dirtImg = new Image("res/img/dirt.png");
        cobbleImg = new Image("res/img/cobble.png");
        normal = new AngelCodeFont("res/fonts/mittel_gruen.fnt", new Image("res/fonts/mittel_gruen.png"));
        gameover = new AngelCodeFont("res/fonts/gross_gruen.fnt", new Image("res/fonts/gross_gruen.png"));
        punkteAnzeige = new Score(20,20,normal);
        dmg = new Damage(40,80,dmgImg);
        ende = new GameOver(container.getHeight(),container.getWidth(),gameover,normal,container, game);
        ender = new Enderman(400,700,container.getInput(),enderImg);
        pig = new Pig(400,100,pigImg);
//        ambient = new Music("res/testloop.ogg");
//        ambient.loop();
//        schuss = new Sound("");
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
    {
        Input input = container.getInput();
        pig.update(delta);
        ender.update(delta);
        punkteAnzeige.update(delta);
        dmg.update(delta);
        ende.update(delta);
        for (Weapon w: shots)
        {
            w.update(delta);
        }
        for (int i = 0; i < drops.size(); i++)
        {
            Drop d = drops.get(i);
            d.update(delta);
            if (ender.pruefeKollision(d))
            {
                drops.remove(d);
                punkteAnzeige.erhoehen(PUNKTE_PORK);
                d.verschwinde();
            }
        }
        for (int i = 0; i < munition.size(); i++)
        {
            Munition m = munition.get(i);
            m.update(delta);
            if (ender.pruefeKollision(m))
            {
                munition.remove(m);
                if (m.getType()=="Pearl")
                {
                    pearls++;
                }
                if (m.getType()=="Cobble")
                {
                    cobbles++;
                }
                m.verschwinde();
            }
        }
        for (int i = 0; i < shots.size(); i++) 
        {
            Weapon schuss = shots.get(i);
            schuss.update(delta);
            if (pig.pruefeKollision(schuss)) 
            {
                shots.remove(schuss);
                punkteAnzeige.erhoehen(PUNKTE_ABSCHUSS);
                schuss.verschwinde();
                sprengeSchwein(pig.getX(), pig.getY());
                neuesSchwein(container);
            }
        }
        if (pig.getY()>(ender.getY()+20))
        {
            dmg.erhoehen();
            neuesSchwein(container);
        }
        if (ender.pruefeKollision(pig) || dmg.getHits() >= MAX_DMG) 
        {  
            List<HighscoreEntry> list = HighscoreState.getHighscoreList();
            for (HighscoreEntry e: list)
            {
                if (punkteAnzeige.getPunkte()>e.getPunkte())
                {
                    ende.setNewHighscore(true,punkteAnzeige.getPunkte());
                    break;
                }
            }
            ende.setGameOver(true);
        }
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && !ende.isGameOver()) 
        {
            wirfDirt(input.getMouseX(),650);
            //schuss.play();
        }
        if (input.isKeyPressed(Input.KEY_1) && !ende.isGameOver()) 
        {
            wirfCobble(input.getMouseX(),650);
            //schuss.play();
        }
        if (input.isKeyPressed(Input.KEY_2) && !ende.isGameOver() && pearls>0) 
        {
            wirfPerle(input.getMouseX(),650);
            //schuss.play();
        }
        if(input.isKeyPressed(Input.KEY_ESCAPE))
        {
            game.enterState(MenuState.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
        if(input.isKeyPressed(Input.KEY_P))
        {
            paused = true;
            game.enterState(PauseState.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
    {
        hintergrund.draw();
        ender.draw(g);
        punkteAnzeige.draw(g);
        dmg.draw(g);
        if (ende.isGameOver())
        {
            pig.verschwinde();
            ender.verschwinde();
            ende.draw(g);
        }
        for (Weapon w: shots)
        {
            w.draw(g);
        }
        for (Drop d: drops)
        {
            d.draw(g);
        }
        for (Munition m : munition)
        {
            m.draw(g);
        }
        pig.draw(g);
    }

    private void sprengeSchwein(int x, int y)
    {
        Drop pork = new Drop(x,y,porkImg,3,Flugbahn.LINKS); //TODO: Random
        drops.add(pork);
    }
    
    private void wirfDirt(int x, int y)
    {
        Weapon w = new Weapon(1,4,x,y,dirtImg);
        shots.add(w);
    }
    
    private void wirfCobble(int x, int y)
    {
        Weapon w = new Weapon(3,5,x,y,cobbleImg);
        shots.add(w);
    }
    
    private void wirfPerle(int x, int y) 
    {
        Weapon w = new Weapon(99,8,x,y,pearlImg);
        shots.add(w);
        pearls--;
    }

    private void neuesSchwein(GameContainer container)
    {
        Random random = new Random();
        pig.setX(random.nextInt(container.getWidth()));
        pig.setY(random.nextInt((int)(container.getHeight()*0.7)));
        //chance auf Pearl-Drop:
        if (random.nextInt(100)<5)
        {
            int x = random.nextInt(container.getWidth());
            int y = random.nextInt((int)(container.getHeight()*0.7));
            munition.add(new Munition(x,y,pearlImg,"Pearl"));
        }
    }

    @Override
    public int getID() {
        return ID;
    }

}
