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

public class EnemyBall extends Entities {
	
	public static double speed = 1.0;
	private int maskx = 0, masky = 0, mwidth = 16, mheight = 16;
	private int frames = 0, maxFrames =10, index = 0, maxIndex = 3;
	public  double life = 40, maxLife = 40;
	private boolean enemyballmove = false;
	private int framesper = 0, maxframesper = 600; // 10 segundos de perseguicao
	public static boolean up = false;
	public static boolean down = false;
	public static boolean right = false;
	public static boolean left = false;
	
	public boolean IsDamage = false;
	private int DamageFrames = 10, DamageCur = 0;
	
	private BufferedImage[] EnemyBallStop;
	private BufferedImage[] EnemyBallRight;
	private BufferedImage[] EnemyBallLeft;
	private BufferedImage[] EnemyBallUp;
	private BufferedImage[] EnemyBallDown;

	public EnemyBall(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		
		EnemyBallStop = new BufferedImage[2];
		EnemyBallStop[0] = Game.spritesheet.getSprite(13*16, 8*16, 16,16);
		EnemyBallStop[1] = Game.spritesheet.getSprite(14*16, 8*16, 16,16);
		
		EnemyBallRight = new BufferedImage[4];
		EnemyBallRight[0] = Game.spritesheet.getSprite(13*16, 10*16, 16,16);
		EnemyBallRight[1] = Game.spritesheet.getSprite(14*16, 10*16, 16,16);
		EnemyBallRight[2] = Game.spritesheet.getSprite(15*16, 10*16, 16,16);
		EnemyBallRight[3] = Game.spritesheet.getSprite(14*16, 10*16, 16,16);
		
		EnemyBallLeft = new BufferedImage[4];
		EnemyBallLeft[0] = Game.spritesheet.getSprite(13*16, 9*16, 16,16);
		EnemyBallLeft[1] = Game.spritesheet.getSprite(14*16, 9*16, 16,16);
		EnemyBallLeft[2] = Game.spritesheet.getSprite(15*16, 9*16, 16,16);
		EnemyBallLeft[3] = Game.spritesheet.getSprite(14*16, 9*16, 16,16);
		
		EnemyBallUp = new BufferedImage[4];
		EnemyBallUp[0] = Game.spritesheet.getSprite(13*16, 11*16, 16,16);
		EnemyBallUp[1] = Game.spritesheet.getSprite(14*16, 11*16, 16,16);
		EnemyBallUp[2] = Game.spritesheet.getSprite(15*16, 11*16, 16,16);
		EnemyBallUp[3] = Game.spritesheet.getSprite(14*16, 11*16, 16,16);
		
		EnemyBallDown = new BufferedImage[4];
		EnemyBallDown[0] = Game.spritesheet.getSprite(13*16, 8*16, 16,16);
		EnemyBallDown[1] = Game.spritesheet.getSprite(14*16, 8*16, 16,16);
		EnemyBallDown[2] = Game.spritesheet.getSprite(15*16, 8*16, 16,16);
		EnemyBallDown[3] = Game.spritesheet.getSprite(14*16, 8*16, 16,16);
		
	}
	
	public void update() {
		depth = 1;
		//MOVIMENTACAO DO ENEMY
		if(this.CalculateDistance(getX(), getY(), Game.player.getX(),Game.player.getY()) < 60) {
			this.enemyballmove = true;
		}
		//tempo de perseguicao do inimigo
		if(enemyballmove == true) {
			//Sound.Clips.bat.loop();
			framesper++;
			if(framesper == maxframesper || (this.CalculateDistance(getX(), getY(), Game.player.getX(),Game.player.getY()) >= 150)) {
				framesper = 0;
				this.enemyballmove = false;
				//Sound.Clips.bat.stop();
			}
		}
			// perseguicao inteligente
			if(enemyballmove == true && !this.isCollidingWithPlayer()) {
			if(path == null || path.size() == 0) {
				Vector2i start = new Vector2i ((int)(x/16), (int)(y/16));
				Vector2i end = new Vector2i ((int)(Game.player.x/16), (int)(Game.player.y/16));
				path = AStar.findPath(Game.word, start, end);
			}
			if(new Random().nextInt(100) < 80) {
			followPathball(path);
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
		Game.enemyball.remove(this);
	}
	
	public void isCollidingRevolver() {
		for (int i = 0; i < Game.shootRevolver.size(); i++) {
			Entities e = Game.shootRevolver.get(i);
			if(EnemyBall.isColidding(this, e)) {
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
			if(EnemyBall.isColidding(this, e)) {
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
		
		for(int i = 0; i < Game.enemyball.size(); i++ ) {
			EnemyBall e = Game.enemyball.get(i);
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
		if(enemyballmove == false) {
			g.drawImage(EnemyBallStop[index], this.getX() - Camera.x, this.getY() - Camera.y,null);
			}else if(enemyballmove == true && down == true) {
				g.drawImage(EnemyBallDown[index], this.getX() - Camera.x, this.getY() - Camera.y,null);
			}else if(enemyballmove == true && up == true) {
				g.drawImage(EnemyBallUp[index], this.getX() - Camera.x, this.getY() - Camera.y,null);
			}else if(enemyballmove == true && right == true) {
				g.drawImage(EnemyBallRight[index], this.getX() - Camera.x, this.getY() - Camera.y,null);
			}else if(enemyballmove == true && left == true) {
				g.drawImage(EnemyBallLeft[index], this.getX() - Camera.x, this.getY() - Camera.y,null);
			}
			
		if(life > 0) {
			g.setColor(Color.red);
			g.fillRect(this.getX() - Camera.x +2 , this.getY() - Camera.y ,12,1);
			g.setColor(Color.green);
			g.fillRect(this.getX() - Camera.x +2, this.getY() - Camera.y,(int)((life/maxLife)*12), 1);
		}
	}

}
