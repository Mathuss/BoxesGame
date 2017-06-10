import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject
{
    private final int WIDTH=16;
    private final int HEIGHT=16;
    private static int numberOfBasicEnemies = 0;
    
    public BasicEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id, handler);
        
        velX = ((int)(Math.random()*100)%10)+1;
        velX = Game.clamp(velX, 1, 10);
        if(Math.random()<0.5){
            velX *= -1;
        }
        velY = ((int)(Math.random()*100)%10)+1;
        velY = Game.clamp(velY, 1, 10);
        if(Math.random()<0.5){
            velY *= -1;
        }
        
        numberOfBasicEnemies++;
    }
    public BasicEnemy(int x, int y,  Handler handler){
        super(x, y, ID.Enemy, handler);
        
        velX = ((int)(Math.random()*100)%10)+1;
        if(Math.random()<0.5){
            velX *= -1;
        }
        velY = ((int)(Math.random()*100))%10+1;
        if(Math.random()<0.5){
            velY *= -1;
        }
        
        numberOfBasicEnemies++;
    }
    
    public static int getNumBasicEnemies(){
        return numberOfBasicEnemies;
    }
    
    @Override
    public void tick(){
        x += velX;
        y += velY;
        
        x = Game.clamp(x, -1, Game.WIDTH-this.WIDTH);
        y = Game.clamp(y, -1, Game.HEIGHT - 2*this.HEIGHT);
        
        if(y <= 0 || y >= Game.HEIGHT - 2*this.HEIGHT-1){
            velY *= -1;
        }
        if(x <= 0 || x >= Game.WIDTH-this.WIDTH-1){
            velX *= -1;
        }

        if(this.isColliding(ID.Projectile)){
            handler.removeObject(this);
            numberOfBasicEnemies--;
            handler.getPlayer().changeScore(100);
        }
    }
    
    @Override
    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x, y, this.WIDTH, this.HEIGHT);
    }
    
    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, this.WIDTH, this.HEIGHT);
    }
}
