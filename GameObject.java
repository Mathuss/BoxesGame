 

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject
{
    protected int x, y;     //protected: only accessed by the objects that inherit it. Stronger than public, weaker than private
    protected ID id;
    protected double velX, velY;
    protected Handler handler;
    
    protected static int uniqueObjectIDCounter = 0;
    protected int uniqueObjectID;
    
    public GameObject(int x, int y, ID id, Handler handler)
    {
        this.x = x;
        this.y = y;
        this.id = id;
        this.handler = handler;
        this.uniqueObjectID = uniqueObjectIDCounter;
        uniqueObjectIDCounter++;
    }
    
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setID(ID id){
        this.id = id;
    }
    public void setVelX(double velX){
        this.velX = velX;
    }
    public void setVelY(double velY){
        this.velY = velY;
    }
    
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public ID getID(){
        return this.id;
    }
    public double getVelX(){
        return this.velX;
    }
    public double getVelY(){
        return this.velY;
    }
    
    public boolean isColliding()
    {
        for(GameObject obj : handler.objectList){
            if(!obj.equals(this) && this.getBounds().intersects(obj.getBounds())){
                return true;
            }
        }
        return false;
    }
    public boolean isColliding(ID id)
    {
        int size = handler.objectList.size();    //For each loops tend to result in ConcurrentModificationExceptions
        for(int i = size-1; i>=0; i--){
            GameObject obj = handler.objectList.get(i);
            if(!obj.equals(this) && this.getBounds().intersects(obj.getBounds()) && obj.getID().equals(id)){
                return true;
            }
        }
        return false;
    }
    
    public static int getNumGameObjectsGenerated()
    {
        return uniqueObjectIDCounter;
    }
    
    public String toString()
    {
        return this.id.name()+"#"+Integer.toString(this.uniqueObjectID);
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();      //Rectangle class has an "intersects" method
}
