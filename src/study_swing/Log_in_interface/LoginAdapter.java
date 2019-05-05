package study_swing.Log_in_interface;

import top.chorg.gui.WindowEventsAdapter;
import top.chorg.gui.WindowManager;
import top.chorg.system.Global;

public class LoginAdapter extends WindowEventsAdapter {
    public LoginAdapter(String... args){
        super(args);
    }
    public int loginStart(){
        if (!WindowManager.add(new LogFrame(), 1)) {
            return -1;//error happened
        }else return 0;
    }
    public static volatile boolean onGoingLogin = false;
    private static String loginTemp;
    public static String login(String username, String password) {
        while (onGoingLogin) Thread.onSpinWait();
        onGoingLogin = true;
        Global.guiAdapter.executeCmd("login", "Normal", username, password);
        //System.out.println("waiting");
        while (onGoingLogin) Thread.onSpinWait();
        //System.out.println("OK");
        return loginTemp;
    }
    public int logResult(){
        //if (!WindowManager.containsKey(1)) return -1;
        loginTemp = nextArg();
        //System.out.println("false");
        onGoingLogin = false;
        return 0;//means success
    }

    public static boolean onGoingReg = false;
    public int regResult(){
        if (!WindowManager.containsKey(2)) return -1;
        onGoingReg = false;
        WindowManager.get(2, RegisterFrame.class).regResult(nextArg());
        return 0;
    }



}
