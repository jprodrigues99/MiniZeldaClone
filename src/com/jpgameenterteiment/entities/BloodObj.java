package com.jpgameenterteiment.entities;

import java.awt.Color;
import java.awt.Rectangle;

public class BloodObj extends Rectangle{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public Color color;
	
	public BloodObj(int x, int y, int width, int height) {
		super(x,y,width, height);
		
		color = new Color(255, 0, 0);
	}
	
	public void update() {
		y++;
		
}
}
