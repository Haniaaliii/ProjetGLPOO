package client.musichub.network;

/**
 * Classe permettant d'�tablir le Design Pattern Commande
 * @author Ha�di
 *
 */
public interface Command {
	
	/**
	 * Methode cl� dans le design pattern Commande.
	 * Elle sera utilis�e par le manager de commande et r�pondra aux diff�rents protocoles �tablie dans les classes o� cette interface est impl�ment�e.
	 * 
	 */
	public void execute();

}
