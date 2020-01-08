package com.mario; 

import java.awt.event.*;
import java.io.*;

import javax.swing.*;
public class Mari0MapEditor {
	private static int block;
	private final static int WIDTH = 2500;
	private final static int HEIGHT = 500;
	private static int[][] map = new int[HEIGHT/20][WIDTH/20];
	private static Mari0Panel panel;
	private static int dx = 0;
	public static void main(String[] args) {
		try{
			int y = 0;
			String line = null;
			FileReader file = new FileReader("com/mario/Mari0Map");
			BufferedReader buffer = new BufferedReader(file);
			while((line = buffer.readLine()) != null){
				String[] token = line.split(" ");
				for(int x=0; x<token.length; x++){
					map[y][x] = Integer.parseInt(token[x]);
				}
				y++;
			}
		}
		catch(FileNotFoundException e){
			System.out.println("file not found");
		}
		catch(IOException e){
			System.out.println("problem reading file");
		}
		JFrame frame = new JFrame();
		panel = new Mari0Panel(map);
		frame.add(panel);
		frame.setSize(800, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		panel.setPlayer(new Mario(), new FireBall[1], 1, 1, 1);
		class KeysList implements KeyListener{
			public void keyPressed(KeyEvent e){
				int code = e.getExtendedKeyCode();
				System.out.println("keyres");
				if(code == e.VK_LEFT){
					panel.moveFrame(5);
					dx += 5;
				}
				else if(code == e.VK_RIGHT){
					panel.moveFrame(-5);
					dx -= 5;
				}
				if(code == e.VK_0){
					block = 0;
				}
				else if(code == e.VK_1){
					block = 1;
				}
				else if(code == e.VK_2){
					block = 2;
				}
				else if(code == e.VK_3){
					block = 3;
				}
				else if(code == e.VK_4){
					block = 4;
				}
				else if(code == e.VK_5){
					block = 5;
				}
				else if(code == e.VK_6){
					block = 6;
				}
				else if(code == e.VK_7){
					block = 7;
				}
				else if(code == e.VK_8){
					block = 8;
				}
				else if(code == e.VK_9){
					block = 9;
				}
				else if(code == e.VK_Q){
					block = 10;
				}
				else if(code == e.VK_W){
					block = 11;
				}
				else if(code == e.VK_E){
					block = 12;
				}
				else if(code == e.VK_R){
					block = 13;
				}
				else if(code == e.VK_T){
					block = 14;
				}
				else if(code == e.VK_Y){
					block = 15;
				}
				else if(code == e.VK_U){
					block = 16;
				}
				else if(code == e.VK_I){
					block = 17;
				}
				else if(code == e.VK_O){
					block = 18;
				}
				else if(code == e.VK_P){
					block = 19;
				}
			}
			
			public void keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){}
		}
		KeyListener keys = new KeysList();
		frame.addKeyListener(keys);
		class MouseList implements MouseListener{
			private Mari0Panel panel;
			public MouseList(Mari0Panel panel){
				this.panel = panel;
			}
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {
				System.out.println("mouse pressed, block is "+block);
				map[(int)(e.getY()-30)/20][(int)(e.getX()-dx-15)/20] = block;
				panel.repaint();
			}

			public void mouseReleased(MouseEvent e) {}
			
		}
		MouseListener list = new MouseList(panel);
		frame.addMouseListener(list);
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	System.out.println("Closed");
            	close();
            }
        });
	}
	public static void close(){
		try{
			PrintWriter write = new PrintWriter("com/mario/Mari0Map", "UTF-8");
			for(int x=0; x<map.length; x++){
				for(int y=0; y<map[0].length; y++){
					if(map[x][y] == 0 && map[x-1][y] == 18){
						write.print(1+" ");
					}else{
						write.print(map[x][y]+" ");
					}
				}
				write.println();
			}
			write.close();
		}catch(IOException e){
			System.out.println("File not found");
		}
	}
}
