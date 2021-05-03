package serveur.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

import serveur.commands.CommandManager;
import serveur.musichub.business.Album;
import serveur.musichub.business.AudioBook;
import serveur.musichub.business.AudioElement;
import serveur.musichub.business.MusicHub;
import serveur.musichub.business.NoAlbumFoundException;
import serveur.musichub.business.NoElementFoundException;
import serveur.musichub.business.NoPlayListFoundException;
import serveur.musichub.business.PlayList;
import serveur.musichub.business.Song;

/**
 *
 * Classe utilisée par les thread gérant les clients
 * 
 *
 */
public class ClientLoop implements Runnable {
	
	Socket socket;
	
	OutputStream out;
	BufferedReader in;
	PrintWriter outPrint;
	MusicHub theHub;
	
	String DIR = System.getProperty("user.dir");
	
	/**
	 * Class qui gère un client
	 * <p>
	 * Contient tout le nécessaire pour l'application musichub
	 * </p>
	 * @param socket correspondant au client souhaitant utiliser l'application
	 * @param main simple référence au main
	 */
	public ClientLoop(Socket socket, Main main) {
		this.socket = socket;
		try {
			out = socket.getOutputStream();
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outPrint = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		theHub = new MusicHub ();
	}
	
	
	/**
	 * Method qui permet d'envoyer la liste des commandes vers le client
	 * 
	 * @param out
	 *		Le PrintWriter qui envoie vers le client
	 */
	public void sendCommands(PrintWriter out) {
		sendMessage("h: display command list", out);
		sendMessage("t: display the album titles, ordered by date",out);
		sendMessage("g: display songs of an album, ordered by genre",out);
		sendMessage("d: display songs of an album",out);
		sendMessage("u: display audiobooks ordered by author",out);
		sendMessage("c: add a new song",out);
		sendMessage("a: add a new album",out);
		sendMessage("+: add a song to an album",out);
		sendMessage("l: add a new audiobook",out);
		sendMessage("p: create a new playlist from existing songs and audio books",out);
		sendMessage("-: delete an existing playlist",out);
		sendMessage("s: save elements, albums, playlists",out);
		sendMessage("m: play a song",out);
	}
	
	public void sendMessage(String msg, PrintWriter out) {
		out.println(msg);
		out.flush();
	}
	
	public String getMessage() throws IOException {
		
		String msg = null;
		
		msg = in.readLine();
		
		
		return msg;
		
	}
	
	public void sendMusic(String fileName, OutputStream out) {
		InputStream is;
		try {
			is = new FileInputStream(DIR+"/Music/"+fileName);
			int count;
			byte[] buffer = new byte[4096];
			while((count = is.read(buffer)) != -1) {
				socket.getOutputStream().write(buffer, 0, count);
			}
			//socket.getOutputStream().flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static int countLines(String str){
	   String[] lines = str.split("\r\n|\r|\n");
	   return  lines.length;
	}

	@Override
	public void run() {
		
		System.out.println("Enter client loop");
		String msg;
		try {
			msg = getMessage();
		} catch (IOException e) {
			return;
			//e.printStackTrace();
		}
		if(msg.equals("OK")) {
			System.out.println("Connexion done");
			CommandManager cmdMan = new CommandManager(theHub, out, outPrint, in, socket);
			while(true) {
				sendMessage("Select an action : (h for help)", outPrint);
				
				try {
					msg = getMessage();
				} catch (IOException e) {
					break;
					//e.printStackTrace();
				}
				System.out.println("from Client : "+msg);
				String listAlbum;
				
				cmdMan.executeCommand(msg);
				
				/*switch(msg) {
					case "h":
						//sendCommands(outPrint);
						break;
					case "t":
					case "g":
					case "u":
					case "p":
					case "-":
						cmdMan.executeCommand(msg);
						break;
					case "m":
						cmdMan.executeCommand(msg);
						break;
				}*/
			}
		}
	}

}
