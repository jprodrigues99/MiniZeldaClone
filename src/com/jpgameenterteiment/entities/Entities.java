package com.jpgameenterteiment.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;

import com.jpgameenterteiment.word.Camera;
import com.jpgameenterteiment.word.Node;
import com.jpgameenterteiment.word.Vector2i;
import com.jpsgameenterteiment.main.Game;

public class Entities {
	
public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(4*16, 19*6, 16, 16);
public static BufferedImage REVOLERPACK_EN = Game.spritesheet.getSprite(4*16, 12*16, 16, 16);
public static BufferedImage BULLET_EN = Game.spritesheet.getSprite(4*16, 15*16, 16, 16);
public static BufferedImage BULLETMACHINEGUN_EN = Game.spritesheet.getSprite(4*16, 16*16, 16,16);
public static BufferedImage ENEMYEASY_EN = Game.spritesheet.getSprite(6*16, 12*16, 16, 16);
public static BufferedImage ENEMYHARD_EN = Game.spritesheet.getSprite(11*16, 12*16, 16, 16);
public static BufferedImage BIGLIFEPACK_EN = Game.spritesheet.getSprite(4*16, 18*16, 16, 16);
public static BufferedImage MACHINEGUN_EN = Game.spritesheet.getSprite(4*16, 13*16, 16, 16);
public static BufferedImage INVENCIBILIDADE_EN = Game.spritesheet.getSprite(5*16, 12*16, 16, 16);
public static BufferedImage TREE_EN = Game.spritesheet.getSprite(5*16, 16*16, 48,64);
public static BufferedImage TREE_EN2 = Game.spritesheet.getSprite(6*16, 16*16, 32,64);
public static BufferedImage TREE_EN3 = Game.spritesheet.getSprite(5*16, 18*16, 48,32);
public static BufferedImage TREE_EN4 = Game.spritesheet.getSprite(5*16, 16*16, 32,64);
public static BufferedImage  BOAT_EN = Game.spritesheet.getSprite(7*16, 8*16, 48, 16);
public static BufferedImage  ROCKUP_EN = Game.spritesheet.getSprite(9*16, 14*16, 16, 16);
public static BufferedImage  RIVER_EN = Game.spritesheet.getSprite(8*16, 17*16, 16, 16);
public static BufferedImage  RIVER2_EN = Game.spritesheet.getSprite(8*16, 18*16, 16, 16);
public static BufferedImage  RIVER3_EN = Game.spritesheet.getSprite(8*16, 19*16, 16, 16);
public static BufferedImage  HELICOPTER_EN = Game.spritesheet.getSprite(7*16, 0, 6*16, 2*16);
public static BufferedImage  FIRE_EN = Game.spritesheet.getSprite(11*16, 15*16, 16, 16);
public static BufferedImage ENEMYBALL_EN = Game.spritesheet.getSprite(13*16, 8*16, 16,16);
public static BufferedImage ENEMYBOSSBAT_EN = Game.spritesheet.getSprite(20*16, 0, 32,32);
public static BufferedImage ENEMYBOSSSNAKE_EN = Game.spritesheet.getSprite(20*16, 10*16, 32,32);
public static BufferedImage ENEMYCAV_EN = Game.spritesheet.getSprite(17*16, 6*16, 16,16);
public static BufferedImage ENEMYGOLLEN_EN = Game.spritesheet.getSprite(14*16, 0, 16,16);
public static BufferedImage ENEMYSPIRIT_EN = Game.spritesheet.getSprite(14*16, 4*16, 16,16);
public static BufferedImage ENEMYSPIRITHARD_EN = Game.spritesheet.getSprite(17*16, 0, 16,16);


	public double x;
	public double y;
	protected int z;
	protected int width;
	protected int height;
	protected double speed = 0.5;//velocidade de perseguicao dos inimigos
	
	public int depth;
	
	protected List<Node> path;
	
	private BufferedImage sprite;
	
	public int maskx,masky,mwidth,mheight;
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public void setZ(int newZ) {
		this.z = newZ;
	}
	
	public Entities(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky = 0;
		this.mwidth = width;
		this.mheight = height;
		
	}
	
	public void setMask(int maskx,int masky,int mwidth,int mheight){
		this.maskx = maskx;
		this.masky = masky;
		this.mwidth = mwidth;
		this.mheight = mheight;
	}
	
public static Comparator<Entities> nodeSorter = new Comparator<Entities>() {
		
		@Override
		public int compare(Entities n0, Entities n1) {
			if(n1.depth < n0.depth)
				return +1;
			if(n1.depth > n0.depth)
				return -1;
			return 0;
		}
	};

	
	public int getX() {
		return (int)this.x;
	}
	public int getY() {
		return (int)this.y;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeigth() {
	return this.height;
    }
	
	public void update() {
		
	}
	// Distancia calculada em angulos
	public double CalculateDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	
	public void followPathball(List<Node> path) {
		if(path != null) {
			if(path.size() > 0) {
				Vector2i target = path.get(path.size() - 1).tile;
			    if(x < target.x * 16 ) {
			    	x+=EnemyBall.speed;
			    	EnemyBall.down = false;
			    	EnemyBall.up = false;
			    	EnemyBall.right = true;
			    	EnemyBall.left = false;
			    }else if(x > target.x * 16 ) {
			    	x-=EnemyBall.speed;
			    	EnemyBall.down = false;
			    	EnemyBall.up = false;
			    	EnemyBall.right = false;
			    	EnemyBall.left = true;
			    }
			    if(y < target.y * 16) {
			    	y+=EnemyBall.speed;
			    	EnemyBall.down = true;
			    	EnemyBall.up = false;
			    	EnemyBall.right = false;
			    	EnemyBall.left = false;
			    }else if(y > target.y * 16) {
			    	y-=EnemyBall.speed;
			    	EnemyBall.down = false;
			    	EnemyBall.up = true;
			    	EnemyBall.right = false;
			    	EnemyBall.left = false;
			    }
			    
			    if(x == target.x * 16 && y == target.y * 16) {
			    	path.remove(path.size() - 1);
			    }
			}
		}
	}
	
	public void followPatheasy(List<Node> path) {
		if(path != null) {
			if(path.size() > 0) {
				Vector2i target = path.get(path.size() - 1).tile;
			    if(x < target.x * 16 ) {
			    	x+=Enemyeasy.speed;
			    	Enemyeasy.down = false;
			    	Enemyeasy.up = false;
			    	Enemyeasy.right = true;
			    	Enemyeasy.left = false;
			    }else if(x > target.x * 16 ) {
			    	x-=Enemyeasy.speed;
			    	Enemyeasy.down = false;
			    	Enemyeasy.up = false;
			    	Enemyeasy.right = false;
			    	Enemyeasy.left = true;
			    }
			    if(y < target.y * 16) {
			    	y+=Enemyeasy.speed;
			    	Enemyeasy.down = true;
			    	Enemyeasy.up = false;
			    	Enemyeasy.right = false;
			    	Enemyeasy.left = false;
			    }else if(y > target.y * 16) {
			    	y-=Enemyeasy.speed;
			    	Enemyeasy.down = false;
			    	Enemyeasy.up = true;
			    	Enemyeasy.right = false;
			    	Enemyeasy.left = false;
			    }
			    
			    if(x == target.x * 16 && y == target.y * 16) {
			    	path.remove(path.size() - 1);
			    }
			}
		}
	}
	
	public void followPathhard(List<Node> path) {
		if(path != null) {
			if(path.size() > 0) {
				Vector2i target = path.get(path.size() - 1).tile;
			    if(x < target.x * 16) {
			    	x+=Enemyhard.speed;
			    }else if(x > target.x * 16) {
			    	x-=Enemyhard.speed;
			    }
			    if(y < target.y * 16) {
			    	y+=Enemyhard.speed;
			    	Enemyhard.down = true;
			    	Enemyhard.up = false;
			    }else if(y > target.y * 16) {
			    	y-=Enemyhard.speed;
			    	Enemyhard.down = false;
			    	Enemyhard.up = true;
			    }
			    
			    if(x == target.x * 16 && y == target.y * 16) {
			    	path.remove(path.size() - 1);
			    }
			}
		}
	}
	
	public static boolean isColidding(Entities e1,Entities e2){
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx,e1.getY()+e1.masky,e1.mwidth,e1.mheight);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx,e2.getY()+e2.masky,e2.mwidth,e2.mheight);
		
		return e1Mask.intersects(e2Mask);
	}
	
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
	   // g.setColor(Color.red);
	  // g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, mwidth, height);
	}
	
	
	
}
