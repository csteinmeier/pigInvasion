package display;

import Utility.GameObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Damage extends GameObject
{
    private int hits;
    
    public Damage(int x, int y, Image img) 
    {
        super(x, y, img);
        this.hits = 0;
    }
    
    @Override
    public void draw(Graphics g) 
    {
        for (int i=0; i<hits; i++)
        {
            image.drawCentered(x+(i*50), y);
        }
    }
    
    public void erhoehen() 
    {
        hits++;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void reset() {
        this.hits=0;
    }
}
