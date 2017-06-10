 

import java.awt.Canvas;     //java.awt handles graphics
import java.awt.Dimension;
import javax.swing.JFrame;  //Extended version of java.awt.Frame

/**
 * Write a description of class Window here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Window extends Canvas
{
    private static final long serialVersionUID = 0L;
    
    public Window(int width, int height, String title, Game game)
    {
        JFrame frame = new JFrame(title);
        
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Makes sure the frame exits when hitting X
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);      //Starts in middle of screen
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
