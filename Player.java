 

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends GameObject
{
    private final int WIDTH=32;
    private final int HEIGHT=32;
    private double health = 100.0;
    private int defense = 0;
    private final double DEF_RATIO = 0.1;
    private long score = 0L;
    private long timer = System.currentTimeMillis();
    private int speed = 3;
    
    public Player(int x, int y, ID id, Handler handler)
    {
        super(x, y, id, handler);
    }
    public Player(int x, int y, Handler handler){
        super(x, y, ID.Player, handler);
    }
    
    public double getHealth()
    {
        return Game.clamp(health, 0, 100);
    }
        
    public long getScore()
    {
        return this.score;
    }
    
    public int getSpeed()
    {
        return this.speed;
    }
    
    public int getDef()
    {
        return this.defense;
    }
    
    public void changeScore(int delta){
        this.score += delta;
    }
    
    @Override
    public void tick()      //What happens on the next frame
    {
        x += velX;
        y+= velY;
        
        x = Game.clamp(x, 1, Game.WIDTH-this.WIDTH-4);
        y = Game.clamp(y, 1, Game.HEIGHT - 2*this.HEIGHT-1);
        
        if(this.isColliding(ID.Enemy))
            health = health-1+defense*DEF_RATIO;
        health = Game.clamp(health, 0, 100);
        
        if(this.isColliding(ID.PowerUp)){
            if((int)(Math.random()*BasicEnemy.getNumBasicEnemies()) > 14 && defense < 5){
                defense++;
                defense = Game.clamp(defense, 0, 8);
            }
            else if(BasicEnemy.getNumBasicEnemies() <35 && (int)(Math.random()*BasicEnemy.getNumBasicEnemies())>20){
                health+=33;
                health = Game.clamp(health, 0, 100);
            }
            else if(speed <10){
                speed+=1;
                speed = Game.clamp(speed, 3, 10);
            }
            else if(BasicEnemy.getNumBasicEnemies() > 25){
                handler.addObject(new Projectile(this.x, this.y, handler, 5, 0));
                handler.addObject(new Projectile(this.x, this.y, handler, 4.83, 1.29));
                handler.addObject(new Projectile(this.x, this.y, handler, 4.33, 2.5));
                handler.addObject(new Projectile(this.x, this.y, handler, 3.54, 3.54));
                handler.addObject(new Projectile(this.x, this.y, handler, 2.5, 4.33));
                handler.addObject(new Projectile(this.x, this.y, handler, 1.29, 4.83));
                handler.addObject(new Projectile(this.x, this.y, handler, 0, 5));
                handler.addObject(new Projectile(this.x, this.y, handler, -4.83, 1.29));
                handler.addObject(new Projectile(this.x, this.y, handler, -4.33, 2.5));
                handler.addObject(new Projectile(this.x, this.y, handler, -3.54, 3.54));
                handler.addObject(new Projectile(this.x, this.y, handler, -2.5, 4.33));
                handler.addObject(new Projectile(this.x, this.y, handler, -1.29, 4.83));
                handler.addObject(new Projectile(this.x, this.y, handler, 4.83, -1.29));
                handler.addObject(new Projectile(this.x, this.y, handler, 4.33, -2.5));
                handler.addObject(new Projectile(this.x, this.y, handler, 3.54, -3.54));
                handler.addObject(new Projectile(this.x, this.y, handler, 2.5, -4.33));
                handler.addObject(new Projectile(this.x, this.y, handler, 1.29, -4.83));
                handler.addObject(new Projectile(this.x, this.y, handler, -4.83, -1.29));
                handler.addObject(new Projectile(this.x, this.y, handler, -4.33, -2.5));
                handler.addObject(new Projectile(this.x, this.y, handler, -3.54, -3.54));
                handler.addObject(new Projectile(this.x, this.y, handler, -2.5, -4.33));
                handler.addObject(new Projectile(this.x, this.y, handler, -1.29, -4.83));
                handler.addObject(new Projectile(this.x, this.y, handler, 0, -5));
                handler.addObject(new Projectile(this.x, this.y, handler, -5, 0));
            }
            else{
                for(int i = handler.objectList.size()-1; i>0; i--){
                    GameObject temp = handler.objectList.get(i);
                    if(temp.getID().equals(ID.Enemy)){
                        temp.setVelX((temp.getVelX()>0)? temp.getVelX()-1 : temp.getVelX()+1);
                        temp.setVelY((temp.getVelY()>0)? temp.getVelY()-1 : temp.getVelY()+1);
                    }
                }
            }
                
            handler.removeObject(ID.PowerUp);
            score+=100;
        }
        
        if(System.currentTimeMillis()-timer>1000/(GameObject.getNumGameObjectsGenerated()-1)){
            timer+=1000/(BasicEnemy.getNumBasicEnemies());
            score++;
        }
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
    
    @Override
    public Rectangle getBounds()
    {
        return new Rectangle (x, y, this.WIDTH, this.HEIGHT);
    }
}
