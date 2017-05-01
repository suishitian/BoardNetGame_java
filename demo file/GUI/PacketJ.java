import java.util.*;
import java.net.*;
/**
 * 
 * @author suishitian
 *
 * PacketJ formate:
 *     first:middle:end
 * connect:
 *     connect:connect:connect
 * login/register : 
 *     login:#account#:#passcode#
 *     login:confirm:confirm
 *     register:#account#:#passcode#
 *     register:confirm:confirm
 * select game:
 *     game:5inrow:
 * input IP:
 *     5inrow:ip:xxx.xxx.xxx.xxx
 *     5inrow:request:@username@
 *     5inrow:request:confirm
 *     5inrow:request:refuse
 * game start:
 *     5inrow:start:ready
 *     5inrow:start:confirm
 *     5inrow:start:B
 *     5inrow:start:W
 * during game:
 *     5inrow:x1:y1
 *     5inrow:x2:y2
 * end:
 *     5inrow:win:B
 *     5inrow:win:W
 *     5inrow:end:confirm
 */
public class PacketJ {
	private
		String content;
		String state;
		String first;
		String middle;
		String end;
		DatagramPacket se;
		
	public PacketJ(){
	}
	
	public void readP(DatagramPacket p){
		se = p;
		content = new String(p.getData(),0,p.getLength());
		first = content.split(":")[0];
		middle = content.split(":")[1];
		end = content.split(":")[2];
		state = first;
	}
	public void readS(String content){
		this.content = content;
		first = content.split(":")[0];
		middle = content.split(":")[1];
		end = content.split(":")[2];
		state = first;
	}
	public void read(String a,String b,String c){
		first = a;
		middle = b;
		end = c;
		content = a+":"+b+":"+c;
		state = first;
	}

	public void analyze(){
		//use .split
		//packet format : XXX:XXX:XXX
	}
	public void buildPacket(){
		//packet format : XXX:XXX:XXX
	}
	
	public String getContent(){
		return this.content;
	}
	public String getFirst(){
		return this.first;
	}
	public String getMiddle(){
		return this.middle;
	}
	public String getEnd(){
		return this.end;
	}
	public String getState(){
		return this.state;
	}
	
	public String getFirst(DatagramPacket p){
		readP(p);
		return getFirst();
	}
	
	public String getMiddle(DatagramPacket p){
		readP(p);
		return getMiddle();
	}
	
	public String getEnd(DatagramPacket p){
		readP(p);
		return getEnd();
	}
}