package com.jpgameenterteiment.entities;

import java.awt.image.BufferedImage;

public class Tree extends Entities {

	public Tree(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}

	public void update() {
		depth = 2;
	}
	
}
