package client.musichub.network;

import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;

import client.musichub.sound.MusicLoader;
import client.musichub.sound.MusicThread;

/**
 * Etablie le protocole pour jouer une musique a partir du serveur distant.
 * 
 *
 */
public class CommandPlayMusic implements Command{

	@Override
	public void execute() {
		ServerConnexion sc = ServerConnexion.getInstance(null, 0);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		MusicLoader ml = MusicLoader.getInstance();
		
		String msg = sc.getMessage();
		System.out.println(msg);
		String outMsg = scan.nextLine();
		sc.sendMessage(outMsg);
		AudioInputStream music = sc.getAudio();
		//ml.listenMusicAIS(music);
		Thread musicplayer = new Thread(new MusicThread(music));
		musicplayer.start();
		boolean quitter = false;
		while(!quitter) {
			
			do {
				System.out.println("Command list for current track:");
				System.out.println("p:pause");
				System.out.println("x:stop");
				outMsg = scan.nextLine();
			}while(!outMsg.equals("x") && !outMsg.equals("p"));
			sc.sendMessage(outMsg);
			if(outMsg.equals("x")) {
				musicplayer.stop();
				quitter = true;
				//sc.throwPlus();
			}
		}
		sc.sendMessage("ok");
		while(!sc.getMessage().equals("OKil963gf")) {
			
		}
	}

}
