package com.jpgameenterteiment.entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class ParticleRain extends Rectangle {
	
	public int speed = 2;
	public double dx, dy;
	public double xa, ya;
	
	public int timer = 0;
	
	public ParticleRain(int x, int y, int width, int height) {
		super(x, y, width, height);
		xa = x;
		ya = y;
		
		dx = new Random().nextGaussian();
		dy = new Random().nextGaussian();
		
	}
	
	
	public void update() {
		timer++;
		xa+=dx*speed;
		ya+=dy*speed;
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(170,255,239));
		g.fillRect((int)xa, (int)ya, width, height);
	}
	}
    

