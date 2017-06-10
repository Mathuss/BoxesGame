 

import java.awt.Graphics;
import java.awt.Color;


/**
 * Write a description of class HUD here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HUD
{
    private final Player player;
    
    public HUD(Player player)
    {
        this.player = player;
    }
    
    public void tick()
    {
    }
    public void render(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        
        g.setColor(Color.green);
        g.fillRect(15, 15, (int)player.getHealth()*2, 32);
        
        g.setColor(Color.red);
        g.drawString(Integer.toString((int)player.getHealth()), (int)player.getHealth()+17, 35);
        
        g.setColor(Color.white);
        g.drawString("Score: "+Long.toString(player.getScore()), Game.WIDTH-75-(int)player.getScore()/1000, 35);
        
        g.setColor(Color.green);
        g.drawString("Spd: " + Integer.toString(player.getSpeed()), Game.WIDTH-65, 50);
        
        g.setColor(Color.cyan);
        g.drawString(" Def: " + Integer.toString(player.getDef()), Game.WIDTH-65, 65);
        
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);    //Border around HUD
    }
}
