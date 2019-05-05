package top.chorg.kernel.communication.auth;

import top.chorg.kernel.communication.api.auth.User;
import top.chorg.support.DateTime;
import top.chorg.system.Global;
import top.chorg.system.Sys;

public class AuthManager {
    private static User user = null;

    /**
     * Complete the authentication and save the user information.
     *
     * @param user The user information variable.
     */
    public static void completeAuth(User user) {

    }

    public static boolean isOnline() {
        return true;
    }

    public static void bringOffline() {

    }

    public static boolean updateUserInfo() {
        return false;
    }

    public static User getUser() {
        return new User(1, new int[]{}, 1, 0, 0, "root", null, null, null, null, null, new DateTime(), 'U');
    }

}
