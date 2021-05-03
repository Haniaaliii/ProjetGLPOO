package client.musichub.sound;

import javax.sound.sampled.AudioInputStream;

/**
 * Thread utilisé dans le protocole d'écoute d'une musique
 * @author Haïdi
 *
 */
public class MusicThread implements Runnable {

	AudioInputStream ais;
	
	/**
	 * Constructeur du thread
	 * @param ais source de la musique
	 */
	public MusicThread(AudioInputStream ais) {
		this.ais = ais;
	}
	
	@Override
	public void run() {
		MusicLoader ml = MusicLoader.getInstance();
		
		ml.listenMusicAIS(this.ais);

	}

}
