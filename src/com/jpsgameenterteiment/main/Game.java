package com.jpsgameenterteiment.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.jpgameenterteiment.entities.Blood;
import com.jpgameenterteiment.entities.EnemyBall;
import com.jpgameenterteiment.entities.EnemyBossBat;
import com.jpgameenterteiment.entities.EnemyBossSnake;
import com.jpgameenterteiment.entities.EnemyCav;
import com.jpgameenterteiment.entities.EnemyGollen;
import com.jpgameenterteiment.entities.EnemySpirit;
import com.jpgameenterteiment.entities.EnemySpiritHard;
import com.jpgameenterteiment.entities.Enemyeasy;
import com.jpgameenterteiment.entities.Enemyhard;
import com.jpgameenterteiment.entities.Entities;
import com.jpgameenterteiment.entities.FirstBoss;
import com.jpgameenterteiment.entities.MachineGun;
import com.jpgameenterteiment.entities.Player;
import com.jpgameenterteiment.entities.Rain;
import com.jpgameenterteiment.entities.River;
import com.jpgameenterteiment.entities.ShootMachineGun;
import com.jpgameenterteiment.entities.ShootRevolver;
import com.jpgameenterteiment.graficos.Spritesheet;
import com.jpgameenterteiment.graficos.UserInterface;
import com.jpgameenterteiment.word.Camera;
import com.jpgameenterteiment.word.Tile;
import com.jpgameenterteiment.word.Word;


public class Game extends Canvas implements Runnable,KeyListener,MouseListener,MouseMotionListener {
	
	
	private static final long serialVersionUID = 1L;
	//VARIAVEIS
	public boolean isRunning = true;
    public Thread thread;
	public static JFrame frame;
	public BufferedImage image;
	
	
	public static final int WIDTH = 320;
	public static final int HEIGHT = 180;
	public static final int SCALE = 4;
	
	private int CUR_LEVEL = 1, MAX_LEVEL = 2;
	public static String GameState = "MENU";
	public static boolean RestartGame = false;
	private boolean ShowMessageEnter = true;
	private boolean saveGame = false;
	private int FramesEnter = 0;
	private String Enter = "ENTER";
	public Menu menu;
	
	long lastTime = System.nanoTime();
	double frames = 60.0;
	double fps = 1000000000 / frames;
	double delta = 0;
	int quadros = 0;
	double timer = System.currentTimeMillis();
	
	public static List<Entities> entities;
	public static List<Tile> tile;
	public static List<Enemyeasy> enemies;
	public static List<Enemyhard> enemieshard;
	public static List<EnemyBall> enemyball;
	public static List<EnemyGollen> enemygollen;
	public static List<EnemySpirit> enemyspirit;
	public static List<EnemyCav> enemycav;
    public static List<EnemySpiritHard> enemyspirithard;
    public static List<EnemyBossBat> enemybossbat;
    public static List<EnemyBossSnake> enemybosssnake;
	public static Spritesheet spritesheet;
	public static Player player;
    public static List<FirstBoss> firstboss;
	public static List<ShootRevolver> shootRevolver;
	public static List<ShootMachineGun> shootMachineGun;
	public static Word word;
	public static Random rand;
	public UserInterface Ui;
	public static MachineGun machinegun;
	public Rain rain;
	public Blood blood;
	
	
    public int mx, my;
    
    //SISTEMA DE CUTSCENE
    public static int getIn = 1;
    public static int start = 2;
    public static int playing = 3;
    public static int scene_state = getIn;
    public static int timerStartScene = 0, maxtimerStartScene = 60*2;
    public static int timerDialogo = 0, maxTimerDialogo = 60*3;
	
	
	//PROPRIEDADES DA JANELA DO JOGO
	public void Window() {
		//this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		frame = new JFrame("JP's game");
		frame.add(this);
		//frame.setUndecorated(true);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}


	
	// CONSTRUTOR
	public Game() {
	Sound.Clips.menumusic.loop();
		rand = new Random();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		Window();
		//Iniciando Objetos
		Ui = new UserInterface();
		tile = new ArrayList<Tile>();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		enemies = new ArrayList<Enemyeasy>();
		enemieshard = new ArrayList<Enemyhard>();
		enemyball = new ArrayList<EnemyBall>();
		enemygollen = new ArrayList<EnemyGollen>();
		enemyspirit = new ArrayList<EnemySpirit>();
		enemyspirithard = new ArrayList<EnemySpiritHard>();
		enemycav = new ArrayList<EnemyCav>();
		enemybossbat = new ArrayList<EnemyBossBat>();
		enemybosssnake = new ArrayList<EnemyBossSnake>();
		entities = new ArrayList<Entities>();
		firstboss = new ArrayList<FirstBoss>();
		shootRevolver = new ArrayList<ShootRevolver>();
		shootMachineGun	= new ArrayList<ShootMachineGun>();
		spritesheet = new Spritesheet("/spritesheet.png");
		player = new Player(0,0,16,16,spritesheet.getSprite(0, 0, 16, 16));
		entities.add(player);
		word = new Word("/Level1.png");
		menu = new Menu();
		rain = new Rain();
	    blood = new Blood();
	  
	}
	
	//SINCRONIZACAO DE THREADS
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	//PARALIZACAO DAS THREADS
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//ATUALIZACAO DO JOGO
	public void update() {
		if(GameState == "NORMAL") {
			if(saveGame == true) {
				saveGame = false;
				String[] opt1 = {"level"};
				int[] opt2 = {this.CUR_LEVEL};
				Menu.saveGame(opt1, opt2,10);
				System.out.println("JOGO SALVO");
			}
			
			Sound.Clips.menumusic.stop();
			Sound.Clips.gameplaymusic.loop();
			RestartGame = false;
			
			if(Game.scene_state == Game.playing) {
		for(int i = 0; i < entities.size(); i++) {
			Entities e = entities.get(i);
			e.update();
		}
		
		for(int i = 0; i < shootRevolver.size(); i++ ) {
			shootRevolver.get(i).update();
		}
		for(int i = 0; i < shootMachineGun.size(); i++ ) {
			shootMachineGun.get(i).update();
		}
			}else {
				if(Game.scene_state == Game.getIn) {
					timerStartScene++;
					if(timerStartScene >= maxtimerStartScene) {
						if(player.getX() <= 200) {							
							player.x+=player.speed;
							player.dir = player.right_dir;
						} else {
							Game.scene_state = start;
						}
					}
				} if(Game.scene_state == start) {
					//DIALOGO
					timerDialogo++;
					if(timerDialogo == maxTimerDialogo) {
						Game.scene_state = playing;
					}
				}
			}
		
		/*if(Game.enemies.size() == 0 && Game.enemieshard.size() == 0) {
			CUR_LEVEL++;
			if(CUR_LEVEL > MAX_LEVEL) {
				CUR_LEVEL = 1;
			}
			String newWord = "Level"+CUR_LEVEL+".png";
			Word.restartGame(newWord);
		}*/
		
		}else if(GameState == "GAME OVER") {
			Sound.Clips.gameplaymusic.stop();
			Sound.Clips.orc.stop();
			Sound.Clips.bat.stop();
			Sound.Clips.river.stop();
			FramesEnter++;
			if(FramesEnter == 30) {
				FramesEnter = 0;
				if(ShowMessageEnter) {
					ShowMessageEnter = false;
				}else {
					ShowMessageEnter = true;
				}
			}
		
		
		if(RestartGame) {
			RestartGame = false;
			GameState = "NORMAL";
			CUR_LEVEL = 1;
		String newWord = "Level"+CUR_LEVEL+".png";
		Word.restartGame(newWord);
		Player.Revolver = false;
		Player.MachineGun = false;
		Player.ammo = 0;
		Player.ammoMachineGun = 0;
		}
		}else if(GameState == "MENU"){
			menu.update();
		}
			rain.update();
			blood.update();
		}
	
	//RENDERIZACAO DO JOGO
	public void render() {
		//renderizando janela
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//renderizando mapa
		word.render(g);
		//renderizando objetos
		Collections.sort(entities,Entities.nodeSorter);//ordem de renderizacao
		for(int i = 0; i < shootRevolver.size(); i++ ) {
			shootRevolver.get(i).render(g);
		}
		for(int i = 0; i < shootMachineGun.size(); i++ ) {
			shootMachineGun.get(i).render(g);
		}
		for(int i = 0; i < entities.size(); i++) {
			Entities e = entities.get(i);
			e.render(g);
			}
		
		
		Ui.render(g);
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);
		//g.drawImage(image, 0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height,null);
		//GAME OVER
		if(GameState == "GAME OVER") {
			Graphics2D g2 = (Graphics2D) g;
			g.setColor(new Color(0,0,0));
			g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
			g.setColor(Color.RED);
			g.setFont(new Font("arial", Font.BOLD,30));
			g.drawString("GAME OVER", (Game.WIDTH*Game.SCALE) / 2 - 90, (Game.HEIGHT*Game.SCALE) / 2);
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD,15));
			if(ShowMessageEnter) {
			Enter = "ENTER";} else {Enter = "             ";}
			g.drawString("Press " +Enter+ " to restart", ((Game.WIDTH*Game.SCALE) / 2) - 80, ((Game.HEIGHT*Game.SCALE) / 2) + 120);
	         }else if(GameState == "MENU") {
	        	 player.updateCamera();
	        	 menu.render(g);
	         }else if(GameState == "PAUSE") {
	        	 menu.render(g);
	        	 
	         }
		if(Game.scene_state == start) {
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.setColor(Color.WHITE);
			g.drawString("Sobreviva ate o resgate chegar!", player.getX() + 150, player.getY() - 250);
		}
		rain.render(g);
		blood.render(g);
		bs.show();
	
	}
	
	//METODO PRINCIPAL
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	//GAME LOOPING
	public void run() {
		requestFocus();//Comando que habilita o teclado assim que o jogo abre.
		while(isRunning) {
			long now = System.nanoTime();
			delta+= (now - lastTime) / fps;
			lastTime = now;
			if(delta >= 1) {
				update();
				render();
				quadros++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: "+ quadros);
				quadros = 0;
				timer+=1000;
		}
			}
		stop();
		}



	@Override
	public void keyPressed(KeyEvent e) {
		//CONTROLE DE MOVIMENTACAO
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Sound.Clips.Jumping.play();
			player.jump = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ||
				e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}if(e.getKeyCode() == KeyEvent.VK_LEFT ||
				e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}if(e.getKeyCode() == KeyEvent.VK_UP ||
				e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
			
			if(GameState == "MENU") {
				menu.up = true;
			}
			
		}if(e.getKeyCode() == KeyEvent.VK_DOWN ||
				e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
			
			if(GameState == "MENU") {
				menu.down = true;
			}
			
		}
		
		//CONTROLE DE TIRO
		if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
			if(Game.player.Revolver == true ) {
			player.shootRevolver = true;
			}
			if(Game.player.MachineGun == true) {
			player.shootMachineGun = true;
			}
			
			if(Game.player.Revolver == true ) {
			player.shootRevolverRender = true;
			}
			if(Game.player.MachineGun == true) {
			player.shootMachineGunRender = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(GameState == "GAME OVER") {
			RestartGame = true;
			} else if(GameState == "MENU") {
				Menu.enter = true;
			}else if(GameState == "MENU" && Menu.PAUSE == true) {
				Menu.enter = true;
			}
			
		}if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			GameState = "MENU";
		    Menu.PAUSE = true;
		    Menu.MENU = false;
		    }
		
		if(e.getKeyCode() == KeyEvent.VK_T) {
			if(GameState == "NORMAL") {
			saveGame = true;
			}
		}

		//CONTROLE DE TROCA DE ARMA
		if(Game.player.packrev == true) {
		if(e.getKeyCode() == KeyEvent.VK_1) {
			player.Revolver = true;
			player.MachineGun = false;
		}
		}
		if(Game.player.packmg == true) {
		if(e.getKeyCode() == KeyEvent.VK_2) {
			player.Revolver = false;
			player.MachineGun = true;
		}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_3) {
			player.Revolver = false;
			player.MachineGun = false;
		}
		
		if(Game.player.packrev == true && Game.player.Revolver == true) {
			if(e.getKeyCode() == KeyEvent.VK_F) {
				player.packrev = false;
				player.Revolver = false;
			}
		}
		
		if(Game.player.packmg == true && Game.player.MachineGun == true) {
			if(e.getKeyCode() == KeyEvent.VK_F) {
				player.packmg = false;
				player.MachineGun = false;
			}
		}
		
		if(Game.player.packlife > 0 && Game.player.life < 100) {
			if(e.getKeyCode() == KeyEvent.VK_5) {
				Sound.Clips.life.play();
				player.life+=20;
				player.packlife--;
			}
		}
		
        if(Game.player.packbiglife > 0 && Game.player.life < 100) {
        	if(e.getKeyCode() == KeyEvent.VK_6) {
        		Sound.Clips.life.play();
				player.life+=60;
				player.packbiglife--;
			}
		}
		
		
		}



	@Override
	public void keyReleased(KeyEvent e) {
		//CONTROLE DE MOVIMENTACAO
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ||
				e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT ||
				e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}if(e.getKeyCode() == KeyEvent.VK_UP ||
				e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN ||
				e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
		//CONTROLE DE TIRO
		if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
			player.shootRevolver = false;
			player.shootMachineGun = false;
			player.shootRevolverRender = false;
			player.shootMachineGunRender = false;
		}

		/*//CONTROLE DE TROCA DE ARMA
				if(e.getKeyCode() == KeyEvent.VK_1) {
					player.Revolver = true;
					player.MachineGun = false;
				}
					if(e.getKeyCode() == KeyEvent.VK_2) {
					player.Revolver = false;
					player.MachineGun = true;
					}*/
				}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		player.MouseShoot = true;
		player.mx = e.getX() / SCALE ;
		player.my = e.getY() / SCALE ;

		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		player.MouseShoot = false;
		
	}



	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseMoved(MouseEvent e) {
	   this.mx = e.getX();
	   this.my = e.getY();
		
	}

	
	
}
