package Arkanoid_A;


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.text.MessageFormat;
import java.util.*;

import javax.sound.sampled.*;

import javax.swing.*;

import Arkanoid_A.*;

public class podstawa_Gry extends JPanel{
	
	static boolean spiacy = false;
	static int jaki_level = 0;
	static int a;
	static int b;
	static int liczbaZyc = 3;
	static double speed = 4;
	static int score = 0;
	public static boolean isRunning = false, isPaused = false , dzialanie = true ,isSound = true ;
	public static long ostatniaZmiana;
	public static int yyp,yys,yyn,yyk;
	static int x;
	static int y;
	static int licz = 0;
	Random losuj = new Random();
	static int paletaW = 100;
	static String powrotMenu = "MENU";
	static int liczOdbicia = 0;
	static boolean czyKoniec = false;
	
	
	boolean war = false;
	int licznik = 0;
	
	
	static boolean czyInterakcja = true;
	
	static int tick = 0;
	static ArrayList<Rectangle> listProstokatow = new ArrayList<Rectangle>();
	static ArrayList listaKulek = new ArrayList();
	static Point paletka = new Point(0, 0);
	static Rectangle paleta ;
	
	
	
	JPanel ten = this;
	static Glowny_Watek gameThread;
	public static Ellipse2D.Double kulka = new Ellipse2D.Double(a,b, 18	, 18);
	
	
	public static Ellipse2D.Double bonus_niespodzia = new Ellipse2D.Double(10,yyn, 40	, 40);
	public static Ellipse2D.Double bonus_serc = new Ellipse2D.Double(1050,yys, 40	, 40);
	public static Ellipse2D.Double bonus_palet = new Ellipse2D.Double(426,yyp, 40	, 40);
	public static Ellipse2D.Double bonus_czaszk = new Ellipse2D.Double(634,yyk, 40	, 40);
	
	public static Rectangle2D.Double podloga = new Rectangle2D.Double(Main.width-1290, 940, Main.width, 10);
	public static Bonus bonus = new Bonus();
	Thread watekKulki = new Thread(new KulkaRunnable());
	
	
	Image serce1 = new ImageIcon("serce.png").getImage();
	Image serce2 = new ImageIcon("serce.png").getImage();
	Image serce3 = new ImageIcon("serce.png").getImage();
	Image bonus_serce = new ImageIcon("bonus-serce.png").getImage();
    Image bonus_prezent = new ImageIcon("bonus-prezent.png").getImage();
    Image bonus_paletka = new ImageIcon("bonus-paletka.png").getImage();
    Image bonus_czaszka = new ImageIcon("bonus-czaszka.png").getImage();
    Image bonus_niespodzianka = new ImageIcon("bonus-prezent.png").getImage();
	
	
	
	
	Image s1 = new ImageIcon("1.png").getImage();
	Image s2 = new ImageIcon("6.png").getImage();
	Image deska = new ImageIcon("deska.png").getImage();
	Image pilka = new ImageIcon("pilka.png").getImage();
	Image image;
	static JLabel menu2 = new JLabel("MENU");
	public static int click = 0;
	
	
	
	public podstawa_Gry()
	{
		Klawiatura();
		Rysuj rysuj = new Rysuj();
		setLayout(null);
		rysujMenu();
		
	
		
		
	}
	
	
	public void rysujMenu()
	{

		menu2.setBounds(50, 5, 100, 50);
		menu2.setForeground(Color.red);
		menu2.setFont(new Font(menu2.getText(), Font.BOLD, 30));
		System.out.println("order: "+ menu2.getComponentZOrder(menu2));

		menu2.setOpaque(true);
	
	
		this.add(menu2);
		
		menu2.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent m)
			{
			
				System.out.println(m.getSource());
				
				if(m.getSource().equals(menu2))
				{
					
				isPaused = true;
				
					OknoStartowe.blok.setVisible(false);
				Main.okno.setVisible(true);
				
				
				
					
				}
			}
		});
		
	}
	
	
	
	public void Klawiatura()
	{
Main.frame.addKeyListener(new KeyListener() {
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent g) {
		if(g.getKeyCode()==KeyEvent.VK_SPACE)
		{
			
				run();
			
			isRunning=true;
		}
		
	}
});

	}
	
	public void run()  {
		if (gameThread != null) 
			if (gameThread.isAlive()) gameThread.interrupt();
		
		gameThread = new Glowny_Watek(this);
		gameThread.start();
		startAnimation();
	
		
		
	}

	public static void grajDzwiek() {
        try {
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 8000, 10, 1, 2, 8000, false);
            File file = new File("alarm1.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            AudioInputStream stream_new = AudioSystem.getAudioInputStream(format, stream);
            Clip clip = AudioSystem.getClip();
            clip.open(stream_new);
            clip.start();
        } catch (Exception e) {
        }
    }
	
	public void playClip(Clip clip)
	{
		stopClip(clip);
		clip.start();
	}
	public void stopClip(Clip clip)
	{
		if(clip.isRunning())
		{
			clip.stop();
		}
		clip.setFramePosition(0);
	}
	

	public void addKulka()
	{
		listaKulek.add(new Kulka());
		watekKulki.start();
		
	}
	
	
	
	
	
	
	public void rysujZycia(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.drawRenderedImage(null, null);
		
		
		if(liczbaZyc==3)
		{
			
			g2.drawImage(s1, 1200, 20, 30, 30,null);
			g2.drawImage(s1, 1165, 20, 30, 30,null);
			g2.drawImage(s1, 1130, 20, 30, 30,null);
			//g2.drawImage(bonus_prezent, 550, 500, 49, 49, null);
			
		}
		if(liczbaZyc==2)
		{
			g2.drawImage(s2, 1130, 20, 30, 30,null);
			g2.drawImage(s1, 1165, 20, 30, 30,null);
			g2.drawImage(s1, 1200, 20, 30, 30,null);
		}
		if(liczbaZyc==1)
		{
			g2.drawImage(s2, 1130, 20, 30, 30,null);
			g2.drawImage(s2, 1165, 20, 30, 30,null);
			g2.drawImage(s1, 1200, 20, 30, 30,null);
		}
		if(liczbaZyc==0)
		{
			g2.drawImage(s2, 1130, 20, 30, 30,null);
			g2.drawImage(s2, 1165, 20, 30, 30,null);
			g2.drawImage(s2, 1200, 20, 30, 30,null);
		}
	}

	public void rysujTlo(Graphics g)
	{
		g.drawImage(Main.tlo,0	,0, Main.width, Main.height,null);

	}
	
	
	
	public void rysujPaletke(Graphics g)
	{
		g.setColor(Color.black);
		paletka.x = x-50;
		paletka.y = y;
		
		//g.fillRect(paletka.x, paletka.y, 100, 10);
		 paleta = new Rectangle(paletka.x, 600, paletaW, 10);
		//g.fillRect(paleta.x, 600, paleta.width, paleta.height);
		g.drawImage(deska, paleta.x, 600,paleta.width, paleta.height, null);
		bonus.czyInterakcjaBonus();
		
		if(kulka.intersects(paleta))
		{
//			if((x+ Kulka.xKulki >= paleta.x) && (y+ Kulka.yKulki >= paleta.y-1000) )
//			{
//			if(czyInterakcja)
//			{
//				
//					Kulka.dx = -Kulka.dx;
//					Kulka.dy = -Kulka.dy;
//					Kulka.dxKonfiguracja = Math.abs(Kulka.dxKonfiguracja-360);
//					czyInterakcja = false;
//					System.out.println(-Kulka.dxKonfiguracja);
//				
//					
//			}
//			
//			else
//			{
//				czyInterakcja = true;
//			}
//			
//			
//			}
			
			
//			Kulka.dy=-Kulka.dy;
//			if (((kulka.x<=(paleta.x+paleta.width/2)) && (Kulka.dx>0)) || ((kulka.x>(paleta.x+paleta.width/2)) && (Kulka.dx<0)))
//			{
//				Kulka.dx=-Kulka.dx;
//			}
//			
			
			
			
			
			
			
			
			
			//xk-wsp�rz�dne kulki
			//xp-wsp�rz�dne paletki
			//width-szeroko�� paletki
			//dx,dy-przesuni�cie paletki
			//reszta to zmienne pomocnicze :)

			
			
			
			
					
			double angle;								//zmienna kata odbicia kulki
			double pom=kulka.x-paleta.x;							//obliczenie miejsca uderzenia kulki
			boolean spr=false;							//zmienna sprawdzajaca ktora polowa paletki
			
			
			if (pom>paleta.width/2) {spr=true;pom=paleta.width-pom;}				//sprawdzenie ktora polowa paletki
			
			
			
			if(pom==paleta.width/2) Kulka.dx=0.0;						//dla odbicia 90st sie pierdzieli takze 0.0 dla dx
			else {
				angle=(pom*(75/(paleta.width/2))+30.0)*(Math.PI/180.0);		//obliczenie kata odbicia kulki
				Kulka.dx=Math.sqrt(2.0/(1.0+Math.pow(Math.tan(angle),2)));		//obliczenie dx dla kazdego kata roznego w zakresie 15-89st
			}
			
			
			Kulka.dy=Math.sqrt(2-(Kulka.dx*Kulka.dx));						//obliczenie dy na podstawie dx
			Kulka.dy=-Kulka.dy;
			if (spr==false) Kulka.dx=-Kulka.dx;							//zmiana dx i dy w zaleznosci od trafionej polowki
		}
			
	}
	
	public void rysujKulke(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		for(int i = 0; i<listaKulek.size(); i++)
		{
			g.setColor(Color.white);
			a = ((Kulka)listaKulek.get(i)).x;
			b = ((Kulka)listaKulek.get(i)).y;
			
			kulka.x = a;
			kulka.y = b;
			//g2.fillOval((int)kulka.x, (int)kulka.y,	 (int)kulka.width	, (int)kulka.height);	
			g2.drawImage(pilka, (int)kulka.x, (int)kulka.y,	 (int)kulka.width	, (int)kulka.height, null);
		}
	}
	
	
	
	
	public void rysujGdyZero(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		if(liczbaZyc==0)
		{
			g2.setColor(Color.white);
			g2.setFont(new Font("Arial", Font.BOLD, 50));
			g2.drawString("KONIEC GRY PRZEGRA�E� !!!", 300, 500);
			g2.drawString("Uzyska�e� :"+score+" punkt�w", 395, 600);
			watekKulki.stop();
			
		}
		else if(licz == 96)
		{
			g2.setColor(Color.white);
			g2.setFont(new Font("Arial", Font.BOLD, 50));
			g2.drawString("KONIEC GRY WYGRA�E� !!!", 300, 500);
			g2.drawString("Uzyska�e� :"+score+" punkt�w", 395, 600);
			watekKulki.stop();
		}
		
		
	}
	
	
	void rysujNapisy(Graphics g) 
	{
		
	
		interakcja();
		g.setColor(Color.white);
		g.setFont(new Font("score : "+score, 200, 50));
		
		g.drawString("score : "+score, 200	, 50);
		g.drawString(menu2.getText(), 30, 50);
		
		
		
		//g.drawString(menu.getText(), 20, 50);
		
		
		
		
	
		if(isRunning == false)
		{
			g.setColor(Color.white);
			g.drawString("Naci�nij Spacje By Rozpocz��", 300, 550);
		}
		
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		super.paint(g2);
		rysujTlo(g2);
		int jaki = 1;
		if(licz >= 95)
		{
			jaki = 2;
		}
			if(jaki==1)
			{
				level_1.rysujBloki(g2,jaki);
			}
			if(jaki==2)
			{
				level_2.rysujBloki(g2,jaki);
			}
			
		if(spiacy == true)
		{
			rysuj_3_2_1(g2, "Czekaj 3 sekundy");
		}
		
		
		rysujGdyZero(g2);
		rysujKulke(g2);
		rysujNapisy(g2);
	
		rysujPaletke(g2);
		
		rysujZycia(g2);
		if(listProstokatow.get(94).x == -100)
		{
			rysujBonusSerce(g2);
		}
		if(listProstokatow.get(88).x == -100)
		{
			rysujBonusPaletka(g2);
		}
		if(listProstokatow.get(90).x == -100)
		{
			rysujBonusCzaszki(g2);
		}
		if(listProstokatow.get(84).x == -100)
		{
			rysujBonusNiespodzianka(g2);
		}

		
	
	}
	public void rysuj_3_2_1(Graphics g, String co)
	{
		g.setFont(new Font(co, Font.BOLD, 40));
		g.drawString(co, 550, 50);
	}
	
	public void rysujBonusSerce(Graphics g)
	{
		bonus.ruszBonusSerce();
		
		yys = bonus.yb;
		
		bonus_serc.y = yys;
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(bonus_serce, (int)bonus_serc.x, (int)bonus_serc.y,	 bonus_serce.getWidth(null)	, bonus_serce.getHeight(null), null);	
	}
	
	public void rysujBonusPaletka(Graphics g)
	{
		bonus.ruszBonusPaletka();
		
		yyp = bonus.yp;
		
		bonus_palet.y = yyp;
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(bonus_paletka, (int)bonus_palet.x, (int)bonus_palet.y,	 bonus_paletka.getWidth(null)	, bonus_paletka.getHeight(null), null);	
	}
	
	public void rysujBonusCzaszki(Graphics g)
	{
		bonus.ruszBonusCzaszki();
		
		yyk = bonus.yc;
		
		bonus_czaszk.y = yyk;
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(bonus_czaszka, (int)bonus_czaszk.x, (int)bonus_czaszk.y,	 bonus_czaszka.getWidth(null)	, bonus_czaszka.getHeight(null), null);	
	}
	
	public void rysujBonusNiespodzianka(Graphics g)
	{
		bonus.ruszBonusNiespodzianka();
		
		yyn = bonus.yn;
		
		bonus_niespodzia.y = yyn;
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(bonus_niespodzianka, (int)bonus_niespodzia.x, (int)bonus_niespodzia.y,	 bonus_niespodzianka.getWidth(null)	, bonus_niespodzianka.getHeight(null), null);	
	}
	public void interakcja()
	{
		
		
		
		for(int i = 0; i<96; i++)
		{
			
			
			
			if(kulka.intersects(listProstokatow.get(i)))
			{
				if(isSound==true)
				{
					grajDzwiek();
				}
				listProstokatow.get(i).x = -100;
				listProstokatow.get(i).y = -100;
				double pomx = kulka.x-listProstokatow.get(i).x-41;
				double pomy = kulka.y-listProstokatow.get(i).y-1;
				
				if (pomx<0 && pomy<0) 
				{
					if (Kulka.dx<0 && Kulka.dy>0) Kulka.dy=-Kulka.dy;
					else if (Kulka.dx>0 && Kulka.dy<0) Kulka.dx=-Kulka.dx;
					else if (pomy>-10) Kulka.dx=-Kulka.dx;
					else Kulka.dy=-Kulka.dy;
				} 
				else if(pomx>0 && pomy<0) 
				{
					if (Kulka.dx>0 && Kulka.dy>0) Kulka.dy=-Kulka.dy;
					else if (Kulka.dx<0 && Kulka.dy<0) Kulka.dx=-Kulka.dx;
					else if (pomy>-10) Kulka.dx=-Kulka.dx;
					else Kulka.dy=-Kulka.dy;
				}
				else if (pomx<0 && pomy>0) 
				{
					if (Kulka.dx<0 && Kulka.dy<0) Kulka.dy=-Kulka.dy;
					else if (Kulka.dx>0 && Kulka.dy>0) Kulka.dx=-Kulka.dx;
					else if (pomy<10) Kulka.dx=-Kulka.dx;
					else Kulka.dy=-Kulka.dy;
				}
				else if (pomx>=0 && pomy>=0)
				{
					if (Kulka.dx>0 && Kulka.dy<0) Kulka.dy=-Kulka.dy;
					else if (Kulka.dx<0 && Kulka.dy>0) Kulka.dx=-Kulka.dx;
					else if (pomy<10) Kulka.dx=-Kulka.dx;
					else Kulka.dy=-Kulka.dy;
				}
				score+=100;
				licz++;
				
				if(czyKoniec == true)
				{
					liczOdbicia++;
				}
			}
			
			
		}
	}
	
	static class Bonus
	{
		public void ruszBonusSerce()
		{
			yb+=3;
		}
		public void ruszBonusPaletka()
		{
			yp+=3;
		}
		public void ruszBonusCzaszki()
		{
			yc+=3;
		}
		public void ruszBonusNiespodzianka()
		{
			yn+=3;
		}
		public void czyInterakcjaBonus()
		{
			if(liczOdbicia == 5)
			{
				czyKoniec = false;
				paletaW = 100;
			}
			if(bonus_serc.intersects(paleta))
			{
				liczbaZyc=3;
				bonus_serc.x = -100;
				bonus_serc.y = -100;
			}
			
			if(bonus_palet.intersects(paleta))
			{
				czyKoniec = true;
				paletaW = 200;
				bonus_palet.x = -100;
				bonus_palet.y = -100;
			}
			
			if(bonus_czaszk.intersects(paleta))
			{
				liczbaZyc--;
				bonus_czaszk.x = -100;
				bonus_czaszk.y = -100;
			}
			
			if(bonus_niespodzia.intersects(paleta))
			{
				 Main.tlo = (Image) new ImageIcon("k.jpg").getImage();
				bonus_niespodzia.x = -100;
				bonus_niespodzia.y = -100;
			}
			
		}
		
		static int xb = 1050;
		static int yb = 280;
		static int xp = 426;
		static int yp = 280;
		static int xc = 634;
		static int yc = 280;
		static int xn = 10;
		static int yn = 280;
	}
	static class Kulka 
	{
		
		public void ruszKulka(JPanel pojemnik)
		{
			
			
			
			Rectangle granice = pojemnik.getBounds();
			x+=dx*podstawa_Gry.speed; 
			y+=dy*podstawa_Gry.speed;
			
			
			
			
			if(y + yKulki >= granice.getMaxY())
			{
				
				podstawa_Gry.liczbaZyc--;
				Kulka.x = 550;
				Kulka.y = 500;
				ostatniaZmiana = System.nanoTime();
				spiacy = true;
				
				
				
				
				
				
				
			}
			
			
			if(x+xKulki >= granice.getMaxX())
			{
				x = (int)(granice.getMaxX()-xKulki);
				dx = -dx;
			}
			
			if(y < granice.getMinY())
			{
				y = (int)granice.getMinY();
				dy = -dy; // k�t 0.28
				
				
			}
			
			if(x < granice.getMinX())
			{
				x = (int)granice.getMinX();
				dx = -dx;
			}
			
			 		
		}
		
		static double dxKonfiguracja = 315;
		
	static double g = 1.000000000000005;
	static int x = 550;
	static int y = 500;
	static double dx =  Math.cos(dxKonfiguracja*(Math.PI/180));
	static double dy =  Math.sin(dxKonfiguracja*(Math.PI/180));
	static double xKulki = (int) podstawa_Gry.kulka.getWidth();
	static double yKulki = (int) podstawa_Gry.kulka.getHeight();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	public void startAnimation()
	{
		this.addKulka();
	}
		
	public class KulkaRunnable implements Runnable
	{
		
		@Override
		public void run() {
	
			
			while(!watekKulki.isInterrupted())
			{
			if(spiacy == true)
			{
				try {
					watekKulki.sleep(2000);
					System.out.println(watekKulki.getState());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			spiacy = false;
				
				
				repaint();
				for(int j=0;j<1; j++)
				{
					
					
				
				if(isPaused == true)
				{
					try {
						watekKulki.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else
				{
					((Kulka) listaKulek.get(j)).ruszKulka(ten);
				}
					
				
				//this.paint(this.getGraphics());//pobranie kontekstu graficznego
			repaint();
				try {
				watekKulki.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
				}
			}
			
			
			
			
			
		}
		
	}
}

	
	
	class Rysuj extends JPanel
	{
		
		
	
		
		
		static JLabel label = new JLabel("SIEMA");
	
		
		public Rysuj()
		{
			super();
			
			MouseMysz();
		}
		
		public void MouseMysz()
		{
			
			Main.frame.addMouseMotionListener(new MouseAdapter() 
			{
				
				
				public void mouseMoved(MouseEvent e) {
					podstawa_Gry.x = e.getX();
					podstawa_Gry.y = e.getY();
				
				}
			});
			
			
			
		}

	}
	
	
	


