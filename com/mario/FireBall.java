package com.mario;

public class FireBall
{
    private String image;
    private double x;
    private double y;
    private int direction;
    private double gravity;
    private int height;
    private int widthFrame;
    private int heightFrame;
    private Player mario;
     public FireBall(){
       
    }
    public FireBall(String s, Player mario){
        image = s;
        this.mario = mario;
        direction = 0;
        gravity = 0;
        height = 20;
        x = -30;
        y = -30;
        widthFrame = 800;
        heightFrame = 500;
    }
    public String getImage(){
        return image;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setX(double a){
        x = a;
    }
    public void setY(double b){
        y = b;
    }
    public void direction(int dir){
        direction = dir;
    }
    public void move(){
        gravity += .4;
        y += gravity;
        if(gravity > 8) gravity = 8;
        if(this.mario.getX() > 3*widthFrame/4 && this.mario.getSx() > 0){
            this.x -= (mario.getSx()-direction);
        }else if(this.mario.getX() < widthFrame/4 && this.mario.getSx() < 0){
            this.x -= (mario.getSx()-direction);
        }else{
            this.x += direction;
        }
    }
    public void setGravity(double g){
        gravity = g;
    }
    public int getH(){
        return height;
    }
    public void setDimension(int w, int h){
    	widthFrame = w;
    	heightFrame = h;
    }
}
