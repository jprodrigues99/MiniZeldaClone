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

public class Enemyeasy extends Entities {
	
	public static double speed = 1.0;
	private int maskx = 0, masky = 0, mwidth = 16, mheight = 16;
	private int frames = 0, maxFrames =10, index = 0, maxIndex = 1;
	public  double life = 30, maxLife = 30;
	private boolean enemyeasymove = false;
	private int framesper = 0, maxframesper = 600; // 10 segundos de perseguicao
	public static boolean up = false;
	public static boolean down = false;
	public static boolean right = false;
	public static boolean left = false;
	
	public boolean IsDamage = false;
	private int DamageFrames = 10, DamageCur = 0;
	
	private BufferedImage[] spriteenemyeasy;
	private BufferedImage[] spriteenemyeasyup;
	private BufferedImage[] spriteenemyeasyright;
	private BufferedImage[] spriteenemyeasyleft;
	private BufferedImage[] spriteenemyeasystop;

	public Enemyeasy(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, null);
		
		spriteenemyeasystop = new BufferedImage[1];
		spriteenemyeasystop[0] = Game.spritesheet.getSprite(6*16, 12*16, 16, 16);
		
		spriteenemyeasy = new BufferedImage[2];
		spriteenemyeasy[0] = Game.spritesheet.getSprite(7*16, 9*16, 16, 16);
		spriteenemyeasy[1] = Game.spritesheet.getSprite(8*16, 9*16, 16, 16);
		
		spriteenemyeasyup = new BufferedImage[2];
		spriteenemyeasyup[0] = Game.spritesheet.getSprite(7*16, 10*16, 16, 16);
		spriteenemyeasyup[1] = Game.spritesheet.getSprite(8*16, 10*16, 16, 16);
		
		spriteenemyeasyright = new BufferedImage[2];
		spriteenemyeasyright[0] = Game.spritesheet.getSprite(7*16, 12*16, 16, 16);
		spriteenemyeasyright[1] = Game.spritesheet.getSprite(8*16, 12*16, 16, 16);
		
		spriteenemyeasyleft = new BufferedImage[2];
		spriteenemyeasyleft[0] = Game.spritesheet.getSprite(7*16, 11*16, 16, 16);
		spriteenemyeasyleft[1] = Game.spritesheet.getSprite(8*16, 11*16, 16, 16);
		
	}
	
	public void update() {
		depth = 1;
		//MOVIMENTACAO DO ENEMY
		if(this.CalculateDistance(getX(), getY(), Game.player.getX(),Game.player.getY()) < 60) {
			this.enemyeasymove = true;
		}
		//tempo de perseguicao do inimigo
		if(enemyeasymove == true) {
			Sound.Clips.bat.loop();
			framesper++;
			if(framesper == maxframesper || (this.CalculateDistance(getX(), getY(), Game.player.getX(),Game.player.getY()) >= 150)) {
				framesper = 0;
				this.enemyeasymove = false;
				Sound.Clips.bat.stop();
			}
		}
			// perseguicao inteligente
			if(enemyeasymove == true && !this.isCollidingWithPlayer()) {
			if(path == null || path.size() == 0) {
				Vector2i start = new Vector2i ((int)(x/16), (int)(y/16));
				Vector2i end = new Vector2i ((int)(Game.player.x/16), (int)(Game.player.y/16));
				path = AStar.findPath(Game.word, start, end);
			}
			if(new Random().nextInt(100) < 80) {
			followPatheasy(path);
			}
			}else if (isCollidingWithPlayer() == true) {
				if(Game.rand.nextInt(100) < 5) {
					Sound.Clips.hurt.play();
					if(Player.Invencivel == false) {
				Game.player.life-=Game.rand.nextInt(3);
					}
				Game.player.IsDamage = true;
		
			}
			}
			
			 if(y <= 16) {
				 y = 16;
			 }
			 if(x >= 768) {
				 x = 768;
			 }
			 if(y >= 768) {
				 y = 768;
			 }
			
			/*
		if(isCollidingWithPlayer() == false) {
		if((int)x < Game.player.getX() && Word.isFree((int)(x+speed), this.getY())
				&& !isColliding((int)(x+speed), this.getY())) {
			x+=speed;
		}else if ((int)x > Game.player.getX() && Word.isFree((int)(x-speed), this.getY())
				&& !isColliding((int)(x-speed), this.getY())){
			x-=speed;
			}	
		if((int)y < Game.player.getY() && Word.isFree(this.getX(),(int)(y+speed))
				&& !isColliding(this.getX(),(int)(y+speed))) {
			y+=speed;
		}else if ((int)y > Game.player.getY() && Word.isFree(this.getX(),(int)(y-speed))
				&& !isColliding(this.getX(),(int)(y-speed))){
			y-=speed;
		}
	
		}else {
			if(Game.rand.nextInt(100) < 5) {
				Sound.hurt.play();
				if(Player.Invencivel == false) {
			Game.player.life-=Game.rand.nextInt(3);
				}
			Game.player.IsDamage = true;
	
		}
		}*/
		//else {
			//fazer movimentacao do inimigo parado
		//}
		
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index =0;
				}
			}		
			
			isCollidingRevolver();
			isCollidingMachineGun();
			
			if(life <= 0) {
				destroySelf();
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
		Game.enemies.remove(this);
	}
	
	public void isCollidingRevolver() {
		for (int i = 0; i < Game.shootRevolver.size(); i++) {
			Entities e = Game.shootRevolver.get(i);
			if(Enemyeasy.isColidding(this, e)) {
				IsDamage = true;
				life-= ShootRevolver.DanoShoot;
				System.out.println(life);
				Game.shootRevolver.remove(i);
				Word.generateParticleBlood(7, (int) x + 8,  (int) y + 8);
				return;
			}
		}
		
	}
	
	public void isCollidingMachineGun() {
		for (int i = 0; i < Game.shootMachineGun.size(); i++) {
			Entities e = Game.shootMachineGun.get(i);
			if(Enemyeasy.isColidding(this, e)) {
				IsDamage = true;
				life-=ShootMachineGun.DanoShoot;
				Game.shootMachineGun.remove(i);
				Word.generateParticleBlood(7, (int) x + 8,  (int) y + 8);
				return;
			}
		}
		
	}
	
	public boolean isCollidingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx, this.getY()+ masky, mwidth,mheight);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(),16,16);
		return enemyCurrent.intersects(player);
	}
	
	
	public boolean isColliding(int xNext, int yNext) {
		Rectangle enemyCurrent = new Rectangle(xNext + maskx, yNext+ masky, mwidth,mheight);
		
		for(int i = 0; i < Game.enemies.size(); i++ ) {
			Enemyeasy e = Game.enemies.get(i);
			if(e == this)
				continue;
			Rectangle targetEnemy = new Rectangle(e.getX() + maskx, e.getY() + masky, mwidth,mheight);
			if(enemyCurrent.intersects(targetEnemy)){
					return true;
			}
		}
		return false;
	}
	
	public void render(Graphics g) {
		if(enemyeasymove == false) {
		g.drawImage(spriteenemyeasystop[0], this.getX() - Camera.x, this.getY() - Camera.y,null);
		}else if(enemyeasymove == true && down == true) {
			g.drawImage(spriteenemyeasy[index], this.getX() - Camera.x, this.getY() - Camera.y,null);
		}else if(enemyeasymove == true && up == true) {
			g.drawImage(spriteenemyeasyup[index], this.getX() - Camera.x, this.getY() - Camera.y,null);
		}else if(enemyeasymove == true && right == true) {
			g.drawImage(spriteenemyeasyright[index], this.getX() - Camera.x, this.getY() - Camera.y,null);
		}else if(enemyeasymove == true && left == true) {
			g.drawImage(spriteenemyeasyleft[index], this.getX() - Camera.x, this.getY() - Camera.y,null);
		}
		
	if(life > 0) {
		g.setColor(Color.red);
		g.fillRect(this.getX() - Camera.x +2 , this.getY() - Camera.y ,12,1);
		g.setColor(Color.green);
		g.fillRect(this.getX() - Camera.x +2, this.getY() - Camera.y,(int)((life/maxLife)*12), 1);
	}
		
		   //g.setColor(Color.red);
		   // g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, mwidth, height);
		}
		}
