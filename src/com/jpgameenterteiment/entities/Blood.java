package com.jpgameenterteiment.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jpgameenterteiment.word.Camera;
import com.jpsgameenterteiment.main.Game;

public class Blood {
	public int timer = 0;
	public int cont = 0, maxCont = 5;
	public static List<BloodObj> rectangles = new ArrayList<BloodObj>();
	
	
	public void update() {
		if(Game.player.life < 95) {
		//timer++;
		}
		if(timer == 30) {
			timer = 0;
			cont++;
			rectangles.add(new BloodObj(Game.player.getX() + 500 ,Game.player.getY() + 300, 3,3));
		}
		if(cont == maxCont) {
			cont = 0;
		}


		for(int i = 0; i < rectangles.size();i++) {
			BloodObj current = rectangles.get(i);
			rectangles.get(i).update();
			if(cont == 4) {
				rectangles.remove(current);			
			}
		}
	}
	
    public void render(Graphics g) {
		for(int i = 0; i < rectangles.size();i++) {
			BloodObj current = rectangles.get(i);
			g.setColor(current.color);
			g.fillRect(current.x - Camera.x, current.y - Camera.y, current.width, current.height);
		}
		
	 }

}
