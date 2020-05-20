package utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/* THE CODES (AND THEIR COMMENTS) IN THIS FILE WERE TAKEN FROM :
Youtube channel : David Dobervich at : https://www.youtube.com/watch?v=ZIzoesrHHQo
 */
public class ServerResponseHandler implements Runnable {

    private Socket socket;
    private BufferedReader receiver;

    public ServerResponseHandler(Socket socket) throws IOException {
        this.socket = socket;
        receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        // keep listening to the server 
        try {
            while (true) {
                String serverResponse = receiver.readLine();
                System.out.println("Server response : " + serverResponse);
                
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                socket.close();
                receiver.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

    }

}
