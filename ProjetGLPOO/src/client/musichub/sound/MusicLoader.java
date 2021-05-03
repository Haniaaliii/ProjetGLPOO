package client.musichub.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 * Classe répondant au Design Pattern Singleton car il n'y a besoin d'initialiser qu'un seul MusicLoader
 * 
 *
 */
public class MusicLoader {

	/**
	 * Instance du Singleton
	 */
	private static MusicLoader instance;
	
	/**
	 * Méthode utilisée pour lire une source AudioInputStream (donc une musique ou livre audio).
	 * @param ais source de la musique sous la forme d'AudioInputStream
	 */
	public void listenMusicAIS(AudioInputStream ais) {
		try {
			AudioFormat audioFormat = ais.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
			SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);
			line.start();
			byte[] samples = new byte[4096];
			int count = 0;
			while((count = ais.read(samples,0,4096)) != -1) {
				line.write(samples, 0, count);
				//System.out.println("PPP");
			}
			System.out.println("écoute finie");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Permet d'obtenir l'instance du Singleton
	 * @return
	 */
	public static MusicLoader getInstance() {
		if(instance == null) {
			instance = new MusicLoader();
		}
		return instance;
	}
	
}
