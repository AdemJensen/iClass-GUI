package study_swing;

import top.chorg.kernel.communication.api.auth.ClassInfo;
import top.chorg.kernel.communication.api.auth.User;
import top.chorg.kernel.communication.api.chat.ChatMsg;
import top.chorg.kernel.communication.api.chat.FetchHistoryRequest;
import top.chorg.kernel.communication.api.file.FileInfo;
import top.chorg.support.Date;
import top.chorg.system.Global;

import java.io.File;

import static study_swing.DataGettersSupport.*;

public class DataGetters {

    public static void logOff() {
        Global.guiAdapter.executeCmd("logoff");
    }

    public static String getLog(int classId) {
        while (getLogOngoingTraffic) Thread.onSpinWait();
        getLogOngoingTraffic = true;
        Global.guiAdapter.executeCmd("getLog", classId + "");
        while (getLogOngoingTraffic) Thread.onSpinWait();
        return getLogTemp;
    }

    public static String alterUser(int sex, int grade, int avatarId, String realName, String nickName,
                                   String email, String phone, Date birthday) {
        while (alterUserOngoingTraffic) Thread.onSpinWait();
        alterUserOngoingTraffic = true;
        Global.guiAdapter.executeCmd("alterUser", sex + "", grade + "", avatarId + "",
                realName, nickName, email, phone, birthday == null ? null : birthday.toString());
        while (alterUserOngoingTraffic) Thread.onSpinWait();
        return alterUserTemp;
    }

    public static String changePassword(String prevPass, String newPass) {
        while (changePasswordOngoingTraffic) Thread.onSpinWait();
        changePasswordOngoingTraffic = true;
        Global.guiAdapter.executeCmd("changePassword", prevPass, newPass);
        while (changePasswordOngoingTraffic) Thread.onSpinWait();
        return changePasswordTemp;
    }

    public static User getUserInfo(int userId) {
        while (getUserInfoOngoingTraffic) Thread.onSpinWait();
        getUserInfoOngoingTraffic = true;
        Global.guiAdapter.executeCmd("fetchUserInfo", userId + "");
        while (getUserInfoOngoingTraffic) Thread.onSpinWait();
        return getUserInfoTemp;
    }

    public static String[] getUserName(int[] data) {
        while (getUserNameOngoingTraffic) Thread.onSpinWait();
        getUserNameOngoingTraffic = true;
        Global.guiAdapter.executeCmd("getUserName", Global.gson.toJson(data));
        while (getUserNameOngoingTraffic) Thread.onSpinWait();
        return getUserNameTemp;
    }
    public static String[] getNickName(int[] data) {
        while (getUserNameOngoingTraffic1) Thread.onSpinWait();
        getUserNameOngoingTraffic1 = true;
        Global.guiAdapter.executeCmd("getNickName", Global.gson.toJson(data));
        while (getUserNameOngoingTraffic1) Thread.onSpinWait();
        return getUserNameTemp;
    }
    public static String[] getRealName(int[] data) {
        while (getUserNameOngoingTraffic2) Thread.onSpinWait();
        getUserNameOngoingTraffic2 = true;
        Global.guiAdapter.executeCmd("getRealName", Global.gson.toJson(data));
        while (getUserNameOngoingTraffic2) Thread.onSpinWait();
        return getUserNameTemp;
    }

    public static int[] judgeOnline(int[] users) {
        while (judgeOnlineOngoingTraffic) Thread.onSpinWait();
        judgeOnlineOngoingTraffic = true;
        Global.guiAdapter.executeCmd("changePassword", Global.gson.toJson(users));
        while (judgeOnlineOngoingTraffic) Thread.onSpinWait();
        return judgeOnlineTemp;
    }

    public static String joinClass(int classId) {
        while (classAction1OngoingTraffic) Thread.onSpinWait();
        classAction1OngoingTraffic = true;
        Global.guiAdapter.executeCmd("joinClass", classId + "");
        while (classAction1OngoingTraffic) Thread.onSpinWait();
        return classAction1Temp;
    }

    public static String exitClass(int classId) {
        while (classAction2OngoingTraffic) Thread.onSpinWait();
        classAction2OngoingTraffic = true;
        Global.guiAdapter.executeCmd("exitClass", classId + "");
        while (classAction2OngoingTraffic) Thread.onSpinWait();
        return classAction2Temp;
    }

    public static String kickMember(int classId, int userId) {
        while (classAction3OngoingTraffic) Thread.onSpinWait();
        classAction3OngoingTraffic = true;
        Global.guiAdapter.executeCmd("kickMember", classId + "", userId + "");
        while (classAction3OngoingTraffic) Thread.onSpinWait();
        return classAction3Temp;
    }

    public static ClassInfo getClassInfo(int classId) {
        while (getClassInfoOngoingTraffic) Thread.onSpinWait();
        getClassInfoOngoingTraffic = true;
        Global.guiAdapter.executeCmd("fetchClassInfo", classId + "");
        while (getClassInfoOngoingTraffic) Thread.onSpinWait();
        return getClassInfoTemp;
    }

    public static int[] getClassOnline(int classId) {
        while (getOnlineOngoingTraffic) Thread.onSpinWait();
        getOnlineOngoingTraffic = true;
        Global.guiAdapter.executeCmd("fetchOnline", classId + "");
        while (getOnlineOngoingTraffic) Thread.onSpinWait();
        return getOnlineTemp;
    }

    public static top.chorg.kernel.communication.api.announcements.FetchListResult[] getAnnounceList(boolean self) {
        while (getAnnounceListOngoingTraffic) Thread.onSpinWait();
        getAnnounceListOngoingTraffic = true;
        Global.guiAdapter.executeCmd("fetchAnnounceList", self ? "published" : "all");
        while (getAnnounceListOngoingTraffic) Thread.onSpinWait();
        return getAnnounceListTemp;
    }

    public static top.chorg.kernel.communication.api.announcements.FetchTemplateResult[] getTemplateList() {
        while (getTemplateListOngoingTraffic) Thread.onSpinWait();
        getTemplateListOngoingTraffic = true;
        Global.guiAdapter.executeCmd("fetchTemplateList");
        while (getTemplateListOngoingTraffic) Thread.onSpinWait();
        return getTemplateListTemp;
    }

    public static String addAnnounce(String title, String content, int classId, int level) {
        while (announceAction1OngoingTraffic) Thread.onSpinWait();
        announceAction1OngoingTraffic = true;
        Global.guiAdapter.executeCmd("addAnnounce", title, content, "2000-01-01 00:00:00", classId + "", level + "", "0");
        while (announceAction1OngoingTraffic) Thread.onSpinWait();
        return announceAction1Temp;
    }
    public static String addTemplate(String name, String title, String content) {
        while (announceAction2OngoingTraffic) Thread.onSpinWait();
        announceAction2OngoingTraffic = true;
        Global.guiAdapter.executeCmd("addTemplate", name, title, content);
        while (announceAction2OngoingTraffic) Thread.onSpinWait();
        return announceAction2Temp;
    }
    public static String alterAnnounce(int announceId, String title, String content, int classId, int level) {
        while (announceAction3OngoingTraffic) Thread.onSpinWait();
        announceAction3OngoingTraffic = true;
        Global.guiAdapter.executeCmd("alterAnnounce", announceId + "", title, content, "2000-01-01 00:00:00", classId + "", level + "", "0");
        while (announceAction3OngoingTraffic) Thread.onSpinWait();
        return announceAction3Temp;
    }
    public static String alterTemplate(int templateId, String name, String title, String content) {
        while (announceAction4OngoingTraffic) Thread.onSpinWait();
        announceAction4OngoingTraffic = true;
        Global.guiAdapter.executeCmd("alterTemplate", templateId + "", name, title, content);
        while (announceAction4OngoingTraffic) Thread.onSpinWait();
        return announceAction4Temp;
    }
    public static String delAnnounce(int announceId) {
        while (announceAction5OngoingTraffic) Thread.onSpinWait();
        announceAction5OngoingTraffic = true;
        Global.guiAdapter.executeCmd("delAnnounce", announceId + "");
        while (announceAction5OngoingTraffic) Thread.onSpinWait();
        return announceAction5Temp;
    }
    public static String delTemplate(int templateId) {
        while (announceAction6OngoingTraffic) Thread.onSpinWait();
        announceAction6OngoingTraffic = true;
        Global.guiAdapter.executeCmd("delTemplate", templateId + "");
        while (announceAction6OngoingTraffic) Thread.onSpinWait();
        return announceAction6Temp;
    }

    public static top.chorg.kernel.communication.api.vote.FetchListResult[] getVoteList(boolean self) {
        while (getVoteListOngoingTraffic) Thread.onSpinWait();
        getVoteListOngoingTraffic = true;
        Global.guiAdapter.executeCmd("fetchVoteList", self ? "published" : "all");
        while (getVoteListOngoingTraffic) Thread.onSpinWait();
        return getVoteListTemp;
    }

    public static top.chorg.kernel.communication.api.vote.FetchInfoResult getVoteInfo(int voteId) {
        while (getVoteInfoOngoingTraffic) Thread.onSpinWait();
        getVoteInfoOngoingTraffic = true;
        Global.guiAdapter.executeCmd("fetchVoteInfo", voteId + "");
        while (getVoteInfoOngoingTraffic) Thread.onSpinWait();
        return getVoteInfoTemp;
    }

    public static String addVote(String title, String content, String[] selections, boolean isMultiple, int classId, int level) {
        while (voteAction1OngoingTraffic) Thread.onSpinWait();
        voteAction1OngoingTraffic = true;
        Global.guiAdapter.executeCmd("addVote", title, content, Global.gson.toJson(selections),
                "2000-01-01 00:00:00", isMultiple ? "1" : "0", classId + "", level + "", "0");
        while (voteAction1OngoingTraffic) Thread.onSpinWait();
        return voteAction1Temp;
    }
    public static String alterVote(int voteId, String title, String content, String[] selections, boolean isMultiple, int classId, int level) {
        while (voteAction2OngoingTraffic) Thread.onSpinWait();
        voteAction2OngoingTraffic = true;
        Global.guiAdapter.executeCmd("alterVote", voteId + "", title, content, Global.gson.toJson(selections),
                "2000-01-01 00:00:00", isMultiple ? "1" : "0", classId + "", level + "", "0");
        while (voteAction2OngoingTraffic) Thread.onSpinWait();
        return voteAction2Temp;
    }
    public static String delVote(int voteId) {
        while (voteAction3OngoingTraffic) Thread.onSpinWait();
        voteAction3OngoingTraffic = true;
        Global.guiAdapter.executeCmd("delVote", voteId + "");
        while (voteAction3OngoingTraffic) Thread.onSpinWait();
        return voteAction3Temp;
    }
    public static String makeVote(int voteId, int[] select, String suggestion) {
        while (voteAction4OngoingTraffic) Thread.onSpinWait();
        voteAction4OngoingTraffic = true;
        Global.guiAdapter.executeCmd("makeVote", voteId + "", Global.gson.toJson(select), suggestion);
        while (voteAction4OngoingTraffic) Thread.onSpinWait();
        return voteAction4Temp;
    }

    public static int[][] getVoteResult(int voteId) {
        while (getVoteResultOngoingTraffic) Thread.onSpinWait();
        getVoteResultOngoingTraffic = true;
        Global.guiAdapter.executeCmd("fetchVoteResult", voteId + "");
        while (getVoteResultOngoingTraffic) Thread.onSpinWait();
        return getVoteResultTemp;
    }

    public static String sendChat(int type, int toId, String content) {
        while (sendChatOngoingTraffic) Thread.onSpinWait();
        sendChatOngoingTraffic = true;
        Global.guiAdapter.executeCmd("sendChat", type + "", toId + "", content);
        while (sendChatOngoingTraffic) Thread.onSpinWait();
        return sendChatTemp;
    }

    public static ChatMsg[] getChatHistory(int type, int toId) {
        while (fetchChatHistoryOngoingTraffic) Thread.onSpinWait();
        fetchChatHistoryOngoingTraffic = true;
        Global.guiAdapter.executeCmd("fetchChatHistory", type + "", toId + "");
        while (fetchChatHistoryOngoingTraffic) Thread.onSpinWait();
        return fetchChatHistoryTemp;
    }

    public static int uploadFile(String path, int classId, int level) {
        while (uploadFileOngoingTraffic) Thread.onSpinWait();
        uploadFileOngoingTraffic = true;
        Global.guiAdapter.executeCmd("uploadFile", path, classId + "", level + "");
        while (uploadFileOngoingTraffic) Thread.onSpinWait();
        return uploadFileTemp;
    }

    public static File downloadFile(int fileId) {
        while (downloadFileOngoingTraffic) Thread.onSpinWait();
        downloadFileOngoingTraffic = true;
        FileInfo info = getFileInfo(fileId);
        File temp = new File("downloads" + File.separator + info.name);
        if (temp.exists()) {
            downloadFileOngoingTraffic = false;
            return temp;
        }
        downloadFileIdTemp = fileId;
        Global.guiAdapter.executeCmd("downloadFile", fileId + "");
        while (downloadFileOngoingTraffic) Thread.onSpinWait();
        return downloadFileTemp;
    }

    public static FileInfo[] getFileList(int classId) {
        while (getFileListOngoingTraffic) Thread.onSpinWait();
        getFileListOngoingTraffic = true;
        Global.guiAdapter.executeCmd("fetchFileList", classId + "");
        while (getFileListOngoingTraffic) Thread.onSpinWait();
        return getFileListTemp;
    }

    public static FileInfo getFileInfo(int fileId) {
        while (getFileInfoOngoingTraffic) Thread.onSpinWait();
        getFileInfoOngoingTraffic = true;
        Global.guiAdapter.executeCmd("fetchFileInfo", fileId + "");
        while (getFileInfoOngoingTraffic) Thread.onSpinWait();
        return getFileInfoTemp;
    }

    public static int getLevel(int classId) {
        while (getLevelOngoingTraffic) Thread.onSpinWait();
        getLevelOngoingTraffic = true;
        Global.guiAdapter.executeCmd("getLevel", classId + "");
        while (getLevelOngoingTraffic) Thread.onSpinWait();
        return getLevelTemp;
    }

}
