package com.mario;
import java.awt.*;
public class Mario implements Player{
    private String name;
    private String standingImage;
    private String[] runImage;
    private double x;
    private double y;
    private double dx;//speed x
    private double dy;//speed y
    private int jump;
    private double rads;
    int screen;
    private Mari0Panel panel;
    private int height;
    private int width;
    private boolean moving = false;
    private String jumpImage;
    private boolean left = true;
    private boolean right = true;
    private boolean dx1;
    public Mario(){
        dx = 0;
        dy = 0;
        x = 100;
        y = 200;
        jump = 0;
    }

    public Mario(String name, String standingImage, String[] image){
        this.name = name;
        this.standingImage = standingImage;
        dx = 0;
        dy = 0;
        x = 100;
        y = 200;
        jump = 0;
        screen = 0;
        runImage = image;
        height = 20;
        width = 20;
        dx1 = true;
    }

    public String getName(){
        return name;
    }

    public String getstandingImage(){
        return standingImage;
    }
    
    public void setstandingImage(String s){
        standingImage = s;
    }

    public String[] getCurrentRunImage(){
        return runImage;
    }

    public void setCurrentRunImage(String[] a){
        runImage = a;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
    public void keepMove(boolean a){
    	moving = a;
    }
    public boolean getMove(){
    	return moving;
    }
    public void move(int width, int height) {
        if(x > 3*width/4 && dx > 0){
            panel.moveFrame(-dx);
        }else if(x < width/4 && dx < 0){
            panel.moveFrame(-dx);
        }else {
            x += dx;
        }
        dy += .3;
        y += dy;
        if(dy > 8) dy = 8;
    }

    public void setSy(double sy){
        this.dy = sy;
    }
    public void setSx(double sx){
        this.dx = sx;
    }

    public double getSx(){
        return dx;
    }

    public double getSy(){
        return dy;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setJump(int jump) {
        this.jump = jump;
    }

    public int getJump() {
        return jump;
    }

    public void rotate(double rads){
        this.rads = rads;
    }

    public double getRotate(){
        return rads;
    }

    public void setPanel(Mari0Panel panel){
        this.panel = panel;
    }

    public void setD(int h, int w){
        height = w;
        width = h;
    }

    public int getH(){
        return height;
    }

    public int getW(){
        return width;
    }
    
    public void setJumpImage(String s){
    	jumpImage = s;
    }
    
    public String getJumpImage(){
    	return jumpImage;
    }
    public boolean isRight(){
    	return right;
    }
    public boolean isLeft(){
    	return left;
    }
    public void setRight(boolean s){
    	right = s;
    }
    public void setLeft(boolean s){
    	left = s;
    }
    /*public boolean getDx(){
    	return dx1;
    }
    public void setDx(boolean s){
    	dx1 = s;
    }*/
}
