package cline_test2;

import java.net.*;
import java.util.*;

public class socketJ {
	private 
		DatagramSocket ds = null;
		int port;
		
		DatagramPacket cl1 = null;
		DatagramPacket cl2 = null;
		DatagramPacket cl_r = null;
		DatagramPacket cl_s = null;
		
	public socketJ(int a){
		port = a;
		try{
			ds = new DatagramSocket(port);
		}
		catch(Exception e){}
		finally{}
	}
	/*public void close(){
		ds.close();       
	}*/

	public void sendJ(String IP , int port , String content){
		try{
			//ds = new DatagramSocket(port);
			DatagramPacket a = new DatagramPacket(content.getBytes(),content.getBytes().length,
					InetAddress.getByName(IP),port);
			ds.send(a);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{}
	}
	public void sendJ(InetAddress IP , int port, String content){
		try{
			//ds =  new DatagramSocket(port);
			DatagramPacket a = new DatagramPacket(content.getBytes(),content.getBytes().length,
					IP,port);
			ds.send(a);
			cl_s = a;
			}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{}
	}
	
	@SuppressWarnings("finally")
	public DatagramPacket receiveJS(){
		byte b[] = new byte[1024];
		DatagramPacket rp = new DatagramPacket(b,b.length);
		try{
			ds.receive(rp);
			cl_r=rp;
		}
		catch(Exception e){}
		finally{
			return rp;
		}
	}
	
	public String getString(DatagramPacket p){
		return new String(p.getData(),0,p.getLength());
		//return new String(p.getData());
	}
	
	public void sendBack(DatagramPacket p,String content){
		InetAddress IP = p.getAddress();
		int port_b = p.getPort();
		this.sendJ(IP,port_b,content);
	}
	
	public static boolean compare(DatagramPacket p1,DatagramPacket p2){
		if(p1.getAddress().equals(p2.getAddress()) && (p1.getPort()==p2.getPort())){
			return true;
		}
		else return false;
	}
	
	public void client1(DatagramPacket p){
		this.cl1 = p;
	}
	public void client2(DatagramPacket p){
		this.cl2 = p;
	}
	public void clr(DatagramPacket p){
		this.cl_r = p;
	}
	public void cls(DatagramPacket p){
		this.cl_s = p;
	}
	public DatagramPacket getCl1(){
		if(this.cl1==null){
			return null;
		}
		else {
			return this.cl1;
		}
	}
	public DatagramPacket getCl2(){
		if(this.cl2==null){
			return null;
		}
		else {
			return this.cl2;
		}
	}
	public DatagramPacket getClr(){
		if(this.cl_r==null){
			return null;
		}
		else {
			return this.cl_r;
		}
	}
	public DatagramPacket getCls(){
		if(this.cl_s==null){
			return null;
		}
		else {
			return this.cl_s;
		}
	}
}