package serveur.commands;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

import serveur.musichub.business.MusicHub;
import serveur.musichub.business.NoPlayListFoundException;
import serveur.musichub.business.PlayList;

/**
 * Protocole établie avec le client afin de supprimer la playlsit souhaité
 * 
 *
 */
public class CommandDeletePlaylist implements Command {

	@Override
	public void execute(MusicHub theHub, OutputStream out, PrintWriter outPrint, BufferedReader in, Socket socket) {
		sendMessage("Delete an existing playlist. Available playlists:",outPrint);
		String listPlaylists = "";
		Iterator<PlayList> itp = theHub.playlists();
		while (itp.hasNext()) {
			PlayList p = itp.next();
			listPlaylists += p.getTitle() + "\n";
		}
		sendMessage(""+countLines(listPlaylists),outPrint);
		sendMessage(listPlaylists,outPrint);
		
		String pltitle = getMessage(in);
		try {
			theHub.deletePlayList(pltitle);
		}	catch (NoPlayListFoundException ex) {
			System.out.println (ex.getMessage());
		}
		sendMessage("Playlist deleted!",outPrint);
	}

}
