package client.musichub.network;

/**
 * Permet d'établir un protocole où le client reçois les informations sur les livres audio du serveur
 * 
 *
 */
public class CommandAudioBooks implements Command {

	@Override
	public void execute() {
		ServerConnexion sc = ServerConnexion.getInstance(null, 0);
		String msg = sc.getMessage();
		int lines = Integer.parseInt(msg);
		for(int i = 0;i<=lines;i++) {
			msg = sc.getMessage();
			System.out.println(msg);
		}
	}

}
