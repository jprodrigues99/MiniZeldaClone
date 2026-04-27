package com.jpgameenterteiment.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jpgameenterteiment.word.Camera;
import com.jpgameenterteiment.word.Tile;
import com.jpsgameenterteiment.main.Game;

public class Fire extends Entities{
	
	private int frames = 0, maxFrames =10, index = 0, maxIndex = 3;
	
	private BufferedImage[] fire;

	public Fire(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		
		fire = new BufferedImage[4];
		fire[0] = Game.spritesheet.getSprite(11*16, 15*16, 16, 16);
		fire[1] = Game.spritesheet.getSprite(11*16, 14*16, 16, 16);
		fire[2] = Game.spritesheet.getSprite(11*16, 15*16, 16, 16);
		fire[3] = Game.spritesheet.getSprite(11*16, 16*16, 16, 16);
		
	} 
	
	public void update() {
		if(Game.GameState == "NORMAL") {
			
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		  }
	}
	
	public void render(Graphics g) {
		g.drawImage(fire[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
	
	

}
