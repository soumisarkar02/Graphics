package scanline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ScanLine 
{
    private static class EdgeNode{
        public int ymax,xmin;
        public double m;
        public EdgeNode(){}
        public EdgeNode(int y,int x,double slope){
            ymax=y;
            xmin=x;
            m=slope;
        }
    }
    static int p[][];
    static int pp[][];
    
    //Function for Vertex Processing
    public static void vertexprocessing(int n){
        int i,prev,next,x,y,tempx;
        for(i=0;i<n;i++)
        {
            prev=prevpoint(n,i);
            next=nextpoint(n,i);
            
            x=p[i][0];
            y=p[i][1];
            
            //Monotonically decreasing
            if(p[next][1]<p[i][1]&&p[prev][1]>p[i][1]){
                x=updatex(p[i][0],p[i][1],p[next][0],p[next][1]);
                y=p[i][1]-1;
                
            }
            //Monotonically increasing
            else if(p[next][1]>p[i][1]&&p[prev][1]<p[i][1])
            {
                tempx=updatex(pp[prev][0],pp[prev][1],pp[prev][2],pp[prev][3]);
                pp[prev][2]=tempx;
                pp[prev][3]-=1;
            }
            
            //System.out.println("["+x+","+y+"]"+"["+p[next][0]+","+p[next][1]+"]");
            pp[i][0]=x;
            pp[i][1]=y;
            pp[i][2]=p[next][0];
            pp[i][3]=p[next][1];
        }
    }
    
    //Associated Functions for finding next and prev point
    public static int nextpoint(int n,int i){
        int x=(i+1)%n;
        return(x);
    }
    public static int prevpoint(int n,int i){
        int x=(i-1);
        if(x==-1)
            x=n-1;
        return(x);
    }
    public static int updatex(int x1,int y1,int x2,int y2)
    {
        //System.out.println("["+x1+","+y1+"]"+"["+x2+","+y2+"]");
        int x;
        
        x=Math.round((x2-x1)*(-1)/(y2-y1)+x1);
        return x;
    }
    
    public static void main(String[] args) throws IOException {
        int n,i,j,ymax=0,ymin=99999,txmin,tymin,tymax,dx,dy;
        double m;
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter number of vertices:");
        n=Integer.parseInt(br.readLine());
        p=new int[n][2];
        pp=new int[n][4];
        System.out.println("Enter the coordinates of each vertex:");
        for(i=0;i<n;i++)
        {
            System.out.println("Enter Coordinate "+(i+1)+":");
            System.out.print("X:");
            p[i][0]=Integer.parseInt(br.readLine());
            System.out.print("Y:");
            p[i][1]=Integer.parseInt(br.readLine());
            if(p[i][1]>ymax)
                ymax=p[i][1];
            if(p[i][1]<ymin)
                ymin=p[i][1];
        }
        System.out.println("Before Vertex processing:\nIteration\tx\ty");
        for(i=0;i<n;i++)
        {
            System.out.print(i);
            for(j=0;j<2;j++){
                System.out.print("\t"+p[i][j]);
            }
            System.out.println();
        }
        //System.out.println("Coordinates obtained during Vertex processing:");
        vertexprocessing(n);
        System.out.println("After Vertex processing:\nIteration\tx1\ty1\tx2\ty2");
        for(i=0;i<n;i++)
        {
            System.out.print(i);
            for(j=0;j<4;j++){
                System.out.print("\t"+pp[i][j]);
            }
            System.out.println();
        }
        
        //Sorted EdgeNode Table Here We come
        ArrayList<EdgeNode>[] vertex = new ArrayList[ymax-ymin];
        for(i=0;i<n;i++)
        {
            //Finding Max and Min Points
            if(pp[i][3]>pp[i][1])
            {
                tymax=pp[i][3];
                tymin=pp[i][1];
                txmin=pp[i][0];
            }
            else
            {
                tymax=pp[i][1];
                tymin=pp[i][3];
                txmin=pp[i][2];
            }
            dx=pp[i][2]-pp[i][0];
            dy=pp[i][3]-pp[i][1];
            m=(double)dx/dy;
            
            //Creating EdgeNode
            EdgeNode e = new EdgeNode(tymax,txmin,m);
            if (vertex[tymin-ymin] == null) {
                vertex[tymin-ymin] = new ArrayList<EdgeNode>();
            }
            vertex[tymin-ymin].add(e);
        }
        
        //Sorting According to xmin
        ArrayList<EdgeNode> list;
        for(i=0;i<ymax-ymin;i++)
        {
            list=vertex[i];
            if(list!=null){
            list.sort((EdgeNode a, EdgeNode b) -> {
                int dec=0;
                if(a.xmin>b.xmin)
                    dec=1;
                if(a.xmin<b.xmin)
                    dec=-1;
                return(dec);
            });
            }
        }
        
        //Displaying ScanLine
        System.out.println("Sorted Edge Table is::");
        
        for(i=ymax-ymin-1;i>=0;i--)
        {
            System.out.print((ymin+i)+"\t->");
            if(vertex[i] == null)
                System.out.print("\tNULL");
            else
            {
                for(EdgeNode a:vertex[i])
                {
                    EdgeNode s=a;
                    System.out.print("\t("+s.ymax+","+s.xmin+","+s.m+")\t->");
                }
                System.out.print("\tNULL");
            }
            System.out.println();
        }
    }
}