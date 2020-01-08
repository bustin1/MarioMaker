package com.mario;
/**
 * Write a description of class Goomba here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Goomba
{   
    private int height;
    private int width;
    private double x;
    private double y;
    private double direction;
    private double gravity;
    private Player mario;
    private double widthFrame;
    private double heightFrame;
    private boolean dead;
    public Goomba(int a, int b, Player mario){
    	dead = false;
        height = 20;
        width = 20;
        gravity = 0;
        x = a;
        y = b;
        heightFrame = 500;
        widthFrame = 800;
        direction = .7;
        this.mario = mario;
    }
    public void move(){
        gravity += .3;
        if(gravity > 5){
            gravity = 5;
        }
        this.y += gravity;
        if(this.mario.getX() >= 3*widthFrame/4 && this.mario.getSx() > 0){
            this.x -= (mario.getSx()-direction);
            //System.out.println(mario.getSx());
        }else if(this.mario.getX() <= widthFrame/4 && this.mario.getSx() < 0){
            this.x -= (mario.getSx()-direction);
            System.out.println(widthFrame/4);
        }else{
            this.x += direction;
        }
        this.x += direction;
    }
    public void setDirection(double d){
        direction = d;
    }
    public void setX(double a){
        x = a;
    }
    public void setY(double b){
        y = b;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public int getH(){
        return height;
    }
    public int getW(){
        return width;
    }
    public void setG(double d){
        gravity = d;
    }
    public double getD(){
        return direction;
    }
    public void setDimension(int w, int h){
        widthFrame = w;
        heightFrame = h;
    }
    public boolean die(){
    	return dead;
    }
    public void setDie(boolean d){
    	dead = d;
    }
}
