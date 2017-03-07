package mySQL;
import java.sql.*;

public class mysqlJ {
	private 
		String username;
		String password;
		int port_sql;
		Statement sql_s = null;
		int database_flag=0;
		String database;
		String table;
		
	public mysqlJ(String name,String word,int port){
		username = name;
		password = word;
		port_sql=port;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:"+port_sql+"/";
		
		try{
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			if(!conn.isClosed()){
				System.out.println("Succeeded connecting to the Database!");
			}
			sql_s = conn.createStatement();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{}
	}
	
	public mysqlJ(){
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/";
		username = "root";
		password = "a4533776";
		port_sql = 3306;
		try{
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			if(!conn.isClosed()){
				System.out.println("Succeeded connecting to the Database!");
			}
			sql_s = conn.createStatement();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{}
	}
	
	public void setDatabase(String database){
		String statement = "USE "+database+";";
		try{
			sql_s.executeQuery(statement);
			database_flag=1;
			this.database=database;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{}
	}
	
	public void showDatabase(){
		String statement = "SHOW DATABASES;";
		try{
			ResultSet res = sql_s.executeQuery(statement);
			while(res.next()){
				System.out.println(res.getString("database"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{}
	}
	
	public void showTable(){
		if(database_flag==0){
			System.out.println("must set database first");
			return;
		}
		String statement = "SHOW TABLES;";
		try{
			ResultSet res = sql_s.executeQuery(statement);
			while(res.next()){
				System.out.println(res.getString("Tables_in_"+database));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{}
	}
	//public String selectIP(String table , String name){
	//}
	public String getPassword(String table,String name){
		if(database_flag==0){
			System.out.println("must set database first");
			return "";
		}
		String statement = "SELECT password"+" FROM "+table+" WHERE name="+"'"+name+"';";
		//System.out.println(statement);
		try{
			ResultSet ans = sql_s.executeQuery(statement);
			ans.next();
			String res =ans.getString("password"); 
			return res;
		}
		catch(Exception e){
			e.printStackTrace();
			return "";
		}
		finally{}
	}
	public String getUserID(String table,String name){
		if(database_flag==0){
			System.out.println("must set database first");
			return "";
		}
		String statement = "SELECT id"+" FROM "+table+" WHERE name="+"'"+name+"';";
		//System.out.println(statement);
		try{
			ResultSet ans = sql_s.executeQuery(statement);
			ans.next();
			String res =ans.getString("id"); 
			return res;
		}
		catch(Exception e){
			e.printStackTrace();
			return "";
		}
		finally{}
	}
	
	public String insertNewAccount(String table ,String name ,String password){
		if(database_flag==0){
			System.out.println("must set database first");
			return "";
		}
		String statement = "INSERT INTO "+table+" VALUES(NULL,'"+name+"','"+password+"',0,0,0);";
		String statement2 = "SELECT id"+" FROM "+table+" WHERE name="+"'"+name+"';";
		//System.out.println(statement);
		try{
			sql_s.executeUpdate(statement);
			ResultSet ans = sql_s.executeQuery(statement2);
			ans.next();
			String res = ans.getString("id");
			return res;
		}
		catch(Exception e){
			e.printStackTrace();
			return "";
		}
		finally{}
	}
}