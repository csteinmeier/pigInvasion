package entities;

import Utility.GameObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class Enderman extends GameObject 
{
    private Input in;
    private Shape kollisionsflaeche;

    public Enderman(int x, int y, Input in, Image image) {
        super(x,y,image);
        this.in = in;
        kollisionsflaeche = new Rectangle(x,y,image.getWidth(),image.getHeight());
    }
    
    @Override
    public void update(int delta)
    {
        this.x = in.getMouseX();
        kollisionsflaeche.setCenterX(x);
        kollisionsflaeche.setCenterY(y);
    }
    
    @Override
    public void draw(Graphics g) {
        image.drawCentered(x,y);
    }

    public boolean pruefeKollision(GameObject o)
    {
        return kollisionsflaeche.contains(o.getX(), o.getY());
    }

    public void reset() {
        this.x= 400;
        this.y=700;
    }
}
