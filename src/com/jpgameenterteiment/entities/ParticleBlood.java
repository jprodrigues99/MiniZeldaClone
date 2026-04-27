package com.jpgameenterteiment.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.jpgameenterteiment.word.Camera;
import com.jpsgameenterteiment.main.Game;

public class ParticleBlood extends Entities {
	
	public int lifeTime = 5;
	public int curLife = 0;
	public int speed = 2;
	public double dx = 0;
    public double dy = 0;
	
	public ParticleBlood(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		dx = new Random().nextGaussian();
		dy = new Random().nextGaussian();
	}

	public void update() {
		x+=dx*speed;
		y+=dy*speed;
		curLife++;
		if(lifeTime == curLife) {
			Game.entities.remove(this);
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(this.getX() - Camera.x, this.getY() - Camera.y, width, height);
	}

}
