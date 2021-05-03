package client.musichub.network;

/**
 * Classe permettant d'établir le Design Pattern Commande
 * @author Haïdi
 *
 */
public interface Command {
	
	/**
	 * Methode clé dans le design pattern Commande.
	 * Elle sera utilisée par le manager de commande et répondra aux différents protocoles établie dans les classes où cette interface est implémentée.
	 * 
	 */
	public void execute();

}
