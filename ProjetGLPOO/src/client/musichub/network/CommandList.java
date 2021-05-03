package client.musichub.network;

/**
 * Protocole simple sans liens ave le serveur pour avoir la liste des commandes
 * 
 *
 */
public class CommandList implements Command{

	@Override
	public void execute() {
		System.out.println("[Command List]");
		System.out.println("h : Show command list");
		System.out.println("t : Display albums");
		System.out.println("g : Display songs of an album");
		System.out.println("u : Display audiobooks");
		System.out.println("p : Create playlist");
		System.out.println("- : Delete playlist");
		System.out.println("m : Play a song");
	}
	
	

}
