package com.jpgameenterteiment.entities;
import java.awt.Color;
import java.awt.Rectangle;

import com.jpsgameenterteiment.main.Game;

public class RainObj extends Rectangle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public Color color;
	
	public RainObj(int x, int y, int width, int height) {
		super(x,y,width, height);
		
		color = new Color(106,239,255);
	}
	
	public void update() {
		y+=16;
		
}
}