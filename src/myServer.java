import java.net.*;
import java.util.*;
import java.io.*;

class myServer{
	int port;
	private Set<String> userNames = new HashSet<>();
    private Set<Threads> userThreads = new HashSet<>();
    
	public myServer() {
		port = 8990;
	}
	
	 void startServer() throws IOException {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("listening on port: " + port);
			
			 while (true) {
	             Socket socket = serverSocket.accept();
	             System.out.println("New user connected");

	             Threads newUser = new Threads(socket, this);
	             userThreads.add(newUser);
	             newUser.start();

	         }
			 
		}
	 
	 
	 void broadcast(String message, Threads excludeUser) {
	        for (Threads thr : userThreads) {
	            if (thr != excludeUser) {
	            	thr.send(message);
	            }
	        }
	    }
	 
	 void addUserName(String userName) {
	        userNames.add(userName);
	    }
	 
	 void removeUser(String userName, Threads thr) {
	        boolean removed = userNames.remove(userName);
	        if (removed) {
	            userThreads.remove(thr);
	            System.out.println("The user " + userName + " quitted");
	        }
	    }
	 
	    Set<String> getUserNames() {
	        return this.userNames;
	    }
	    
	    boolean hasUsers() {
	        return !this.userNames.isEmpty();
	    }
	    
	public static void main(String args[]) throws IOException {
		myServer server = new myServer();
		server.startServer();

	} 
	
}