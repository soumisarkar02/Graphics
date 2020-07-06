package ellipse_draw;
/**
 *
 * @author Soumi
 */
import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Ellipse_Draw extends JFrame implements ActionListener
{
    JFrame f;
    JTextField tf1,tf2,tf3,tf4;
    JButton b1;
    JLabel l1,l2,l3;
    Midpoint ob;
    Graphics g;
    
    Ellipse_Draw()
    {
        f = new JFrame();
        JPanel p1=new JPanel();
        JPanel p2=new JPanel();
        JPanel p3=new JPanel();
        
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        f.setSize(1000,700);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        ob=new Midpoint();
        
        l1=new JLabel("Center Point Coordinates:");
        l1.setBounds(40, 90, 100, 30);
        l2=new JLabel("Major Axis:");
        l2.setBounds(30, 130, 100, 30);
        l3=new JLabel("Minor Axis:");
        l3.setBounds(50, 170, 100, 30);
        
        tf1=new JTextField(5);
        tf1.setBounds(130, 90,100,30);
        tf2=new JTextField(5);
        tf2.setBounds(250,90,100,30);
        tf3=new JTextField(5);
        tf3.setBounds(130,130,100,30);
        tf4=new JTextField(5);
        tf4.setBounds(250,130,100,30);
        
        b1=new JButton("Draw Ellipse");  
        b1.setBounds(210,180,120, 40);
        b1.addActionListener(this);
        
        p1.add(l1);
        p1.add(tf1);
        p1.add(tf2);
        p1.add(l2);
        p1.add(tf3);
        p1.add(l3);
        p1.add(tf4);
        p2.add(b1);
        
        p3.add(p1,BorderLayout.NORTH);
        p3.add(p2,BorderLayout.SOUTH);
        f.add(p3,BorderLayout.NORTH);
        f.add(ob,BorderLayout.CENTER);
        
        f.setVisible(true);
    }
    
    public static void main(String[] args) 
    {
        new Ellipse_Draw();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
            String x1=tf1.getText();
            if(x1.equalsIgnoreCase(""))
            {
                JOptionPane.showMessageDialog(b1,"Enter X-Coordinate of the center of the circle");
            }
            String y1=tf2.getText();
            if(y1.equalsIgnoreCase(""))
            {
                JOptionPane.showMessageDialog(b1,"Enter Y-Coordinate of the center of the circle");
            }
            String major=tf3.getText();
            if(major.equalsIgnoreCase(""))
            {
                JOptionPane.showMessageDialog(b1,"Enter radius of the circle");
            }
            String minor=tf4.getText();
            if(minor.equalsIgnoreCase(""))
            {
                JOptionPane.showMessageDialog(b1,"Enter radius of the circle");
            }
           
            int x_1=Integer.parseInt(x1);
            int y_1=Integer.parseInt(y1);
            int a=Integer.parseInt(major);
            int b=Integer.parseInt(minor);
            
            if ((tf1.equals("")== false) && (tf2.equals("")== false) && (tf3.equals("")== false) && (tf4.equals("")== false))
            {
                try
                {
                    ob.Midpoint(x_1, y_1, a, b);
                }
                catch(Exception E)
                {
                    JOptionPane.showMessageDialog(b1, E);
                }
            }
        }
    }
}
