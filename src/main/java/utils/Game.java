package utils;

import com.mycompany.kelimeavigame.models.Client;
import com.mycompany.kelimeavigame.models.ConnectionInfo;
import com.mycompany.kelimeavigame.models.Player;
import com.mycompany.kelimeavigame.models.Rules;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public static List<String> connectedPlayersNicknames = new ArrayList<String>();
    public static Rules gameRules;
    public static ConnectionInfo serverConnectionInfo;
    public static ConnectionInfo clientConnectionInfo;
    public static String serverNickname;
    public static String clientNickname;
    public static Client client;
    public static javax.swing.JFrame clientLoginWindow;
    public static javax.swing.JFrame clientGameInfoWindow;
}
