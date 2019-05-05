package study_swing.Vote;

import study_swing.DataGetters;
import top.chorg.kernel.communication.api.vote.FetchInfoResult;
import top.chorg.system.Global;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class V_Single extends VoteUI{
    private JRadioButton[] jRadioButtons;
    private FetchInfoResult fetchInfoResult;
    private JPanel jPanel =  new JPanel(new FlowLayout(FlowLayout.CENTER));
    //private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
     V_Single(Message titleMessage, Messages messages, ArrayList<String> chooseMessage){
         super(titleMessage, messages, chooseMessage);
         jRadioButtons = new JRadioButton[chooseMessage.size()];
     }
    public V_Single(FetchInfoResult fetchInfoResult) {
        this(new Message(fetchInfoResult.title, VoteMaker.defaultFontForTitle), Messages.getMessagesFromString(fetchInfoResult.content),
                getArray(Global.gson.fromJson(fetchInfoResult.selections, String[].class)));
        this.fetchInfoResult = fetchInfoResult;
        if (fetchInfoResult.isVoted) {
            for (int op : fetchInfoResult.ops) {
                jRadioButtons[op].setSelected(true);
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
                if (jRadioButtons[t].isSelected()) size = t;
            }
            int[] res = {size};
            //TODO upload the result
            String result = DataGetters.makeVote(fetchInfoResult.id, res, suggestion.getText());
            dispose();
        });
    }

    @Override
    void setSelectItems() { ButtonGroup buttonGroup = new ButtonGroup();
         for (int t= 0; t < jRadioButtons.length; t++){
             if (t == 0)             jRadioButtons[t] = new JRadioButton(chooseMessage.get(t), true);
             else jRadioButtons[t] = new JRadioButton(chooseMessage.get(t));
             jRadioButtons[t].setPreferredSize(new Dimension(300, 40));
             buttonGroup.add(jRadioButtons[t]);
             choosePanel.add(jRadioButtons[t]);
         }
    }
    void addItems() {
        super.addItems();
        container.add(choosePanel);
        jPanel.setPreferredSize(new Dimension(600, 40));
        jPanel.add(OK);
        jPanel.add(cancel);
        container.add(suggestion);
        container.add(jPanel);
    }

}