package Utility;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Weapon extends GameObject
{
    protected int schaden;
    protected int geschwindigkeit;
   
    public Weapon(int schaden, int geschwindigkeit, Image image) {
        super(image);
        this.schaden = schaden;
        this.geschwindigkeit = geschwindigkeit;
    }

    public Weapon(int schaden, int geschwindigkeit, int x, int y, Image image) {
        super(x, y, image);
        this.schaden = schaden;
        this.geschwindigkeit = geschwindigkeit;
    }
    
    @Override
    public void draw(Graphics g) 
    {
        image.drawCentered(x,y);
    }
    
    @Override
    public void update(int delta) 
    {
        y -= geschwindigkeit;
    }
}
