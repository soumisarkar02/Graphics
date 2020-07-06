package ellipse_draw;
/**
 *
 * @author Soumi
 */
import java.awt.*;
import java.util.*;
import javax.swing.JPanel;

public class Midpoint extends JPanel
{
    ArrayList<Point> points=new ArrayList<>();
    
    protected void paintComponent(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.black);
        
        for(int i=0;i<points.size();i++)
        {
            g.drawRect((int)points.get(i).getX(),(int)points.get(i).getY(),1,1);
        }
    }
    
    public void Midpoint(int xc,int yc,int rx,int ry)
    {
        points.clear();
        
        int rx2 = rx * rx;
        int ry2 = ry * ry;
        int tworx2 = 2 * rx2 ;
        int twory2 = 2 * ry2;
        
        int p;
        int x = 0;
        int y = ry;
        int px = 0;
        int py = tworx2 * y;
        
        points.add(new Point((xc+x),(yc+y)));
        points.add(new Point((xc-x),(yc+y)));
        points.add(new Point((xc+x),(yc-y)));
        points.add(new Point((xc-x),(yc-y)));
        
        p = (int) Math.round(ry2 - (rx2 * ry) + (0.25 * rx2));
        
        while(px<py)
        {
            x =x + 1;
            px = px + twory2;
            
            if(p<0)
            {
                p = p + ry2 + px;
            }
            else
            {
                y = y - 1;
                py = py - tworx2;
                p = p + ry2 + px - py;
            }
            
            points.add(new Point((xc+x),(yc+y)));
            points.add(new Point((xc-x),(yc+y)));
            points.add(new Point((xc+x),(yc-y)));
            points.add(new Point((xc-x),(yc-y)));
        }
        
        p = (int) Math.round((ry2 * (x + 0.5) * (x + 0.5)) + (rx2 * (y - 1) * (y - 1)) - (rx2 * ry2));
        
        while(y>0)
        {
            y = y - 1;
            py = py - tworx2;
            if(p>0)
            {
                p = p + rx2 - py;
            }
            else
            {
                x = x + 1;
                px = px + twory2;
                p = p + rx2 - py + px;
            }
            
            points.add(new Point((xc+x),(yc+y)));
            points.add(new Point((xc-x),(yc+y)));
            points.add(new Point((xc+x),(yc-y)));
            points.add(new Point((xc-x),(yc-y)));
        }
        
        repaint();
    }
    
}
