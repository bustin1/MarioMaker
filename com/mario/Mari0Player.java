package com.mario; 
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Mari0Player {
	private static final int HEIGHT = 500;
	private static final int WIDTH = 2500;
	private static Mari0Frame panel;
	private static JFrame frame;
	public static void main(String[] args) {
		int map[][] = new int[HEIGHT/20][WIDTH/20];
		int numOfMysBlock = 0;
		int numOfGoomba = 0;
		int numOfCannons = 0;
		ArrayList<Integer> posx = new ArrayList<Integer>();
		ArrayList<Integer> posy = new ArrayList<Integer>();
		//ArrayList<Integer> posx2 = new ArrayList<Integer>();
		//ArrayList<Integer> posy2 = new ArrayList<Integer>();
		try{// read the blocks
			Scanner in = new Scanner(new File("com/mario/Mari0Map"));
			int x = 0;
			int y = 0;
			while(in.hasNextInt()){
				map[x][y] = in.nextInt();
				if(map[x][y] == 16){
				    numOfGoomba++;
				    posx.add(y*20);
				    posy.add(x*20);
				}
				if(map[x][y] == 18 || map[x][y] == 19){
					numOfCannons++;
				}
				numOfMysBlock++;
				y++;
				if(y == map[0].length){
					y = 0;
					x++;
				}
				
			}
		}catch(IOException e){
			System.out.println("file has trouble reading from it so i don't know . . . fix it!");
		}
		String[] listImages = {"com/mario/marioImages/mario0.png",
				"com/mario/marioImages/mario1.png",
				"com/mario/marioImages/mario2.png"
		};//images of mario running
		Mario mario = new Mario("Justin", "com/mario/marioImages/standingmario.png", listImages);
		FireBall[] ball = new FireBall[10];
		for(int i=0; i<10; i++){
			ball[i] = new FireBall("com/mario/marioImages/fireball.png", mario);
		}
		panel = new Mari0Frame(map, mario, ball, posx, posy, numOfCannons);
		panel.setDim(800, HEIGHT);
		frame = new JFrame();
		frame.add(panel);
		frame.setSize(800, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addComponentListener(new ComponentAdapter() {//painting different dimensions
		    public void componentResized(ComponentEvent e) {
		    	int w = (int)frame.getBounds().getWidth();
		    	int h = (int)frame.getBounds().getHeight();
            	panel.setDim(w,h);
		    }
		});
	}

}
