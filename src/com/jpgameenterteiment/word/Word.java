package com.jpgameenterteiment.word;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.jpgameenterteiment.entities.BigLifePack;
import com.jpgameenterteiment.entities.Boat;
import com.jpgameenterteiment.entities.Bullet;
import com.jpgameenterteiment.entities.BulletMachineGun;
import com.jpgameenterteiment.entities.EnemyBall;
import com.jpgameenterteiment.entities.Enemyeasy;
import com.jpgameenterteiment.entities.Enemyhard;
import com.jpgameenterteiment.entities.Entities;
import com.jpgameenterteiment.entities.Fire;
import com.jpgameenterteiment.entities.FirstBoss;
import com.jpgameenterteiment.entities.Helicopter;
import com.jpgameenterteiment.entities.Invencibilidade;
import com.jpgameenterteiment.entities.LifePack;
import com.jpgameenterteiment.entities.MachineGun;
import com.jpgameenterteiment.entities.Particle;
import com.jpgameenterteiment.entities.ParticleBlood;
import com.jpgameenterteiment.entities.ParticleGreen;
import com.jpgameenterteiment.entities.Passage;
import com.jpgameenterteiment.entities.Player;
import com.jpgameenterteiment.entities.Revolver;
import com.jpgameenterteiment.entities.River;
import com.jpgameenterteiment.entities.River2;
import com.jpgameenterteiment.entities.River3;
import com.jpgameenterteiment.entities.Rockup;
import com.jpgameenterteiment.entities.Tree;
import com.jpgameenterteiment.entities.Tree2;
import com.jpgameenterteiment.entities.Tree3;
import com.jpgameenterteiment.entities.Tree4;
import com.jpgameenterteiment.graficos.Spritesheet;
import com.jpsgameenterteiment.main.Game;

public class Word {

	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static int TILE_SIZE = 16;
	
	public  Word(String path) {
		
		/*
		Game.player.setX(0);
		Game.player.setY(0);
		WIDTH = 50;
		HEIGHT = 50;
		tiles = new Tile[WIDTH*HEIGHT];
		
		for(int xx = 0; xx < WIDTH; xx++) {
			for(int yy = 0; yy < HEIGHT; yy++) {
				tiles[xx+yy*WIDTH] = new WallTile(xx*16, yy*16,Tile.TILE_WALL);
			}
		}
		
		int dir = 0;
		int xx = 0, yy = 00;
		
		for(int i = 0; i < 200; i++) {
		tiles[xx+yy*WIDTH] = new FloorTile(xx*16, yy*16,Tile.TILE_FLOOR);
			if(dir == 0) {
				//Right
				
				if(xx < WIDTH) {
					xx++;
				}
			}else if(dir == 1) {
				//Left
				
				if(xx > WIDTH) {
					xx--;
				}
			}else if(dir == 2) {
				//Down
				if(xx < HEIGHT) {
					yy++;
				}
			}else if(dir == 3) {
				//Up
				if(xx > HEIGHT) {
					yy--;
				}
			}
			
			if(Game.rand.nextInt(100) < 30) {
				dir = Game.rand.nextInt(4);
			}
			
			
		}
		
		*/
		
		
		
		
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0,0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
		for(int xx = 0; xx < map.getWidth(); xx++) {
			for(int yy = 0; yy < map.getHeight(); yy++) {
				int pixelAtual = pixels[xx + (yy*map.getWidth())];
				tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
				if(pixelAtual == 0XFF000000) {
					//grama
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
				}else if(pixelAtual == 0XFFF0FFB7 ) {
					//TRONCO
					tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_TREE);
				}else if(pixelAtual == 0XFFFFFFFF ) {
					//parede
					WallTile pack = new WallTile(xx*16,yy*16,Tile.TILE_WALL);
					pack.setMask(0, 0, 16, 16);
					Game.tile.add(pack);				
					tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL);
				}else if(pixelAtual == 0XFF0026FF) {
					//player
					Game.player.setX(xx*16);
					Game.player.setY(yy*16);
				}else if(pixelAtual == 0XFFFF0088) {
					//enemy Ball
					EnemyBall en = new EnemyBall(xx*16,yy*16,16,16, Entities.ENEMYBALL_EN);
					Game.entities.add(en);
					Game.enemyball.add(en);
				}else if(pixelAtual == 0XFFFF0000) {
					//enemy facil
					Enemyeasy en = new Enemyeasy(xx*16,yy*16,16,16, Entities.ENEMYEASY_EN);
					Game.entities.add(en);
					Game.enemies.add(en);
				}else if(pixelAtual == 0XFFFFE97F) {
					//enemy hard
					Enemyhard en = new Enemyhard(xx*16, yy*16,16,16, Entities.ENEMYHARD_EN);
				    Game.entities.add(en);
				    Game.enemieshard.add(en);
			    }else if(pixelAtual == 0XFFEEFF00) {
					//ARVORE OBJETO
					Game.entities.add(new Tree(xx*16,yy*16,48,64, Entities.TREE_EN));
				}else if(pixelAtual == 0XFFA4AF74) {
					//ARVORE OBJETO2
					Game.entities.add(new Tree2(xx*16,yy*16,32,64, Entities.TREE_EN2));
				}else if(pixelAtual == 0XFFD0FF14) {
					//ARVORE OBJETO3
					Game.entities.add(new Tree3(xx*16,yy*16,48,32, Entities.TREE_EN3));
				}else if(pixelAtual == 0XFFFFFAAD) {
					//ARVORE OBJETO3
					Game.entities.add(new Tree4(xx*16,yy*16,32,64, Entities.TREE_EN4));
				}else if(pixelAtual == 0XFFFF3593) {
					//BARCO
					Game.entities.add(new Boat(xx*16,yy*16,48,16, Entities.BOAT_EN));
				}else if(pixelAtual == 0XFF78D5C6) {
					//ROCK
					tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_ROCKLEFTUP);
				}else if(pixelAtual == 0XFF00FF50) {
					//ROCKUP
					tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_ROCKLEFT);
				}else if(pixelAtual == 0XFF78D59B) {
					//ROCKDOWN
					tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_ROCKLEFTDOWN);
				}else if(pixelAtual == 0XFF00FFC6) {
					//ROCKLEFT	
					Game.entities.add(new Rockup(xx*16,yy*16,16,16, Entities.ROCKUP_EN));
				}else if(pixelAtual == 0XFF6CB7B1) {
					//ROCKRIGHT
					tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_ROCK);
				}else if(pixelAtual == 0XFF00C6C6) {
					//ROCKDOWNLEFT
					tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_ROCKDOWN);
				}else if(pixelAtual == 0XFF6C8CB1) {
					//ROCKDOWNLEFT
					tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_ROCKRIGHTUP);
				}else if(pixelAtual == 0XFF00C650) {
					//ROCKDOWNRIGHT
					tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_ROCKRIGHT);
				}else if(pixelAtual == 0XFF00DD50) {
					//ROCKDOWNRIGHT
					tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_ROCKRIGHTDOWN);
				}else if(pixelAtual == 0XFF7F3F5B) {
					//BUSH
					tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_BUSH);
				}else if(pixelAtual == 0XFFE9CCFF) {
					//FIRE
					Game.entities.add(new Fire(xx*16,yy*16,16,16, Entities.FIRE_EN));
				}else if(pixelAtual == 0XFF939077) {
					//river
					Game.entities.add(new River(xx*16,yy*16,16,16, Entities.RIVER_EN));
				}else if(pixelAtual == 0XFFA8A488) {
					//river2
					Game.entities.add(new River2(xx*16,yy*16,16,16, Entities.RIVER2_EN));
				}else if(pixelAtual == 0XFFBCB898) {
					//river3
					Game.entities.add(new River3(xx*16,yy*16,16,16, Entities.RIVER3_EN));
				}else if(pixelAtual == 0XFFB200FF) {
					//revolver
					Game.entities.add(new Revolver(xx*16,yy*16,16,16, Entities.REVOLERPACK_EN));
				}else if(pixelAtual == 0XFFB6FF00) {
					//machinegun
			        Game.entities.add(new MachineGun(xx*16,yy*16,16,16, Entities.MACHINEGUN_EN));
			    
				}else if(pixelAtual == 0XFF00FFFF) {
					//bullet
					Game.entities.add(new Bullet(xx*16,yy*16,16,16, Entities.BULLET_EN));
				}else if(pixelAtual == 0XFF00C4C4) {
					//bullet machinegun
					Game.entities.add(new BulletMachineGun(xx*16,yy*16,16,16, Entities.BULLETMACHINEGUN_EN));
				}else if(pixelAtual == 0XFFFFD891) {
					//life pack
					LifePack pack = new LifePack(xx*16,yy*16,16,16, Entities.LIFEPACK_EN);
					pack.setMask(0,0,16,16);
					Game.entities.add(pack);
			    }else if(pixelAtual == 0XFF00FF90) {
					//Big life pack
			    	BigLifePack bigpack = new BigLifePack(xx*16,yy*16,16,16, Entities.BIGLIFEPACK_EN);
			    	bigpack.setMask(0,0,16,16);
					Game.entities.add(bigpack);
				}else if(pixelAtual == 0XFFFF006E) {
					//Invencibilidade
					Game.entities.add(new Invencibilidade(xx*16,yy*16,16,16, Entities.INVENCIBILIDADE_EN));
				}else if(pixelAtual == 0XFF7C19FF) {
					//HELICOPTER
					Game.entities.add(new Helicopter(xx*16,yy*16,6*16,2*16, Entities.HELICOPTER_EN));
				}
				else if(pixelAtual == 0XFF7F6A00) {
					//FIRST BOSS
					//if(Game.enemies.size() == 0 && Game.enemieshard.size() == 0) {
					//FirstBoss boss = new FirstBoss(xx*16,yy*16,32,48, Entities.FIRSTBOSS_EN);
					//Game.entities.add(boss);
					//Game.firstboss.add(boss);
					}
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void generateParticle(int amount, int x, int y) {
			for(int i = 0; i < amount; i++) {
				Game.entities.add(new Particle(x,y,1,1,null));
			}
	}
	
	public static void generateParticleBlood(int amount, int x, int y) {
		for(int i = 0; i < amount; i++) {
			Game.entities.add(new ParticleBlood(x,y,1,1,null));
		}
}
	
	public static void generateParticleGreen(int amount, int x, int y) {
		for(int i = 0; i < amount; i++) {
			Game.entities.add(new ParticleGreen(x,y,1,1,null));
		}
}
	
	//VERIFICAÇAO DE COLISAO
	public static boolean isFree(int xNext,int yNext) {
		int x1 = xNext / TILE_SIZE;
		int y1 = yNext / TILE_SIZE;
		
		int x2 = (xNext+TILE_SIZE-2) / TILE_SIZE;
		int y2 = yNext / TILE_SIZE;
		
		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext+TILE_SIZE-2) / TILE_SIZE;
		
		int x4 = (xNext+TILE_SIZE-2) / TILE_SIZE;
		int y4 = (yNext+TILE_SIZE-2) / TILE_SIZE;
		
		//VERIFICANDO SE ESTÁ LIVRE
		return !((tiles[x1 + (y1*Word.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*Word.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*Word.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*Word.WIDTH)] instanceof WallTile));
	}
	
	public static void restartGame(String Level) {
    	Game.entities.clear();
    	Game.enemies.clear();
    	Game.enemieshard.clear();
    	Game.shootMachineGun.clear();
    	Game.shootRevolver.clear();
    	Game.entities = new ArrayList<Entities>();
		Game.enemies = new ArrayList<Enemyeasy>();
		Game.enemieshard = new ArrayList<Enemyhard>();
		Game.spritesheet = new Spritesheet("/spritesheet.png");
		Game.player = new Player(0,0,16,16,Game.spritesheet.getSprite(32, 0, 16, 16));
		Game.entities.add(Game.player);
		Game.word = new Word("/"+Level);
		
		return;
	}
	
	
	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
