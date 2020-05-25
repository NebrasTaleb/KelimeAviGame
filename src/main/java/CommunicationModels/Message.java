package CommunicationModels;

import java.io.Serializable;

public class Message implements Serializable {

    public String header;
    public Object content;

    public Message(String header, Object content) {
        this.header = header;
        this.content = content;
    }
}
