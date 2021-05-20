import java.io.*;
import java.net.*;

public class Write extends Thread {
	private Socket socket;
    private myClient client;
	private PrintWriter write;

	public Write(Socket socket, myClient client) throws IOException {
		  this.socket = socket;
	      this.client = client;
	      
	      OutputStream output = socket.getOutputStream();
          write = new PrintWriter(output, true);
	}

	
	public void run() {
			Console console = System.console();	 
	        String userName = console.readLine("\nEnter your Username: ");
	        client.setUserName(userName);
	        write.println(userName);
	 
	        String text;
	 
	        do {
	            text = console.readLine("[" + userName + "]: ");
	            write.println(text);
	 
	        } while (!text.equals("quit"));
	        
	        try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
