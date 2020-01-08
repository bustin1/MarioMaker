package com.mario; 

public class Animation {
	public static double bump(double bumpY){
		bumpY++;
		return bumpY;
	}
	public static double popUp(double popUpY){
		popUpY--;
		return popUpY;
	}
	public static boolean detection(double mariox, double marioy, int x, int y, double dx, Player mario){
		//marioy += .05;
		//marioy = marioy-marioy%.30;
		return (int)Math.abs(mariox - (y+dx)) < 20 && (int)Math.abs((mario.getH()/2+marioy) - (x+10)) < mario.getH()/2+10;
	}
	public static boolean detection7(double mariox, double marioy, double mariow, double marioh, double x, double y, double w, double h){
		return (int)Math.abs(mariox+mariow - (x+w)) < mariow+w && (int)Math.abs(marioy+marioh - (y+h)) < marioh+h;
	}
}
