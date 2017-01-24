package Arkanoid_A;

import java.awt.*;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OknoStartowe extends JPanel {
	
	JLabel start = new JLabel("START");
	JLabel wyjscie = new JLabel("WYJŒCIE");
	JLabel opcje = new JLabel("OPCJE");
    
	JPanel tenPanel = this;
	static Image tlo = new ImageIcon("arkann.png").getImage();
	static Image tloStartowe = new ImageIcon("jungle.jpg").getImage();
	public boolean czyOtwarty = false;
	public int okno;
	File plik;
	String nazwa_pliku;
	static podstawa_Gry blok = new podstawa_Gry(); 
	
	static Image cursorImage = Toolkit.getDefaultToolkit().getImage ("kul.png");
	static Cursor kursor = Toolkit.getDefaultToolkit().createCustomCursor (cursorImage, new Point(0, 0), null);;

	public OknoStartowe() 
	{	
		setLayout(null);
		
		start.setBounds(570, 450, 200, 50);
		start.setForeground(Color.ORANGE.brighter());
		start.setBackground(null);
		start.setFont(new Font(start.getText(), Font.BOLD, 40));
		start.setName("start");
	
	
		
		wyjscie.setBounds(545, 650, 200, 50);
		wyjscie.setForeground(Color.orange.brighter());
		wyjscie.setFont(new Font(start.getText(), Font.BOLD, 40));
		wyjscie.setName("wyjscie");
		
		
		opcje.setBounds(565, 550, 200, 50);
		opcje.setForeground(Color.orange.brighter());
		opcje.setFont(new Font(start.getText(), Font.BOLD, 40));
		opcje.setName("opcje");
		
		
	this.add(start);
	this.add(wyjscie);
	this.add(opcje);
	
	this.setCursor (kursor);

	mouselisenerStartAndExit();
	
	}
	
	
	
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(tloStartowe,0	,0, Main.width, Main.height,null);
		g2d.drawImage(tlo, 0, 0, 1280, 500, null);
		
	}
	
	public void mouselisenerStartAndExit()
	{
		start.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e)
			{
				
				if(e.getSource().equals(start))
				{
					
					podstawa_Gry.click++;
					System.out.println(podstawa_Gry.click);
				tenPanel.setVisible(false);
				
				Main.frame.add(blok);
				blok.setVisible(true);
				podstawa_Gry.isPaused = false;
				
				System.out.println("click");
				Main.okno.setVisible(false);
				
				
				}
			}
		});
		
		
		
		wyjscie.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e)
			{
				
				if(e.getSource().equals(wyjscie))
				{
				System.exit(0);
					
				}
			}
		});
		
		opcje.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e)
			{
				
				if(e.getSource().equals(opcje))
				{
				Opcje option = new Opcje();
				System.out.println("click");
				}
			}
		});
		
	}
	
	
public class Opcje 
{
	JFrame ramkaOpcji;
	JPanel panelOpcji;
	JButton pZamknij;
	JFileChooser wyborPliku;
	JButton pOtworzPlik;
	JRadioButton dzwiekON;
	JRadioButton dzwiekOFF;
	ButtonGroup grupa = new ButtonGroup();
	
	
	
	public Opcje()
	{
		setLayout(null);
		
		panelOpcji = new JPanel();
		ramkaOpcji = new JFrame("OPCJE");
		ramkaOpcji.add(panelOpcji);
		ramkaOpcji.setVisible(true);
		ramkaOpcji.setResizable(false);
		ramkaOpcji.setLocationRelativeTo(null);
		ramkaOpcji.setSize(200,150);
		
		pZamknij = new JButton("Zamknij Opcje");
		panelOpcji.add(pZamknij);
		pZamknij.setPreferredSize(new Dimension(150,20));
		pOtworzPlik = new JButton("Wgraj swoje t³o");
		pOtworzPlik.setPreferredSize(new Dimension(150,20));
		panelOpcji.add(pOtworzPlik);
		
		dzwiekON = new JRadioButton("W³¹cz dzwiêk",true);
		panelOpcji.add(dzwiekON);
		dzwiekON.isSelected();
		grupa.add(dzwiekON);
		
		dzwiekOFF = new JRadioButton("Wy³¹cz dzwiêk");
		panelOpcji.add(dzwiekOFF);
		grupa.add(dzwiekOFF);
		

	
		
	
		
		opcjeZamknij();
		otworzPlik();
		opcjeDzwiek();
		
		
	}
	
	public void opcjeZamknij()
	{
		pZamknij.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent m)
			{
				if(m.getButton()==m.BUTTON1)
				{
				ramkaOpcji.hide();
				
				}
			}
		});
	}
	
	public void otworzPlik()
	{
		
		pOtworzPlik.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent m)
			{
				
				
				if(m.getButton()==m.BUTTON1)
				{
					if(czyOtwarty == false)
					{
						wyborPliku = new JFileChooser();
						panelOpcji.add(wyborPliku);
						
					}
					czyOtwarty = true;
					
					if(czyOtwarty == true)
					{
						okno = wyborPliku.showOpenDialog(panelOpcji);
					}
					
					
					 if(okno == JFileChooser.APPROVE_OPTION)
					 {  
						 plik = wyborPliku.getSelectedFile();
						 nazwa_pliku = plik.toString();
						 //System.out.println(nazwa_pliku);
						 Main.tlo = (Image) new ImageIcon(nazwa_pliku).getImage();
						 
				     }
				}
			}
		});
	}
	
	public void opcjeDzwiek()
	{
		dzwiekON.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent m)
			{
				if(dzwiekON.isSelected())
				{
					podstawa_Gry.isSound = true;
				}
			}
		});
		
		dzwiekOFF.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent m)
			{
				if(dzwiekOFF.isSelected())
				{
					podstawa_Gry.isSound = false;
				}
			}
		});
	}
	
	
}
	


}
