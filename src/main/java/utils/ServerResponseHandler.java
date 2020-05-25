package utils;

import CommunicationModels.Message;
import com.mycompany.kelimeavigame.models.Rules;
import com.mycompany.kelimeavigame.views.ClientWaitingWindow;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import javax.swing.JOptionPane;


/* THE CODES (AND THEIR COMMENTS) IN THIS FILE WERE TAKEN FROM :
Youtube channel : David Dobervich at : https://www.youtube.com/watch?v=ZIzoesrHHQo
Youtube channel : zaneacademy at : https://www.youtube.com/watch?v=1up-oHjCcis
 */
public class ServerResponseHandler implements Runnable {

    private Socket socket;
    private ObjectInputStream objectReceiver;
    public Boolean threadIsRunning = true;

    public Boolean getThreadIsRunning() {
        return threadIsRunning;
    }

    public ServerResponseHandler(Socket socket) throws IOException {
        this.socket = socket;
        objectReceiver = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        // keep listening to the server 
        try {
            while (threadIsRunning) {
                Message serverResponse = (Message) objectReceiver.readObject();
                checkServerResponseKeyword(serverResponse);
            }
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void checkServerResponseKeyword(Message serverResponse) throws IOException {
        if (serverResponse.header.equals("playersList")) {
            // when a new client (player) joines the game by sending join/nickname message
            // the server responses by greeting the newly joined client
            // so navigate him/her to WaitingWindow
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    ClientWaitingWindow waitingWindow = new ClientWaitingWindow((List<String>) serverResponse.content);
                    waitingWindow.setVisible(true);
                }

            });
        } else if (serverResponse.header.equals("error")) {
            // an error happend, show its message to the user
            showErrorMessage(serverResponse);
        } else if (serverResponse.header.equals("playerJoined")) {
            // new player has just joined the game, so update the players list
            ClientWaitingWindow.addNewPlayerToList((String) serverResponse.content);
        } else if (serverResponse.header.equals("playerDisjoined")) {
            // a player has just disjoined from the game, so update the players list
            System.out.println(Game.clientNickname + " received playerDisjooined signal! and the new list is : " + serverResponse.content + " and threadIsRunning value is " + threadIsRunning);
            ClientWaitingWindow.updatePlayersList((List<String>) serverResponse.content);
        } else if (serverResponse.header.equals("gameInfo")) {
            var gameInfoWindow = (GameInfoWindow) Game.clientGameInfoWindow;
            System.out.println("received game info  " + (Rules) serverResponse.content);
            gameInfoWindow.initFieldsWithRemoteGameInfo((Rules) serverResponse.content);
            gameInfoWindow.setVisible(true);
        }

    }

    private void showErrorMessage(Message serverResponse) {
        JOptionPane.showMessageDialog(null, (String) serverResponse.content);
    }

}
