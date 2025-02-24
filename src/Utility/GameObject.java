package Utility;

import org.newdawn.slick.*;


public abstract class GameObject 
{
    protected int x;
    protected int y;
    protected Image image;
    
    public void draw(Graphics g){};
    public void update(int delta){};
    
    public GameObject(int x, int y, Image image) 
    {
        this.x = x;
        this.y = y;
        this.image = image;
    }
    
    public GameObject(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }
    
    public GameObject(Image image) 
    {
        this.image = image;
    }
    
    public GameObject() 
    {
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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
    
    public void verschwinde()
    {
        this.x = -9999;
        this.y = -9999;
    }
}
