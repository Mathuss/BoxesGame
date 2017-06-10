import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Projectile extends GameObject
{
    private final int WIDTH = 8;
    private final int HEIGHT = 8;
    
    public Projectile(int x, int y, ID id, Handler handler, double velX, double velY)
    {
        super(x, y, id, handler);
        this.velX = velX;
        this.velY = velY;
    }
    public Projectile(int x, int y, Handler handler, double velX, double velY)
    {
        super(x, y, ID.Projectile, handler);
        this.velX = velX;
        this.velY = velY;
    }
    
    @Override
    public void tick()
    {
        x += velX;
        y += velY;
        
        if(x>Game.WIDTH || y>Game.HEIGHT){
            handler.removeObject(this);
        }
    }
    
    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.orange);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle (x, y, this.WIDTH, this.HEIGHT);
    }
}