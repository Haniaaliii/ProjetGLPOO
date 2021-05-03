package client.musichub.network;

import java.util.HashMap;

/**
 * Classe clée au sein du pattern Commande, et implémentant aussi le design pattern singleton, elle permet d'utiliser les différentes commandes enregistrées sans pour autant en connaitre le contenue
 * @author Haïdi
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
	 * Méthode qui permet d'éxecuter une commande et son protocole
	 * @param cmd
	 */
	public void executeCommand(String cmd) {
		if(this.commands.containsKey(cmd)) {
			this.commands.get(cmd).execute();
		}
	}
	
	/**
	 * Méthode qui ajoute toutes les commande gérées par le manager
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
	 * Vérifie qu'une commande existe
	 * @param cmd nom de la commande
	 * @return vrai si la commande existe, faux si ce n'est pas le cas
	 */
	public boolean commandExists(String cmd) {
		return this.commands.containsKey(cmd);
	}
	
	/**
	 * Permet l'ajout d'une commande dans la liste
	 * @param name nom de la commande
	 * @param command commande implémentant l'interface Command
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
