package top.chorg.adapters.auth;

import top.chorg.WindowEventsAdapter;
import top.chorg.WindowManager;
import top.chorg.views.Auth.LoginFrameTest;

public class OpenLoginWindow extends WindowEventsAdapter {

    public OpenLoginWindow(String... args) {
        super(args);
    }

    public int onInvoke() throws IllegalArgumentException {
        if (!WindowManager.add(new LoginFrameTest(), 1)) {
            System.out.println("ERROR");
        }
        return 0;
    }
}
