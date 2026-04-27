package com.jpgameenterteiment.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.jpgameenterteiment.word.AStar;
import com.jpgameenterteiment.word.Camera;
import com.jpgameenterteiment.word.Vector2i;
import com.jpgameenterteiment.word.Word;
import com.jpsgameenterteiment.main.Game;
import com.jpsgameenterteiment.main.Sound;

public class Enemyhard extends Entities {

	public static double speed = 1.0;
	private int maskx = 0, masky = 0, maskw = 8, maskh = 8;
	private int frames = 0, maxFrames =10, framesStop = 0, maxFramesStop = 30, index = 0, maxIndex = 3, indexstop = 0, maxIndexstop = 1;
	private double life = 60, maxLife = 60;
	public boolean IsDamage = false;
	private int DamageFrames = 10, DamageCur = 0;
	private boolean enemyhardmove = false;
	private int framesper = 0, maxframesper = 900; // 15 segundos
	
	public static boolean up = false;
	public static boolean down = false;
	
	private BufferedImage[] spriteenemyhard;
	private BufferedImage[] spriteenemyhardstop;
	private BufferedImage[] spriteenemyhardup;
	
	public Enemyhard(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, null);
		spriteenemyhard = new BufferedImage[4];
		spriteenemyhard[0] = Game.spritesheet.getSprite(6*16, 13*16, 16, 16);
		spriteenemyhard[1] = Game.spritesheet.getSprite(7*16, 13*16, 16, 16);
		spriteenemyhard[2] = Game.spritesheet.getSprite(8*16, 13*16, 16, 16);
		spriteenemyhard[3] = Game.spritesheet.getSprite(7*16, 13*16, 16, 16);
		
		spriteenemyhardstop = new BufferedImage[2];
		spriteenemyhardstop[0] = Game.spritesheet.getSprite(11*16, 12*16, 16, 16);
		spriteenemyhardstop[1] = Game.spritesheet.getSprite(11*16, 11*16, 16, 16);
		
		spriteenemyhardup = new BufferedImage[4];
		spriteenemyhardup[0] = Game.spritesheet.getSprite(9*16, 13*16, 16, 16);
		spriteenemyhardup[1] = Game.spritesheet.getSprite(10*16, 13*16, 16, 16);
		spriteenemyhardup[2] = Game.spritesheet.getSprite(11*16, 13*16, 16, 16);
		spriteenemyhardup[3] = Game.spritesheet.getSprite(10*16, 13*16, 16, 16);
		
	}

	public void update() {
		depth = 1;
		if(this.CalculateDistance(getX(), getY(), Game.player.getX(),Game.player.getY()) < 80) {
			this.enemyhardmove = true;
		}
		//tempo de perseguicao do inimigo
				if(enemyhardmove == true) {
					Sound.Clips.orc.loop();
					framesper++;
					if(framesper == maxframesper || (this.CalculateDistance(getX(), getY(), Game.player.getX(),Game.player.getY()) >= 150)) {
						framesper = 0;
						this.enemyhardmove = false;
						Sound.Clips.orc.stop();
					}
				}
				
				
					// perseguicao inteligente
					if(enemyhardmove == true && !this.isCollidingWithPlayer()) {
					if(path == null || path.size() == 0) {
						Vector2i start = new Vector2i ((int)(x/16), (int)(y/16));
						Vector2i end = new Vector2i ((int)(Game.player.x/16), (int)(Game.player.y/16));
						path = AStar.findPath(Game.word, start, end);
					}
					if(new Random().nextInt(100) < 60) {
					followPathhard(path);
					}
					}else if (isCollidingWithPlayer() == true) {
						if(Game.rand.nextInt(100) < 20) {
							Sound.Clips.hurt.play();
							if(Player.Invencivel == false) {
							Game.player.life-=Game.rand.nextInt(5);
							}
							Game.player.IsDamage = true;
							
					}
					}
		/*
			if(this.enemyhardmove == true) {
		if(isCollidingWithPlayer() == false) {	 
			if((int)x < Game.player.getX() && Word.isFree((int)(x+speed), this.getY())
					&& !isColliding((int)(x+speed), this.getY())) {
				x+=speed;
			}else if ((int)x > Game.player.getX() && Word.isFree((int)(x-speed), this.getY())
					&& !isColliding((int)(x-speed), this.getY())){
				x-=speed;
			}if((int)y < Game.player.getY() && Word.isFree(this.getX(),(int)(y+speed))
					&& !isColliding(this.getX(),(int)(y+speed))) {
				y+=speed;
			}else if ((int)y > Game.player.getY() && Word.isFree(this.getX(),(int)(y-speed))
					&& !isColliding(this.getX(),(int)(y-speed))){
				y-=speed;
		}
		
		}else {
			if(Game.rand.nextInt(100) < 20) {
				Sound.hurt.play();
				if(Player.Invencivel == false) {
				Game.player.life-=Game.rand.nextInt(5);
				}
				Game.player.IsDamage = true;
				
		}
		}
			}*/
			
					 
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index =0;
				}			
				}	
			
			framesStop++;
			if(framesStop == maxFramesStop) {
				framesStop = 0;
				indexstop++;
				if(indexstop > maxIndexstop) {
					indexstop = 0;
				}
			}
			
			isCollidingRevolver();
			isCollidingMachineGun();
			
			if(life <= 0) {
				destroySelf();
				Sound.Clips.orc.stop();
				return;
			}
			
			if(IsDamage) {
				DamageCur++;
				if(DamageCur == DamageFrames) {
					DamageCur = 0;
					IsDamage = false;
				}
			}
		}
	
	public void destroySelf() {
		Game.entities.remove(this);
		Game.enemieshard.remove(this);
	}
	
	public void isCollidingRevolver() {
		for (int i = 0; i < Game.shootRevolver.size(); i++) {
			Entities e = Game.shootRevolver.get(i);
			if(Enemyhard.isColidding(this, e)) {
				IsDamage = true;
				life-= ShootRevolver.DanoShoot;
				Game.shootRevolver.remove(i);
				Word.generateParticleGreen(7, (int) x + 8,  (int) y + 8);
				return;
			}
		}
		
	}
	
	public void isCollidingMachineGun() {
		for (int i = 0; i < Game.shootMachineGun.size(); i++) {
			Entities e = Game.shootMachineGun.get(i);
			if(Enemyhard.isColidding(this, e)) {
				IsDamage = true;
				life-=ShootMachineGun.DanoShoot;
				Game.shootMachineGun.remove(i);
				Word.generateParticleGreen(7, (int) x + 8,  (int) y + 8);
				return;
			}
		}
		
			
		}
	
	public boolean isCollidingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx, this.getY()+ masky, maskw,maskh);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(),16,16);
		return enemyCurrent.intersects(player);
	}
	
	public boolean isColliding(int xNext, int yNext) {
		Rectangle enemyCurrent = new Rectangle(xNext + maskx, yNext+ masky, maskw,maskh);
		
		for(int i = 0; i < Game.enemieshard.size(); i++ ) {
			Enemyhard e = Game.enemieshard.get(i);
			if(e == this)
				continue;
			Rectangle targetEnemy = new Rectangle(e.getX() + maskx, e.getY() + masky, maskw,maskh);
			if(enemyCurrent.intersects(targetEnemy)){
					return true;
			}
		}
		return false;
	}
	
	public void render (Graphics g) {
		if(enemyhardmove && down == true) {
		g.drawImage(spriteenemyhard[index], this.getX() - Camera.x, this.getY() - Camera.y,null);
		}else if(enemyhardmove && up == true){
			g.drawImage(spriteenemyhardup[index], this.getX() - Camera.x, this.getY() - Camera.y,null);	
		}else if(enemyhardmove == false) {
			g.drawImage(spriteenemyhardstop[indexstop], this.getX() - Camera.x, this.getY() - Camera.y,null);
		}
		
		if(life > 0) {
			g.setColor(Color.red);
			g.fillRect(this.getX() - Camera.x , this.getY() - Camera.y -5 ,16,1);
			g.setColor(Color.green);
			g.fillRect(this.getX() - Camera.x, this.getY() - Camera.y -5,(int)((life/maxLife)*16), 1);
		}
		}
	
	}
