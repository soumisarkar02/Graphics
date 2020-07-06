package circle_draw;
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
    
    public void Midpoint(int x1,int y1,int radius)
    {
        points.clear();
        
        int x = 0;
        int y = radius;
        
        points.add(new Point((x1+x),(y1+y)));
        points.add(new Point((x1-x),(y1+y)));
        points.add(new Point((x1+x),(y1-y)));
        points.add(new Point((x1-x),(y1-y)));
        points.add(new Point((x1+y),(y1+x)));
        points.add(new Point((x1-y),(y1+x)));
        points.add(new Point((x1+y),(y1-x)));
        points.add(new Point((x1-y),(y1-x)));
        
        
        int d = 1 - radius;
        
        while(y>x)
        {
            if(d<0)
            {
                x = x + 1;
                d = d + (2 * x) + 3;
            }
            else
            {
                x = x + 1;
                y = y - 1;
                d = d + (2 * x) - (2 * y) + 5;
            }
            
            points.add(new Point((x1+x),(y1+y)));
            points.add(new Point((x1-x),(y1+y)));
            points.add(new Point((x1+x),(y1-y)));
            points.add(new Point((x1-x),(y1-y)));
            points.add(new Point((x1+y),(y1+x)));
            points.add(new Point((x1-y),(y1+x)));
            points.add(new Point((x1+y),(y1-x)));
            points.add(new Point((x1-y),(y1-x)));
        }
        
        repaint();
    }
}
