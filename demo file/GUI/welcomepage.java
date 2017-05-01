import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
;public class welcomepage {
	private socketJ client;
	private DatagramPacket serverP;
	private PacketJ packet;
	
	private JFrame frame;
	private JTextField title;
	//private int[][] info;
	
	private int loginFlag;
	private int registerFlag;
	private int startFlag;
	
	private int width;
	private int height;
	
	private JMenuBar menuBar;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel cardpanel;
	private JPanel loginPage;
	private JPanel registerPage;
	private JPanel game;
	private JPanel mutilgame;
	
	private DrawArea drawArea;
	private mutliDrawArea multiDrawArea;
	
	public welcomepage(int width ,int height,String name){
		packet = new PacketJ();
		client = new socketJ(5555);
		client.sendJ("127.0.0.1", 5050, "connect:connect:connect");
		client.receiveJS();
		if(client.getString(client.getClr()).equals("connect:connect:confirm")){
			System.out.println("connection successfully");
			serverP = client.getClr();
		}
		loginFlag = 0;
		registerFlag = 0;
		this.width = width;
		this.height = height;
		frame = new JFrame(name);
		frame.setSize(width,height);
		menuBar = new JMenuBar();
		title = new JTextField();
		cardpanel = new JPanel();
		
		JMenu menu = new JMenu("menu");
		JMenuItem menuItem1 = new JMenuItem("register");
		JMenuItem menuItem2 = new JMenuItem("login in");
		menu.add(menuItem1);
		menu.add(menuItem2);
		
		setPanel1();
		setPanel2();
		setPanelRegister();
		setPanelLogin();
		setPanelGame();
		Container content = frame.getContentPane();
		content.setLayout(new GridLayout());
		content.add(cardpanel);
		cardpanel.setLayout(new CardLayout());
		cardpanel.add(panel1, "one");
		cardpanel.add(panel2, "two");
		cardpanel.add(registerPage, "three");
		cardpanel.add(loginPage, "loginPage");
		cardpanel.add(drawArea,"gamePage");
		cardpanel.add(multiDrawArea,"multigamePage");
		((CardLayout)cardpanel.getLayout()).show(cardpanel,"one");
		menuItem1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				((CardLayout)cardpanel.getLayout()).show(cardpanel,"three");
			}
		});
		menuItem2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				((CardLayout)cardpanel.getLayout()).show(cardpanel,"loginPage");
			}
		});
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void runLogin(String account,String passcode){
		client.sendBack(serverP, "login:"+account+":"+passcode);
		client.receiveJS();
		if(client.getString(client.getClr()).equals("login:confirm:confirm")){
			loginFlag = 1;
			System.out.println("login successfully");
		}
	}
	public void runRegister(String account,String passcode){
		client.sendBack(serverP, "register:"+account+":"+passcode);
	}
	public void setPanelGame(){
		drawArea = new DrawArea(client,1);
		//game = new JPanel();
		//game.setLayout(new BorderLayout());
		//game.add(drawArea);
		//Graphics g = game.getGraphics();
		//g.drawLine(0, 0, game.WIDTH,game.HEIGHT);
		//game.setVisible(true);
		drawArea.setVisible(true);
	}
	
	public void setPanelLogin(){
		loginPage = new JPanel();
		loginPage.setLayout(new GridLayout(3,2));
		
		JLabel label1 = new JLabel("account");
		JLabel label2 = new JLabel("passcode");
		JTextField account = new JTextField();
		JTextField passcode = new JTextField();
		JButton confirm = new JButton("login in");
		JButton goback = new JButton("go back");
		
		confirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				runLogin(account.getText(),passcode.getText());
			}
		});
		goback.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				((CardLayout)cardpanel.getLayout()).show(cardpanel,"one");
			}
		});
		loginPage.add(label1);
		loginPage.add(account);
		loginPage.add(label2);
		loginPage.add(passcode);
		loginPage.add(confirm);
		loginPage.add(goback);
	}
	public void setPanel1(){
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3,1));
		JButton startgame = new JButton("start"); 
		//System.out.print(panel1.size()+" "+panel1.HEIGHT);
		startgame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//transfer();
				//System.out.print("asd;);");
				((CardLayout)cardpanel.getLayout()).show(cardpanel,"two");
			}
		});
		JButton quit = new JButton("quit");
		quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
		JLabel lable = new JLabel("board game");
		
		panel1.add(lable);
		//lable.setBounds(frame.getWidth()/2, frame.getHeight()/4,350,100);
		panel1.add(startgame);
		//startgame.setBounds(frame.getWidth()/2, frame.getHeight()*5/8, 100,80);
		panel1.add(quit);
		//quit.setBounds(frame.getWidth()/2, frame.getHeight()*7/8, 200,80);
		startgame.setVisible(true);
		lable.setVisible(true);
		lable.setHorizontalAlignment(SwingConstants.CENTER);
		quit.setVisible(true);
	}
	
	public void setPanel2(){
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(3,1));
		JButton single = new JButton("single player");
		JButton multiple = new JButton("multiple player");
		JButton goback = new JButton("go back");
		single.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				((CardLayout)cardpanel.getLayout()).show(cardpanel,"gamePage");
				System.out.println("there should be a new window to play single game.");
			}
		});
		multiple.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("there should be a new window to play multiple game");
				if(loginFlag==0) ((CardLayout)cardpanel.getLayout()).show(cardpanel,"loginPage");
				else {
					client.sendBack(serverP, "request:5inrow:127.0.0.1");
					client.receiveJS();
					if(client.getString(client.getClr()).equals("request:5inrow:confirm")){
						client.receiveJS();
						if(packet.getFirst(client.getClr()).equals("request")){
							if(packet.getEnd(client.getClr()).equals("B")){
								//todo
								multiDrawArea = new mutliDrawArea(client,1);
								((CardLayout)cardpanel.getLayout()).show(cardpanel,"multigamePage");
							}
							else if(packet.getEnd(client.getClr()).equals("W")){
								multiDrawArea = new mutliDrawArea(client,2);
								((CardLayout)cardpanel.getLayout()).show(cardpanel,"multigamePage");
							}
						}
					}
				}
				
			}
		});
		goback.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				((CardLayout)cardpanel.getLayout()).show(cardpanel,"one");
			}
		});
		panel2.add(single);
		panel2.add(multiple);
		panel2.add(goback);
		//label.setVisible(true);
	}
	public void setPanelRegister(){
		registerPage = new JPanel();
		registerPage.setLayout(new GridLayout(3,2));
		JLabel label1 = new JLabel("account");
		JLabel label2 = new JLabel("passcode");
		JTextField account = new JTextField();
		JTextField passcode = new JTextField();
		JButton confirm = new JButton("confirm");
		JButton goback = new JButton("go back");
		confirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("confirm");
				runRegister(account.getText(),passcode.getText());
			}
		});
		goback.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				((CardLayout)cardpanel.getLayout()).show(cardpanel,"one");
			}
		});
		registerPage.add(label1);
		registerPage.add(account);
		registerPage.add(label2);
		registerPage.add(passcode);
		registerPage.add(confirm);
		registerPage.add(goback);
	}
	
	public void setLayout(){
		Container content = frame.getContentPane();
		content.setLayout(new CardLayout());
		//todo
	}
	public static void main(String[] args){
		welcomepage w = new welcomepage(800,600,"hhee");
	}
}
