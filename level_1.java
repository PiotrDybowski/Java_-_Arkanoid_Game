package Arkanoid_A;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class level_1 {
	
	static int jaki_level = 1;
	static Point point = new Point();
	static Color []k={Color.red, Color.green, Color.pink, Color.yellow, Color.blue, Color.gray, Color.ORANGE, Color.getHSBColor(184, 3, 100), Color.black, Color.white };
	static int kolor = 0;
	
	static Color wezKolor;
	
	
	
	public static void rysujBloki(Graphics g, int jaki)
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
			
				
				podstawa_Gry.listProstokatow.add(r);
				
				przesunx +=104;			
			}
		}
		for(int a=0; a<96; a++)
		{
				kolor=(a%6);
				g.setColor(k[kolor]);	
				g.fillRect(podstawa_Gry.listProstokatow.get(a).x, podstawa_Gry.listProstokatow.get(a).y, podstawa_Gry.listProstokatow.get(a).width, podstawa_Gry.listProstokatow.get(a).height);
				System.out.println(a+" ( " + podstawa_Gry.listProstokatow.get(a).x + " , "+ podstawa_Gry.listProstokatow.get(a).y + " )");
				wezKolor = g.getColor();
				//System.out.println(wezKolor);
				
				
				for (int i = 0; i < 20/4; i++) {
					g.setColor(k[kolor].darker());
					g.drawLine(podstawa_Gry.listProstokatow.get(a).x+i, podstawa_Gry.listProstokatow.get(a).y+20-i, podstawa_Gry.listProstokatow.get(a).x+100-1, podstawa_Gry.listProstokatow.get(a).y+20-i);
					g.drawLine(podstawa_Gry.listProstokatow.get(a).x+100-1-i, podstawa_Gry.listProstokatow.get(a).y+i, podstawa_Gry.listProstokatow.get(a).x+100-1-i, podstawa_Gry.listProstokatow.get(a).y+20);
					g.setColor(k[kolor].brighter());
					g.drawLine(podstawa_Gry.listProstokatow.get(a).x, podstawa_Gry.listProstokatow.get(a).y+i, podstawa_Gry.listProstokatow.get(a).x+100-1-i, podstawa_Gry.listProstokatow.get(a).y+i);
					g.drawLine(podstawa_Gry.listProstokatow.get(a).x+i, podstawa_Gry.listProstokatow.get(a).y+20-i, podstawa_Gry.listProstokatow.get(a).x+i,podstawa_Gry.listProstokatow.get(a).y);
				}	
		}
		
	}
}
