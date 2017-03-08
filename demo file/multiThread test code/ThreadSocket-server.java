import java.net.*;

public class ThreadSocket {
	private Thread rece;
	private Thread t1;
	private Thread t2;
	private Thread t3;
	socketJ s1;
	socketJ s2;
	socketJ s3;
	socketJ re;
	int p1,p2,p3,pc;
	int state[] = {0,0,0};
	public ThreadSocket(int a ,int b,int c,int d){
		//super();
		pc=a;
		p1=b;
		p2=c;
		p3=d;
		re = new socketJ(pc);
		s1 = new socketJ(p1);
		s2 = new socketJ(p2);
		s3 = new socketJ(p3);
	}
	public void receThreadRun(){
		rece = new Thread(){
			public void run(){
				while(true){
					System.out.println("thread rece !!!");
					re.receiveJS();
					System.out.println(re.getString(re.getClr()));
					if(re.getString(re.getClr()).equals("connect")){
						System.out.println(1);
						if(state[0]==0) t1_run(re.getClr());
						else if(state[1]==0) t2_run(re.getClr());
						else if(state[2]==0) t3_run(re.getClr());
					}
				}
			}
		};
	}
	public void t1_start(DatagramPacket p){
		t1 = new Thread(){
			public void run(){
				System.out.println("thread1 !!!");
				s1.client1(p);
				s1.sendBack(s1.getCl1(), "confirm");
				while(true){
					s1.receiveJS();
					if(s1.getString(s1.getClr()).equals("close")){
						//System.out.println("ccccc");
						break;
					}
					else System.out.println("thread1 :" + s1.getString(s1.getClr()));
				}
				state[0]=0;
				System.out.println("thread 1 close");
			}
		};
	}
	
	public void t1_run(DatagramPacket p){
		t1_start(p);
		t1.start();
		state[0]=1;
	}
	public void t2_start(DatagramPacket p){
		t2 = new Thread(){
			public void run(){
				System.out.println("thread2 !!!");
				s2.client1(p);
				s2.sendBack(s2.getCl1(), "confirm");
				while(true){
					s2.receiveJS();
					if(s2.getString(s2.getClr()).equals("close")){
						//state[1]=0;
						break;
					}
					else System.out.println("thread2 :" + s2.getString(s2.getClr()));
				}
				state[1]=0;
				System.out.println("Thread2 close");
			}
		};
	}
	
	public void t2_run(DatagramPacket p){
		t2_start(p);
		t2.start();
		state[1]=1;
	}
	public void t3_start(DatagramPacket p){
		t3 = new Thread(){
			public void run(){
				System.out.println("thread3 !!!");
				s3.client1(p);
				s3.sendBack(s3.getCl1(), "confirm");
				while(true){
					s3.receiveJS();
					if(s3.getString(s3.getClr()).equals("close")){
						//state[2]=0;
						break;
					}
					else System.out.println("thread3 :" + s3.getString(s3.getClr()));
				}
				state[2]=0;
				System.out.println("thread 3 close");
			}
		};
	}
	
	public void t3_run(DatagramPacket p){
		t3_start(p);
		t3.start();
		state[2]=1;
	}
	public void process(){
		receThreadRun();
		rece.start();
	}
	public static void main(String[] args){
		ThreadSocket a = new ThreadSocket(5050,5051,5052,5053);
		a.process();
	}
}