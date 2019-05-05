package study_swing.Chat;

import study_swing.DataGetters;
import top.chorg.gui.WindowEventsAdapter;
import top.chorg.gui.WindowManager;
import top.chorg.kernel.communication.api.chat.ChatMsg;
import top.chorg.kernel.communication.auth.AuthManager;
import top.chorg.system.Global;

import java.util.Objects;
import java.util.Timer;

public class ChatAdapter extends WindowEventsAdapter {

    public ChatAdapter(String... args){
        super(args);
    }

//    public static boolean chatSent = false;
//    public int sendChat() {
//        if (!chatSent) return -1;
//        Global.setVar("SEND_CHAT_RESULT", nextArg());
//        chatSent = false;
//        return 0;
//    }
//
    public int receiveChat() {
        ChatMsg msg = Objects.requireNonNull(nextArg(ChatMsg.class));
        if (msg.type == 2) {
            if (WindowManager.containsKey(100 + msg.fromId)) {
                WindowManager.get(100 + msg.fromId, singleChatFrame.class).insertIncomingMessage(msg);
            } else {
                new top.chorg.support.Timer(1000, (Object[] arg) -> {
                    new singleChatFrame(AuthManager.getUser().getId(), msg.fromId);
                    return 0;
                });
            }
        }
        else {
            if (WindowManager.containsKey(1000 + msg.toId)) {
                WindowManager.get(1000 + msg.toId, groupChatFrame.class).insertIncomingMessage(msg);
            } else {
                new top.chorg.support.Timer(1000, (Object[] arg) -> {
                    new groupChatFrame(AuthManager.getUser().getId(), DataGetters.getClassInfo(msg.toId));
                    return 0;
                });
            }
        }
        return 0;
    }
//
//    public static boolean requestChatHistory = false;
//    public int getChatHistory() {
//        if (!requestChatHistory) return -1;
//        Global.setVar("GET_CHAT_HISTORY", nextArg());
//        requestChatHistory = false;
//        return 0;
//    }









}
