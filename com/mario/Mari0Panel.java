package com.mario; 
import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;
import java.util.concurrent.TimeUnit;
public class Mari0Panel extends JPanel{
    private int[][] map;
    private BufferedImage[] image;
    private Player mario;
    private FireBall[] ball;
    private int width;
    private int height;
    private double dx = 0;
    private int animation;
    private double bumpY = 0;
    private double popUpY = 0;
    private double permanantY = -20;
    private int[][] mysBlock;
    private int whichMys;
    private int flag;
    private int invince;
    private int coinCount;
    private int lives;
    private double flagDrop;
    private boolean over;
    private double[] gx;
    private double[] gy;
    private double[] direction;
    private double[] gravity;
    private boolean[] dead;
    private double[] bulletx;
    private double[] bulletdx;
    private double[] bullety;
    private int[] bulletTime;
    public Mari0Panel(int[][] map){
        this.map = map;
        over = false;
        mysBlock = new int[map.length][map[0].length];
        image = new BufferedImage[25];
        animation = 0;
        flag = 0;
        flagDrop = 0;
        invince = 0;
        coinCount = 0;
        lives = 3;
        try{
            image[0] = ImageIO.read(new File("com/mario/marioImages/dirtBlock.png"));
            image[1] = ImageIO.read(new File("com/mario/marioImages/ugBlock.png"));
            image[2] = ImageIO.read(new File("com/mario/marioImages/lgBlock.png"));
            image[3] = ImageIO.read(new File("com/mario/marioImages/dgBlock.png"));
            image[4] = ImageIO.read(new File("com/mario/marioImages/rgBlock.png"));
            image[5] = ImageIO.read(new File("com/mario/marioImages/0-0-gBlock.png"));
            image[6] = ImageIO.read(new File("com/mario/marioImages/1-0-gBlock.png"));
            image[7] = ImageIO.read(new File("com/mario/marioImages/0-1-gBlock.png"));
            image[8] = ImageIO.read(new File("com/mario/marioImages/1-1-gBlock.png"));
            image[9] = ImageIO.read(new File("com/mario/marioImages/1-1-d-gBlock.png"));
            image[10] = ImageIO.read(new File("com/mario/marioImages/0-1-d-gBlock.png"));
            image[11] = ImageIO.read(new File("com/mario/marioImages/1-0-d-gBlock.png"));
            image[12] = ImageIO.read(new File("com/mario/marioImages/0-0-d-gBlock.png"));
            image[13] = ImageIO.read(new File("com/mario/marioImages/mysBlock.png"));
            image[14] = ImageIO.read(new File("com/mario/marioImages/mushroom.png"));
            image[15] = ImageIO.read(new File("com/mario/marioImages/fireflower.png"));
            image[16] = ImageIO.read(new File("com/mario/marioImages/goomba.png"));
            image[17] = ImageIO.read(new File("com/mario/marioImages/coin.png"));
            image[18] = ImageIO.read(new File("com/mario/marioImages/pole.png"));
            image[19] = ImageIO.read(new File("com/mario/marioImages/flag.png"));
            image[20] = ImageIO.read(new File("com/mario/marioImages/cloud.png"));
            image[21] = ImageIO.read(new File("com/mario/marioImages/castle.png"));
            image[22] = ImageIO.read(new File("com/mario/marioImages/cannon.png"));
            image[23] = ImageIO.read(new File("com/mario/marioImages/bulletleft.png"));
            image[24] = ImageIO.read(new File("com/mario/marioImages/bullet.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        /*if(dx > 0){
        	//dx = 0;
        	mario.setDx(false);
        }else{
        	mario.setDx(true);
        }*/
        Graphics2D g2 = (Graphics2D)g;
        Color color = new Color(150, 150, 255);
        setBackground(color);
        int cannonCount = 0;
        if(invince >= 1){
        	invince++;
        	if(invince == 100){
        		invince = 0;
        	}
        }
        g2.drawImage(image[18], (int)(1825+dx), 250, 10, 230, null);
        g2.drawImage(image[19], (int)(1800+dx), (int)(250+flagDrop), 40, 30, null);
        g2.drawImage(image[20], (int)(500+dx), 200, 50, 40, null);
        g2.drawImage(image[20], (int)(1000+dx), 200, 50, 40, null);
        g2.drawImage(image[20], (int)(1500+dx), 200, 100, 80, null);
        g2.drawImage(image[21], (int)(2000+dx), 330, 150, 150, null);
        
        for(int i=0; i<bulletTime.length; i++){
        	if(bulletx[i] == 0){
        		bulletTime[i]++;
        	}
        }
        
        if(Animation.detection7(mario.getX(), mario.getY(), mario.getW()/2, mario.getH()/2, 1825+dx, 250, 5, 135) && flagDrop <= 190){//mario and flagpole
        	if(mario.getY() < 250 && flagDrop == 0){
        		coinCount += 5000;
        	}
        	else if(mario.getY() < 300 && flagDrop == 0){
        		coinCount += 4000;
        	}
        	else if(mario.getY() < 350 && flagDrop == 0){
        		coinCount += 3000;
        	}
        	else if(mario.getY() < 400 && flagDrop == 0){
        		coinCount += 2000;
        	}
        	else if(mario.getY() < 450 && flagDrop == 0){
        		coinCount += 1000;
        	}
        	else if(flagDrop == 0){
        		coinCount += 500;
        	}
        	mario.setSx(0);
        	mario.setSy(3);
        	flagDrop += 3.3;
        	over = true;
        }else if(over){
        	if(Animation.detection7(mario.getX(), mario.getY(), mario.getW()/2, mario.getH()/2, (int)(2070+dx), 430, 20, 20)){//mario and castle
        		System.out.println("you win");
        		try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		System.exit(0);
        	}
        }
        for(int x=0; x<map.length; x++){//rows(y-axis)
            for(int y=0; y<map[0].length; y++){//collums(x-axis)
            	if(flag == 0){
	            	for(FireBall ball : this.ball){
	            		for(int i=0; i<gx.length; i++){
		            		if(!dead[i] && Animation.detection7(gx[i], gy[i], 10, 10, ball.getX(), ball.getY(), ball.getH()/2, ball.getH()/2)){//fire ball and goomba
		            			 direction[i] = 0;
		            			 gravity[i] = 4;
		            			 dead[i] = true;
	                             ball.setX(-30);
	                             ball.setY(-30);
	                             ball.setGravity(0);
	                             ball.direction(0);
		            		}
	            		}
	            		if(map[x][y] != 0 && map[x][y] != 17 && map[x][y] != 16 && Animation.detection7(ball.getX(), ball.getY(), ball.getH()/2, ball.getH()/2, dx+y*20, x*20, 10, 10)){//fireball and wall
		                    if(ball.getY() + ball.getH() > x*20 && ball.getY() < (x*20 - ball.getH() + 10)){//mario going down
		                        ball.setY(x*20 - ball.getH());
		                        ball.setGravity(-4);
		                    }
		                    else {
		                        ball.setX(-30);
		                        ball.setY(-30);
		                        ball.direction(0);
		                        ball.setGravity(0);
		                    }
	                	}
	                }
            	}
            	if(map[x][y] == 18 || map[x][y] == 19){
                    if(flag == 0){
                    	if(bulletTime[cannonCount] == 200 && bulletx[cannonCount] == 0){
                    		if(map[x][y] == 18) bulletx[cannonCount]--;
	                    	if(map[x][y] == 19) bulletx[cannonCount]++;
                        	bulletTime[cannonCount] = 0;
                    	}
                    	if(bulletx[cannonCount] != 0){
	                    	bullety[cannonCount] = x*20;
	                    	if(map[x][y] == 18) bulletx[cannonCount]--;
	                    	if(map[x][y] == 19) bulletx[cannonCount]++;
	                    	bulletdx[cannonCount] = (y*20+bulletx[cannonCount]+dx);
	                    	g2.drawImage(image[map[x][y]+5], (int)(bulletdx[cannonCount]), x*20, 20, 20, null);
                    	}
                    	cannonCount++;
                    }
                    g2.drawImage(image[22], (int)(y*20+dx), x*20, 20, 20, null);

                }
            	if(map[x][y] != 0 && map[x][y] != 16 && map[x][y] != 18 && map[x][y] != 17 && map[x][y] != 19){
            		int wait = 0;
	            	for(int i=0; i<bulletx.length; i++){
	            		if(Animation.detection7(bulletdx[i], bullety[i], 10, 10, y*20+dx, x*20, 10, 10)){
	            			bulletx[i] = 0;
	            			bullety[i] = 0;
	            			bulletdx[i] = 0;
	            		}
                    	if(Animation.detection7(mario.getX(), mario.getY(), mario.getW()/2, mario.getH()/2, bulletdx[i]+2, bullety[i]+2, 8, 8)){//mario and goomba
                        	if(mario.getY() + mario.getH() > bullety[i] && mario.getY() < (bullety[i] - mario.getH()+10)){//mario killing goomba
                                mario.setY(bullety[i] - mario.getH());
                                mario.setSy(-3);
                                bulletx[i] = 0;
    	            			bullety[i] = 0;
    	            			bulletdx[i] = 0;
                            }else if(invince == 0){
                            	invince = 1;
                            	bulletx[i] = 0;
    	            			bullety[i] = 0;
    	            			bulletdx[i] = 0;
	                        	if(mario.getH() == 34){
	                        		String[] listImages = {"com/mario/marioImages/mario0.png",
	                                        "com/mario/marioImages/mario1.png",
	                                        "com/mario/marioImages/mario2.png"
	                                };
	                        		try {
										TimeUnit.SECONDS.sleep(1);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
	                                mario.setD(20, 20);
	                                mario.setCurrentRunImage(listImages);
	                                mario.setstandingImage("com/mario/marioImages/standingmario.png");
	                        	}
	                        	else if(mario.getH() == 30){
	                        		String[] listImages = {"com/mario/marioImages/bigMario1.png",
	                                        "com/mario/marioImages/bigMario2.png",
	                                        "com/mario/marioImages/bigMario3.png"
	                                };
	                        		try {
										TimeUnit.SECONDS.sleep(1);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
	                                mario.setD(20, 34);
	                                mario.setCurrentRunImage(listImages);
	                                mario.setstandingImage("com/mario/marioImages/bigMario0.png");
	                        	}else {
	                        		lives--;
	                        		try {
										TimeUnit.SECONDS.sleep(1);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
	                        		System.out.println("you lose");
	                        		mario.setX(100);
	                        		mario.setY(200);
	                        		mario.setSx(0);
	                        		mario.setSy(0);
	                        		mario.setJump(0);
	                        		wait = 1;
	                        		if(lives == 0){
	                        			//System.exit(0);
	                        		}
	                        	}
                            }
	                    }
	            	}
	            	if(wait == 1){
	            		for(int i=0; i<bulletx.length; i++){
	            			bulletx[i] = 0;
	            		}
	            		for(int i=0; i<gx.length; i++){
                    		gx[i] = gx[i]-dx;
                    	}
	            		dx = 0;
	            	}
            	}
                if(map[x][y] != 0 && map[x][y] != 17 && !(map[x][y] <= 9 && map[x][y] >= 6) && map[x][y] != 16){
                    if(Animation.detection(mario.getX(), mario.getY(), x*20, y*20, dx, mario)){
                        mario.rotate(0);
                        if(mario.getY() + mario.getH() > x*20 && mario.getY() < (x*20 - mario.getH()+9) && mario.getSy()>=0){//mario going down
                        	System.out.println("down ");
                            //System.out.println(mario.getX() +", " + mario.getY());
                            mario.setY(x*20 - mario.getH());
                            mario.setSy(0);
                            mario.setJump(0);
                            if(mario.getMove()){
                            	mario.setSy(0);
                            	mario.setSx(0);
                            }
                            mario.keepMove(false);
                            if(mario.getH() == 30){
                        		mario.setstandingImage("com/mario/marioImages/fireMario0.png");
                        	}else if(mario.getH() == 34){
                        		mario.setstandingImage("com/mario/marioImages/bigMario0.png");
                        	}else {
                            	mario.setstandingImage("com/mario/marioImages/standingmario.png");
                        	}
                        }
                        else if(mario.getSx() > 0 && (y*20+dx) > mario.getX() + 15){//mario going right
                        	System.out.println("right ");
                            //System.out.println(mario.getX() +", " + mario.getY());
                            mario.setX((y*20+dx) - 20);
                        	mario.setSx(0);
                        	mario.setRight(false);
                        }
                        else if(mario.getSx() < 0 && (y*20+dx) + 15 < mario.getX()){//mario going left
                        	 System.out.println("left ");
                             //System.out.println(mario.getX() +", " + mario.getY());
                            mario.setX((y*20+dx) + 20);
                        	mario.setSx(0);
                        	mario.setLeft(false);
                        }
                        else if((map[x][y] != 14 && map[x][y] != 15) && mario.getY() < x*20 + 20 && mario.getSy() < 0 && mario.getSy() != -7.4){//mario going up
                            System.out.println(mario.getX() +", " + mario.getY() +",speedy is "+mario.getSy() + ", speedx is "+mario.getSx());
                            mario.setY(x*20 + 20);
                        	mario.setSy(0);
                            //System.out.print("up ");
                        }
                    }
                }
                if(flag == 0){
                	int wait = 0;
                    for(int i=0; i<gx.length; i++){
                    	if(!dead[i]){
	                    	if(gy[i] > 500){
	                    		gy[i] = -30;
	                            gx[i] = -30;
	                    	}
	                        if(map[x][y] != 17 && map[x][y] != 0 && map[x][y] != 16 && Animation.detection7(gx[i], gy[i], 10, 10, dx+y*20, x*20, 10, 10)){//goomba and wall
	                            if(gy[i] + 20 > x*20 && gy[i] < (x*20 - 20+10)){//goomba going down
	                                gy[i] = x*20 - 20;
	                                gravity[i] = 0;
	                            }
	                            else if(direction[i] > 0 && (y*20 + dx) > gx[i] + 15){//goomba going right
	                                gx[i] = y*20 - 20 + dx;
	                                direction[i] = -1.4;
	                            }
	                            else if(direction[i] < 0 && (y*20 + dx) + 15 < gx[i]){//goomba going left
	                                gx[i] = y*20 + 20 + dx;
	                                direction[i] = 1.4;
	                            }
	                        }
                    	}
                    	if(Animation.detection7(mario.getX(), mario.getY(), mario.getW()/2, mario.getH()/2, gx[i]+2, gy[i]+2, 6, 6)){//mario and goomba
                        	if(mario.getY() + mario.getH() > gy[i] && mario.getY() < (gy[i] - mario.getH()+10)){//mario killing goomba
                                mario.setY(gy[i] - mario.getH());
                                mario.setSy(-3);
                                direction[i] = 0;
                                gravity[i] = 4;
                                dead[i] = true;
                            }else if(invince == 0){
                            	invince = 1;
	                        	if(mario.getH() == 34){
	                        		String[] listImages = {"com/mario/marioImages/mario0.png",
	                                        "com/mario/marioImages/mario1.png",
	                                        "com/mario/marioImages/mario2.png"
	                                };
	                        		try {
										TimeUnit.SECONDS.sleep(1);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
	                                mario.setD(20, 20);
	                                mario.setCurrentRunImage(listImages);
	                                mario.setstandingImage("com/mario/marioImages/standingmario.png");
	                        	}
	                        	else if(mario.getH() == 30){
	                        		String[] listImages = {"com/mario/marioImages/bigMario1.png",
	                                        "com/mario/marioImages/bigMario2.png",
	                                        "com/mario/marioImages/bigMario3.png"
	                                };
	                        		try {
										TimeUnit.SECONDS.sleep(1);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
	                                mario.setD(20, 34);
	                                mario.setCurrentRunImage(listImages);
	                                mario.setstandingImage("com/mario/marioImages/bigMario0.png");
	                        	}else {
	                        		lives--;
	                        		try {
										TimeUnit.SECONDS.sleep(1);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
	                        		System.out.println("you lose");
	                        		mario.setX(100);
	                        		mario.setY(200);
	                        		mario.setSx(0);
	                        		mario.setSy(0);
	                        		mario.setJump(0);
	                        		wait = 1;
	                        		if(lives == 0){
	                        			//System.exit(0);
	                        		}
	                        	}
                            }
                        
                        }
                    }
                    if(wait == 1){
                    	for(int i=0; i<gx.length; i++){
                    		gx[i] = gx[i]-dx;
                    	}
                    	dx = 0;
                    }
                }
                if(map[x][y] == 1){
                    g2.drawImage(image[0], (int)(y*20+dx), x*20, 20, 20, null);
                }
                else if(map[x][y] == 2){
                    g2.drawImage(image[1], (int)(y*20+dx), x*20, 20, 20, null);
                }
                else if(map[x][y] == 3){
                    g2.drawImage(image[2], (int)(y*20+dx), x*20, 20, 20, null);
                }
                else if(map[x][y] == 4){
                    g2.drawImage(image[3], (int)(y*20+dx), x*20, 20, 20, null);
                }
                else if(map[x][y] == 5){
                    g2.drawImage(image[4], (int)(y*20+dx), x*20, 20, 20, null);
                }
                else if(map[x][y] == 6){
                    if(Animation.detection(mario.getX(), mario.getY(), x*20, y*20, dx, mario)){
                        if(mario.getY() + 9 < x*20 && mario.getSy() > 0){
                            mario.setY(x*20-mario.getH());
                            mario.setSy(0);
                            mario.rotate(0);
                            mario.setJump(0);
                            if(mario.getMove()){
                            	mario.setSy(0);
                            	mario.setSx(0);
                            }
                            mario.keepMove(false);
                            
                        }else if(mario.getX() + 15 < (y*20+dx) && mario.getSx() > 0){
                            mario.setX((y*20+dx)-20);
                            mario.setSx(0);
                            mario.rotate(0);
                            mario.setRight(false);

                        }else{
                            for(int i=-20; i<20; i++){
                                if(mario.getY() < -i+(x*20+mario.getH()) && (int)mario.getX() == (int)(y*20+dx)+i){
                                    mario.setX((y*20+dx)+i+2);
                                    mario.setY(-i+(x*20+mario.getH()));
                                    mario.setSy(2);
                                    break;
                                }
                            }
                        }
                    }
                    g2.drawImage(image[5], (int)(y*20+dx), x*20, 20, 20, null);
                }
                else if(map[x][y] == 7){
                    if(Animation.detection(mario.getX(), mario.getY(), x*20, y*20, dx, mario)){
                        if(mario.getY() + 9 < x*20 && mario.getSy() > 0){
                            mario.setY(x*20-mario.getH());
                            mario.setSy(0);
                            mario.rotate(0);
                            mario.setJump(0);
                            if(mario.getMove()){
                            	mario.setSy(0);
                            	mario.setSx(0);
                            }
                            mario.keepMove(false);
                        }else if(mario.getX() > (y*20+dx) + 15 && mario.getSx() < 0){
                            mario.setX((y*20+dx)+20);
                            mario.setSx(0);
                            mario.rotate(0);
                            mario.setLeft(false);

                        }else{
                            for(int i=-20; i<20; i++){
                                if(mario.getY() < -i+(x*20+mario.getH()) && (int)mario.getX() == (int)(y*20+dx)-i){
                                    mario.setX((y*20+dx)-i-2);
                                    mario.setY(-i+(x*20+mario.getH()));
                                    mario.setSy(2);
                                    break;
                                }
                            }
                        }
                    }
                    g2.drawImage(image[6], (int)(y*20+dx), x*20, 20, 20, null);
                }
                else if(map[x][y] == 8){
                    if(Animation.detection(mario.getX(), mario.getY(), x*20, y*20, dx, mario)){
                        if(mario.getY() > x*20 + 11 && mario.getSy() < 0){
                            mario.setY(x*20 + mario.getH());
                            mario.setSy(0);
                            mario.rotate(0);
                        }else if(mario.getX() + 15 < (y*20+dx) && mario.getSx() > 0){
                            mario.setX((y*20+dx)-20);
                            mario.setSx(0);
                            mario.rotate(0);
                            mario.setRight(false);
                        }else if(mario.getX() < (y*20+dx)){
                            mario.setY(x*20-mario.getH());
                            mario.setSy(0);
                            mario.setJump(0);
                            mario.rotate(0);
                            if(mario.getMove()){
                            	mario.setSy(0);
                            	mario.setSx(0);
                            }
                            mario.keepMove(false);
                            
                        }else{
                        	if(mario.getMove()){
                            	mario.setSy(0);
                            	mario.setSx(0);
                            }
                            mario.keepMove(false);
                        	if(mario.getH() == 30){
                        		mario.setstandingImage("com/mario/marioImages/fireMario0.png");
                        	}else if(mario.getH() == 34){
                        		mario.setstandingImage("com/mario/marioImages/bigMario0.png");
                        	}else {
                            	mario.setstandingImage("com/mario/marioImages/standingmario.png");
                        	}
                            for(int i=0; i<20; i++){
                                if(mario.getY()+mario.getH() > i+x*20 && (int)mario.getX() == (int)(y*20+dx)+i){
                                    mario.setX((y*20+dx)+i);
                                    mario.setY(i+x*20-mario.getH());
                                    mario.setSy(0);
                                    mario.setJump(0);
                                    mario.rotate(Math.PI/4);
                                    break;
                                }
                            }
                        }
                    }
                    g2.drawImage(image[7], (int)(y*20+dx), x*20, 20, 20, null);
                }
                else if(map[x][y] == 9){
                    if(Animation.detection(mario.getX(), mario.getY(), x*20, y*20, dx, mario)){
                        if(mario.getY() > x*20 + 11 && mario.getSy() < 0){
                            mario.setY(x*20 + mario.getH());
                            mario.setSy(0);
                            mario.rotate(0);
                        }else if(mario.getX() > (y*20+dx)){
                            mario.setY(x*20-20);
                            mario.setSy(0);
                            mario.setJump(0);
                            mario.rotate(0);
                            if(mario.getMove()){
                            	mario.setSy(0);
                            	mario.setSx(0);
                            }
                            mario.keepMove(false);
                        }else if(mario.getX() > (y*20+dx) + 15 && mario.getSx() < 0){
                            mario.setX((y*20+dx)+20);
                            mario.setSx(0);
                            mario.rotate(0);
                            mario.setLeft(false);
                            
                        }else{
                        	if(mario.getMove()){
                            	mario.setSy(0);
                            	mario.setSx(0);
                            }
                            mario.keepMove(false);
                        	if(mario.getH() == 30){
                        		mario.setstandingImage("com/mario/marioImages/fireMario0.png");
                        	}else if(mario.getH() == 34){
                        		mario.setstandingImage("com/mario/marioImages/bigMario0.png");
                        	}else {
                            	mario.setstandingImage("com/mario/marioImages/standingmario.png");
                        	}
                            for(int i=0; i<20; i++){
                                if(mario.getY()+mario.getH() > i+x*20 && (int)mario.getX() == (int)(y*20+dx)-i){
                                    mario.setX((y*20+dx)-i);
                                    mario.setY(i+x*20-mario.getH());
                                    mario.setSy(0);
                                    mario.setJump(0);
                                    mario.rotate(-Math.PI/4);
                                    break;
                                }
                            }
                        }
                    }
                    g2.drawImage(image[8], (int)(y*20+dx), x*20, 20, 20, null);
                }
                else if(map[x][y] == 10){
                    g2.drawImage(image[9], (int)(y*20+dx), x*20, 20, 20, null);
                }
                else if(map[x][y] == 11){
                    g2.drawImage(image[10], (int)(y*20+dx), x*20, 20, 20, null);
                }
                else if(map[x][y] == 12){
                    g2.drawImage(image[11], (int)(y*20+dx), x*20, 20, 20, null);
                }
                else if(map[x][y] == 13){
                    g2.drawImage(image[12], (int)(y*20+dx), x*20, 20, 20, null);
                }
                else if(map[x][y] == 17){
                	g2.drawImage(image[17], (int)(y*20+dx), x*20, 20, 20, null);
                	if(Animation.detection(mario.getX(), mario.getY(), x*20, y*20, dx, mario)){
                		map[x][y] = 0;
                		coinCount++;
                	}
                }
                else if(map[x][y] == 14 || map[x][y] == 15){
                    
                    if(Animation.detection(mario.getX(), mario.getY(), x*20, y*20, dx, mario)){
                        if(mario.getY() < x*20 + 20 && mario.getSy() < 0){//mario going up
                            mario.setY(x*20 + 20);
                            mario.setSy(0);
                            if(mysBlock[x][y] == 0){
                                mysBlock[x][y] = 1;
                                animation = 1;//flag
                                bumpY = -6;//mysBlock animation
                                popUpY = -8;//item animation
                                whichMys = map[x][y];
                            }
                        }
                    }
                    if(animation == 1 && mysBlock[x][y] == 1){
                        bumpY = Animation.bump(bumpY);
                        g2.drawImage(image[13], (int)(y*20+dx), (int)(x*20+bumpY), 20, 20, null);
                        popUpY = Animation.popUp(popUpY);
                        g2.drawImage(image[whichMys], (int)(y*20+dx), (int)(x*20+popUpY), 20, 20, null);
                        if(bumpY == 6){
                            animation = 0;
                            bumpY = 0;
                            popUpY = 0;
                            mysBlock[x][y] = whichMys;
                        }
                    }else{
                        if(mysBlock[x][y] < 1){
                            g2.drawImage(image[map[x][y]], (int)(y*20+dx), (int)(x*20), 20, 20, null);
                        }else{  
                            g2.drawImage(image[map[x][y]], (int)(y*20+dx), (int)(x*20+permanantY), 20, 20, null);
                        }
                        g2.drawImage(image[13], (int)(y*20+dx), x*20, 20, 20, null);
                    }
                }
                else if(map[x][y] == 16 && flag == 1){//Goomba
                    g2.drawImage(image[16], (int)(y*20+dx), x*20, 20, 20, null);
                }
                
                if(Animation.detection(mario.getX(), mario.getY(), x*20-20, y*20, dx, mario) && map[x][y] != 0){
                     if(mysBlock[x][y] == 14){
                        String[] listImages = {"com/mario/marioImages/bigMario1.png",
                                "com/mario/marioImages/bigMario2.png",
                                "com/mario/marioImages/bigMario3.png"
                        };//images of mario running
                        mario.setD(20, 34);
                        mario.setCurrentRunImage(listImages);
                        mario.setstandingImage("com/mario/marioImages/bigMario0.png");
                        mysBlock[x][y] = -1;
                    }
                    else if(mysBlock[x][y] == 15){
                        String[] listImages = {"com/mario/marioImages/fireMario1.png",
                                "com/mario/marioImages/fireMario2.png",
                                "com/mario/marioImages/fireMario3.png"
                        };//images of mario running
                        mario.setD(20, 30);
                        mario.setCurrentRunImage(listImages);
                        mario.setstandingImage("com/mario/marioImages/fireMario0.png");
                        mysBlock[x][y] = -1;
                    }
                    
                }
            }
        }
        g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20)); 
        g2.setColor(Color.CYAN);
        g2.drawString("Points: "+coinCount, 100, 100);
        g2.drawString("Lives: "+lives, 500, 100);
    }
    public void setPlayer(Player mario, FireBall[] ball, int x, int flag, int y){
        this.mario = mario;
        this.ball = ball;
        dead = new boolean[x];
        bulletx = new double[y];
        bulletdx = new double[y];
        bullety = new double[y];
        bulletTime = new int[y];
        for(int i=0; i<dead.length; i++){
        	dead[i] = false;
        }
        this.flag = flag;
    }
    public void setDim(int w, int h){
        width = w;
        height = h;
    }
    public void moveFrame(double d){
        dx += d;
        repaint();
    }
    public void passCor(double[] gx, double[] gy, double[] d, double[] g){
    	this.gx = gx;
    	this.gy = gy;
    	direction = d;
    	gravity = g;
    }
   /* public void setFire(boolean a){
    	fire = a;
    }*/
}
