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
 *     register:#account#:#passcode#
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
		int state;
		int middle;
		int end;
		DatagramPacket se;
		//mysqpJ my;
	public PacketJ(DatagramPacket p){
		content = new String(p.getData(),0,p.getLength());
	};
	public PacketJ(String a){
		content = a;
	}
	
	
	
	public void analyze(){
		//use .split
		//packet format : XXX:XXX:XXX
	}
	public void buildPacket(){
		//packet format : XXX:XXX:XXX
	}
}
