package entities;

import Utility.GameObject;
import Utility.Flugbahn;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Drop extends GameObject
{
    private int punkteWert;
    private int startX;
    private double geschwindigkeit = 4;
    private final double beschleunigung = 0.001;
    private Flugbahn flugbahn;
    
    public Drop(int x, int y, Image image, int punkte, Flugbahn flug) 
    {
        super(x, y, image);
        this.startX = x;
        this.punkteWert = punkte;
        this.flugbahn = flug;
    }
    
    @Override
    public void draw(Graphics g) 
    {
        this.image.drawCentered(x, y);
    }

    @Override
    public void update(int delta) 
    {   
        geschwindigkeit += beschleunigung;
        switch (flugbahn)
        {
            case LINKS:
                if (x>(startX-50))
                {
                    x -= geschwindigkeit;
                }
                y += geschwindigkeit;
                break;
            case RECHTS: 
                if (x<(startX+50))
                {
                    x += geschwindigkeit;
                }
                y += geschwindigkeit;
                break;
            case KEINE:
                break;
        }
    }  
    
    public int getPunkteWert() {
        return punkteWert;
    }

    public void setPunkteWert(int punkteWert) {
        this.punkteWert = punkteWert;
    }

    public Flugbahn getFlugbahn() {
        return flugbahn;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setFlugbahn(Flugbahn flugbahn) {
        this.flugbahn = flugbahn;
    }

}
