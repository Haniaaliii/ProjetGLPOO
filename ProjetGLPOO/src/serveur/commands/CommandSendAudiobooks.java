package serveur.commands;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import serveur.musichub.business.MusicHub;

/**
 * Etablie un protocole avec le client pour lui envoyer la liste des livres audio
 *
 *
 */
public class CommandSendAudiobooks implements Command {

	@Override
	public void execute(MusicHub theHub, OutputStream out, PrintWriter outPrint, BufferedReader in, Socket socket) {
		String listAudioB = theHub.getAudiobooksTitlesSortedByAuthor();
		sendMessage(""+countLines(listAudioB), outPrint);
		sendMessage(listAudioB, outPrint);
	}

}
