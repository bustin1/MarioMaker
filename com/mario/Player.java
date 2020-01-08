package com.mario;
/**
 * Write a description of interface Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Player
{
    public String getName();
	public String getstandingImage();
	public void setstandingImage(String s);
	public void keepMove(boolean a);
	public boolean getMove();
	public String[] getCurrentRunImage();
	public void setCurrentRunImage(String[] a);
	public double getX();
	public double getY();
	public void move(int width, int height);
	public void setSx(double sy);
	public void setSy(double sy);
	public double getSx();
	public double getSy();
	public void setX(double x);
	public void setY(double y);
	public void setJump(int jump);
	public int getJump();
	public void rotate(double rads);
	public double getRotate();
	public void setPanel(Mari0Panel panel);
	public void setD(int w, int h);
	public int getW();
	public int getH();
	public void setJumpImage(String s);
	public String getJumpImage();
	public void setLeft(boolean s);
	public void setRight(boolean s);
	public boolean isRight();
	public boolean isLeft();
	//public boolean getDx();
	//public void setDx(boolean s);
}
