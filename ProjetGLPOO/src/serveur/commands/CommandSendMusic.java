package serveur.commands;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

import serveur.main.MusicThread;
import serveur.musichub.business.AudioElement;
import serveur.musichub.business.MusicHub;

/**
 * Etablie un protocole avec le client pour lui envoyer la musique qu'il souhaite écouter
 *
 *
 */
public class CommandSendMusic implements Command{

	@Override
	public void execute(MusicHub theHub, OutputStream out, PrintWriter outPrint, BufferedReader in, Socket socket) {
		sendMessage("Enter a song you want to listen :", outPrint);
		
		String st = getMessage(in);
		
		Iterator<AudioElement> itel = theHub.elements();
		
		String pathtosong = "error.wav";
		
		while (itel.hasNext()) {
			AudioElement ae = itel.next();
			if(ae.getTitle().equals(st)) pathtosong = ae.getContent();
		}
		
		Thread sendmusic = new Thread(new MusicThread(pathtosong, out));
		sendmusic.start();
		boolean quitter = false;
		while(!quitter) {
			st = getMessage(in);
			
			if(st.equals("x")) {
				sendmusic.stop();
				quitter = true;
			}
		}
		
		//sendMusic(pathtosong,out,socket);
		getMessage(in);
		sendMessage("\nOKil963gf", outPrint);
		
	}

}
