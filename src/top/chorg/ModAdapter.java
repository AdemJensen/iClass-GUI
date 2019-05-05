package top.chorg;

import top.chorg.gui.FullGuiAdapter;
import top.chorg.system.Global;

import static study_swing.DataGettersSupport.registerAdapters;

public class ModAdapter {
    public void init() {
        Global.guiAdapter = new FullGuiAdapter();
        Global.guiAdapter.register(
                "loginResult",
                study_swing.Log_in_interface.LoginAdapter.class, "logResult");
        Global.guiAdapter.register(
                "startup",
                study_swing.Log_in_interface.LoginAdapter.class, "loginStart"
        );
        Global.guiAdapter.register(
                "regResult",
                study_swing.Log_in_interface.LoginAdapter.class, "regResult");
//        Global.guiAdapter.register(
//                "uploadFile",
//                study_swing.Document.DocAdapter.class, "getUpload");
//        Global.guiAdapter.register(
//                "fetchFileList",
//                study_swing.Document.DocAdapter.class, "getDocumentMessages");
//        Global.guiAdapter.register(
//                "downloadFile",
//                study_swing.Document.DocAdapter.class, "getDownload");

        registerAdapters();
//        Global.guiAdapter.register(
//                "sendChat",
//                study_swing.Chat.ChatAdapter.class, "sendChat"
//        );
        Global.guiAdapter.register(
                "onChat",
                study_swing.Chat.ChatAdapter.class, "receiveChat"
        );
        Global.guiAdapter.register(
                "onNewAnnounce",
                study_swing.announecment.AnnounceAdapter.class, "onNewAnnounce"
        );
        Global.guiAdapter.register(
                "onNewVote",
                study_swing.announecment.AnnounceAdapter.class, "onNewVote"
        );
//        Global.guiAdapter.register(
//                "fetchChatHistory",
//                study_swing.Chat.ChatAdapter.class, "getChatHistory"
//        );

    }
}
//setUpFile