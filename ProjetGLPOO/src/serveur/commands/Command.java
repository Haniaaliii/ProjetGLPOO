package serveur.commands;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import serveur.musichub.business.MusicHub;

/**
 * Classe permettant d'établir le Design Pattern Commande
 * @author Hania
 *
 */
public interface Command {
	
	public void execute(MusicHub theHub,OutputStream out, PrintWriter outPrint, BufferedReader in, Socket socket);
	
	public default void sendMessage(String msg, PrintWriter out) {
		out.println(msg);
		out.flush();
	}
	
	default int countLines(String str){
	   String[] lines = str.split("\r\n|\r|\n");
	   return  lines.length;
	}
	
	public default void sendMusic(String fileName, OutputStream out, Socket socket) {
		String DIR = System.getProperty("user.dir");
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
	
	public default String getMessage(BufferedReader in) {
		
		String msg = null;
		
		try {
			msg = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return msg;
		
	}

}
