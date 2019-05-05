package study_swing.announecment;

import study_swing.Vote.V_Single;
import study_swing.Vote.V_multiple;
import study_swing.Vote.voteList;
import top.chorg.gui.WindowEventsAdapter;
import top.chorg.gui.WindowManager;
import top.chorg.kernel.communication.api.announcements.FetchListResult;
import top.chorg.kernel.communication.api.vote.FetchInfoResult;

public class AnnounceAdapter extends WindowEventsAdapter {

    public AnnounceAdapter(String... args) {
        super(args);
    }

    public int onNewAnnounce() {
        FetchListResult infoResult = nextArg(FetchListResult.class);
        if (WindowManager.containsKey(5000 + infoResult.classId)) {
            WindowManager.get(5000 + infoResult.classId, listOfAnnouncement.class).onNewAnnounce(infoResult);
        }
        new Announcement(infoResult).setVisible(true);
        return 0;
    }
    public int onNewVote() {
        FetchInfoResult infoResult = nextArg(FetchInfoResult.class);
        if (WindowManager.containsKey(7000 + infoResult.classId)) {
            WindowManager.get(7000 + infoResult.classId, voteList.class).onNewVote(infoResult);
        }
        if (infoResult.method == 0) {
            new V_Single(infoResult);
        } else {
            new V_multiple(infoResult);
        }
        return 0;
    }
}
