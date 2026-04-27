package com.jpgameenterteiment.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jpgameenterteiment.word.Camera;
import com.jpsgameenterteiment.main.Game;

public class River2 extends Entities{

private int frames = 0, maxFrames =30, index = 0, maxIndex = 15;
	
	private BufferedImage[] river;
	
	public River2(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		river = new BufferedImage[16];
		river[0] = Game.spritesheet.getSprite(8*16, 18*16, 16,16);
		river[1] = Game.spritesheet.getSprite(9*16, 18*16, 16,16);
		river[2] = Game.spritesheet.getSprite(8*16, 18*16, 16,16);
		river[3] = Game.spritesheet.getSprite(9*16, 18*16, 16,16);
		river[4] = Game.spritesheet.getSprite(8*16, 18*16, 16,16);
		river[5] = Game.spritesheet.getSprite(9*16, 18*16, 16,16);
		river[6] = Game.spritesheet.getSprite(8*16, 18*16, 16,16);
		river[7] = Game.spritesheet.getSprite(9*16, 18*16, 16,16);
		river[8] = Game.spritesheet.getSprite(8*16, 18*16, 16,16);
		river[9] = Game.spritesheet.getSprite(9*16, 18*16, 16,16);
		river[10] = Game.spritesheet.getSprite(8*16, 18*16, 16,16);
		river[11] = Game.spritesheet.getSprite(9*16, 18*16, 16,16);
		river[12] = Game.spritesheet.getSprite(10*16, 18*16, 16,16);
		river[13] = Game.spritesheet.getSprite(11*16, 18*16, 16,16);
		river[14] = Game.spritesheet.getSprite(10*16, 18*16, 16,16);
		river[15] = Game.spritesheet.getSprite(9*16, 18*16, 16,16);
	}
	
	public void update() {
		depth = 0;
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
		    g.drawImage(river[index], this.getX() - Camera.x, this.getY() - Camera.y, null);	
	}

	

}
