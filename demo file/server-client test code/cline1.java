package cline_test2;
import java.util.*;
public class cline1 {
	public static void main(String[] args){
		socketJ cl1 = new socketJ(6666);
		Scanner input = new Scanner(System.in);
		
		while(true){
			System.out.println("please input 'connect':");
			String content = input.next();
			cl1.sendJ("127.0.0.1",9999,content);
			if(cl1.getString(cl1.receiveJS()).equals("confirm")){
				System.out.println("connected with server");
				cl1.client1(cl1.getClr());
				break;
			}
		}
		
		while(true){
			System.out.println("please input the target IP :");
			String IP = input.next();
			cl1.sendBack(cl1.getCl1(), IP);
			if(cl1.getString(cl1.receiveJS()).equals("succeed")){
				System.out.println("connected with the target host");
				break;
			}
		}
		
		while(true){
			String sss = input.next();
			cl1.sendBack(cl1.getCl1(), sss);
			if(socketJ.compare(cl1.receiveJS(), cl1.getCl1())){
				String content = cl1.getString(cl1.getClr());
				System.out.println("client 2: "+content);
				String s1s = input.next();
				cl1.sendBack(cl1.getCl1(), s1s);
			}
			else continue;	
		}
		
	}
}
