package client.musichub.network;

import java.util.HashMap;

/**
 * Classe cl�e au sein du pattern Commande, et impl�mentant aussi le design pattern singleton, elle permet d'utiliser les diff�rentes commandes enregistr�es sans pour autant en connaitre le contenue
 * @author Ha�di
 *
 */
public class CommandManager {
	
	private static CommandManager instance;
	private final HashMap<String,Command> commands;
	
	/**
	 * Constructeur qui initialise la liste des commandes
	 */
	private CommandManager() {
		this.commands = new HashMap<>();
		this.createCommands();
	}
	
	/**
	 * M�thode qui permet d'�xecuter une commande et son protocole
	 * @param cmd
	 */
	public void executeCommand(String cmd) {
		if(this.commands.containsKey(cmd)) {
			this.commands.get(cmd).execute();
		}
	}
	
	/**
	 * M�thode qui ajoute toutes les commande g�r�es par le manager
	 */
	public void createCommands() {
		addCommand("h",new CommandList());
		addCommand("t",new CommandDisplayAlbum());
		addCommand("g",new CommandSongsAlbum());
		addCommand("u",new CommandAudioBooks());
		addCommand("p",new CommandCreatePlaylist());
		addCommand("-",new CommandDeletePlaylist());
		addCommand("m",new CommandPlayMusic());
	}
	
	/**
	 * V�rifie qu'une commande existe
	 * @param cmd nom de la commande
	 * @return vrai si la commande existe, faux si ce n'est pas le cas
	 */
	public boolean commandExists(String cmd) {
		return this.commands.containsKey(cmd);
	}
	
	/**
	 * Permet l'ajout d'une commande dans la liste
	 * @param name nom de la commande
	 * @param command commande impl�mentant l'interface Command
	 */
	public void addCommand(String name, Command command) {
		this.commands.put(name, command);
	}
	
	/**
	 * Permet d'obtenir l'instance du manager
	 * @return l'instance du manager
	 */
	public static CommandManager getInstance() {
		if(instance == null) {
			instance = new CommandManager();
		}
		return instance;
	}

}
