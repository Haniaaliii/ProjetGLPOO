package serveur.commands;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import serveur.musichub.business.MusicHub;

/**
 * Classe qui permet l'utilisation du pattern Commande
 * @author Hania
 *
 */
public class CommandManager {
	
	private final HashMap<String,Command> commands;
	
	OutputStream out;
	BufferedReader in;
	PrintWriter outPrint;
	MusicHub theHub;
	Socket socket;
	
	/**
	 * Constructeur de la classe
	 * @param theHub injection de dépendance
	 * @param out injection de dépendance
	 * @param outPrint injection de dépendance
	 * @param in injection de dépendance
	 * @param socket injection de dépendance
	 */
	public CommandManager(MusicHub theHub,OutputStream out, PrintWriter outPrint, BufferedReader in, Socket socket) {
		this.commands = new HashMap<>();
		this.out = out;
		this.outPrint = outPrint;
		this.in = in;
		this.theHub = theHub;
		this.socket = socket;
		this.createCommands();
	}
	
	/**
	 * permet d'éxecuter la commande souahité
	 * @param cmd nom de la commande
	 */
	public void executeCommand(String cmd) {
		if(this.commands.containsKey(cmd)) {
			this.commands.get(cmd).execute(theHub,out,outPrint,in,socket);
		}
	}
	
	/**
	 * ajoute les différentes commande à la liste
	 */
	public void createCommands() {
		this.commands.put("t", new CommandSendAlbums());
		this.commands.put("g", new CommandSendSongsA());
		this.commands.put("u", new CommandSendAudiobooks());
		this.commands.put("p", new CommandCreatePlaylist());
		this.commands.put("-", new CommandDeletePlaylist());
		this.commands.put("m", new CommandSendMusic());
	}
	
	/**
	 * vérifie que la commande éxiste
	 * @param cmd
	 * @return
	 */
	public boolean commandExists(String cmd) {
		return this.commands.containsKey(cmd);
	}
	
	/**
	 * ajoute une commande
	 * @param name
	 * @param command
	 */
	public void addCommand(String name, Command command) {
		this.commands.put(name, command);
	}

}
