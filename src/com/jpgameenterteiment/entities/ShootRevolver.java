package com.jpgameenterteiment.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jpgameenterteiment.word.Camera;
import com.jpgameenterteiment.word.Word;
import com.jpsgameenterteiment.main.Game;

public class ShootRevolver extends Entities {
	
	private double dx;
	private double dy;
	private double speed = 2;
	private int Life = 50, curLife = 0;
	public static int DanoShoot = 10;

	public ShootRevolver(int x, int y, int width, int height, BufferedImage sprite, double dx, double dy) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;
	}
	
		public void update() {
			depth = 1;
			if(Word.isFree((int)(x+speed), this.getY())) {
			x+=dx*speed;
			y+=dy*speed;
			//DESTRUINDO MUNICAO
			curLife++;
			if(curLife == Life) {
				Game.shootRevolver.remove(this);
				return;
			}
		}else {Game.shootRevolver.remove(this);
		Word.generateParticle(30,(int) x, (int) y);
		}
			if(y >= 780) {
				Game.shootRevolver.remove(this);
				return;
			}
		}
		public void render(Graphics g) {
			g.setColor(Color.YELLOW);
			g.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, width, height);
		}
		
	}

