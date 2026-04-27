package com.jpgameenterteiment.word;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.jpgameenterteiment.entities.Entities;
import com.jpsgameenterteiment.main.Game;

public class Tile {

	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(6*16, 14*16, 16, 16);
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(7*16, 14*16, 16, 16);
	public static BufferedImage TILE_TREE = Game.spritesheet.getSprite(6*16, 15*16, 16, 16);
	public static BufferedImage  TILE_ROCKLEFTUP = Game.spritesheet.getSprite(8*16, 14*16, 16, 16);
	public static BufferedImage  TILE_ROCKLEFT = Game.spritesheet.getSprite(8*16, 15*16, 16, 16);
	public static BufferedImage  TILE_ROCKLEFTDOWN = Game.spritesheet.getSprite(8*16, 16*16, 16, 16);
	public static BufferedImage  TILE_ROCKUP = Game.spritesheet.getSprite(9*16, 14*16, 16, 16);
	public static BufferedImage  TILE_ROCK = Game.spritesheet.getSprite(9*16, 15*16, 16, 16);
	public static BufferedImage  TILE_ROCKDOWN = Game.spritesheet.getSprite(9*16, 16*16, 16, 16);
	public static BufferedImage  TILE_ROCKRIGHTUP = Game.spritesheet.getSprite(10*16, 14*16, 16, 16);
	public static BufferedImage  TILE_ROCKRIGHT = Game.spritesheet.getSprite(10*16, 15*16, 16, 16);
	public static BufferedImage  TILE_ROCKRIGHTDOWN = Game.spritesheet.getSprite(10*16, 16*16, 16, 16);
	public static BufferedImage  TILE_BUSH = Game.spritesheet.getSprite(7*16, 15*16, 16, 16);


   private BufferedImage sprite;
   private int x,y;
   public int maskx,masky,mwidth,mheight;
   protected int width;
   protected int height;
   
   public Tile(int x, int y, BufferedImage sprite) {
	   this.x = x;
	   this.y = y;
	   this.sprite = sprite;
	   
	    this.maskx = 0;
		this.masky = 0;
		this.mwidth = width;
		this.mheight = height;
   }
   
	public void setMask(int maskx,int masky,int mwidth,int mheight){
		this.maskx = maskx;
		this.masky = masky;
		this.mwidth = mwidth;
		this.mheight = mheight;
	}
   
   public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int)this.x;
	}
	public int getY() {
		return (int)this.y;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeigth() {
	return this.height;
    }
	
   public static boolean isColidding(Entities e1,Entities e2){
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx,e1.getY()+e1.masky,e1.mwidth,e1.mheight);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx,e2.getY()+e2.masky,e2.mwidth,e2.mheight);
		
		return e1Mask.intersects(e2Mask);
	}
   
	public void render(Graphics g) {
	g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
	 g.setColor(Color.red);
	  g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, mwidth, height);
	}
}

