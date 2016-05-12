package display;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class MenuEntry 
{
    private final int WIDTH = 300;
    private final int HEIGHT = 40;

    private final Color TEXT_NORMAL = Color.magenta;
    private final Color TEXT_HIGHLIGHTED = Color.cyan;
    
    private int x;
    private int y;
    private String text;
    private int transID;
    private Shape kollisionsflaeche;
    private Color color;
    private Font font;

    public MenuEntry(int x, int y, String text, int transID, Font f) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.transID = transID;
        this.kollisionsflaeche = new Rectangle(x,y,WIDTH,HEIGHT);
        this.color = TEXT_NORMAL;
        this.font = f;
    }
  

    public void draw(Graphics g) 
    {
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, x, y);
    }
    
    public boolean pruefeKollision(int x, int y)
    {
        boolean mouseover = kollisionsflaeche.contains(x, y);
        if (mouseover)
        {
            this.color = TEXT_HIGHLIGHTED;
        }
        else
        {
            this.color = TEXT_NORMAL;
        }
        return mouseover;
    }
    

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTransID() {
        return transID;
    }

    public void setTransID(int transID) {
        this.transID = transID;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
