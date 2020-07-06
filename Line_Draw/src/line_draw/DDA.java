package line_draw;
/**
 *
 * @author Soumi
 */
import java.awt.*;
import java.util.*;
import javax.swing.JPanel;

public class DDA extends JPanel
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
    
    public void DDA(int x1,int y1,int x2,int y2)
    {
        points.clear();
        int dx,dy,k;
        float x,y,xinc,yinc,steps;
        dx=x2-x1;
        dy=y2-y1;
        if(Math.abs(dx)>Math.abs(dy))
            steps=Math.abs(dx);
        else
            steps=Math.abs(dy);
        x=x1;
        y=y1;
        xinc=dx/steps;
        yinc=dy/steps;
        
        points.add(new Point(Math.round(x),Math.round(y)));
        
        for(k=1;k<=steps;k++)
        {
            x=x+xinc;
            y=y+yinc;
            points.add(new Point(Math.round(x),Math.round(y)));
        }
        repaint();
    }
}
