 package com.mario;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.io.*;
public class Mari0Frame extends Mari0Panel implements KeyListener, ActionListener{
    private Player mario;//mario duh
    //private double speedx;//speed of mario in x direction
   // private double gravity;//gravity of mario
    private int jump;//flag if mario jumped
    //private int tStep;
    //private String[] runImage;//list of running images
    private int counter;//counts the interval between drawing mario running
    private int index;//index of mario running
    private int width;//width of the screen
    private int height;//height of the screen
    private int left;//signifies mario left
    private int right;//signifies mario right
    private boolean fireball;
    private FireBall[] ball;
    private int numOfGoomba;
    private int ballCount;
    private int refire;
    private double[] xGo;
    private double[] yGo;
    private double[] direction;
    private double[] gravity;
    private double speedx;
    private BufferedImage imageG;
    public Mari0Frame(int[][] map, Player player, FireBall[] ball, ArrayList<Integer> xpos, ArrayList<Integer> ypos, int y) {
        super(map);
        direction = new double[xpos.size()];
        ballCount = 0;
        refire = 0;
        mario = player;
        gravity = new double[xpos.size()];
        jump = 0;
        speedx = 0;
        fireball = false;
        counter = 0;
        index = 0;
        left = 0;
        right = 1;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer t = new Timer(5, this);
        t.start();
        this.ball = ball;
        xGo = new double[xpos.size()];
        yGo = new double[ypos.size()];
       for(int i=0; i<xpos.size(); i++){
    	   yGo[i] = ypos.get(i);
    	   xGo[i] = xpos.get(i);
    	   direction[i] = 1.4;
       }
        passCor(xGo, yGo, direction, gravity);
        setPlayer(mario, this.ball, xpos.size(), 0, y);
        mario.setPanel(this);
        try {
			imageG = ImageIO.read(new File("com/mario/marioImages/goomba.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        BufferedImage image;
        try{
			image = ImageIO.read(new File(ball[0].getImage()));
			for(int i=0; i<ballCount; i++){
			    ball[i].move();
			    g2.drawImage(image, (int)ball[i].getX(), (int)ball[i].getY(), ball[i].getH(), ball[i].getH(), null);
			}
			for(int i=0; i<xGo.length; i++){
				 g2.drawImage(imageG, (int)xGo[i], (int)yGo[i], 20, 20, null);
			}
			if(mario.getSx() > 0){//mario running right
				if(mario.getJump() == 1){
					image = ImageIO.read(new File(mario.getJumpImage()));
				}else{
					image = ImageIO.read(new File(mario.getCurrentRunImage()[index]));
				}
			    g2.rotate(mario.getRotate(), (int)mario.getX()+10, (int)mario.getY()+mario.getH()/2);
			    g2.drawImage(image, (int)mario.getX(), (int)mario.getY(), mario.getW(), mario.getH(), null);
			    right = 1;
			    left = 0;
			}else if(mario.getSx() < 0){//mario running left
				if(mario.getJump() == 1){
					image = ImageIO.read(new File(mario.getJumpImage()));
				}else{
					image = ImageIO.read(new File(mario.getCurrentRunImage()[index]));
				}
			    g2.rotate(mario.getRotate(), (int)mario.getX()+10, (int)mario.getY()+mario.getH()/2);
			    g2.drawImage(image, (int)mario.getX()+20, (int)mario.getY(), -mario.getW(), mario.getH(), null);
			    left = 1;
			    right = 0;
			}else if(right == 1){//mario standing right
				if(mario.getJump() == 1){
					image = ImageIO.read(new File(mario.getJumpImage()));
				}else{
				    image = ImageIO.read(new File(mario.getstandingImage()));
				}
			    g2.rotate(mario.getRotate(), (int)mario.getX()+10, (int)mario.getY()+mario.getH()/2);
			    g2.drawImage(image, (int)mario.getX(), (int)mario.getY(), mario.getW(), mario.getH(), null);
			}else if(left == 1){//mario standing left
				if(mario.getJump() == 1){
					image = ImageIO.read(new File(mario.getJumpImage()));
				}else{
				    image = ImageIO.read(new File(mario.getstandingImage()));
				}
			    g2.rotate(mario.getRotate(), (int)mario.getX()+10, (int)mario.getY()+mario.getH()/2);
			    g2.drawImage(image, (int)mario.getX()+20, (int)mario.getY(), -mario.getW(), mario.getH(), null);
			}
			//count the speed of changing sprites
			counter++;
			if(counter%6 == 0){
			    index++;
			    if(index == 3){
			        index = 0;
			        counter = 0;
			    }
			}
			for(int i=0; i<xGo.length; i++){
				 gravity[i] += .3;
			     if(gravity[i] > 5){
			         gravity[i] = 5;
			     }
			     yGo[i] += gravity[i];
			     if(mario.getX() > 3*width/4 && mario.getSx() > 0){
			         xGo[i] -= (mario.getSx()-direction[i]);
			     }else if(mario.getX() < width/4 && mario.getSx() < 0){
			         xGo[i] -= (mario.getSx()-direction[i]);
			     }else{
			         xGo[i] += direction[i];
			     }
			}
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void keyPressed(KeyEvent e){
        int code = e.getExtendedKeyCode();
        
        if(code == KeyEvent.VK_UP && mario.getJump() == 0 && mario.getSy() < 2){
        	System.out.println(mario.getY());
            mario.setSy(-8);
            mario.setJump(1);
            mario.move(width , height);
            if(mario.getH() == 30){
            	mario.setJumpImage("com/mario/marioImages/firemariojump.png");
            }
            else {
            	mario.setJumpImage("com/mario/marioImages/mariojump.png");
            }
        }
        else if(code == KeyEvent.VK_LEFT && mario.isLeft()){
            mario.setSx(-3);
            //System.out.println(mario.getX() +", " + mario.getY());
        }
        else if(code == KeyEvent.VK_RIGHT && mario.isRight()){
            mario.setSx(3);
            System.out.println(mario.getX() +", " + mario.getY());
        }
        if(code == KeyEvent.VK_F && mario.getH() == 30 && refire == 0){
            ball[ballCount].setX(mario.getX());
            ball[ballCount].setY(mario.getY());
            if(left == 1){
                ball[ballCount].direction(-2);
            }
            else if(right == 1){
                ball[ballCount].direction(2);
            }
            ballCount++;
            if(ballCount == 10){
            	ballCount = 0;
            }
            refire = 1;
        }
       /* if(code == KeyEvent.VK_RIGHT){
        	System.out.println(mario.getY() + " but right");
        }
        if(code == KeyEvent.VK_LEFT){
        	System.out.println(mario.getY() + " but left");
        }*/
    }
    public void keyReleased(KeyEvent e){
        if(!(mario.getSy() < .4 && mario.getSy() > .2)){
        	if(mario.getSx() < 0){
        		mario.setSx(-2);
        		mario.keepMove(true);
        	}else if(mario.getSx() > 0){
        		mario.setSx(2);
        		mario.keepMove(true);
        	}
        }else {
        	mario.keepMove(false);
        	mario.setSx(0);
        }
        mario.setRight(true);
        mario.setLeft(true);
        
    }
    
    public void keyTyped(KeyEvent e){}
    
    public void actionPerformed(ActionEvent e) {
    	mario.move(width, height);
    	if(refire >= 1){
    		refire++;
    		if(refire == 70){
    			refire = 0;
    		}
    	}
        repaint(0, 0, width, height);
        //System.out.println(mario.isRight());
    }
    
    public void setDim(int w, int h){
        width = w;
        height = h;
        for(FireBall ball : this.ball){
        	ball.setDimension(width, height);
        }
        super.setDim(w, h);
    }
}
