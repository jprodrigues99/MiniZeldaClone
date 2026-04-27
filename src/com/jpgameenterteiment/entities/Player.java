package com.jpgameenterteiment.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import com.jpgameenterteiment.word.*;
import com.jpgameenterteiment.graficos.Spritesheet;
import com.jpgameenterteiment.word.Camera;

import com.jpgameenterteiment.word.WallTile;
import com.jpgameenterteiment.word.Word;
import com.jpsgameenterteiment.main.Game;
import com.jpsgameenterteiment.main.Sound;

public class Player extends Entities{

	public boolean right, up, left, down;
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	public double speed = 1.0;
	public boolean moved = false;
	private int frames = 0, maxFrames =7, index = 0, maxIndex = 3;
	private int framesinvencivel = 0, framesinvencivelmax = 600;
	private int maskx = 0, masky = 0, maskw = 8, maskh = 8;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	
	private BufferedImage[] rightPlayerRev;
	private BufferedImage[] leftPlayerRev;
	private BufferedImage[] upPlayerRev;
	private BufferedImage[] downPlayerRev;
	
	private BufferedImage[] rightPlayerMg;
	private BufferedImage[] leftPlayerMg;
	private BufferedImage[] upPlayerMg;
	private BufferedImage[] downPlayerMg;
	
	private BufferedImage rightPlayerRevShoot;
	private BufferedImage leftPlayerRevShoot;
	private BufferedImage downPlayerRevShoot;
	private BufferedImage upPlayerRevShoot;
	
	private BufferedImage rightPlayerRevShootCrouch;
	private BufferedImage rightPlayerRevShootCrouch2;
	private BufferedImage leftPlayerRevShootCrouch;
	private BufferedImage leftPlayerRevShootCrouch2;
	private BufferedImage downPlayerRevShootCrouch;
	private BufferedImage downPlayerRevShootCrouch2;
	private BufferedImage upPlayerRevShootCrouch;
	private BufferedImage upPlayerRevShootCrouch2;
	
	private BufferedImage rightPlayerMgShoot;
	private BufferedImage leftPlayerMgShoot;
	private BufferedImage downPlayerMgShoot;
	private BufferedImage upPlayerMgShoot;
	
	private BufferedImage rightPlayerMgShootCrouch;
	private BufferedImage rightPlayerMgShootCrouch2;
	private BufferedImage leftPlayerMgShootCrouch;
	private BufferedImage leftPlayerMgShootCrouch2;
	private BufferedImage downPlayerMgShootCrouch;
	private BufferedImage downPlayerMgShootCrouch2;
	private BufferedImage upPlayerMgShootCrouch;
	private BufferedImage upPlayerMgShootCrouch2;
	
	private BufferedImage rightPlayerDamage;
	private BufferedImage rightPlayerDamage2;
	private BufferedImage rightPlayerDamage3;
	private BufferedImage leftPlayerDamage;
	private BufferedImage leftPlayerDamage2;
	private BufferedImage leftPlayerDamage3;
	private BufferedImage upPlayerDamage;
	private BufferedImage upPlayerDamage2;
	private BufferedImage upPlayerDamage3;
	private BufferedImage downPlayerDamage;
	private BufferedImage downPlayerDamage2;
	private BufferedImage downPlayerDamage3;
	
	private BufferedImage rightPlayerDamagerev;
	private BufferedImage rightPlayerDamagerev2;
	private BufferedImage rightPlayerDamagerev3;
	private BufferedImage leftPlayerDamagerev;
	private BufferedImage leftPlayerDamagerev2;
	private BufferedImage leftPlayerDamagerev3;
	private BufferedImage upPlayerDamagerev;
	private BufferedImage upPlayerDamagerev2;
	private BufferedImage upPlayerDamagerev3;
	private BufferedImage downPlayerDamagerev;
	private BufferedImage downPlayerDamagerev2;
	private BufferedImage downPlayerDamagerev3;
	
	private BufferedImage rightPlayerDamagemg;
	private BufferedImage rightPlayerDamagemg2;
	private BufferedImage rightPlayerDamagemg3;
	private BufferedImage leftPlayerDamagemg;
	private BufferedImage leftPlayerDamagemg2;
	private BufferedImage leftPlayerDamagemg3;
	private BufferedImage upPlayerDamagemg;
	private BufferedImage upPlayerDamagemg2;
	private BufferedImage upPlayerDamagemg3;
	private BufferedImage downPlayerDamagemg;
	private BufferedImage downPlayerDamagemg2;
	private BufferedImage downPlayerDamagemg3;
	
	public static boolean Revolver = false;
	public static boolean MachineGun = false;
	public static boolean packrev = false;
	public static boolean packmg = false;
	
	public static int packlife = 0;
	public static int packbiglife = 0;
	
	public static int ammo = 0;
	public static int ammoMachineGun = 0;
	
	public boolean shootRevolver = false;
	public static boolean shootRevolverRender = false;
	public static boolean shootMachineGunRender = false;
	public boolean shootMachineGun = false;
	public boolean MouseShoot = false;
	
	public boolean IsDamage = false;
	private int DamageFrames = 0;
	
	private boolean Gate = false;
	
	public static double life = 100, maxLife = 100;
	public int mx,my;
	
	public boolean jump = false;
	public static int z = 0;
	public int JumpFrames = 30, JumpCur = 0;
	public boolean JumpUp = false, JumpDown = false;
	public boolean IsJumping = false;
	public double JumpSpeed = 2;
	private int walking = 0, maxWalking = 8;
	
	public static boolean Invencivel = false;
	
	
	public Player(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, sprite);
		
		//POSICAO DOS SPRITES DE ANIMACAO
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		downPlayer = new BufferedImage[4];
		
		rightPlayerRev = new BufferedImage[4];
		leftPlayerRev = new BufferedImage[4];
		upPlayerRev = new BufferedImage[4];
		downPlayerRev = new BufferedImage[4];
		
		rightPlayerMg = new BufferedImage[4];
		leftPlayerMg = new BufferedImage[4];
		upPlayerMg = new BufferedImage[4];
		downPlayerMg = new BufferedImage[4];
		
		//rightRevShoot = Game.spritesheet.getSprite(16*13, 0, 16,16 );
		//leftRevShoot = Game.spritesheet.getSprite(16*14, 0, 16,16 );
	//	downRevShoot = Game.spritesheet.getSprite(16*15, 0, 16,16 );
		
		rightPlayerRevShoot = Game.spritesheet.getSprite(2*16, 9*16, 16,16);
		rightPlayerRevShootCrouch = Game.spritesheet.getSprite(16, 9*16, 16, 16);
		rightPlayerRevShootCrouch2 = Game.spritesheet.getSprite(3*16, 9*16, 16, 16);
		leftPlayerRevShoot = Game.spritesheet.getSprite(16, 10*16, 16,16);
		leftPlayerRevShootCrouch = Game.spritesheet.getSprite(0, 10*16, 16, 16);
		leftPlayerRevShootCrouch2 = Game.spritesheet.getSprite(2*16, 10*16, 16, 16);
		downPlayerRevShoot = Game.spritesheet.getSprite(16*2, 8*16, 16,16);
		downPlayerRevShootCrouch = Game.spritesheet.getSprite(16, 8*16, 16, 16);
		downPlayerRevShootCrouch2 = Game.spritesheet.getSprite(16*3, 8*16, 16, 16);
		upPlayerRevShoot = Game.spritesheet.getSprite(2*16, 11*16, 16,16);
		upPlayerRevShootCrouch = Game.spritesheet.getSprite(16, 11*16, 16, 16);
		upPlayerRevShootCrouch2 = Game.spritesheet.getSprite(16*3, 11*16, 16, 16);
		
		rightPlayerMgShoot = Game.spritesheet.getSprite(2*16, 18*16, 16,16);
		rightPlayerMgShootCrouch = Game.spritesheet.getSprite(16, 18*16, 16, 16);
		rightPlayerMgShootCrouch2 = Game.spritesheet.getSprite(3*16, 18*16, 16, 16);
		leftPlayerMgShoot = Game.spritesheet.getSprite(16, 19*16, 16,16);
		leftPlayerMgShootCrouch = Game.spritesheet.getSprite(0, 19*16, 16, 16);
		leftPlayerMgShootCrouch2 = Game.spritesheet.getSprite(2*16, 19*16, 16, 16);
		downPlayerMgShoot = Game.spritesheet.getSprite(2*16, 16*16, 16,16);
		downPlayerMgShootCrouch = Game.spritesheet.getSprite(16, 16*16, 16, 16);
		downPlayerMgShootCrouch2 = Game.spritesheet.getSprite(3*16, 16*16, 16, 16);
		upPlayerMgShoot = Game.spritesheet.getSprite(2*16, 17*16, 16,16);
		upPlayerMgShootCrouch = Game.spritesheet.getSprite(16, 17*16, 16, 16);
		upPlayerMgShootCrouch2 = Game.spritesheet.getSprite(3*16, 17*16, 16, 16);
		
		//POSICAO DOS SPRITES DE DANO
		rightPlayerDamage = Game.spritesheet.getSprite(4*16, 0, 16, 16);
		rightPlayerDamage2 = Game.spritesheet.getSprite(5*16, 0, 16, 16);
		rightPlayerDamage3 = Game.spritesheet.getSprite(6*16, 0, 16, 16);
		leftPlayerDamage = Game.spritesheet.getSprite(4*16, 16, 16, 16);
		leftPlayerDamage2 = Game.spritesheet.getSprite(5*16, 16, 16, 16);
		leftPlayerDamage3 = Game.spritesheet.getSprite(6*16, 16, 16, 16);
		upPlayerDamage = Game.spritesheet.getSprite(4*16, 3*16, 16, 16);
		upPlayerDamage2 = Game.spritesheet.getSprite(5*16, 3*16, 16, 16);
		upPlayerDamage3 = Game.spritesheet.getSprite(6*16, 3*16, 16, 16);
		downPlayerDamage = Game.spritesheet.getSprite(4*16, 2*16, 16, 16);
		downPlayerDamage2 = Game.spritesheet.getSprite(5*16, 2*16, 16, 16);
		downPlayerDamage3 = Game.spritesheet.getSprite(6*16, 2*16, 16, 16);
		
		rightPlayerDamagerev = Game.spritesheet.getSprite(4*16, 6*16, 16, 16);
		rightPlayerDamagerev2 = Game.spritesheet.getSprite(5*16, 6*16, 16, 16);
		rightPlayerDamagerev3 = Game.spritesheet.getSprite(6*16, 6*16, 16, 16);
		leftPlayerDamagerev = Game.spritesheet.getSprite(4*16, 7*16, 16, 16);
		leftPlayerDamagerev2 = Game.spritesheet.getSprite(5*16, 7*16, 16, 16);
		leftPlayerDamagerev3 = Game.spritesheet.getSprite(6*16, 7*16, 16, 16);
		upPlayerDamagerev = Game.spritesheet.getSprite(4*16, 5*16, 16, 16);
		upPlayerDamagerev2 = Game.spritesheet.getSprite(5*16, 5*16, 16, 16);
		upPlayerDamagerev3 = Game.spritesheet.getSprite(6*16, 5*16, 16, 16);
		downPlayerDamagerev = Game.spritesheet.getSprite(4*16, 4*16, 16, 16);
		downPlayerDamagerev2 = Game.spritesheet.getSprite(5*16, 4*16, 16, 16);
		downPlayerDamagerev3 = Game.spritesheet.getSprite(6*16, 4*16, 16, 16);
		
		rightPlayerDamagemg = Game.spritesheet.getSprite(4*16, 16*10, 16, 16);
		rightPlayerDamagemg2 = Game.spritesheet.getSprite(5*16, 16*10, 16, 16);
		rightPlayerDamagemg3 = Game.spritesheet.getSprite(6*16, 16*10, 16, 16);
		leftPlayerDamagemg = Game.spritesheet.getSprite(4*16, 16*11, 16, 16);
		leftPlayerDamagemg2 = Game.spritesheet.getSprite(5*16, 16*11, 16, 16);
		leftPlayerDamagemg3 = Game.spritesheet.getSprite(6*16, 16*11, 16, 16);
		upPlayerDamagemg = Game.spritesheet.getSprite(4*16, 16*9, 16, 16);
		upPlayerDamagemg2 = Game.spritesheet.getSprite(5*16, 16*9, 16, 16);
		upPlayerDamagemg3 = Game.spritesheet.getSprite(6*16, 16*9, 16, 16);
		downPlayerDamagemg = Game.spritesheet.getSprite(4*16, 16*8, 16, 16);
		downPlayerDamagemg2 = Game.spritesheet.getSprite(5*16, 16*8, 16, 16);
		downPlayerDamagemg3 = Game.spritesheet.getSprite(6*16, 16*8, 16, 16);
		
		
		
		
		//ANIMACAO PLAYER
		for(int i =0; i<4; i++) {
		rightPlayer[i] = Game.spritesheet.getSprite(0 + (i*16),0, 16, 16);
		}
		for(int i =0; i<4; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(0 + (i*16),16, 16, 16);
			}
		for(int i =0; i<4; i++) {
			upPlayer[i] = Game.spritesheet.getSprite(0 + (i*16), 48, 16, 16);
			}
		for(int i =0; i<4; i++) {
			downPlayer[i] = Game.spritesheet.getSprite(0 + (i*16), 32,16, 16);
			}
		//ANIMACAO PLAYER COM REVOLVER
		for(int i =0; i<4; i++) {
			rightPlayerRev[i] = Game.spritesheet.getSprite(0 + (i*16),6*16, 16, 16);
			}
			for(int i =0; i<4; i++) {
				leftPlayerRev[i] = Game.spritesheet.getSprite(0 + (i*16),7*16, 16, 16);
				}
			for(int i =0; i<4; i++) {
				upPlayerRev[i] = Game.spritesheet.getSprite(0 + (i*16),5*16, 16, 16);
				}
			for(int i =0; i<4; i++) {
				downPlayerRev[i] = Game.spritesheet.getSprite(0 + (i*16), 4*16,16, 16);
				}
			
			//ANIMACAO PLAYER COM MACHINE GUN
			for(int i =0; i<4; i++) {
				rightPlayerMg[i] = Game.spritesheet.getSprite(0 + (i*16),14*16, 16, 16);
				}
				for(int i =0; i<4; i++) {
					leftPlayerMg[i] = Game.spritesheet.getSprite(0 + (i*16),15*16, 16, 16);
					}
				for(int i =0; i<4; i++) {
					upPlayerMg[i] = Game.spritesheet.getSprite(0 + (i*16),13*16, 16, 16);
					}
				for(int i =0; i<4; i++) {
					downPlayerMg[i] = Game.spritesheet.getSprite(0 + (i*16), 12*16, 16, 16);
					}
			
		
	}
	
	public void update() {
		depth = 1;
		
		if(jump){
			if(IsJumping == false) {
				jump = false;
				IsJumping = true;
				JumpUp = true;
			}
		}
		
		if (IsJumping == true) {
			
				if(JumpUp) {
				JumpCur+=JumpSpeed;
				}else if(JumpDown) {
					JumpCur-=JumpSpeed;
					if(JumpCur <= 0) {
						IsJumping = false;
						JumpDown = false;
						JumpUp = false;
					}
				}
				z = JumpCur;
				if(JumpCur >= JumpFrames ) {
					JumpUp = false;
					JumpDown = true;
				}
			}
		
		moved = false;
		if(right && Word.isFree(this.getX()+(int)(speed), this.getY())) {
			dir = right_dir;
			moved = true;
			if(moved = true) {
				walking++;
				if(walking == maxWalking) {
					walking = 0;
					//Sound.Walking.play();
				}
			}
			x+= speed;
			}
		else if(left && Word.isFree(this.getX()-(int)(speed), this.getY())) {
			dir = left_dir;
			moved = true;
			if(moved = true) {
				walking++;
				if(walking == maxWalking) {
					walking = 0;
					//Sound.Walking.play();
				}
			}
			x-= speed;
			}
		 if(up && Word.isFree(this.getX(), this.getY()-(int)(speed))) {
			dir = up_dir;
			moved = true;
			if(moved = true) {
				walking++;
				if(walking == maxWalking) {
					walking = 0;
					//Sound.Walking.play();
				}
			}
			y-= speed;
			}
		else if(down && Word.isFree(this.getX(), this.getY()+(int)(speed))) {
			dir = down_dir;
			moved = true;
			if(moved = true) {
				walking++;
				if(walking == maxWalking) {
					walking = 0;
					//Sound.Walking.play();
				}
			}
			y+= speed;
			}
		 //RESOLVENDO BUG DE QUANDO PLAYER CHEGA NO TILE SUPERIOR
		 //por algum motivo o y chevaga a 15.9999 o que resultava na colisao do player com a tile o que impedia o movimento.
		 if(y <= 32) {
			 y = 32;
		 }
		 if(x >= 768) {
			 x = 768;
		 }
		 if(y >= 736) {
			 y = 736;
		 }
		 if(y > 675) {
			 Sound.Clips.river.loop();
		 }else {
			 Sound.Clips.river.stop();
		 }
		 
		 if(life > 100) {
			 life = 100;
		 }
		 
	
		if(moved) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index =0;
				}
			}			
		}
		
		CheckCollisionLifePack();
		CheckCollisionBigLifePack();
		CheckCollisionAmmo();
		CheckCollisionAmmoMachineGUn();
		CheckCollisionRevolver();
		CheckCollisionMachineGun();
		CheckCollisionInvencibilidade();
		
		if(Invencivel) {
			framesinvencivel++;
			if(framesinvencivel == framesinvencivelmax) {
				framesinvencivel = 0;
				Sound.Clips.Powerdown.play();
				Invencivel = false;
				
			}
		}
		
		if(IsDamage){
			DamageFrames++;
			if(DamageFrames == 4 ) {
				DamageFrames = 0;
				IsDamage = false;
			}
		}
		
		if(shootRevolver && Revolver && ammo > 0) {
			Sound.Clips.pistolshoot.play();
			//Sound.Clips.shootrevolver.play();
			ammo --;
			shootRevolver = false;
			int dx = 0;
			int dy = 0;
			int posicaobulletx = 0;
			int posicaobullety = 0;
			if (index == 0 && dir == right_dir ) {
				 dx = 1;
				 posicaobulletx = 13;
				 posicaobullety = 5;
			}if (index == 2 && dir == right_dir ) {
				 dx = 1;
				 posicaobulletx = 13;
				 posicaobullety = 5;
			}if (index == 1 && dir == right_dir ) {
				 dx = 1;
				 posicaobulletx = 13;
				 posicaobullety = 6;
			}if (index == 3 && dir == right_dir ) {
				 dx = 1;
				 posicaobulletx = 13;
				 posicaobullety = 6;
			}if (index == 0 && dir == left_dir) {
				 dx = -1;
				 posicaobulletx = 1;
				 posicaobullety = 6;
			}if (index == 2 && dir == left_dir) {
				 dx = -1;
				 posicaobulletx = 1;
				 posicaobullety = 6;
			}if (index == 1 && dir == left_dir) {
				 dx = -1;
				 posicaobulletx = 1;
				 posicaobullety = 5;
			}if (index == 3 && dir == left_dir) {
				 dx = -1;
				 posicaobulletx = 1;
				 posicaobullety = 5;
			} if(dir == up_dir) {
				dy = -1;
				posicaobulletx = 10;
				posicaobullety = 1;
			}if (dir == down_dir) {
				dy = 1;
				posicaobulletx = 3;
				posicaobullety = 7;
			}
			
			ShootRevolver shootRevolver = new ShootRevolver(this.getX() + posicaobulletx , this.getY() + posicaobullety, 2,2,null,dx,dy);
			Game.shootRevolver.add(shootRevolver);
		} else if(shootRevolver && Revolver && ammo == 0){
			Sound.Clips.ammoempity.play();
			shootRevolver = false;
		}
		
		if(shootMachineGun && MachineGun && ammoMachineGun > 0) {
			//Sound.Clips.shootmachinegun.play();
			Sound.Clips.machinegunshoot.play();
			ammoMachineGun --;
			shootMachineGun = false;
			int dx = 0;
			int dy = 0;
			int posicaobulletMachineGunx = 0;
			int posicaobulletMachineGuny = 0;
			if (index == 0 && dir == right_dir) {
				 dx = 1;
				 posicaobulletMachineGunx = 12;
				 posicaobulletMachineGuny = 7;
			}if (index == 1 && dir == right_dir) {
				 dx = 1;
				 posicaobulletMachineGunx = 12;
				 posicaobulletMachineGuny = 8;
			}if (index == 2 && dir == right_dir) {
				 dx = 1;
				 posicaobulletMachineGunx = 12;
				 posicaobulletMachineGuny = 7;
			}if (index == 3 && dir == right_dir) {
				 dx = 1;
				 posicaobulletMachineGunx = 12;
				 posicaobulletMachineGuny = 8;
			}if (index == 0 && dir == left_dir) {
				 dx = -1;
				 posicaobulletMachineGunx = 1;
				 posicaobulletMachineGuny = 8;
			}if (index == 1 && dir == left_dir) {
				 dx = -1;
				 posicaobulletMachineGunx = 1;
				 posicaobulletMachineGuny = 7;
			}if (index == 2 && dir == left_dir) {
				 dx = -1;
				 posicaobulletMachineGunx = 1;
				 posicaobulletMachineGuny = 8;
			}if (index == 3 && dir == left_dir) {
				 dx = -1;
				 posicaobulletMachineGunx = 1;
				 posicaobulletMachineGuny = 7;
			} else if(dir == up_dir) {
				dy = -1;
				posicaobulletMachineGunx = 9;
				 posicaobulletMachineGuny = 2;
			} else if (dir == down_dir) {
				dy = 1;
				posicaobulletMachineGunx = 5;
				 posicaobulletMachineGuny = 6;
			}
			
			ShootMachineGun shootMachineGun = new ShootMachineGun(this.getX() + posicaobulletMachineGunx, this.getY() + posicaobulletMachineGuny, 2,2,null,dx,dy);
			Game.shootMachineGun.add(shootMachineGun);
			
		} else if (shootMachineGun && MachineGun && ammoMachineGun == 0) {
			Sound.Clips.ammoempity.play();
			shootMachineGun = false;
		}
		
		if(MouseShoot && MachineGun && ammoMachineGun > 0) {	
			Sound.Clips.shootmachinegun.play();
			MouseShoot = false;
			ammoMachineGun --;
			double angle = Math.atan2(my - (this.getY()+8 - Camera.y), mx - (this.getX()+8 + Camera.x));
			
			double dx = Math.cos(angle);
			double dy = Math.sin(angle);
			int posicaobulletMachineGunx = 0;
			int posicaobulletMachineGuny = 0;
			if (dir == right_dir) {
				 dx = 1;
				 posicaobulletMachineGunx = 19;
				 posicaobulletMachineGuny = 5;
			} else if (dir == left_dir) {
				 dx = -1;
				 posicaobulletMachineGunx = -2;
				 posicaobulletMachineGuny = 4;
			} else if(dir == up_dir) {
				dy = -1;
				posicaobulletMachineGunx = 12;
				 posicaobulletMachineGuny = -4;
			} else if (dir == down_dir) {
				dy = 1;
				posicaobulletMachineGunx = 3;
				 posicaobulletMachineGuny = 20;
			}
			
			ShootMachineGun shootMachineGun = new ShootMachineGun(this.getX() + posicaobulletMachineGunx, this.getY() + posicaobulletMachineGuny, 2,2,null,dx,dy);
			Game.shootMachineGun.add(shootMachineGun);
			
		} else if(MouseShoot && MachineGun && ammoMachineGun == 0){
			Sound.Clips.ammoempity.play();
			MouseShoot = false;
		}
		
		if(MouseShoot && Revolver && ammo > 0) {	
			Sound.Clips.shootrevolver.play();
			MouseShoot = false;
			ammo--;
			double angle = Math.atan2(my - (this.getY()+8 - Camera.y), mx - (this.getX()+8 + Camera.x));
			
			double dx = Math.cos(angle);
			double dy = Math.sin(angle);
			int posicaobulletx = 0;
			int posicaobullety = 0;
			if (dir == right_dir) {
				 dx = 1;
				 posicaobulletx = 18;
				 posicaobullety = 4;
			} else if (dir == left_dir) {
				 dx = -1;
				 posicaobulletx = -4;
				 posicaobullety = 4;
			} else if(dir == up_dir) {
				dy = -1;
				posicaobulletx = 12;
				posicaobullety = 0;
			} else if (dir == down_dir) {
				dy = 1;
				posicaobulletx = 4;
				posicaobullety = 15;
			}
			
			ShootRevolver shootRevolver = new ShootRevolver(this.getX() + posicaobulletx, this.getY() + posicaobullety, 2,2,null,dx,dy);
			Game.shootRevolver.add(shootRevolver);
			
		}else if(MouseShoot && Revolver && ammo == 0) {
			Sound.Clips.ammoempity.play();
			MouseShoot = false;
		}
			
		
	    if(life <=0) {
        Game.GameState = "GAME OVER";
        Sound.Clips.GameOver.play();
	    }
	    
	    if(life >= 10 && life <=30) {
	    	speed = 1.3;
	    
	    }else if(life < 10) {
	    	speed = 1.4;
	    	
	    }else {
	    	speed = 1.2;
	    }
		
	    updateCamera();
		
		}
	
	public void updateCamera() {
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0,Word.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2),0,Word.HEIGHT*16 - Game.HEIGHT);
	}
	
	//DESCARTANDO ARMAS

	
	//COLETANDO LIFEPACK
	public void CheckCollisionLifePack() {
		if(packlife < 2)
		for(int i =0; i < Game.entities.size(); i++) {
			Entities atual = Game.entities.get(i);
			if(atual instanceof LifePack) {
				if(Entities.isColidding(this, atual)) {
					Sound.Clips.life.play();
					packlife+=1;
					//life+=20;
					Game.entities.remove(atual);
					if(life > 100) {
						life = 100;
					}
				}
			}
		}
	}
	
	//COLETANDO BIGLIFEPACK
		public void CheckCollisionBigLifePack() {
			if(packbiglife < 2)
			for(int i =0; i < Game.entities.size(); i++) {
				Entities atual = Game.entities.get(i);
				if(atual instanceof BigLifePack) {
					if(Entities.isColidding(this, atual)) {
						Sound.Clips.life.play();
						packbiglife+=1;
						//life+=80;
						Game.entities.remove(atual);
						if(life > 100) {
							life = 100;
						}
					}
				}
			}
		}
		//COLETANDO AMMO REVOLVER
		public void CheckCollisionAmmo() {
			if(ammo < 36)
			for(int i =0; i < Game.entities.size(); i++) {
				Entities atual = Game.entities.get(i);
				if(atual instanceof Bullet) {
					if(Entities.isColidding(this, atual)) {
						Sound.Clips.ammo.play();
						ammo+=12;
						Game.entities.remove(atual);
						if(ammo > 36) {
							ammo = 36;
						}
					}
				}
			}
		}
		
		//COLETANDO AMMO MACHINEGUN
				public void CheckCollisionAmmoMachineGUn() {
					if(ammoMachineGun < 90)
					for(int i =0; i < Game.entities.size(); i++) {
						Entities atual = Game.entities.get(i);
						if(atual instanceof BulletMachineGun) {
							if(Entities.isColidding(this, atual)) {
								Sound.Clips.ammo.play();
								ammoMachineGun+=30;
								Game.entities.remove(atual);
								if(ammoMachineGun > 90) {
									ammoMachineGun = 90;
								}
							}
						}
					}
				}
		
		//COLETANDO REVOLVER
		public void CheckCollisionRevolver() {
			if(!packrev)
			for(int i =0; i < Game.entities.size(); i++) {
				Entities atual = Game.entities.get(i);
				if(atual instanceof Revolver) {
					if(Entities.isColidding(this, atual)) {
					//Sound.Clips.revolver.play();
						Sound.Clips.pistolcock.play();
						packrev = true;				
						Revolver = true;
						MachineGun = false;
						ammo+= 6;
						Game.entities.remove(atual);
						
						}
					}
				}
			}
		
		//COLETANDO MACHINE GUN
				public void CheckCollisionMachineGun() {
					if(!packmg)
					for(int i =0; i < Game.entities.size(); i++) {
						Entities atual = Game.entities.get(i);
						if(atual instanceof MachineGun) {
							if(Entities.isColidding(this, atual)) {
								Sound.Clips.machinegun.play();
								packmg = true;
								MachineGun = true;
								Revolver = false;
								ammoMachineGun+=30;
								Game.entities.remove(atual);
							
								
								
								}
							}
						}
					}
				
				//COLETANDO INVENCIVEL
				public void CheckCollisionInvencibilidade() {
					for(int i =0; i < Game.entities.size(); i++) {
						Entities atual = Game.entities.get(i);
						if(atual instanceof Invencibilidade) {
							if(Entities.isColidding(this, atual)) {
								Sound.Clips.Powerup.play();
								Invencivel = true;
								Game.entities.remove(atual);
								
							}
						}
					}
				}
					
	
	
	public void render(Graphics g) {
		if(!IsDamage && Revolver == false && MachineGun == false) {
			if(dir == right_dir) {
				g.drawImage(rightPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}else if(dir == left_dir) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}else if(dir == up_dir) {
			g.drawImage(upPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}else if(dir == down_dir) {
			g.drawImage(downPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);	
		}
		}
		
		if(!IsDamage && Revolver == true && shootRevolver == false && shootRevolverRender == false) {
				if(dir == right_dir) {
					g.drawImage(rightPlayerRev[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);
			}else if(dir == left_dir) {
				g.drawImage(leftPlayerRev[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);
			}else if(dir == up_dir) {
				g.drawImage(upPlayerRev[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);
			}else if(dir == down_dir) {
				g.drawImage(downPlayerRev[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);	
			}
			}
		
		if(dir == right_dir && shootRevolverRender == true && ammo > 0 && index == 0) {
			g.drawImage(rightPlayerRevShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		if(dir == right_dir && shootRevolverRender == true && ammo > 0 && index == 2) {
			g.drawImage(rightPlayerRevShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		if(dir == right_dir && shootRevolverRender == true && ammo > 0 && index == 1) {
			g.drawImage(rightPlayerRevShootCrouch, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		if(dir == right_dir && shootRevolverRender == true && ammo > 0 && index == 3) {
			g.drawImage(rightPlayerRevShootCrouch2, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}	
		
		if(dir == right_dir && shootRevolverRender == true && ammo == 0) {
			g.drawImage(rightPlayerRev[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		
		
		if(dir == left_dir && shootRevolverRender == true && ammo > 0 && index == 1) {
			g.drawImage(leftPlayerRevShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		if(dir == left_dir && shootRevolverRender == true && ammo > 0 && index == 3) {
			g.drawImage(leftPlayerRevShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		if(dir == left_dir && shootRevolverRender == true && ammo > 0 && index == 0) {
			g.drawImage(leftPlayerRevShootCrouch, this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		if(dir == left_dir && shootRevolverRender == true && ammo > 0 && index == 2) {
			g.drawImage(leftPlayerRevShootCrouch2, this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		
		if(dir == left_dir && shootRevolverRender == true && ammo == 0 ) {
			g.drawImage(leftPlayerRev[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		
		
		if(dir == down_dir && shootRevolverRender == true && ammo > 0 && index == 0) {
			g.drawImage(downPlayerRevShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		if(dir == down_dir && shootRevolverRender == true && ammo > 0 && index == 2) {
			g.drawImage(downPlayerRevShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		if(dir == down_dir && shootRevolverRender == true && ammo > 0 && index == 1) {
			g.drawImage(downPlayerRevShootCrouch, this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		if(dir == down_dir && shootRevolverRender == true && ammo > 0 && index == 3) {
			g.drawImage(downPlayerRevShootCrouch2, this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		
		if(dir == down_dir && shootRevolverRender == true && ammo == 0 ) {
			g.drawImage(downPlayerRev[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		
		
		if(dir == up_dir && shootRevolverRender == true && ammo > 0 && index == 0) {
			g.drawImage(upPlayerRevShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		if(dir == up_dir && shootRevolverRender == true && ammo > 0 && index == 2) {
			g.drawImage(upPlayerRevShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		if(dir == up_dir && shootRevolverRender == true && ammo > 0 && index == 1) {
			g.drawImage(upPlayerRevShootCrouch, this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		if(dir == up_dir && shootRevolverRender == true && ammo > 0 && index == 3) {
			g.drawImage(upPlayerRevShootCrouch2, this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		
		if(dir == up_dir && shootRevolverRender == true && ammo == 0 ) {
			g.drawImage(upPlayerRev[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		
		if(!IsDamage && MachineGun == true && shootMachineGun == false && shootMachineGunRender == false) {
			if(dir == right_dir) {
				g.drawImage(rightPlayerMg[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}else if(dir == left_dir) {
			g.drawImage(leftPlayerMg[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}else if(dir == up_dir) {
			g.drawImage(upPlayerMg[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}else if(dir == down_dir) {
			g.drawImage(downPlayerMg[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);	
		}
		}
		
		if(dir == right_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 0) {
			g.drawImage(rightPlayerMgShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		if(dir == right_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 2) {
			g.drawImage(rightPlayerMgShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		if(dir == right_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 1) {
			g.drawImage(rightPlayerMgShootCrouch, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		if(dir == right_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 3) {
			g.drawImage(rightPlayerMgShootCrouch2, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}	
		
		if(dir == right_dir && shootMachineGunRender == true && ammoMachineGun == 0) {
			g.drawImage(rightPlayerMg[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		
		if(dir == left_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 1) {
			g.drawImage(leftPlayerMgShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		if(dir == left_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 3) {
			g.drawImage(leftPlayerMgShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		if(dir == left_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 0) {
			g.drawImage(leftPlayerMgShootCrouch, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		if(dir == left_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 2) {
			g.drawImage(leftPlayerMgShootCrouch2, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}	
		
		if(dir == left_dir && shootMachineGunRender == true && ammoMachineGun == 0) {
			g.drawImage(leftPlayerMg[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		
		if(dir == up_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 0) {
			g.drawImage(upPlayerMgShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		if(dir == up_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 2) {
			g.drawImage(upPlayerMgShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		if(dir == up_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 1) {
			g.drawImage(upPlayerMgShootCrouch, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		if(dir == up_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 3) {
			g.drawImage(upPlayerMgShootCrouch2, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}	
		
		if(dir == up_dir && shootMachineGunRender == true && ammoMachineGun == 0) {
			g.drawImage(upPlayerMg[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		
		if(dir == down_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 0) {
			g.drawImage(downPlayerMgShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		if(dir == down_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 2) {
			g.drawImage(downPlayerMgShoot, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		if(dir == down_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 1) {
			g.drawImage(downPlayerMgShootCrouch, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		if(dir == down_dir && shootMachineGunRender == true && ammoMachineGun > 0 && index == 3) {
			g.drawImage(downPlayerMgShootCrouch2, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}	
		
		if(dir == down_dir && shootMachineGunRender == true && ammoMachineGun == 0) {
			g.drawImage(downPlayerMg[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);		
		}
		
		
		//LEVANDO DANO
		if(IsDamage && dir == right_dir && index == 1 && Revolver == false && MachineGun == false) {
			g.drawImage(rightPlayerDamage, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == right_dir && index == 3 && Revolver == false && MachineGun == false) {
			g.drawImage(rightPlayerDamage3, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == right_dir && index == 0 && Revolver == false && MachineGun == false) {
			g.drawImage(rightPlayerDamage2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == right_dir && index == 2 && Revolver == false && MachineGun == false) {
			g.drawImage(rightPlayerDamage2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		if(IsDamage && dir == left_dir && index == 1 && Revolver == false && MachineGun == false) {
			g.drawImage(leftPlayerDamage2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == left_dir && index == 3 && Revolver == false && MachineGun == false) {
			g.drawImage(leftPlayerDamage2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == left_dir && index == 0 && Revolver == false && MachineGun == false) {
			g.drawImage(leftPlayerDamage, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == left_dir && index == 2 && Revolver == false && MachineGun == false) {
			g.drawImage(leftPlayerDamage3, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		if(IsDamage && dir == up_dir && index == 1 && Revolver == false && MachineGun == false) {
			g.drawImage(upPlayerDamage, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == up_dir && index == 3 && Revolver == false && MachineGun == false) {
			g.drawImage(upPlayerDamage3, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == up_dir && index == 0 && Revolver == false && MachineGun == false) {
			g.drawImage(upPlayerDamage2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == up_dir && index == 2 && Revolver == false && MachineGun == false) {
			g.drawImage(upPlayerDamage2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		if(IsDamage && dir == down_dir && index == 1 && Revolver == false && MachineGun == false) {
			g.drawImage(downPlayerDamage, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == down_dir && index == 3 && Revolver == false && MachineGun == false) {
			g.drawImage(downPlayerDamage3, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == down_dir && index == 0 && Revolver == false && MachineGun == false) {
			g.drawImage(downPlayerDamage2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == down_dir && index == 2 && Revolver == false && MachineGun == false) {
			g.drawImage(downPlayerDamage2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		// LEVANDO DANO COM REVOLVER
		if(IsDamage && Revolver == true && dir == right_dir  && index == 1) {
			g.drawImage(rightPlayerDamagerev, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && Revolver == true && dir == right_dir &&  index == 3) {
			g.drawImage(rightPlayerDamagerev3, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && Revolver == true && dir == right_dir  && index == 0) {
			g.drawImage(rightPlayerDamagerev2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && Revolver == true && dir == right_dir  && index == 2) {
			g.drawImage(rightPlayerDamagerev2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		if(IsDamage && dir == left_dir && Revolver == true &&  index == 1) {
			g.drawImage(leftPlayerDamagerev2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == left_dir && Revolver == true && index == 3) {
			g.drawImage(leftPlayerDamagerev2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == left_dir && Revolver == true && index == 0) {
			g.drawImage(leftPlayerDamagerev, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == left_dir && Revolver == true && index == 2) {
			g.drawImage(leftPlayerDamagerev3, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		if(IsDamage && dir == up_dir && Revolver == true && index == 1) {
			g.drawImage(upPlayerDamagerev, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == up_dir && Revolver == true && index == 3) {
			g.drawImage(upPlayerDamagerev3, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == up_dir && Revolver == true && index == 0) {
			g.drawImage(upPlayerDamagerev2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == up_dir && Revolver == true && index == 2) {
			g.drawImage(upPlayerDamagerev2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		if(IsDamage && dir == down_dir && Revolver == true && index == 1) {
			g.drawImage(downPlayerDamagerev, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == down_dir && Revolver == true && index == 3) {
			g.drawImage(downPlayerDamagerev3, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == down_dir && Revolver == true && index == 0) {
			g.drawImage(downPlayerDamagerev2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(IsDamage && dir == down_dir && Revolver == true && index == 2) {
			g.drawImage(downPlayerDamagerev2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		// LEVANDO DANO COM MACHINE GUN
				if(IsDamage && MachineGun == true && dir == right_dir  && index == 1) {
					g.drawImage(rightPlayerDamagemg, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				if(IsDamage && MachineGun == true && dir == right_dir &&  index == 3) {
					g.drawImage(rightPlayerDamagemg3, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				if(IsDamage && MachineGun == true && dir == right_dir  && index == 0) {
					g.drawImage(rightPlayerDamagemg2, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				if(IsDamage && MachineGun == true && dir == right_dir  && index == 2) {
					g.drawImage(rightPlayerDamagemg2, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				
				if(IsDamage && dir == left_dir && MachineGun == true &&  index == 1) {
					g.drawImage(leftPlayerDamagemg2, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				if(IsDamage && dir == left_dir && MachineGun == true && index == 3) {
					g.drawImage(leftPlayerDamagemg2, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				if(IsDamage && dir == left_dir && MachineGun == true && index == 0) {
					g.drawImage(leftPlayerDamagemg, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				if(IsDamage && dir == left_dir && MachineGun == true && index == 2) {
					g.drawImage(leftPlayerDamagemg3, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				
				if(IsDamage && dir == up_dir && MachineGun == true && index == 1) {
					g.drawImage(upPlayerDamagemg, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				if(IsDamage && dir == up_dir && MachineGun == true && index == 3) {
					g.drawImage(upPlayerDamagemg3, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				if(IsDamage && dir == up_dir && MachineGun == true && index == 0) {
					g.drawImage(upPlayerDamagemg2, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				if(IsDamage && dir == up_dir && MachineGun == true && index == 2) {
					g.drawImage(upPlayerDamagemg2, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				
				if(IsDamage && dir == down_dir && MachineGun == true && index == 1) {
					g.drawImage(downPlayerDamagemg, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				if(IsDamage && dir == down_dir && MachineGun == true && index == 3) {
					g.drawImage(downPlayerDamagemg3, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				if(IsDamage && dir == down_dir && MachineGun == true && index == 0) {
					g.drawImage(downPlayerDamagemg2, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				if(IsDamage && dir == down_dir && MachineGun == true && index == 2) {
					g.drawImage(downPlayerDamagemg2, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
	
		
		/*
		if(!IsDamage) {
		if(dir == right_dir) {
		g.drawImage(rightPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		if(Revolver) {
			// desenhar revolver direta
			g.drawImage(Entities.REVOLERPACK_EN,this.getX()+8 - Camera.x,this.getY()-3 - Camera.y - z,null);
		}
		if(MachineGun) {
			//desenhar machinegun direita
			g.drawImage(Entities.MACHINEGUN_EN,this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		}
		}else if(dir == left_dir) {
		g.drawImage(leftPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		if(Revolver) {
			//revolver esquerda
			g.drawImage(Entities.REVOLERPACKLEFT_EN,this.getX()-8 - Camera.x,this.getY()-3 - Camera.y - z,null);
		}
		if(MachineGun) {
			//machinegun esquerda
			g.drawImage(Entities.MACHINEGUNLEFT_EN,this.getX()-10 - Camera.x,this.getY() - Camera.y - z,null);
		}		
		}else if(dir == up_dir) {
			if(Revolver) {
				//revolver cima
				g.drawImage(Entities.REVOLERPACKUP_EN,this.getX()+4 - Camera.x,this.getY()-4 - Camera.y - z,null);
			}
			if(MachineGun) {
				//MachineGun cima
				g.drawImage(Entities.MACHINEGUNUP_EN,this.getX()+4 - Camera.x,this.getY()-15 - Camera.y - z,null);
			}
		g.drawImage(upPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);
		
		}else if(dir == down_dir) {
		g.drawImage(downPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y - z,null);	
		if(Revolver) {
			//revolver baixo
			g.drawImage(Entities.REVOLERPACKDOWN_EN,this.getX()-4 - Camera.x,this.getY()+5 - Camera.y - z,null);
		}
		if(MachineGun) {
			//MachineGun baixo
			g.drawImage(Entities.MACHINEGUNDOWN_EN,this.getX()-4 - Camera.x,this.getY()+2 - Camera.y - z,null);
		}
		}	
		} else {
			//LEVANDO DANO EM MOVIMENTO
			if(right) {
			g.drawImage(PlayerDamage, this.getX() - Camera.x, this.getY() - Camera.y - z, null);
			if(Revolver) {
				// desenhar revolver direta
				g.drawImage(Entities.DAMAGEREVOLERPACK_EN,this.getX()+8 - Camera.x,this.getY()-3 - Camera.y - z,null);
			}
			if(MachineGun) {
				//desenhar machinegun direita
				g.drawImage(Entities.DAMAGEMACHINEGUN_EN,this.getX() - Camera.x,this.getY() - Camera.y - z,null);
			}
			} else if(left){
				g.drawImage(PlayerDamageLeft, this.getX() - Camera.x, this.getY() - Camera.y - z, null);	
				if(Revolver) {
					//revolver esquerda
					g.drawImage(Entities.DAMAGEREVOLERPACKLEFT_EN,this.getX()-8 - Camera.x,this.getY()-3 - Camera.y - z,null);
				}
				if(MachineGun) {
					//machinegun esquerda
					g.drawImage(Entities.DAMAGEMACHINEGUNLEFT_EN,this.getX()-10 - Camera.x,this.getY() - Camera.y - z,null);
			}
			
			}else if (up){
				g.drawImage(PlayerDamageUp, this.getX() - Camera.x, this.getY() - Camera.y - z, null);
				if(Revolver) {
					//revolver cima
					g.drawImage(Entities.DAMAGEREVOLERPACKUP_EN,this.getX()+4 - Camera.x,this.getY()-4 - Camera.y - z,null);
				}
				if(MachineGun) {
					//MachineGun cima
					g.drawImage(Entities.DAMAGEMACHINEGUNUP_EN,this.getX()+4 - Camera.x,this.getY()-15 - Camera.y - z,null);
				}
			
			} else if(down) {
				g.drawImage(PlayerDamageDown, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
				if(Revolver) {
					//revolver baixo
					g.drawImage(Entities.DAMAGEREVOLERPACKDOWN_EN,this.getX()-4 - Camera.x,this.getY()+5 - Camera.y - z,null);
				}
				if(MachineGun) {
					//MachineGun baixo
					g.drawImage(Entities.DAMAGEMACHINEGUNDOWN_EN,this.getX()-4 - Camera.x,this.getY()+2 - Camera.y - z,null);
				}
				
			}else {
				//LEVANDO DANO PARADO
				if(dir == right_dir) {
					g.drawImage(PlayerDamage, this.getX() - Camera.x, this.getY() - Camera.y - z, null);
				}else if(dir == left_dir) {
					g.drawImage(PlayerDamageLeft, this.getX() - Camera.x, this.getY() - Camera.y - z, null);
				} else if(dir == up_dir) {
					g.drawImage(PlayerDamageUp, this.getX() - Camera.x, this.getY() - Camera.y - z, null);
				}else if(dir == down_dir) {
					g.drawImage(PlayerDamageDown, this.getX() - Camera.x,this.getY() - Camera.y - z,null);
				}
			}
	}
		*/
	//g.setColor(Color.red);
	//g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, mwidth, height);

	}
}

	


