package nfloodfill;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NFLOODFILL extends JPanel implements MouseListener
{
    private BufferedImage image;
    private Graphics2D g2;
    
    public NFLOODFILL()
    {
        image = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(image.getWidth(),image.getHeight()));
        setMinimumSize(getPreferredSize());
        g2 = image.createGraphics();
        g2.setColor(Color.yellow);
        g2.drawRect(120, 120, 250, 250);
        addMouseListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(image, 0, 0, null);
    }
    
    public void floodfill(int sedx,int sedy)
    {
        int xmove = sedx,ymove = sedy;
         
           while(image.getRGB(xmove, ymove) == Color.black.getRGB())//checking current color is boundary or not
            {        
                while(image.getRGB(xmove, ymove) == Color.black.getRGB())
		{
                    //System.out.println(image.getRGB(sedx, sedy));
                    //System.out.println(Color.yellow.getRGB());
                    //System.out.println(Color.blue.getRGB());

                    image.setRGB(xmove,ymove,Color.blue.getRGB());//changing current color to white
                    update(getGraphics());
                    ymove--; //downward move
		}
                ymove=sedy+1; //reset y
                
                //filling downwards
		while(image.getRGB(xmove, ymove) == Color.black.getRGB())
		{
                    image.setRGB(xmove,ymove,Color.blue.getRGB());//changing current color to white
                    update(getGraphics());
                    ymove++; //downward move
		}
                ymove=sedy; //reset y
                xmove--; 
            }
            
            xmove = sedx+1;
            ymove = sedy;
            
            while(image.getRGB(xmove, ymove) == Color.black.getRGB())//checking current color is oundary or not
            {
                while(image.getRGB(xmove, ymove) == Color.black.getRGB())
		{ 
                    image.setRGB(xmove,ymove,Color.blue.getRGB());//changing current color to white
                    update(getGraphics());
                    ymove--;
		}
                ymove=sedy+1; //reset y
                
		while(image.getRGB(xmove, ymove) == Color.black.getRGB())
		{
                    image.setRGB(xmove,ymove,Color.blue.getRGB());//changing current color to white
                    update(getGraphics());
                    ymove++;
		}
                ymove=sedy;
                xmove++; 
            }
    }
    
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("FLOOD FILL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(450,100);
        NFLOODFILL fill = new NFLOODFILL();
        frame.add(fill);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent me) 
    {
        if(me.getX()>120 && me.getX() < 370 && me.getY()>120 && me.getY() < 370)
        {
            floodfill(me.getX(),me.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
