import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class welcomepage {
	private JFrame frame;
	private JTextField title;
	//private JButton startgame;
	//private JButton quit;
	private JMenuBar menuBar;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel cardpanel;
	private JPanel registerPage;
	
	public welcomepage(int width ,int height,String name){
		frame = new JFrame(name);
		frame.setSize(width,height);
		menuBar = new JMenuBar();
		title = new JTextField();
		cardpanel = new JPanel();
		
		
		JMenu menu = new JMenu("menu");
		JMenuItem menuItem1 = new JMenuItem("register");
	
		menu.add(menuItem1);

		setPanel1();
		setPanel2();
		setPanelRegister();
		Container content = frame.getContentPane();
		content.setLayout(new GridLayout());
		content.add(cardpanel);
		cardpanel.setLayout(new CardLayout());
		cardpanel.add(panel1, "one");
		cardpanel.add(panel2, "two");
		cardpanel.add(registerPage, "three");
		((CardLayout)cardpanel.getLayout()).show(cardpanel,"one");
		menuItem1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				((CardLayout)cardpanel.getLayout()).show(cardpanel,"three");
			}
		});
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void setPanel1(){
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3,1));
		JButton startgame = new JButton("start"); 
		startgame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//transfer();
				System.out.print("asd;);");
				
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
				System.out.println("there should be a new window to play single game.");
			}
		});
		multiple.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("there should be a new window to play multiple game");
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
