package boundary_fill;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BOUNDARY_FILL extends JPanel implements MouseListener
{
    private BufferedImage image;
    private Graphics2D g2;
    
    public BOUNDARY_FILL()
    {
        image = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(image.getWidth(),image.getHeight()));
        setMinimumSize(getPreferredSize());
        g2 = image.createGraphics();
        g2.setColor(Color.yellow);
        g2.drawRect(200, 200, 80, 80);
        addMouseListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(image, 0, 0, null);
    }
    
    public void boundaryfill(int sedx,int sedy,int fill,int boundary)
    {
        if(image.getRGB(sedx, sedy) != fill && image.getRGB(sedx, sedy) != boundary)
        {
            image.setRGB(sedx,sedy,fill);
            update(getGraphics());
            
            boundaryfill(sedx,sedy-1,fill,boundary);
            boundaryfill(sedx,sedy+1,fill,boundary);
            boundaryfill(sedx-1,sedy,fill,boundary);
            boundaryfill(sedx+1,sedy,fill,boundary);
        }
    }
    
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("BOUNDARY FILL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BOUNDARY_FILL fill = new BOUNDARY_FILL();
        frame.add(fill);
        frame.setResizable(false);
        frame.setLocation(450,100);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getX()>200 && me.getX() < 280 && me.getY()>200 && me.getY() < 280)
            boundaryfill(me.getX(),me.getY(),Color.blue.getRGB(),Color.yellow.getRGB());
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
