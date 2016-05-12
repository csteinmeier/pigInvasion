package display;

import Utility.GameObject;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;


public class Score extends GameObject
{
    private Font font;
    private int punkte;
    
    public Score(int x, int y, Font font) 
    {
        super(x, y);
        this.font = font;
        this.punkte = 0;
    }
    
    @Override
    public void draw(Graphics g) 
    {
        g.setFont(font);
        String punkteMitNullen = String.format("%04d", punkte);
        g.drawString(punkteMitNullen, x, y);
    }
    
    public void erhoehen(int pkt) 
    {
        punkte+=pkt;
    }

    public int getPunkte() {
        return punkte;
    }

    public void reset() {
        this.punkte = 0;
    }
    
}
