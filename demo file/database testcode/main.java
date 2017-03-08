package mySQL;

public class main {
	public static void main(String[] args){
		mysqlJ sql = new mysqlJ();
		sql.setDatabase("servertest");
		socketJ server = new socketJ(9999);
		
		while(true){
			server.receiveJS();
			if(server.getString(server.getClr()).equals("register")){
				System.out.println("register request");
				server.client1(server.getClr());
				server.sendBack(server.getCl1(), "confirm");
				break;
			}
		}
		String name = "";
		String password = "";
		while(true){
			server.receiveJS();
			if(socketJ.compare(server.getClr(),server.getCl1())){
				name = server.getString(server.getClr());
				System.out.println(name);
				server.sendBack(server.getCl1(), "confirm");
				break;
			}
		}
		while(true){
			server.receiveJS();
			if(socketJ.compare(server.getClr(),server.getCl1())){
				password = server.getString(server.getClr());
				System.out.println(password);
				server.sendBack(server.getCl1(), "confirm");
				break;
			}
		}
		//if(!sql.insertNewAccount("info2", name, password).equals("") ){
		//	System.out.println("register successfully");
		//}
		sql.insertNewAccount("info2", name, password);
		String password1 = "";
		while(true){
			server.receiveJS();
			if(server.getString(server.getClr()).equals("login")){
				if(socketJ.compare(server.getClr(), server.getCl1())){
					String cl1_name = server.getString(server.getClr());
					password = sql.getPassword("info2", cl1_name);
					server.sendBack(server.getCl1(), "confirm");
					break;
				}
			}
		}
		while(true){
			server.receiveJS();
			if(socketJ.compare(server.getClr(), server.getCl1())){
				if(server.getString(server.getClr()).equals(password1)){
					server.sendBack(server.getCl1(), "successfully");
					break;
				}
				else server.sendBack(server.getCl1(), "declined");
			}
		}
	}
}

