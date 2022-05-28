package bombgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//КОМПЛЕКТ: MyGameGUI_2, MyGameThread_2, MyGameStart_2, MyGameFrame_2
//SUBTASK_10
//Task1 - construct actual GUI with all corresponding members
//good GUI solution was in MyGameShell and than it was passed in 
//GameShell - take from GameShell. This Time make special class
//for startin game MyGameStart_2. And Create class MyGameFrame for creation
//of JFrame.  -SUCCED!
//Task2 - all current functionality for former classes
//transfer to news.   -SUCCED!
//Task3 - create level increasion. For this doing
//antoher try class Levels.  -SUCCED!
//Task4 - make check up is it victory. All this
//functionality was produced in Operate2. 
//Added methods CheckForVictory, RizeLevel  -SUCCED!
//EVERYTHING IS ALLRIGHT.
//Task5 - change font of label. Add JOptinPanes.
//
//Task6 - adding JOptionPane.
//
//
//

public class MyGameGUI_2 implements ActionListener {
	
		
		final static Color onRed = new Color(255, 0, 0);
		final static Color ofRed = new Color(123, 42, 42);
		final static Color onBlue = new Color(0, 0, 255);
		final static Color ofBlue = new Color(42, 42, 80);
		private Color currentRed = onRed;
		private Color currentBlue = ofBlue;
		JPanel common;
		JPanel upcommon;
		JPanel downcommon;
		JPanel lights;
		JPanel shotpanel;
		JPanel panelRed;
		JPanel panelBlue;
		JPanel panelLevel;
		JPanel panelNewGame;
		
		JPanel zaruwkaRed;
		JPanel zaruwkaBlue;
		JButton shot;
		JButton start;
		JLabel labelLevel;
		
		static float timelap = 1000;	
		static int level = 1;
		
		
		
		
		MyGameThread_2 mgt;
		
		MyGameGUI_2() {
			
//			creating containers
			
			 common = new JPanel(); // main panel, which will by passed to MyFrame.
//			---------------------------
			 upcommon = new JPanel();
			 downcommon = new JPanel();
//			----------------upcommon -------------
			 lights = new JPanel();
			 shotpanel = new JPanel();
	//-------------------fires----------------		
			 panelRed = new JPanel();
			 panelBlue = new JPanel();
	//-----------------downcommon------------
			 panelLevel = new JPanel();
			 panelNewGame = new JPanel();
			

			
//			creating elements
			zaruwkaRed = new JPanel();
			zaruwkaBlue = new JPanel();
			shot = new JButton("SHOT!!!");
			labelLevel = new JLabel("Level:" + 1);
			start = new JButton("Continue Game");
			
//	 		doing layouts
			common.setLayout(new BorderLayout());
			upcommon.setLayout(new GridLayout(2, 1));
			lights.setLayout(new GridLayout(1,2 ));
			downcommon.setLayout(new GridLayout(1,2,1, 10));
			

//			Flows
			shotpanel.setLayout(new FlowLayout (FlowLayout.CENTER, 0, 23));
			panelRed.setLayout(new FlowLayout (FlowLayout.CENTER, 0, 60));
			panelBlue.setLayout(new FlowLayout (FlowLayout.CENTER, 0, 60));
			panelLevel.setLayout(new FlowLayout (FlowLayout.LEFT, 40, 8));
			panelNewGame.setLayout(new FlowLayout (FlowLayout.RIGHT, 40, 8));
			
			
//			setting color
			downcommon.setBackground(Color.gray);
			upcommon.setBackground(Color.gray);
			
			zaruwkaRed.setBackground(onRed);
			zaruwkaBlue.setBackground(ofBlue);
			zaruwkaRed.setPreferredSize(new Dimension(100, 60));
			zaruwkaBlue.setPreferredSize(new Dimension(100, 60));
			shot.setPreferredSize(new Dimension(100, 40));
			labelLevel.setFont(new Font("Arial",Font.PLAIN,25)); 
		
//			adding elements
		
			panelRed.add(zaruwkaRed);
			panelBlue.add(zaruwkaBlue);
			lights.add(panelBlue);
			lights.add(panelRed);
			shotpanel.add(shot);
			upcommon.add(lights);
			upcommon.add(shotpanel);
			
			
			panelLevel.add(labelLevel);
			panelNewGame.add(start);
			downcommon.add(panelLevel);
			downcommon.add(panelNewGame);
		
		
			common.add(upcommon, BorderLayout.CENTER);
			common.add(downcommon, BorderLayout.SOUTH);
//			-------------------------------------
//			раскладка закончилась, теперь экшн
			
			shot.addActionListener(this);
			start.addActionListener(this);
			shot.setEnabled(false);
			
//			send GUI to Frame
			new MyGameFrame(common,"MyGameGUI_2", 600, 400);
			JOptionPane.showMessageDialog(null, "Today you should disarm the bomb. \r\n"
					+ "It could be done by hitting the SHOT, while red signal is alive.\r\n"
					+ "If you SHOT while  blue is alive - the bomb will detonate.\r\n"
					+ "Try not to screw up.\r\n"
					+ "Are you ready?", "BombGame", JOptionPane.PLAIN_MESSAGE  );
		}
		
		public void ChangeLabel() {
			zaruwkaRed.setBackground(currentRed);
			
			if(currentRed == ofRed) {
				currentRed = onRed;
			}else{
				currentRed = ofRed;}
			
			zaruwkaBlue.setBackground(currentBlue);
			if(currentBlue == ofBlue) {
				currentBlue = onBlue;
			}else{
				currentBlue = ofBlue;}
		}

		public void CheckForVictory() {
			if (currentRed==ofRed) {
//				System.out.println("Вы победили!!!");	
//				тут добавляетм опшнпэйн с победой
				level++; // поднимаем уровень. 
				labelLevel.setText("Level:" + level); // меняем текст на лейбле
				timelap = RiseLevel(level);  //изменяем скорость.
				JOptionPane.showMessageDialog(null, "V I C T O R Y ! ! ! \r\n"
						+ "You did it. Your level is "+ level +"\r\n"
						+ "Go On!", "BombGame", JOptionPane.PLAIN_MESSAGE  );
				
			}else {
//				System.out.println("Вы проиграли((((");
				JOptionPane.showMessageDialog(null, "Sorry. You lost. The bomb has exploided. \r\n"
						+ "Close window. Try again!", "BombGame", JOptionPane.PLAIN_MESSAGE  );
				start.setEnabled(false);
			}
		}
		
		
		public float RiseLevel(int l) {
			float baza = (float)l;
			float result = 1000* (1/baza);
			return result;


	}
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==start) {
				System.out.println("start");
				mgt = new MyGameThread_2(this, timelap);
				mgt.start();
				System.out.println(timelap);
			start.setEnabled(false);
			shot.setEnabled(true);
			}else {
				mgt.stopMe();
			start.setEnabled(true);
			shot.setEnabled(false);
			this.CheckForVictory();
			}
			
			}
}
