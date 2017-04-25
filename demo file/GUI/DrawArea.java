import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawArea extends JPanel{
	private socketJ client;
	
	private int width;
	private int height;
	private int BX1;
	private int BX2;
	private int BY1;
	private int BY2;
	private int l;
	private int length;
	private int position;
	private int enposition;
	private int step;
	
	private int[][] info;
	private int MX;
	private int MY;
	private Color myColor;
	private Color enColor;
	private int waitFlag;
	
	private int winner=0;
	//private Graphics g;
	
	public DrawArea(socketJ client){
		super(null);
		this.client = client;
		MX=0;
		MY=0;
		waitFlag = 0;
		step = 0;
		position = c;
		if(position == 1) enposition = 2;
		else if(position == 2) enposition = 1;
		
		if(c==1) {
			myColor = Color.BLACK;
			enColor = Color.WHITE;	
		}
		else if(c==2){
			myColor = Color.WHITE;
			enColor = Color.BLACK;
		}
		
		info  = new int[16][16];
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++){
				info[i][j]=0;
			}
		}

		super.setVisible(true);
	}
	
	public static int round(double a){
		if(a-(double)Math.floor(a) < 0.5){
			return (int)Math.floor(a);
		}
		else {
			return (int)Math.ceil(a);
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics h = getGraphics();
		this.width = super.getWidth();
		this.height = super.getHeight();
		int norm;
		if(width >= height) norm = height;
		else norm = width;
		l = norm/32;
		length = l*30;	
		BX1 = width/2-l*15;
		BX2 = width/2+l*15;
		BY1 = height/2-l*15;
		BY2 = height/2+l*15;
		
		System.out.println(width+" "+height);
		System.out.println("BX1="+BX1);
		System.out.println("BX2="+BX2);
		System.out.println("BY1="+BY1);
		System.out.println("BY2="+BY2);
		
		g.drawLine(BX1, BY1, BX2, BY1);
		g.drawLine(BX2, BY1, BX2, BY2);
		g.drawLine(BX1, BY2, BX2, BY2);
		g.drawLine(BX1, BY1, BX1, BY2);
		
		for(int i=0;i<15;i++){
			g.drawLine(BX1+i*l*2, BY1, BX1+i*l*2, BY2);
			g.drawLine(BX1, BY1+i*l*2, BX2, BY1+i*l*2);
		}
		
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++){
				System.out.print(info[i][j]+"  ");
				if(info[i][j]==0) continue;
				if(info[i][j]==1){
					g.setColor(myColor);
					g.fillOval(i*2*l+BX1-l, j*2*l+BY1-l, 2*l, 2*l);
					continue;
				}
				if(info[i][j]==2){
					g.setColor(enColor);
					g.fillOval(i*2*l+BX1-l, j*2*l+BY1-l, 2*l, 2*l);
					continue;
				}
			}
			System.out.println();
		}
		
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
			
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(waitFlag == 1){
					return;
				}
				else if(waitFlag == 0){
					int x = e.getX();
					int y = e.getY();
					System.out.println("X:"+x+" "+"Y:"+y);
					//System.out.println("XX:"+(round((e.getX()-BX1)/(2*l))*2*l+BX1-l)+" "+"YY:"+(round((e.getY()-BY1)/(2*l))*2*l+BY1-l));
					Graphics h = getGraphics();
					int xx = round((double)(x-BX1)/(2*l));
					int yy = round((double)(y-BY1)/(2*l));
					MX = xx*2*l+BX1-l;
					MY = yy*2*l+BY1-l;
					//h.drawOval(MX, MY, 2*l, 2*l);
					if(info[xx][yy]==0){
						info[xx][yy] = 1;
						h.setColor(myColor);
						h.fillOval(MX,MY,2*l,2*l);
						step++;
						if(winTest(position)){
							System.out.println(step+" youwin");
							winner = position;
						}
						waitFlag=1;
						int rx = round(Math.random()*15);
						int ry = round(Math.random()*15);
						while(info[rx][ry]!=0){
							rx = round(Math.random()*15);
							ry = round(Math.random()*15);
						}
						info[rx][ry] = 2;
						h.setColor(enColor);
						h.fillOval(rx*2*l+BX1-l, ry*2*l+BY2-l, 2*l, 2*l);
						waitFlag=0;
						if(winTest(enposition)){
							System.out.println(step+" enemy win");
							winner = enposition;
						}
						repaint();
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	public boolean winTest(int side){
		return rowTestBool(side,5)||colTestBool(side,5)||LRTestBool(side,5)||RLTestBool(side,5);
	}
	public int[] rowTest(int side1 ,int num){
		int flag = 0;
		int count = 0;
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++){
				if(info[i][j]==side1){
					if(flag==0) {
						flag=1;
						count=1;
						continue;
					}
					else if(flag==1){
						count++;
						if(count==num){
							int xx = i;
							int yy = 0;
							if(j==num) yy=num+1;
							if(j==15) yy=14;
							if(info[xx][yy+1]==0){ 
								int[] r1 = new int[2];
								r1[0]=xx;
								r1[1]=yy+1;
								return r1;
							}
							if(info[xx][yy-num-1]==0){
								int[] rr = new int[]{xx,yy-num-1};
								return rr;
							}
						}
					}
				}
				else if(info[i][j]!=side1){
					flag=0;
				}
			}
		}
		return new int[]{-1,-1};
	}
	
	public boolean rowTestBool(int side,int num){
		int flag = 0;
		int count = 0;
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++){
				if(info[i][j]==side){
					if(flag==0) {
						flag=1;
						count=1;
						continue;
					}
					else if(flag==1){
						count++;
						if(count==num){
							return true;
						}
					}
				}
				else if(info[i][j]!=side){
					flag=0;
					count=0;
				}
			}
		}
		return false;
	}
	
	public boolean colTestBool(int side,int num){
		int flag = 0;
		int count = 0;
		for(int j=0;j<16;j++){
			for(int i=0;i<16;i++){
				if(info[i][j]==side){
					if(flag==0) {
						flag=1;
						count=1;
						continue;
					}
					else if(flag==1){
						count++;
						if(count==num){
							return true;
						}
					}
				}
				else if(info[i][j]!=side){
					flag=0;
					count=0;
				}
			}
		}
		return false;
	}
	
	public boolean LRTestBool(int side,int num){
		int flag1 = 0;
		int flag2 = 0;
		int count1 = 0;
		int count2 = 0;
		int q = 0;
		for(int i=0;i<=15-num+1;i++){
			q=0;
			flag1=0;
			flag2=0;
			count1=0;
			count2=0;
			for(int j=i;j<=15;j++,q++){
				if(info[j][q]==side){
					if(flag1==0){
						flag1=1;
						count1=1;
					}
					else if(flag1==1){
						count1++;
						flag1=1;
						if(count1==num){
							System.out.println("LR: "+count1+" "+count2);
							return true;
						}
					}
				}
				
				else if(info[j][q]!=side){
					flag1=0;
					count1=0;
				}
				
				if(info[q][j]==side){
					if(flag2==0){
						flag2=1;
						count2=1;
					}
					else if(flag2==1){
						count2++;
						flag2=1;
						if(count2==num){
							System.out.println("LR: "+count1+" "+count2);
							return true;
						}
					}
				}
				
				else if(info[q][j]!=side){
					flag2=0;
					count2=0;
				}
			}
			System.out.println("LR: "+count1+" "+count2);
		}
		
		return false;
	}
	
	public boolean RLTestBool(int side,int num){
		int flag1 = 0;
		int flag2 = 0;
		int count1 = 0;
		int count2 = 0;
		int q = 0;
		for(int i=0;i<=15-num+1;i++){
			q=0;
			flag1=0;
			flag2=0;
			count1=0;
			count2=0;
			for(int j=i;j<=15;j++,q++){
				if(info[15-j][q]==side){
					if(flag1==0){
						flag1=1;
						count1=1;
					}
					else if(flag1==1){
						count1++;
						flag1=1;
						if(count1==num){
							System.out.println("RL: "+count1+" "+count2);
							return true;
						}
					}
				}
				
				else if(info[15-j][q]!=side){
					flag1=0;
					count1=0;
				}
				
				if(info[15-q][j]==side){
					if(flag2==0){
						flag2=1;
						count2=1;
					}
					else if(flag2==1){
						count2++;
						flag2=1;
						if(count2==num){
							System.out.println("RL: "+count1+" "+count2);
							return true;
						}
					}
				}
				
				else if(info[15-q][j]!=side){
					flag2=0;
					count2=0;
				}
				System.out.println("RL: "+count1+" "+count2);
			}
		}
		
		return false;
	}
}
