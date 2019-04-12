package top.chorg.adapters.auth;

import top.chorg.WindowEventsAdapter;
import top.chorg.WindowManager;
import top.chorg.views.Auth.LoginFrameTest;

public class GetLoginResult extends WindowEventsAdapter {

    public GetLoginResult(String... args) {
        super(args);
    }

    @Override
    public int onInvoke() throws IllegalArgumentException {
        if (!WindowManager.containsKey(1)) return 1;
        (WindowManager.get(1, LoginFrameTest.class)).loginResult(nextArg());
        return 0;
    }
}
