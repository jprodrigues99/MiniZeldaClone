package com.jpsgameenterteiment.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.jpgameenterteiment.entities.Player;
import com.jpgameenterteiment.word.Word;

public class Menu {

	public String[] options = {"novo jogo","carregar jogo","sobre","sair","voltar ao menu"};
	
	public int currentOption = 0;
	public int maxOption = options.length - 2;
	
	public static boolean up,down,enter,escape;
	
	public static boolean PAUSE = false;
	public static boolean MENU = true;
	public static boolean sobre = false;
	
	public static boolean saveExists = false;
	public static boolean saveGame = false;
	
	
	public void update() {
		File file = new File("save.txt");
		if(file.exists()) {
			saveExists = true;
		}else {
			saveExists = false;
		}
		if(up) {
			Sound.Clips.menuclick.play();
			up = false;
			currentOption--;
			if(currentOption < 0)
				currentOption = maxOption;
		}
		if(down) {
			Sound.Clips.menuclick.play();
			down = false;
			currentOption++;
			if(currentOption > maxOption)
				currentOption = 0;
		}
	
		
		if(enter) {
			Sound.Clips.menuclick.play();
			enter = false;
			if(options[currentOption] == "novo jogo" || options[currentOption] == "continuar") {
				Game.GameState = "NORMAL";
				PAUSE = false;
				file = new File("save.txt");
				file.delete();                  //jogo pausado volta para menu
			}else if(options[currentOption] == "sobre" && PAUSE == true) {
		         PAUSE = false;
		         MENU = true;
		        int CUR_LEVEL = 1;
		        String newWord = "Level"+CUR_LEVEL+".png";
		 		Word.restartGame(newWord);
		 		Player.Revolver = false;
		 		Player.MachineGun = false;
		 		Player.ammo = 0;
		 		Player.ammoMachineGun = 0;
		 		Sound.Clips.menumusic.loop();  //jogo no memu vai para  tutorial
			}else if(options[currentOption] == "sobre" && MENU == true) {				
		        MENU = false; 
				sobre = true;                   //jogo no tutorial volta para menu
			}else if(options[currentOption] == "sobre" && sobre == true) {				
				sobre = false;
				MENU = true;          
			}else if(options[currentOption] == "sair") {
				System.exit(1);
			}else if(options[currentOption] == "carregar jogo") {
				file = new File("save.txt");
				if(file.exists()) {
					String saver = loadGame(10);
					applySave(saver);
				}
			}
		} 
	}
	
	public static void applySave(String str) {
		String[] spl = str.split("/");
		for(int i = 0; i < spl.length; i++) {
			String[] spl2 = spl[i].split(":");
			switch(spl2[0]) {
			case "level":
				Word.restartGame("level"+spl2[1]+".png");
				Game.GameState = "NORMAL";
				PAUSE = false;
				break;
			}
		}
	}
	
	public static String loadGame(int encode) {
		String line = "";
		File file = new File("save.txt");
		if(file.exists()) {
			try {
				String singleLine = null;
				BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
				try {
					while((singleLine = reader.readLine()) != null){
						String[] trans = singleLine.split(":");
						char[] valor = trans[1].toCharArray();
						trans[1] = "";
						for(int i = 0; i < valor.length;i++) {
							valor[i] -=encode;
							trans[1] += valor[i];
						} 
						line += trans[0];
						line += ":";
						line += trans[1];
						line+="/";
					}
				}catch(IOException e) {}
			}catch(FileNotFoundException e) {}
		}
		return line;
	}
	
	public static void saveGame(String[] valor1, int[] valor2, int encode) {
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter("save.txt"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
		for(int i = 0; i < valor1.length;i++) {
			String current = valor1[i];
			current+=":";
			char[] value = Integer.toString(valor2[i]).toCharArray();
			for(int n = 0; n< value.length;n++) {
				value[n]+=encode;
				current+=value[n];
			}
			try {
				write.write(current);
				if(i < valor1.length - 1) {
					write.newLine();
				}
			}catch(IOException e) {}
		}
		try {
			write.flush();
			write.close();
		}catch(IOException e) {}
	}
	
	public void render(Graphics g) {
		//OPCOES DE MENU
		Graphics2D g2 = (Graphics2D) g;
		if(MENU == true) {
			
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		g.setColor(Color.RED);
		g.setFont(new Font("arial",Font.BOLD,36));	
		g.drawString("JP'S GAME", (Game.WIDTH*Game.SCALE) / 2 - 110, (Game.HEIGHT*Game.SCALE) / 2 - 250);
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,24));
		g.drawString("Novo jogo", (Game.WIDTH*Game.SCALE) / 2 - 60, 280);
		g.drawString("Carregar jogo", (Game.WIDTH*Game.SCALE) / 2 - 80, 320);
		g.drawString("Sobre", (Game.WIDTH*Game.SCALE) / 2 - 40, 360);
		g.drawString("Sair", (Game.WIDTH*Game.SCALE) / 2 - 30, 400);
		} else if(PAUSE == true){
			
			//OPCOES DE MENU PAUSADO
			g2.setColor(new Color(0,0,0,100));
			g2.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
			g.setColor(Color.RED);
			g.setFont(new Font("arial",Font.BOLD,36));	
			g.drawString("JP'S GAME", (Game.WIDTH*Game.SCALE) / 2 - 110, (Game.HEIGHT*Game.SCALE) / 2 - 250);
			g.setColor(Color.white);
			g.setFont(new Font("arial",Font.BOLD,24));
			g.drawString("Continuar", (Game.WIDTH*Game.SCALE) / 2 - 60, 280);
			g.drawString("Carregar jogo", (Game.WIDTH*Game.SCALE) / 2 - 80, 320);
			g.drawString("Voltar ao menu", (Game.WIDTH*Game.SCALE) / 2 - 80, 360);
			g.drawString("Sair", (Game.WIDTH*Game.SCALE) / 2 - 20, 400);
		}else if(sobre == true){
			
			//OPCOES DE MENU SOBRE
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial",Font.BOLD,24));	
			g.drawString("TUTORIAL", 50,50);
			g.drawString("blablablabla", 50,70);
			g.drawString("blablablabla", 50,90);
			g.drawString("Voltar ao menu", (Game.WIDTH*Game.SCALE) / 2 - 80, 360);
		}
		
		if(options[currentOption] == "novo jogo") {
			g.drawString(">", (Game.WIDTH*Game.SCALE) / 2 - 80, 280);
		}else if(options[currentOption] == "carregar jogo") {
			g.drawString(">", (Game.WIDTH*Game.SCALE) / 2 - 100, 320);
		}else if(options[currentOption] == "sobre") {
			g.drawString(">", (Game.WIDTH*Game.SCALE) / 2 - 60, 360);
		}else if(options[currentOption] == "sair") {
			g.drawString(">", (Game.WIDTH*Game.SCALE) / 2 - 55, 400);
		}
		
		if(options[currentOption] == "Continuar") {
			g.drawString(">", (Game.WIDTH*Game.SCALE) / 2 - 90, 280);
		}else if(options[currentOption] == "carregar jogo") {
			g.drawString(">", (Game.WIDTH*Game.SCALE) / 2 - 100, 320);
		}else if(options[currentOption] == "voltar ao menu") {
			g.drawString(">", (Game.WIDTH*Game.SCALE) / 2 - 40, 360);
		}else if(options[currentOption] == "sair") {
			g.drawString(">", (Game.WIDTH*Game.SCALE) / 2 - 55, 400);
		}
	}
	
}
