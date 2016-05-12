package entities;

import Utility.Flugbahn;
import Utility.GameObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Munition extends GameObject
{
    private double geschwindigkeit = 8;
    private final double beschleunigung = 0.002;
    private String type;
    
    public Munition(int x, int y,Image image, String type) 
    {
        super(x, y, image);
        this.type = type;
    }
    
    @Override
    public void update(int delta) 
    {   
        geschwindigkeit += beschleunigung;
        y += geschwindigkeit;
    } 
    
    @Override
    public void draw(Graphics g) {
        this.image.drawCentered(x, y);
    }

    public String getType() {
        return this.type;
    }
}
