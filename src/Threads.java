import java.io.*;
import java.net.*;

public class Threads extends Thread {
	private Socket socket;
    private myServer server;
	private PrintWriter write;
	
	 public Threads(Socket socket, myServer server) {
	        this.socket = socket;
	        this.server = server;
	    }
	 
	    public void run() {
	        try {
	            InputStream input = socket.getInputStream();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	 
	            OutputStream output = socket.getOutputStream();
	            write = new PrintWriter(output, true);
	 
	            printUsers();
	 
	            String userName = reader.readLine();
	            server.addUserName(userName);
	 
	            String serverMessage = userName + " is connected";
	            server.broadcast(serverMessage, this);
	 
	            String clientMessage;
	 
	            do {
	                clientMessage = reader.readLine();
	                serverMessage = "[" + userName + "]: " + clientMessage;
	                server.broadcast(serverMessage, this);
	 
	            } while (!clientMessage.equals("quit"));
	 
	            server.removeUser(userName, this);
	            socket.close();
	 
	            serverMessage = userName + " quitted.";
	            server.broadcast(serverMessage, this);
	 
	        } catch (IOException e) {
	        }
	    }

	    void printUsers() {
	        if (server.hasUsers()) {
	            write.println("Connected users: " + server.getUserNames());
	        } else {
	            write.println("No users connected");
	        }
	    }

	    void send(String message) {
	        write.println(message);
	    }
}
