package Arkanoid_A;


import Arkanoid_A.*;

public class Glowny_Watek extends Thread{
	public static podstawa_Gry game;
	public Glowny_Watek(podstawa_Gry podstawa_Gry) {
		this.game = podstawa_Gry;
	}
	
	public void run() {
		//Init
		game.isRunning = true;
		game.isPaused = true;
		game.ostatniaZmiana = System.nanoTime();
		//Gameloop
		while (game.isRunning) {
			
				if (game.isPaused) {
					game.ostatniaZmiana = System.nanoTime();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					
					game.ostatniaZmiana = System.nanoTime();
					try {
						Thread.sleep((long)(1000.0));
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			
			}
		}
		//Exit

	public void start() {
		// TODO Auto-generated method stub
		
	}
}
