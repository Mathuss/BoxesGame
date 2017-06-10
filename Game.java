/**
 * @version 6/6/2016
 */
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable        //Implement runnable if class executed by thread
{
    private static final long serialVersionUID = 0L;
    
    public static final int WIDTH=1280,          //640
                            HEIGHT=WIDTH*3/4;
                            
    private Thread thread;      //Thread: program's path of execution. Allows for multiple executions at once. This game is single threaded.
    
    private Handler handler;
    
    private HUD hud;
    
    private long gameTimer = 0L;
    
    private boolean running = false;
    
    public Game()
    {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));    //Take in keyboard input
        
        new Window(WIDTH, HEIGHT, "Game!", this);
        
        Player player = new Player(WIDTH/2-32, HEIGHT/2-32, handler);
        
        handler.addObject(player);
        
        handler.addObject(new BasicEnemy(1, 1, handler));
        
       
        hud = new HUD(player);
    }
    
    public synchronized void start()        //Synchronized: Makes sure only one thing happens in a thread. Establishes happens-before relationships with other synchronized methods
    {
        thread = new Thread(this);      //Creates a new thread for the Game class.
        thread.start();                 //When a thread starts, the object that the thread belongs to runs its run() method.
        running = true;
    }
    
    public synchronized void stop()
    {
        try{
            thread.join();      //T.join() Forces current running thread to pause until T terminates.
                                //Since thread is our only Thread, this will kill thread.
            running = false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override //The run() method in Runnable
    public void run()
    {
        this.requestFocus();                //Game does not have to be clicked on to begin game
        long lastTime = System.nanoTime();
        double ns = 1000000000/60.0;     // 1/60th of a second
        double delta = 0.0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        long powerUpTimer = 0L;
        
        while(running){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            while(delta >= 1){
                tick();         //Goes to handler. Handler then tick() all objects. tick() controls what happens to each object in the next frame
                delta--;
            }
            if(running)
                render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                gameTimer += 1000;
                powerUpTimer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
            
            /**Enemy Spawning**/
            if(gameTimer >= clamp(10000/((BasicEnemy.getNumBasicEnemies()==0)?1:BasicEnemy.getNumBasicEnemies()), 3000, 10000)){
                gameTimer = 0;
                switch(((int)(Math.random()*100))%4){
                    case 0:
                        handler.addObject(new BasicEnemy(1, 1, handler));
                        break;
                    case 1:
                        handler.addObject(new BasicEnemy(WIDTH-1, 1, handler));
                        break;
                    case 2:
                        handler.addObject(new BasicEnemy(1, HEIGHT-1, handler));
                        break;
                    case 3:
                        handler.addObject(new BasicEnemy(WIDTH-1, HEIGHT-1, handler));
                        break;
                }
            }
            
            /**PowerUp Spawning**/
            if(powerUpTimer >= 1000){
                while(PowerUp.getNumPowerUps() > 0){
                    handler.removeObject(ID.PowerUp);
                    PowerUp.changeNumPowerUps((short)(-1));
                }
                
                powerUpTimer = 0;
                handler.addObject(new PowerUp((int)(Math.random()*WIDTH), clamp((int)(Math.random()*HEIGHT), 45, HEIGHT-45), handler));
            }
            
            
            /**Player Death**/
            if(handler.getPlayer().getHealth() <= 0){
                render();       //Update screen one more time before stopping
                break;
            }
        }
        
        stop();
    }
    private void tick() //What to do next frame
    {
        try{
            handler.tick();
        }
        catch(NullPointerException e){
            System.out.println("Handler Tick Exception: Null Pointer");
        }
        try{
            hud.tick();
        }
        catch(NullPointerException e){
            System.out.println("HUD Tick Exception: Null Pointer");
        }
    }
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();           //BufferStrategy: Mechanism to organize memory on a Canvas or Window
        if(bs == null){     //Has not been created yet
            this.createBufferStrategy(3);       //Creates 3 buffer strategies. Reccomended to be between 2-3
            return;
        }
        
        Graphics g = bs.getDrawGraphics();      //Creates a graphics context (context: carries info about colors, lines, etc)
        
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        handler.render(g);
        
        hud.render(g);
        
        g.dispose();        //Deletes the graphics object so it doesn't linger.
        bs.show();          //Displays the next buffer
    }
    
    public static int clamp(int var, int min, int max)
    {
        if(var >= max)
            return max;
        if(var <= min)
            return min;
        return var;
    }
    public static double clamp(double var, double min, double max){
        if(var >= max)
            return max;
        if(var <= min)
            return min;
        return var;
    }
    
    public static void main(String[] args)
    {
        new Game();
    }
}
