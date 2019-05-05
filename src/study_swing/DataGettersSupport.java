package study_swing;

import top.chorg.gui.WindowEventsAdapter;
import top.chorg.kernel.communication.api.auth.ClassInfo;
import top.chorg.kernel.communication.api.auth.User;
import top.chorg.kernel.communication.api.chat.ChatMsg;
import top.chorg.kernel.communication.api.chat.FetchHistoryRequest;
import top.chorg.kernel.communication.api.file.FileInfo;
import top.chorg.system.Global;

import java.io.File;
import java.util.Objects;

import static study_swing.DataGetters.getFileInfo;

public class DataGettersSupport extends WindowEventsAdapter {

    public DataGettersSupport(String... args){
        super(args);
    }
    public static void registerAdapters() {
        Global.guiAdapter.register(
                "getLog",
                study_swing.DataGettersSupport.class, "getLogRespond"
        );
        Global.guiAdapter.register(
                "getUserName",
                study_swing.DataGettersSupport.class, "getUserNameRespond"
        );
        Global.guiAdapter.register(
                "getNickName",
                study_swing.DataGettersSupport.class, "getNickNameRespond"
        );
        Global.guiAdapter.register(
                "getRealName",
                study_swing.DataGettersSupport.class, "getRealNameRespond"
        );
        Global.guiAdapter.register(
                "fetchFileInfo",
                study_swing.DataGettersSupport.class, "getFileInfoRespond"
        );
        Global.guiAdapter.register(
                "downloadFile",
                study_swing.DataGettersSupport.class, "downloadFileRespond"
        );
        Global.guiAdapter.register(
                "uploadFile",
                study_swing.DataGettersSupport.class, "uploadFileRespond"
        );
        Global.guiAdapter.register(
                "fetchFileList",
                study_swing.DataGettersSupport.class, "getFileListRespond"
        );
        Global.guiAdapter.register(
                "fetchUserInfo",
                study_swing.DataGettersSupport.class, "getUserInfoRespond"
        );
        Global.guiAdapter.register(
                "fetchClassInfo",
                study_swing.DataGettersSupport.class, "getClassInfoRespond"
        );
        Global.guiAdapter.register(
                "fetchOnline",
                study_swing.DataGettersSupport.class, "getOnlineRespond"
        );
        Global.guiAdapter.register(
                "fetchAnnounceList",
                study_swing.DataGettersSupport.class, "getAnnounceListRespond"
        );
        Global.guiAdapter.register(
                "fetchTemplateList",
                study_swing.DataGettersSupport.class, "getTemplateListRespond"
        );

        Global.guiAdapter.register(
                "fetchVoteList",
                study_swing.DataGettersSupport.class, "getVoteListRespond"
        );
        Global.guiAdapter.register(
                "fetchVoteInfo",
                study_swing.DataGettersSupport.class, "getVoteInfoRespond"
        );
        Global.guiAdapter.register(
                "fetchVoteResult",
                study_swing.DataGettersSupport.class, "getVoteResultRespond"
        );
        Global.guiAdapter.register(
                "alterUser",
                study_swing.DataGettersSupport.class, "alterUserRespond"
        );
        Global.guiAdapter.register(
                "changePassword",
                study_swing.DataGettersSupport.class, "changePasswordRespond"
        );
        Global.guiAdapter.register(
                "judgeOnline",
                study_swing.DataGettersSupport.class, "judgeOnlineRespond"
        );

        Global.guiAdapter.register(
                "joinClass",
                study_swing.DataGettersSupport.class, "joinClassRespond"
        );
        Global.guiAdapter.register(
                "exitClass",
                study_swing.DataGettersSupport.class, "exitClassRespond"
        );
        Global.guiAdapter.register(
                "kickMember",
                study_swing.DataGettersSupport.class, "kickMemberRespond"
        );

        Global.guiAdapter.register(
                "addAnnounce",
                study_swing.DataGettersSupport.class, "addAnnounceRespond"
        );
        Global.guiAdapter.register(
                "addTemplate",
                study_swing.DataGettersSupport.class, "addTemplateRespond"
        );
        Global.guiAdapter.register(
                "alterAnnounce",
                study_swing.DataGettersSupport.class, "alterAnnounceRespond"
        );
        Global.guiAdapter.register(
                "alterTemplate",
                study_swing.DataGettersSupport.class, "alterTemplateRespond"
        );
        Global.guiAdapter.register(
                "delAnnounce",
                study_swing.DataGettersSupport.class, "delAnnounceRespond"
        );
        Global.guiAdapter.register(
                "delTemplate",
                study_swing.DataGettersSupport.class, "delTemplateRespond"
        );

        Global.guiAdapter.register(
                "addVote",
                study_swing.DataGettersSupport.class, "addVoteRespond"
        );
        Global.guiAdapter.register(
                "alterVote",
                study_swing.DataGettersSupport.class, "alterVoteRespond"
        );
        Global.guiAdapter.register(
                "delVote",
                study_swing.DataGettersSupport.class, "delVoteRespond"
        );
        Global.guiAdapter.register(
                "makeVote",
                study_swing.DataGettersSupport.class, "makeVoteRespond"
        );

        Global.guiAdapter.register(
                "sendChat",
                study_swing.DataGettersSupport.class, "sendChatRespond"
        );
        Global.guiAdapter.register(
                "fetchChatHistory",
                study_swing.DataGettersSupport.class, "fetchChatHistoryRespond"
        );
        Global.guiAdapter.register(
                "getLevel",
                study_swing.DataGettersSupport.class, "getLevelRespond"
        );
    }
    public static volatile boolean getLogOngoingTraffic = false;
    public static String getLogTemp;
    public int getLogRespond() {
        if (!getLogOngoingTraffic) return -1;
        getLogTemp = nextArg();
        getLogOngoingTraffic = false;
        return 0;
    }
    public static volatile boolean getUserNameOngoingTraffic = false;
    public static String[] getUserNameTemp;
    public int getUserNameRespond() {
        if (!getUserNameOngoingTraffic) return -1;
        getUserNameTemp = Objects.requireNonNull(nextArg(String[].class));
        getUserNameOngoingTraffic = false;
        return 0;
    }
    public static volatile boolean getUserNameOngoingTraffic1 = false;
    public static String[] getUserNameTemp1;
    public int getNickNameRespond() {
        if (!getUserNameOngoingTraffic1) return -1;
        getUserNameTemp1 = Objects.requireNonNull(nextArg(String[].class));
        getUserNameOngoingTraffic1 = false;
        return 0;
    }
    public static volatile boolean getUserNameOngoingTraffic2 = false;
    public static String[] getUserNameTemp2;
    public int getRealNameRespond() {
        if (!getUserNameOngoingTraffic2) return -1;
        getUserNameTemp2 = Objects.requireNonNull(nextArg(String[].class));
        getUserNameOngoingTraffic2 = false;
        return 0;
    }

    public static volatile boolean downloadFileOngoingTraffic = false;
    public static File downloadFileTemp;
    public static int downloadFileIdTemp = -1;
    public int downloadFileRespond() {
        if (!downloadFileOngoingTraffic) return -1;
        if (nextArg().equals("OK")) {
            FileInfo info = getFileInfo(downloadFileIdTemp);
            downloadFileTemp = new File("downloads" + File.separator + info.name);
        } else downloadFileTemp = null;
        downloadFileOngoingTraffic = false;
        downloadFileIdTemp = -1;
        return 0;
    }

    public static volatile boolean getFileInfoOngoingTraffic = false;
    public static FileInfo getFileInfoTemp;
    public int getFileInfoRespond() {
        if (!getFileInfoOngoingTraffic) return -1;
        getFileInfoTemp = Objects.requireNonNull(nextArg(FileInfo.class));
        getFileInfoOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean uploadFileOngoingTraffic = false;
    public static int uploadFileTemp;
    public int uploadFileRespond() {
        if (!uploadFileOngoingTraffic) return -1;
        String temp = nextArg();
        try {
            uploadFileTemp = Integer.parseInt(temp);
        } catch (NumberFormatException e) {
            uploadFileTemp = -1;
        }
        uploadFileOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean getFileListOngoingTraffic = false;
    public static FileInfo[] getFileListTemp;
    public int getFileListRespond() {
        if (!getFileListOngoingTraffic) return -1;
        getFileListTemp = Objects.requireNonNull(nextArg(FileInfo[].class));
        getFileListOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean getUserInfoOngoingTraffic = false;
    public static User getUserInfoTemp;
    public int getUserInfoRespond() {
        if (!getUserInfoOngoingTraffic) return -1;
        getUserInfoTemp = Objects.requireNonNull(nextArg(User.class));
        getUserInfoOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean getClassInfoOngoingTraffic = false;
    public static ClassInfo getClassInfoTemp;
    public int getClassInfoRespond() {
        if (!getClassInfoOngoingTraffic) return -1;
        getClassInfoTemp = Objects.requireNonNull(nextArg(ClassInfo.class));
        getClassInfoOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean getOnlineOngoingTraffic = false;
    public static int[] getOnlineTemp;
    public int getOnlineRespond() {
        if (!getOnlineOngoingTraffic) return -1;
        getOnlineTemp = Objects.requireNonNull(nextArg(int[].class));
        getOnlineOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean getAnnounceListOngoingTraffic = false;
    public static top.chorg.kernel.communication.api.announcements.FetchListResult[] getAnnounceListTemp;
    public int getAnnounceListRespond() {
        if (!getAnnounceListOngoingTraffic) return -1;
        getAnnounceListTemp =
                Objects.requireNonNull(nextArg(top.chorg.kernel.communication.api.announcements.FetchListResult[].class));
        getAnnounceListOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean getTemplateListOngoingTraffic = false;
    public static top.chorg.kernel.communication.api.announcements.FetchTemplateResult[] getTemplateListTemp;
    public int getTemplateListRespond() {
        if (!getTemplateListOngoingTraffic) return -1;
        getTemplateListTemp =
                Objects.requireNonNull(nextArg(top.chorg.kernel.communication.api.announcements.FetchTemplateResult[].class));
        getTemplateListOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean getVoteListOngoingTraffic = false;
    public static top.chorg.kernel.communication.api.vote.FetchListResult[] getVoteListTemp;
    public int getVoteListRespond() {
        if (!getVoteListOngoingTraffic) return -1;
        getVoteListTemp =
                Objects.requireNonNull(nextArg(top.chorg.kernel.communication.api.vote.FetchListResult[].class));
        getVoteListOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean getVoteInfoOngoingTraffic = false;
    public static top.chorg.kernel.communication.api.vote.FetchInfoResult getVoteInfoTemp;
    public int getVoteInfoRespond() {
        if (!getVoteInfoOngoingTraffic) return -1;
        getVoteInfoTemp =
                Objects.requireNonNull(nextArg(top.chorg.kernel.communication.api.vote.FetchInfoResult.class));
        getVoteInfoOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean getVoteResultOngoingTraffic = false;
    public static int[][] getVoteResultTemp;
    public int getVoteResultRespond() {
        if (!getVoteResultOngoingTraffic) return -1;
        getVoteResultTemp = Objects.requireNonNull(nextArg(int[][].class));
        getVoteResultOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean alterUserOngoingTraffic = false;
    public static String alterUserTemp;
    public int alterUserRespond() {
        if (!alterUserOngoingTraffic) return -1;
        alterUserTemp = nextArg();
        alterUserOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean changePasswordOngoingTraffic = false;
    public static String changePasswordTemp;
    public int changePasswordRespond() {
        if (!changePasswordOngoingTraffic) return -1;
        changePasswordTemp = nextArg();
        changePasswordOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean judgeOnlineOngoingTraffic = false;
    public static int[] judgeOnlineTemp;
    public int judgeOnlineRespond() {
        if (!judgeOnlineOngoingTraffic) return -1;
        judgeOnlineTemp = Objects.requireNonNull(nextArg(int[].class));
        judgeOnlineOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean classAction1OngoingTraffic = false;
    public static String classAction1Temp;
    public int joinClassRespond() {
        if (!classAction1OngoingTraffic) return -1;
        classAction1Temp = nextArg();
        classAction1OngoingTraffic = false;
        return 0;
    }

    public static volatile boolean classAction2OngoingTraffic = false;
    public static String classAction2Temp;
    public int exitClassRespond() {
        if (!classAction2OngoingTraffic) return -1;
        classAction2Temp = nextArg();
        classAction2OngoingTraffic = false;
        return 0;
    }

    public static volatile boolean classAction3OngoingTraffic = false;
    public static String classAction3Temp;
    public int kickMemberRespond() {
        if (!classAction3OngoingTraffic) return -1;
        classAction3Temp = nextArg();
        classAction3OngoingTraffic = false;
        return 0;
    }

    public static volatile boolean announceAction1OngoingTraffic = false;
    public static String announceAction1Temp;
    public int addAnnounceRespond() {
        if (!announceAction1OngoingTraffic) return -1;
        announceAction1Temp = nextArg();
        announceAction1OngoingTraffic = false;
        return 0;
    }
    public static volatile boolean announceAction2OngoingTraffic = false;
    public static String announceAction2Temp;
    public int addTemplateRespond() {
        if (!announceAction2OngoingTraffic) return -1;
        announceAction2Temp = nextArg();
        announceAction2OngoingTraffic = false;
        return 0;
    }
    public static volatile boolean announceAction3OngoingTraffic = false;
    public static String announceAction3Temp;
    public int alterAnnounceRespond() {
        if (!announceAction3OngoingTraffic) return -1;
        announceAction3Temp = nextArg();
        announceAction3OngoingTraffic = false;
        return 0;
    }
    public static volatile boolean announceAction4OngoingTraffic = false;
    public static String announceAction4Temp;
    public int alterTemplateRespond() {
        if (!announceAction4OngoingTraffic) return -1;
        announceAction4Temp = nextArg();
        announceAction4OngoingTraffic = false;
        return 0;
    }
    public static volatile boolean announceAction5OngoingTraffic = false;
    public static String announceAction5Temp;
    public int delAnnounceRespond() {
        if (!announceAction5OngoingTraffic) return -1;
        announceAction5Temp = nextArg();
        announceAction5OngoingTraffic = false;
        return 0;
    }
    public static volatile boolean announceAction6OngoingTraffic = false;
    public static String announceAction6Temp;
    public int delTemplateRespond() {
        if (!announceAction6OngoingTraffic) return -1;
        announceAction6Temp = nextArg();
        announceAction6OngoingTraffic = false;
        return 0;
    }

    public static volatile boolean voteAction1OngoingTraffic = false;
    public static String voteAction1Temp;
    public int addVoteRespond() {
        if (!voteAction1OngoingTraffic) return -1;
        voteAction1Temp = nextArg();
        voteAction1OngoingTraffic = false;
        return 0;
    }
    public static volatile boolean voteAction2OngoingTraffic = false;
    public static String voteAction2Temp;
    public int alterVoteRespond() {
        if (!voteAction2OngoingTraffic) return -1;
        voteAction2Temp = nextArg();
        voteAction2OngoingTraffic = false;
        return 0;
    }
    public static volatile boolean voteAction3OngoingTraffic = false;
    public static String voteAction3Temp;
    public int delVoteRespond() {
        if (!voteAction3OngoingTraffic) return -1;
        voteAction3Temp = nextArg();
        voteAction3OngoingTraffic = false;
        return 0;
    }
    public static volatile boolean voteAction4OngoingTraffic = false;
    public static String voteAction4Temp;
    public int makeVoteRespond() {
        if (!voteAction4OngoingTraffic) return -1;
        voteAction4Temp = nextArg();
        voteAction4OngoingTraffic = false;
        return 0;
    }

    public static volatile boolean sendChatOngoingTraffic = false;
    public static String sendChatTemp;
    public int sendChatRespond() {
        if (!sendChatOngoingTraffic) return -1;
        sendChatTemp = nextArg();
        sendChatOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean fetchChatHistoryOngoingTraffic = false;
    public static ChatMsg[] fetchChatHistoryTemp;
    public int fetchChatHistoryRespond() {
        if (!fetchChatHistoryOngoingTraffic) return -1;
        fetchChatHistoryTemp = Objects.requireNonNull(nextArg(ChatMsg[].class));
        fetchChatHistoryOngoingTraffic = false;
        return 0;
    }

    public static volatile boolean getLevelOngoingTraffic = false;
    public static int getLevelTemp;
    public int getLevelRespond() {
        if (!getLevelOngoingTraffic) return -1;
        getLevelTemp = Objects.requireNonNull(nextArg(int.class));
        getLevelOngoingTraffic = false;
        return 0;
    }

}
