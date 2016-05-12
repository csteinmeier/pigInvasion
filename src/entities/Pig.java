package entities;

import Utility.GameObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Pig extends GameObject
{
    private double geschwindigkeit=4;
    private double beschleunigung = 0.001;
    private Shape kollisionsflaeche;
    
    public Pig(int x, int y, Image image) {
        super(x, y, image);
        kollisionsflaeche = new Rectangle(x,y,image.getWidth(),image.getHeight());
    }
    
    @Override
    public void update(int delta) 
    {   
        geschwindigkeit += beschleunigung;
        y += geschwindigkeit;
        kollisionsflaeche.setCenterX(x);
        kollisionsflaeche.setCenterY(y);
    }   

    @Override
    public void draw(Graphics g) {
        this.image.drawCentered(x, y);
    }
    
    public boolean pruefeKollision(GameObject o)
    {
        return kollisionsflaeche.contains(o.getX(), o.getY());
    }

    public void reset() 
    {
        this.x = 400;
        this.y = 100;
    }
}
