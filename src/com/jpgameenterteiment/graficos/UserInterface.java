package com.jpgameenterteiment.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.jpgameenterteiment.entities.Player;
import com.jpsgameenterteiment.main.Game;

public class UserInterface {
	
	private int AjusteUIRevolverx = 1030, AjusteUIRevolvery = 695;
	private int AjusteUIMachineGunx = 1046, AjusteUIMachineGuny = 685;
	
	
	public void render(Graphics g) {
		//BARRA DE VIDA
		g.setColor(Color.white);
		g.fillRect(20, 20,50, 10);
		g.setColor(Color.red);
		g.fillRect(21, 21,48, 8);
		g.setColor(Color.green);
		g.fillRect(21, 21, (int)((Game.player.life/Game.player.maxLife)*48), 8);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD,8));
		g.drawString((int)Game.player.life+" /"+(int)Game.player.maxLife, 30, 28);
		
		//MUNICAO REVOLVER
	
		g.setFont(new Font("arial", Font.BOLD,9));
		g.drawString("Revolver: "+ Player.ammo, Game.WIDTH*Game.SCALE - AjusteUIRevolverx ,Game.HEIGHT*Game.SCALE - AjusteUIRevolvery);
		
		//MUNICAO MACHINEGUN

		g.setFont(new Font("arial", Font.BOLD,9));
		g.drawString("MachineGun: "+ Player.ammoMachineGun, Game.WIDTH*Game.SCALE - AjusteUIMachineGunx ,Game.HEIGHT*Game.SCALE - AjusteUIMachineGuny);
				
		//PACK LIFE
		g.setFont(new Font("arial", Font.BOLD,9));
		g.drawString("Life: "+ Player.packlife, Game.WIDTH*Game.SCALE - 1007 ,Game.HEIGHT*Game.SCALE - 675);
		g.drawString("BigLife: "+ Player.packbiglife, Game.WIDTH*Game.SCALE - 1023 ,Game.HEIGHT*Game.SCALE - 665);
		
		
		//SILHUETA JOGADOR		
				
		
	}
}
