package client.musichub.main;

import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;

import client.musichub.network.CommandManager;
import client.musichub.network.ServerConnexion;
import client.musichub.sound.MusicLoader;

/**
 * Classe principale du client qui gère toute les requêtes
 * 
 */
public class Main {
	
	ServerConnexion sc;
	MusicLoader ml;
	Scanner scan;
	
	/**
	 * Constructeur classe principale client
	 * <p>
	 * Initialise un scanner pour permettre au client d'envoyer ensuite des messages au serveur
	 * </p>
	 */
	public Main() {
		scan = new Scanner(System.in);
	}
	
	/**
	 * Méthode qui lance toute la logique et gère la communication client -> serveur
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception {
		sc = ServerConnexion.getInstance("localhost", 5213);
		ml = MusicLoader.getInstance();
		sc.sendMessage("OK");
		CommandManager cmdMan = CommandManager.getInstance();
		while(true) {
			
			String msg = sc.getMessage();
			String outMsg="error";
			do {
				System.out.println("msg = " + msg);
				outMsg = scan.nextLine();
			}while(!cmdMan.commandExists(outMsg));
			sc.sendMessage(outMsg);
			
			//Lance la commande que l'utilisateur souhaite lancer
			cmdMan.executeCommand(outMsg);
		}
	}
	
	/**
	 * Méthode de lancement de l'application client
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Main mc = new Main();
		try {
			mc.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
