package serveur.commands;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import serveur.musichub.business.MusicHub;
import serveur.musichub.business.NoAlbumFoundException;

/**
 * Etablie un protocole avec le client pour lui envoyer la liste des musiques d'un album
 * 
 *
 */
public class CommandSendSongsA implements Command {

	@Override
	public void execute(MusicHub theHub, OutputStream out, PrintWriter outPrint, BufferedReader in, Socket socket) {
		sendMessage("Songs of an album sorted by genre will be displayed; enter the album name, available albums are:",outPrint);
		String listAlbum = theHub.getAlbumsTitlesSortedByDate();
		sendMessage(""+countLines(listAlbum), outPrint);
		sendMessage(listAlbum, outPrint);
		String msg = getMessage(in);
		try {
			//System.out.println(theHub.getAlbumSongsSortedByGenre(msg));
			String listSongs = theHub.getAlbumSongsSortedByGenre(msg).toString();
			sendMessage(""+countLines(listSongs),outPrint);
			sendMessage(listSongs,outPrint);
		} catch (NoAlbumFoundException ex) {
			sendMessage(""+1,outPrint);
			sendMessage("No album found with the requested title " + ex.getMessage(), outPrint);
		}
	}

}
