package CommunicationModels;

import java.io.Serializable;
import java.util.List;

public class JoinedPlayers implements Serializable {

    List<String> joinedPlayrs;
 

    public JoinedPlayers(List<String> joinedPlayrs) {
        this.joinedPlayrs = joinedPlayrs;
    }

}
