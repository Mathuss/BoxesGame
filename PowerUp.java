import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class PowerUp extends GameObject
{
    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    public static short numPowerUps;
    
    public PowerUp(int x, int y, ID id, Handler handler)
    {
        super(x, y, id, handler);
        numPowerUps++;
    }
    
    public PowerUp(int x, int y, Handler handler)
    {
        super(x, y, ID.PowerUp, handler);
        numPowerUps++;
    }
    
    public static short getNumPowerUps()
    {
        return numPowerUps;
    }
    public static void changeNumPowerUps(short delta)
    {
        numPowerUps += delta;
    }
    
    @Override
    public void tick(){}
    
    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.green);
        g.fillRect(x, y, this.WIDTH, this.HEIGHT);
    }
    
    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, this.WIDTH, this.HEIGHT);
    }
}
