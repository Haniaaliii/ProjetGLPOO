package serveur.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Thread qui permet d'envoyer la musique au client
 * @author Hania
 *
 */
public class MusicThread implements Runnable {

	String fn;
	OutputStream out;
	
	public MusicThread(String fn, OutputStream out) {
		this.fn = fn;
		this.out = out;
	}
	
	@Override
	public void run() {
		this.sendMusic(fn, out);
	}
	
	public void sendMusic(String fileName, OutputStream out) {
		String DIR = System.getProperty("user.dir");
		InputStream is;
		try {
			is = new FileInputStream(DIR+"/Music/"+fileName);
			int count;
			byte[] buffer = new byte[4096];
			while((count = is.read(buffer)) != -1) {
				out.write(buffer, 0, count);
			}
			//socket.getOutputStream().flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
