package client.musichub.network;

/**
 * Etablie un protocole avec le serveur afin d'obtenir la liste des albums disponibles.
 * 
 *
 */
public class CommandDisplayAlbum implements Command{

	@Override
	public void execute() {
		ServerConnexion sc = ServerConnexion.getInstance(null, 0);
		String msg = sc.getMessage();
		int lines = Integer.parseInt(msg);
		System.out.println(lines);
		for(int i = 0;i<=lines;i++) {
			msg = sc.getMessage();
			System.out.println(msg);
		}
	}

}
