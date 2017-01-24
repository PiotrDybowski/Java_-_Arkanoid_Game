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
import Arkanoid_A.Glowny_Watek;

public class Blok extends JPanel{
	
	
	static int a;
	static int b;
	static int liczbaZyc = 3;
	static double speed = 4;
	static int score = 0;
	public static boolean isRunning = false, isPaused = true , dzialanie = true;
	public long ostatniaZmiana;
	
	static int x;
	static int y;
	static int licz = 0;
	
	static int kolor = 0;
	boolean war = false;
	int licznik = 0;
	static Color []k={Color.red, Color.green, Color.pink, Color.yellow, Color.blue, Color.gray, Color.ORANGE, Color.getHSBColor(184, 3, 100) };
	
	static boolean czyInterakcja = true;
	
	static int tick = 0;
	static ArrayList<Rectangle> listProstokatow = new ArrayList<Rectangle>();
	static ArrayList listaKulek = new ArrayList();
	static Point paletka = new Point(0, 0);
	static Rectangle paleta ;
	Point point = new Point();
	JPanel ten = this;
	Glowny_Watek gameThread;
	public static Ellipse2D.Double kulka = new Ellipse2D.Double(a,b, 18	, 18);
	public static Rectangle2D.Double podloga = new Rectangle2D.Double(Main.width-1290, 940, Main.width, 10);

	Thread watekKulki = new Thread(new KropelaRunnable());
	
	Image serce1 = new ImageIcon("serce.png").getImage();
	Image serce2 = new ImageIcon("serce.png").getImage();
	Image serce3 = new ImageIcon("serce.png").getImage();
	
	Image s1 = new ImageIcon("1.png").getImage();
	Image s2 = new ImageIcon("6.png").getImage();
	Image deska = new ImageIcon("deska.png").getImage();
	Image pilka = new ImageIcon("pilka.png").getImage();
	Image image;
	JButton buttonMenu = new JButton("MENU");
	
	
	public Blok()
	{
		Klawiatura();
		Rysuj rysuj = new Rysuj();
		
		buttonMenu.setOpaque(true);
	
		buttonMenu.setPreferredSize(new Dimension(100, 100));
		this.add(buttonMenu);
		
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
		if (gameThread != null) if (gameThread.isAlive()) gameThread.interrupt();
		
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
	

	public void addKropelka()
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
//	public void rysujSerca(Graphics g)
//	{
//		try {
//			image = new ImageIcon(new URL("http://www.gify.net/data/media/373/serce-ruchomy-obrazek-0879.gif")).getImage();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		g.drawImage(image, 500, 500, null);
//	}
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
		 paleta = new Rectangle(paletka.x, 600, 100, 10);
		//g.fillRect(paleta.x, 600, paleta.width, paleta.height);
		g.drawImage(deska, paleta.x, 600,paleta.width, paleta.height, null);
		
		
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
			
			
			
			
			
			
			
			
			//xk-wspó³rzêdne kulki
			//xp-wspó³rzêdne paletki
			//width-szerokoœæ paletki
			//dx,dy-przesuniêcie paletki
			//reszta to zmienne pomocnicze :)

			
			
			
			
					
			double pom=kulka.x-paleta.x;							//obliczenie miejsca uderzenia kulki
			boolean spr=false;							//zmienna sprawdzajaca ktora polowa paletki
			if (pom>paleta.width/2) {spr=true;pom=paleta.width-pom;}				//sprawdzenie ktora polowa paletki
			double angle;								//zmienna kata odbicia kulki
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
	
	public void rysujBloki(Graphics g)
	{
		
		Rectangle r ;
		point.setLocation(10, 40);
		
		 int przesunx = 0;
		 int przesuny = 0;
		 int rysx ;
		 int rysy;
		
		for(int i = 0;  i<8; i++)
		{
			przesuny +=30;
			przesunx = 0;
		
			for(int j=0; j<12; j++)
			{
				rysx = point.x+przesunx;
				rysy =  point.y+przesuny;
				
				r= new Rectangle(rysx,rysy, 100, 20);
				
				listProstokatow.add(r);
				
				przesunx +=104;			
			}
		}
		for(int a=0; a<96; a++)
		{
				kolor=a%8;
				g.setColor(k[kolor]);	
				g.fillRect(listProstokatow.get(a).x, listProstokatow.get(a).y, listProstokatow.get(a).width, listProstokatow.get(a).height);
				
				
				for (int i = 0; i < 20/4; i++) {
					g.setColor(k[kolor].darker());
					g.drawLine(listProstokatow.get(a).x+i, listProstokatow.get(a).y+20-i, listProstokatow.get(a).x+100-1, listProstokatow.get(a).y+20-i);
					g.drawLine(listProstokatow.get(a).x+100-1-i, listProstokatow.get(a).y+i, listProstokatow.get(a).x+100-1-i, listProstokatow.get(a).y+20);
					g.setColor(k[kolor].brighter());
					g.drawLine(listProstokatow.get(a).x, listProstokatow.get(a).y+i, listProstokatow.get(a).x+100-1-i, listProstokatow.get(a).y+i);
					g.drawLine(listProstokatow.get(a).x+i, listProstokatow.get(a).y+20-i, listProstokatow.get(a).x+i,listProstokatow.get(a).y);
				}	
		}
	}
	
	@SuppressWarnings("deprecation")
	public void rysujGdyZero(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		if(liczbaZyc==0)
		{
			g2.setColor(Color.white);
			g2.setFont(new Font("Arial", Font.BOLD, 50));
			g2.drawString("KONIEC GRY PRZEGRA£EŒ !!!", 300, 500);
			g2.drawString("Uzyska³eœ :"+score+" punktów", 395, 600);
			watekKulki.stop();
			
		}
		else if(licz == 96)
		{
			g2.setColor(Color.white);
			g2.setFont(new Font("Arial", Font.BOLD, 50));
			g2.drawString("KONIEC GRY WYGRA£EŒ !!!", 300, 500);
			g2.drawString("Uzyska³eœ :"+score+" punktów", 395, 600);
			watekKulki.stop();
		}
	}
	
	
	void rysujNapisy(Graphics g) 
	{
		
	
		interakcja();
		g.setColor(Color.white);
		g.setFont(new Font("score : "+score, 200, 50));
		g.drawString("score : "+score, 200	, 50);
	
		if(isRunning == false)
		{
			g.setColor(Color.white);
			g.drawString("Naciœnij Spacje By Rozpocz¹æ", 300, 550);
		}
		
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		super.paint(g2);
		rysujTlo(g2);
		rysujBloki(g2);
		rysujGdyZero(g2);
		rysujKulke(g2);
		rysujNapisy(g2);
		rysujPaletke(g2);
		
		rysujZycia(g2);
		
	
	}
	
	
	
	public void interakcja()
	{
		if(kulka.intersects(podloga))
		{
			liczbaZyc--;
			Kulka.x = 550;
			Kulka.y = 500;
			
		}
		
		
		for(int i = 0; i<96; i++)
		{
			if(kulka.intersects(listProstokatow.get(i)))
			{
				grajDzwiek();
				listProstokatow.get(i).x = -100;
				listProstokatow.get(i).y = -100;
				if(tick%2==0)
				{
					Kulka.dx = -Kulka.dx;
					
				}
				else
				{
					Kulka.dy = -Kulka.dy;
				}
				tick++;
			//System.out.println(Blok.licz++);
				score+=100;
				licz++;
			}
		}
	}
	
	
	
	
	public void startAnimation()
	{
		this.addKropelka();
	}
		
	public class KropelaRunnable implements Runnable
	{
		
		@Override
		public void run() {
	
			
			while(!Thread.currentThread().isInterrupted())
			{
			
				
				
				repaint();
				for(int j=0;j<1; j++)
				{
					
					
				((Kulka) listaKulek.get(j)).ruszKropelka(ten);
				
				
				//this.paint(this.getGraphics());//pobranie kontekstu graficznego
			repaint();
				try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
				}
			}
			
			
			
			
			
		}
		
	}
}

	class Kulka
	{
		
		public void ruszKropelka(JPanel pojemnik)
		{
			
			
			
			Rectangle granice = pojemnik.getBounds();
			x+=dx*Blok.speed; 
			y+=dy*Blok.speed;
			
			
			
			
			if(y + yKulki >= granice.getMaxY())
			{
				Blok.liczbaZyc--;
				Kulka.x = 550;
				Kulka.y = 500;
			}
			
			if(x+xKulki >= granice.getMaxX())
			{
				x = (int)(granice.getMaxX()-xKulki);
				dx = -dx;
			}
			
			if(y < granice.getMinY())
			{
				y = (int)granice.getMinY();
				dy = -dy; // k¹t 0.28
				
				
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
	static double xKulki = (int) Blok.kulka.getWidth();
	static double yKulki = (int) Blok.kulka.getHeight();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
					Blok.x = e.getX();
					Blok.y = e.getY();
				
				}
			});
			
		}

	}
	
	
	


