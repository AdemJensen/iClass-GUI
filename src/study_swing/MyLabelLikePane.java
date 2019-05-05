package study_swing;

public class MyLabelLikePane extends Dialog_Plug_in{
    public MyLabelLikePane(String message){
        super();
        this.setText(message);
        this.setEditable(false);
    }
    public MyLabelLikePane(){
        super();
        this.setEditable(false);
    }
}

