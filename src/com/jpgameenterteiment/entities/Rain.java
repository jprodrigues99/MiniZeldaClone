package com.jpgameenterteiment.entities;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jpgameenterteiment.word.Camera;
import com.jpsgameenterteiment.main.Game;
import com.jpsgameenterteiment.main.Sound;


public class Rain {
	
	public boolean Chuva = false;
	public static int z = 0;
	public int RainFrames = 15, RainCur = 0;
	public boolean RainDown = false;
	public boolean IsRain = false;
	public static double RainSpeed = 2;
	
	
	private int cont = 0, maxcont = 60*60;
	public double timer = 0;
	public static List<RainObj> rectangles = new ArrayList<RainObj>();
	public static List<ParticleRain> particles = new ArrayList<ParticleRain>();
	
	public void update() {
		/*
		if (Chuva) {
			if (IsRain == false){
				Chuva = false;
				IsRain = true;
			}
		}
		
		if (IsRain == true) {
			if(RainDown) {
				RainCur+=RainSpeed;
			}
			z = RainCur;
			if(RainCur >= RainFrames) {
				RainDown = true;
			}
		}
		*/
		
		if(Game.scene_state == Game.playing) {
		cont++;
		timer++;
		}
		if(timer == 1) {
			timer = 0;
			if(cont > 60*30 && cont < maxcont) {
			rectangles.add(new RainObj(new Random().nextInt(1280),0,2,3));
			Sound.Clips.rainrain.loop();
		}
		}
		if(cont == maxcont) {
			Rain.rectangles.clear();
			Sound.Clips.rainrain.stop();
		}
	
		
		for(int i = 0; i < rectangles.size();i++) {
			RainObj current = rectangles.get(i);
			rectangles.get(i).update();
			if(current.y == (new Random().nextInt(100)*16)) {
				rectangles.remove(current);
				
				if(current.y > 732) {
					rectangles.remove(current);
				}
				
				for(int n = 0; n < 10; n++) {
					particles.add(new ParticleRain(current.x, current.y,3,3));
				}
				
			}
		}
			
		
		for(int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
			ParticleRain part = particles.get(i);
			if(part.timer >= 2) {
				particles.remove(part);
			}
		}
		
	}
	
    public void render(Graphics g) {
		for(int i = 0; i < rectangles.size();i++) {
			RainObj current = rectangles.get(i);
			g.setColor(current.color);
			g.fillRect(current.x , current.y , current.width, current.height);
		}
		for(int i = 0; i < particles.size(); i++) {
			particles.get(i).render(g);
		}
		
	}

}

