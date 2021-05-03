package serveur.commands;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

import serveur.musichub.business.AudioElement;
import serveur.musichub.business.MusicHub;
import serveur.musichub.business.NoElementFoundException;
import serveur.musichub.business.NoPlayListFoundException;
import serveur.musichub.business.PlayList;

/**
 * Protocole utilisé pour communiquer avec un client qui souhaite créer un playlist
 * 
 *
 */
public class CommandCreatePlaylist implements Command {

	@Override
	public void execute(MusicHub theHub, OutputStream out, PrintWriter outPrint, BufferedReader in, Socket socket) {
		sendMessage("Add an existing song or audiobook to a new playlist",outPrint);
		sendMessage("Type the name of the playlist you wish to create:",outPrint);
		String pName = getMessage(in);
		PlayList pl = new PlayList(pName);
		theHub.addPlaylist(pl);
		sendMessage("Available elements: ",outPrint);
		String listelems = "";
		Iterator<AudioElement> itael = theHub.elements();
		while (itael.hasNext()) {
			AudioElement ae = itael.next();
			listelems += ae.getTitle() + "\n";
		}
		sendMessage(""+countLines(listelems),outPrint);
		sendMessage(listelems,outPrint);
		boolean stillAdd = true;
		do {
			sendMessage("Type the name of the audio element you wish to add or 'n' to exit:",outPrint);
			String elementTitle = getMessage(in);
			if(!elementTitle.equals("n")) {
				try {
					theHub.addElementToPlayList(elementTitle, pName);
				} catch (NoPlayListFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoElementFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				stillAdd = false;
			}
		}while(stillAdd);
		sendMessage("Playlist created!",outPrint);
	}

}
