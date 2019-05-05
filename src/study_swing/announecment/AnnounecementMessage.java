package study_swing.announecment;

public class AnnounecementMessage {
    public String title;
    public String body;
    public static AnnounecementMessage deafaulrMessage = new AnnounecementMessage("TITLE", "BODY");
    public AnnounecementMessage(String title, String body){
        this.body = body;
        this.title = title;
    }
}
