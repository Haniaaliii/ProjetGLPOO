package client.musichub.network;

import java.util.Scanner;

/**
 * Etablie un protocole avec le serveur afin de supprimer les playlists créées
 * @author Haïdi
 *
 */
public class CommandDeletePlaylist implements Command {

	@Override
	public void execute() {
		ServerConnexion sc = ServerConnexion.getInstance(null, 0);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		String msg = sc.getMessage();
		System.out.println(msg);
		msg = sc.getMessage();
		int lines = Integer.parseInt(msg);
		for(int i = 0;i<=lines;i++) {
			msg = sc.getMessage();
			System.out.println(msg);
		}
		
		String outMsg = scan.nextLine();
		sc.sendMessage(outMsg);
		
		msg = sc.getMessage();
		System.out.println(msg);
	}

}
