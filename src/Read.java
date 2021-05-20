import java.io.*;
import java.net.*;

public class Read extends Thread {
		private Socket socket;
		private myClient client;
	 	private BufferedReader read;
	 	
	 	public Read(Socket socket, myClient client) throws IOException {
	 		  this.socket = socket;
	 	      this.client = client;
	 	      
	 	      InputStream input = socket.getInputStream();
	 	      read = new BufferedReader(new InputStreamReader(input));
		}
	    public void run() {
	        while (true) {
	            try {
	                String message = read.readLine();
	                System.out.println("\n"+message);
	            } catch (IOException e) {
	                break;
	            }
	       	 
                if (client.getUserName() != null) {
                    System.out.print("[" + client.getUserName() + "]: ");
                }
	        }
	    }
}
