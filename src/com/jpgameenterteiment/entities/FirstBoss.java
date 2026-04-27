package com.jpgameenterteiment.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.jpgameenterteiment.word.Camera;
import com.jpgameenterteiment.word.Word;
import com.jpsgameenterteiment.main.Game;
import com.jpsgameenterteiment.main.Sound;

public class FirstBoss extends Entities {
	
	private BufferedImage[] Boss;
	private BufferedImage[] DamageBoss;
	private double Speed = 0.3;
	public static int Life = 100;
	private int frames = 0, maxFrames =10, index = 0, maxIndex = 3;
	private int DamageFrames = 10, DamageCur = 0;
	private int maskx = 8, masky = 24, maskw = 16, maskh = 48;
	private boolean IsDamage = false;
	private boolean move = false;

	public FirstBoss(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		Boss = new BufferedImage[4];
		Boss[0] = Game.spritesheet.getSprite(2*16, 7*16, 32, 48);
		Boss[1] = Game.spritesheet.getSprite(4*16, 7*16, 32, 48);
		Boss[2] = Game.spritesheet.getSprite(6*16, 7*16, 32, 48);
		Boss[3] = Game.spritesheet.getSprite(8*16, 7*16, 32, 48);
		
		DamageBoss = new BufferedImage[4];
		DamageBoss[0] = Game.spritesheet.getSprite(2*16, 10*16, 32, 48);
		DamageBoss[1] = Game.spritesheet.getSprite(4*16, 10*16, 32, 48);
		DamageBoss[2] = Game.spritesheet.getSprite(6*16, 10*16, 32, 48);
		DamageBoss[3] = Game.spritesheet.getSprite(8*16, 10*16, 32, 48);
		
	}
	
	public void update() {
		if(isCollidingWithPlayer() == false) {
			
			if((int)x < Game.player.getX() && Word.isFree((int)(x+Speed), this.getY())
					&& !isColliding((int)(x+Speed), this.getY())) {
				x+=Speed;
			}else if ((int)x > Game.player.getX() && Word.isFree((int)(x-Speed), this.getY())
					&& !isColliding((int)(x-Speed), this.getY())){
				x-=Speed;
				}	
			if((int)y < Game.player.getY() && Word.isFree(this.getX(),(int)(y+Speed))
					&& !isColliding(this.getX(),(int)(y+Speed))) {
				y+=Speed;
			}else if ((int)y > Game.player.getY() && Word.isFree(this.getX(),(int)(y-Speed))
					&& !isColliding(this.getX(),(int)(y-Speed))){
				y-=Speed;
			}	
			}else {
				if(Game.rand.nextInt(100) < 3) {
					Sound.Clips.hurt.play();
					if(Player.Invencivel == false) {
				Game.player.life-=Game.rand.nextInt(30);
					}
				Game.player.IsDamage = true;
		
			}
			}
		
		
		this.isCollidingRevolver();
		this.isCollidingMachineGun();
		this.isCollidingWithPlayer();
		
		if(Life <= 0) {
			DestroySelf();
			return;
		}
		
		if(IsDamage) {
			DamageCur++;
			if(DamageCur == DamageFrames) {
				DamageCur = 0;
				IsDamage = false;
			}
		}
		
		frames++;
		if (frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) {
				index =0;
			}
		}		
		
	}
	
	public void DestroySelf() {
		Game.entities.remove(this);
		Game.firstboss.remove(this);
	}
	
	public void isCollidingRevolver() {
		for (int i = 0; i < Game.shootRevolver.size(); i++) {
			Entities e = Game.shootRevolver.get(i);
			if(FirstBoss.isColidding(this, e)) {
				IsDamage = true;
				Life-= ShootRevolver.DanoShoot;
				Game.shootRevolver.remove(i);
				return;
			}
		}
		
	}
	
	public void isCollidingMachineGun() {
		for (int i = 0; i < Game.shootMachineGun.size(); i++) {
			Entities e = Game.shootMachineGun.get(i);
			if(FirstBoss.isColidding(this, e)) {
				IsDamage = true;
				Life-=ShootMachineGun.DanoShoot;
				Game.shootMachineGun.remove(i);
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
		
		for(int i = 0; i < Game.firstboss.size(); i++ ) {
			FirstBoss e = Game.firstboss.get(i);
			if(e == this)
				continue;
			Rectangle targetEnemy = new Rectangle(e.getX() + maskx, e.getY() + masky, maskw,maskh);
			if(enemyCurrent.intersects(targetEnemy)){
					return true;
			}
		}
		return false;
	}
	
	public void render(Graphics g) {
		if(!IsDamage) {
			g.drawImage(Boss[index], this.getX() - Camera.x, this.getY() - Camera.y,null);
			}else {
				g.drawImage(DamageBoss[index], this.getX() - Camera.x, this.getY() - Camera.y,null);
			}
		super.render(g);
		g.setColor(Color.BLUE);
		g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y,maskx,masky);
			}
	}
