package com.jpgameenterteiment.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jpgameenterteiment.word.Camera;
import com.jpsgameenterteiment.main.Game;

public class Invencibilidade extends Entities {
	
	private int frames = 0, maxFrames =10, index = 0, maxIndex = 5;
	
	private BufferedImage[] spritein;

	public Invencibilidade(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, sprite);
		
		spritein = new BufferedImage[6];
		spritein[0] = Game.spritesheet.getSprite(5*16, 12*16, 16, 16);
		spritein[1] = Game.spritesheet.getSprite(5*16, 13*16, 16, 16);
		spritein[2] = Game.spritesheet.getSprite(5*16, 14*16, 16, 16);
		spritein[3] = Game.spritesheet.getSprite(5*16, 15*16, 16, 16);
		spritein[4] = Game.spritesheet.getSprite(5*16, 14*16, 16, 16);
		spritein[5] = Game.spritesheet.getSprite(5*16, 13*16, 16, 16);
	
	}
	
	public void update() {
		frames++;
		if (frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) {
				index =0;
			}
		}		
		
	}
	
	public void render(Graphics g) {
		g.drawImage(spritein[index], this.getX() - Camera.x, this.getY() - Camera.y,null);
	}


}
