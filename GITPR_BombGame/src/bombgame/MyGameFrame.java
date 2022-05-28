package bombgame;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyGameFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pan;
	String name;
	int w;
	int h;
	
	MyGameFrame(JPanel pan, String nam, int w, int h){
			
		this.pan=pan;
		name = nam;
		this.w=w;
		this.h=h;
		
		JFrame frame = new JFrame(name);	
		frame.setContentPane(pan);
							
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLocationRelativeTo(null);
		frame.setBounds(400, 120, w, h);
		//frame.setSize(500,500);
		//frame.setLayout(null);  
		// ибо если оставить setLayout null
		frame.setVisible(true);
		//frame.pack();
	
		
		
	}
	
	
	
	
	
}

