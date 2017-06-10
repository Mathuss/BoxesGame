 

import java.awt.event.KeyAdapter;   //Recieves keyboard events
import java.awt.event.KeyEvent;

/**
 * Write a description of class KeyInput here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KeyInput extends KeyAdapter
{
    private Handler handler;
    private boolean wPressed, sPressed, dPressed, aPressed;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    
    //private static long projectileTimer = System.currentTimeMillis();
    
    
    public KeyInput(Handler handler){
        this.handler = handler;
    }
    
    @Override 
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();       //key is the ID of the key that was pressed
        
        int size = handler.objectList.size();
        Player p = handler.getPlayer();
        int speed = p.getSpeed();
        switch(key){
            case KeyEvent.VK_W:         //Virtual Key W
                wPressed = true;
                p.setVelY(-speed);
                break;
            case KeyEvent.VK_S:
                sPressed = true;
                p.setVelY(speed);
                break;
            case KeyEvent.VK_A:
                aPressed = true;
                p.setVelX(-speed);
                break;
            case KeyEvent.VK_D:
                dPressed = true;
                p.setVelX(speed);
                break;
            default:
                break;
        }
        switch(key){
            case KeyEvent.VK_UP:
                upPressed = true;
                p.setVelY(-speed);
                break;
            case KeyEvent.VK_DOWN:
                downPressed = true;
                p.setVelY(speed);
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                p.setVelX(-speed);
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                p.setVelX(speed);
                break;
            default:
                break;
        }
        /*
        if(System.currentTimeMillis()-projectileTimer >= 100){
            projectileTimer = System.currentTimeMillis();
            if(upPressed)
                handler.addObject(new Projectile(handler.getPlayer().getX(), handler.getPlayer().getY(), handler, 0, -10));
            if(downPressed)
                handler.addObject(new Projectile(handler.getPlayer().getX(), handler.getPlayer().getY(), handler, 0, 10));
            if(leftPressed)
                handler.addObject(new Projectile(handler.getPlayer().getX(), handler.getPlayer().getY(), handler, -10, 0));
            if(rightPressed)
                handler.addObject(new Projectile(handler.getPlayer().getX(), handler.getPlayer().getY(), handler, 10, 0));
        }
        */
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();       //key is the ID of the key that was pressed
        
        Player p = handler.getPlayer();
        int speed = p.getSpeed();
        switch(key){
            case KeyEvent.VK_W:         //Virtual Key W
                wPressed = false;
                if(!sPressed)           //If these if/else aren't present, the actions will be "sticky"
                    p.setVelY(0);
                else
                    p.setVelY(speed);
                break;
            case KeyEvent.VK_S:
                sPressed = false;
                if(!wPressed)
                    p.setVelY(0);
                else
                    p.setVelY(-speed);
                break;
            case KeyEvent.VK_A:
                aPressed = false;
                if(!dPressed)
                    p.setVelX(0);
                else
                    p.setVelX(speed);
                break;
            case KeyEvent.VK_D:
                dPressed = false;
                if(!aPressed)
                    p.setVelX(0);
                else
                    p.setVelX(-speed);
                break;
            default:
                break;
        }
        switch(key){
           case KeyEvent.VK_UP:
                upPressed = false;
                if(!downPressed)           //If these if/else aren't present, the actions will be "sticky"
                    p.setVelY(0);
                else
                    p.setVelY(speed);
                break;
            case KeyEvent.VK_DOWN:
                downPressed = false;
                if(!upPressed)
                    p.setVelY(0);
                else
                    p.setVelY(-speed);
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                if(!rightPressed)
                    p.setVelX(0);
                else
                    p.setVelX(speed);
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                if(!leftPressed)
                    p.setVelX(0);
                else
                    p.setVelX(-speed);
                break;
            default:
                break;
        }
    }
}
