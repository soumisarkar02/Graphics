/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package line_draw_bres;
/**
 *
 * @author Soumi
 */
import java.awt.*;
import java.util.*;
import javax.swing.JPanel;

public class Bresenham extends JPanel
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
    
    public void Bresenham(int x1,int y1,int x2,int y2)
    {
        points.clear();
        int dx,dy,a,b,d,x,y,k;
        
        dx = Math.abs(x2-x1);
        dy = Math.abs(y2-y1);
        
        x = x1;
        y = y1;
        
        points.add(new Point(x,y));
        
        a = -dy;
        b = dx;
        
        d = Math.round(((2 * a) + b)/2);
        
        if(dx != 0)
        {
            for(k=1;k<=dx;k++)
            {
                if(x <= x2)
                    x = x + 1;
                else
                    x = x - 1;

                if(d >= 0)
                {
                    d = d + a;
                }
                else
                {
                    if(y <= y2)
                        y = y + 1;
                    else
                        y = y - 1;

                    d = d + a + b;
                }
                points.add(new Point(x,y));
            }
        }
        else
        {
            for(k=1;k<=dy;k++)
            {
                if(d >= 0)
                {
                    d = d + a;
                }
                else
                {
                    if(y <= y2)
                        y = y + 1;
                    else
                        y = y - 1;

                    d = d + a + b;
                }
                points.add(new Point(x,y));
            }
        }
        repaint();
    }
}