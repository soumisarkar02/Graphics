package fillalgo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FILLALGO extends JPanel implements MouseListener
{
    private BufferedImage image;
    private Graphics2D g2;
    
    public FILLALGO()
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
    
    public void floodfill(int sedx,int sedy,int rgb)
    {
        if(image.getRGB(sedx, sedy) == rgb)
        {
            image.setRGB(sedx,sedy,Color.blue.getRGB());
            update(getGraphics());

            floodfill(sedx,sedy-1,rgb);
            floodfill(sedx,sedy+1,rgb);
            floodfill(sedx-1,sedy,rgb);
            floodfill(sedx+1,sedy,rgb);
            
        }
    }
    
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("FLOOD FILL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(450,100);
        FILLALGO fill = new FILLALGO();
        frame.add(fill);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent me) 
    {
        if(me.getX()>200 && me.getX() < 280 && me.getY()>200 && me.getY() < 280)
            floodfill(me.getX(),me.getY(),image.getRGB(me.getX(), me.getY()));
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
