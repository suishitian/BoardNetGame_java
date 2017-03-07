package client_test2;
import java.util.*;
public class client2 {
	public static void main(String[] args){
		socketJ cl2 = new socketJ(5050);
		Scanner input = new Scanner(System.in);
		while(true){
			if(cl2.getString(cl2.receiveJS()).equals("request")){
				System.out.println("connection requested , please input 'accept' :");
				String content = input.next();
				cl2.client2(cl2.getClr());
				//cl2.client2(cl2.getClr());
				//cl2.sendBack(cl2.getCl2(), content);
				cl2.sendJ("127.0.0.1", 9999, content);
				break;
			}
			else continue;
		}
		while(true){
			if(cl2.getString(cl2.receiveJS()).equals("succeed")){
				if(socketJ.compare(cl2.getClr(), cl2.getCl2())){
					System.out.println("connected with client 1");
					break;
				}
			}
			else continue;
		}
		while(true){
			cl2.receiveJS();
			System.out.println(cl2.getString(cl2.getClr()));
			System.out.println(cl2.getClr().getAddress().toString());
			if(socketJ.compare(cl2.getClr(), cl2.getCl2())){
				String content = cl2.getString(cl2.getClr());
				System.out.println("client 2: "+content);
				String sss = input.next();
				cl2.sendBack(cl2.getCl2(), sss);
			}
			else continue;	
		}
	}
}
