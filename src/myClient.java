import java.net.*;
import java.io.*;

class myClient {
	private String userName;
	private int port;
	private String localhost;;
	
	public myClient() {
        port = 8990;
        localhost = "127.0.0.1";
    }
	public static void main(String args[]) throws IOException {
		myClient client = new myClient();
		client.Connect();
	}
	 void Connect() throws IOException {
		Socket s = new Socket(localhost, port);
		System.out.println("Connected to the server...");
		new Read(s, this).start();
        new Write(s, this).start();
	}
	void setUserName(String userName) {
        this.userName = userName;
    }
 
    String getUserName() {
        return this.userName;
    }
	
}