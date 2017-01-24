package Arkanoid_A;

import Arkanoid_A.*;


import java.applet.Applet;
import java.awt.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;


import javax.swing.*;
import javax.tools.Tool;

public class Main {
	
	static JFrame frame = new JFrame(); // tworzenie ramki
	public static int width, height; // zmienne przechowywujace wysokosc i szerokosc
	static Image tlo = new ImageIcon("tlo.jpg").getImage(); // obiekt a dokladniej obraz bedacy tlem
	
	 
	 static OknoStartowe okno = new OknoStartowe(); // tworzenie obiektu okna startowego
public Main()
{
	
	width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(); // szerokosc
	height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-40; // wysokosc

	
	Image image = new ImageIcon("serce.png").getImage(); // wprowadzenie ikony programu
	frame.setIconImage(image); // dodanie ikony ekranu do ramki

	frame.pack(); // spakowanie ramki przy tej instrukcji program dzia³a szybciej
	frame.setVisible(true); // ustawienie widzialnoœci ramki
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // operacja prawid³owo zamykaj¹ca ramke
	frame.setSize(width,height); // ustawienie wysokosci i szerokosci ramki szerokoscia i wysokoscia ekranu
	frame.setResizable(true); // ustawienie rozszerzalnosci ramki
	//frame.add(blok);
	//blok.setOpaque(true);
	
	

frame.add(okno); // dodanie okna startowego do ramki
	
	
}



	public static void main(String[] args) 
	{
		
		Main grafiki = new Main();	// g³ówny obiekt gry odpowiedzialny za jej dzialanie
		
		
		
		
		
		 
	
	}	
}
