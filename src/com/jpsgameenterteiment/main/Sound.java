package com.jpsgameenterteiment.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	
	public static class Clips{
		public Clip[] clips;
		private int p;
		private int count;
		
		public Clips(byte[] buffer, int count) throws LineUnavailableException, IOException, UnsupportedAudioFileException{
			if(buffer == null) {
				return;
			}
			clips = new Clip[count];
			this.count = count;
			
			for(int i = 0; i < count; i++) {
				clips[i] = AudioSystem.getClip();
				clips[i].open(AudioSystem.getAudioInputStream(new ByteArrayInputStream(buffer)));
			}
		}
		
		public void play() {
			if(clips == null) {
				return;
			}
			clips[p].stop();
			clips[p].setFramePosition(0);
			clips[p].start();
			p++;
			if(p >= count) {
				p = 0;
			}
		}
		
		public void loop() {
			if(clips == null) {
				return;
			}
			clips[p].loop(300);
			
		}
		
		public void stop() {
			if(clips == null) {
				return;
			}
			clips[p].stop();
			
		}
		
		public static Clips musicbackground = load("/background.wav",1);
		public static Clips hurt = load("/hurt.wav",1);
		public static Clips life = load("/life.wav",1);
		public static Clips shootrevolver = load("/shootrevolver.wav",1);
		public static Clips shootmachinegun = load("/shootmachinegun.wav",1);
		public static Clips machinegun = load("/machinegun.wav",1);
		public static Clips ammo = load("/ammo2.wav",1);
		public static Clips menu = load("/menu.wav",1);
		public static Clips ammoempity = load("/ammoempity.wav",1);
		public static Clips revolver = load("/revolver.wav",1);
		public static Clips Powerup = load("/Powerup.wav",1);
		public static Clips Powerdown = load("/Powerdown.wav",1);
		public static Clips GameOver = load("/gameOver.wav",1);
		public static Clips Walking = load("/walking.wav",1);
		public static Clips Jumping = load("/jump.wav",1);
		public static Clips pistolshoot = load("/pistolshoot.wav",1);
		public static Clips pistolcock = load("/pistolcock.wav",1);
		public static Clips machinegunshoot = load("/machinegunshoot.wav",1);
		public static Clips menuclick= load("/mouseclick.wav",1);
		public static Clips menumusic= load("/cyberpunk.wav",1);
		public static Clips gameplaymusic= load("/gameplaybackground.wav",1);
		public static Clips river= load("/river.wav",1);
		public static Clips orc= load("/orc.wav",1);
		public static Clips bat= load("/bat.wav",1);
		public static Clips rainrain= load("/rainrain.wav",1);
		
		
		public static Clips load(String name, int count) {
			try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataInputStream dis = new DataInputStream(Sound.class.getResourceAsStream(name));
			
			byte[] buffer = new byte[1024];
			int read = 0;
			while((read = dis.read(buffer)) >= 0) {
				baos.write(buffer,0,read);
			}
			dis.close();
			byte[] data = baos.toByteArray();
			return new Clips(data,count);
			}catch(Exception e) {
				try {
					return new Clips(null, 0);
				}catch(Exception ee) {
					return null;
				}
			}
		}
	}
	
	/*
	private AudioClip clip;
	public static final Sound musicbackground = new Sound("/background.wav");
	public static final Sound hurt = new Sound("/hurt.wav");
	public static final Sound life = new Sound("/life.wav");
	public static final Sound shootrevolver = new Sound("/shootrevolver.wav");
	public static final Sound shootmachinegun = new Sound("/shootmachinegun.wav");
	public static final Sound machinegun = new Sound("/machinegun.wav");
	public static final Sound ammo = new Sound("/ammo2.wav");
	public static final Sound menu = new Sound("/menu.wav");
	public static final Sound ammoempity = new Sound("/ammoempity.wav");
	public static final Sound revolver = new Sound("/revolver.wav");
	public static final Sound Powerup = new Sound("/Powerup.wav");
	public static final Sound Powerdown = new Sound("/Powerdown.wav");
	public static final Sound GameOver = new Sound("/gameover.wav");
	public static final Sound Walking = new Sound("/walking.wav");
	public static final Sound Jumping = new Sound("/jump.wav");
	
	private Sound(String name) {
		try{
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		}catch(Throwable e) {}
	}
	
	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		}catch(Throwable e) {}
	}
	
	public void stop() {
		try {
		new Thread() {
			public void run() {
				clip.stop();
			}
		}.start();
		}catch(Throwable e) {}
	}
	
	public void loop() {
		try {
			new Thread() {
				public void run() {
					clip.loop();
				}
			}.start();
		}catch(Throwable e) {}
	}
	*/
}
