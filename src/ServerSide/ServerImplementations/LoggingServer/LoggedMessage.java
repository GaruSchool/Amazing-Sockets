package ServerSide.ServerImplementations.LoggingServer;

/**
 * Created by Garu on 07/11/2014.
 */
public class LoggedMessage {
    private String message;
    private String senderNickname;
    private String senderIP;

    public LoggedMessage(String message, String senderNickname, String senderIP) {
        this.message = message;
        this.senderNickname = senderNickname;
        this.senderIP = senderIP;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public String getSenderIP() {
        return senderIP;
    }

    public void setSenderIP(String senderIP) {
        this.senderIP = senderIP;
    }
}
