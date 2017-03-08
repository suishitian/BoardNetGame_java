package cline_test2;
import java.util.*;
public class mutliThreadClientTest {
	public static void main(String[] args){
		socketJ client1 = new socketJ(9090);
		Scanner input = new Scanner(System.in);
		while(true){
			System.out.println("input::::");
			String content = input.next();
			client1.sendJ("127.0.0.1", 5050, content);
			client1.receiveJS();
			if(client1.getString(client1.getClr()).equals("confirm")){
				client1.client1(client1.getClr());
				System.out.println("___loaded");
				while(true){
					System.out.println("thread input:");
					String str = input.next();
					client1.sendBack(client1.getCl1(), str);
				}
			}
			System.out.println("from: " + client1.getString(client1.getClr()));
		}
	}
}
