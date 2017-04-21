import java.awt.*;
import javax.swing.*;

public class window{
	private JPanel DrawArea;
	private JPanel buttonArea;
	private JMenuBar menuBar;
	private JFrame frame;
	public window(int width,int height,String title){
		frame =  new JFrame(title);
		frame.setSize(width,height);
		DrawArea = new JPanel();
		menuBar = new JMenuBar();
		buttonArea = new JPanel();
		Container content = frame.getContentPane();
		DrawArea.setSize(width*3/4, height-menuBar.getHeight());
		buttonArea.setSize(width-DrawArea.getWidth(),height-menuBar.getHeight());
		
		content.setLayout(new BorderLayout());
		content.add(DrawArea, BorderLayout.WEST);
		content.add(menuBar,BorderLayout.NORTH);
		content.add(buttonArea, BorderLayout.EAST);
		frame.setVisible(true);
	}
	public static void main(String[] args){
		window a = new window(300,400,"boardgame");
	}
}
