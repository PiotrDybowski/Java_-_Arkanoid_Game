package Arkanoid_A;

import java.awt.*;
import javax.swing.*;

import Arkanoid_A.Blok;

public class Interakcje {
	
	static int tick = 0;
	
	
	
	
	static void interakcje()
	{
		
		if(Blok.kulka.intersects(Blok.podloga))
		{
			Blok.liczbaZyc--;
			Kulka.x = 550;
			Kulka.y = 500;
			
		}
		
		
		
		for(int i = 0; i<96; i++)
		{
			if(Blok.kulka.intersects(Blok.listProstokatow.get(i)))
			{
				Blok.listProstokatow.get(i).x = -100;
				Blok.listProstokatow.get(i).y = -100;
				
					Kulka.dx = -Kulka.dx;
					Kulka.dy = -Kulka.dy;
			
				tick++;
			//System.out.println(Blok.licz++);
				Blok.score+=100;
				Blok.licz++;
			}
		}
		
		
//		for(int a=0; a<96; a++)
//		{
//			
//			
//		if(Blok.listProstokatow.get(a).intersects(Blok.paleta))
//		{
//				Blok.listProstokatow.get(a).x = -100;
//				Blok.listProstokatow.get(a).y = -100;
//			
//			System.out.println(Blok.licz++);
//				Blok.score+=100;	
//		}
//		
//		}
		
	}
}
