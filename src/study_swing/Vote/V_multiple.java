package study_swing.Vote;

import study_swing.DataGetters;
import top.chorg.kernel.communication.api.vote.FetchInfoResult;
import top.chorg.system.Global;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * use for multiple vote
 */
public class V_multiple extends VoteUI {
    private JCheckBox[] jCheckBoxes = new JCheckBox[n];
    private ArrayList<Integer> SelectedItems;
    //private JPanel choosePanel = new JPanel();
    private JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private int type = 1;
    private FetchInfoResult fetchInfoResult;

    V_multiple(Message titleMessage, Messages messages, ArrayList<String> chooseMessage) {
        super(titleMessage, messages, chooseMessage);
        setSelectItems();
        addItems();
        pack();
        this.setVisible(true);
    }

    public V_multiple(FetchInfoResult fetchInfoResult) {
        this(new Message(fetchInfoResult.title, VoteMaker.defaultFontForTitle), Messages.getMessagesFromString(fetchInfoResult.content),
                getArray(Global.gson.fromJson(fetchInfoResult.selections, String[].class)));
        this.fetchInfoResult = fetchInfoResult;
        for (int t = 0; t < n; t++) choosePanel.add(jCheckBoxes[t]);
        choosePanel.setBorder(BorderFactory.createTitledBorder("多选: "));
        if (fetchInfoResult.isVoted) {
            for (int op : fetchInfoResult.ops) {
                jCheckBoxes[op].setSelected(true);
            }
            if (fetchInfoResult.addition != null) suggestion.setText(fetchInfoResult.addition);
        }
        this.repaint();
//
    }
    @Override

    void setOK() {
        OK.addActionListener(Event -> {
            int size = 0;
            for (int t = 0; t < n; t++) {
                if (jCheckBoxes[t].isSelected()) size++;
            }
            int[] res = new int[size];
            size = 0;
            for (int t = 0; t < n; t++) {
                if (jCheckBoxes[t].isSelected()) {
                    res[size] = t;
                    size++;
                }
            }
            //TODO upload the result
            String result = DataGetters.makeVote(fetchInfoResult.id, res, suggestion.getText());
            dispose();
        });
    }

    @Override
    void setSelectItems() {
        for (int t = 0; t < n; t++) {
            try {
                jCheckBoxes[t] = new JCheckBox(super.chooseMessage.get(t));
                jCheckBoxes[t].setPreferredSize(new Dimension(300, 40));
            } catch (NullPointerException e) {
                System.out.println(chooseMessage.get(t) + "is error");
            }
        }
        choosePanel.setLayout(new GridLayout(n, 1));
        choosePanel.setPreferredSize(new Dimension(300, 45 * n + 35));
    }

    void addItems() {
        super.addItems();
        container.add(choosePanel);
        jPanel.setPreferredSize(new Dimension(600, 45));
        jPanel.add(OK);
        jPanel.add(cancel);
        container.add(suggestion);
        container.add(jPanel);
    }

    public ArrayList<Integer> getSelectedItems() {
        return SelectedItems;
    }
}
