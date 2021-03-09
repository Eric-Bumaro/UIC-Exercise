import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Controller {
    JFrame myframe=new JFrame();
    ConnectDB nowuser=new ConnectDB();
    Map<String,String> allinfor=new  LinkedHashMap<String,String>();
    JList userlist;
    JComboBox userselector;
    JPanel selectModePanel=new JPanel();
    ButtonGroup selectmodegroup=new ButtonGroup();
    JTextArea chosen=new JTextArea(4,10);
    Box box=new Box(BoxLayout.X_AXIS);
    JPanel chosenpanel=new JPanel();
    JButton delete=new JButton("Delete");
    JButton deleteController=new JButton("Delete Controller");
    JButton addController=new JButton("Add new Croller");
    JButton exit=new JButton("Log out");
    JButton changeowninfor=new JButton("Change my own information");
    JPanel toolbuttonpanel=new JPanel();
    JPanel allinforpanel=new JPanel();
    JTextArea allinfortext=new JTextArea(10,10);
    JLabel judgelabel=new JLabel();
    Controller(ConnectDB nowuser,JFrame myframe) throws Exception {
        this.myframe=myframe;
        this.nowuser=nowuser;
        getallinfor();
        userlist=new JList(allinfor.keySet().toArray());
        userlist.setVisibleRowCount(10);
        userlist.setSelectionInterval(0,0);
        addSelectModelButton("No limitation",ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        addSelectModelButton("Single",ListSelectionModel.SINGLE_SELECTION);
        addSelectModelButton("Single range", ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        Box left=new Box(BoxLayout.Y_AXIS);
        left.add(new JScrollPane(userlist));
        userlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Object[] users= userlist.getSelectedValues();
                chosen.setText("");
                allinfortext.setText("");
                for(Object i:users){
                    chosen.append(i.toString()+"\n");
                    try {
                        HashMap<String,String>one=nowuser.getUserinf(Long.valueOf(i.toString()));
                        allinfortext.append(one.toString()+"\n");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
        Vector<String> usercollection=new Vector<String>();
        for(String i:allinfor.keySet()){
            usercollection.add(i);
        }
        userselector=new JComboBox(usercollection);
        userselector.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                Object i=userselector.getSelectedItem();
                chosen.setText(i.toString()+"\n");
                HashMap<String,String>one= null;
                try {
                    one = nowuser.getUserinf(Long.valueOf(i.toString()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                allinfortext.setText(one.toString());
            }
        });
        userselector.setEditable(true);
        userselector.setMaximumRowCount(6);
        Box right=new Box(BoxLayout.Y_AXIS);
        delete.addActionListener(deleteaction);
        deleteController.addActionListener(delcontroller);
        exit.addActionListener(backListener);
        addController.addActionListener(insertcontrolleraction);
        changeowninfor.addActionListener(ModifyAction);
        JPanel temp=new JPanel();
        temp.setLayout(new GridLayout(1,1));
        temp.add(changeowninfor);
        toolbuttonpanel.setLayout(new GridLayout(2,2));
        toolbuttonpanel.add(delete);
        toolbuttonpanel.add(deleteController);
        toolbuttonpanel.add(exit);
        toolbuttonpanel.add(addController);
        right.add(userselector);
        right.add(toolbuttonpanel);
        judgelabel.setMaximumSize(new Dimension(800,20));
        judgelabel.setOpaque(true);
        judgelabel.setVisible(true);
        JPanel temp1=new JPanel();
        temp1.add(judgelabel);
        right.add(temp1);
//        right.add(judgelabel);
        right.add(temp);
        allinforpanel.setLayout(new BorderLayout());
        allinforpanel.add(new JScrollPane(allinfortext));
        allinforpanel.add(new JLabel("The information of chosen:"),BorderLayout.NORTH);
        right.add(allinforpanel);
        chosenpanel.setLayout(new BorderLayout());
        chosenpanel.add(new JScrollPane(chosen));
        chosenpanel.add(new JLabel("Chosen one:"),BorderLayout.NORTH);
        left.add(chosenpanel);
        left.add(selectModePanel);
        left.setMaximumSize(new Dimension(300,600));
        box.add(left);
        box.add(right);
        box.setVisible(true);
    }
    void getallinfor() throws Exception {
        for(Object i:nowuser.getallinfor()){
            HashMap<String,String> one=(HashMap<String,String>)i;
            allinfor.put(one.get((Object)"ID"),one.get(((Object)"Name")));
        }
    }
    private void addSelectModelButton(String label,int selectMode){
        selectModePanel.setBorder(new TitledBorder(new EtchedBorder(),"Choosing mode"));
        JRadioButton button=new JRadioButton(label);
        selectModePanel.add(button);
        if(selectmodegroup.getButtonCount()==0){
            button.setSelected(true);
        }
        selectmodegroup.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userlist.setSelectionMode(selectMode);
            }
        });
    }
    ActionListener deleteaction=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] users= chosen.getText().split("\n");
            ArrayList<Long> faileone=new ArrayList<Long>();
            for(Object i:users){
                try {
                    if(Long.valueOf(i.toString())==1930026100){
                        continue;
                    }
                    nowuser.delete(Long.valueOf(i.toString()));
                } catch (Exception exception) {
                    faileone.add(Long.valueOf(i.toString()));
                    exception.printStackTrace();
                }
            }
            if(!faileone.isEmpty()){
                judgelabel.setText(faileone.toString()+" Failed");
                judgelabel.setForeground(Color.red);
            }else{
                judgelabel.setText("Succeed");
                judgelabel.setForeground(new Color(66, 142, 58, 226));
            }
            box.removeAll();
            box.repaint();
            box.revalidate();
            Container j=box.getParent();
            j.removeAll();
            j.repaint();
            j.revalidate();
            j.setBounds(myframe.getWidth() * 7/ 21, (int) myframe.getHeight() * 2 / 7, 650, 400);
            j.setLayout(new GridLayout(1,1));
            try {
                j.add(new Controller(nowuser,myframe).box);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    };
    ActionListener delcontroller=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] users= chosen.getText().split("\n");
            ArrayList<Long> faileone=new ArrayList<Long>();
            for(Object i:users){
                try {
                    if(Long.valueOf(i.toString())==1930026100){
                        continue;
                    }
                    nowuser.deletecontroller(Long.valueOf(i.toString()));
                } catch (Exception exception) {
                    faileone.add(Long.valueOf(i.toString()));
                    exception.printStackTrace();
                }
            }
            if(!faileone.isEmpty()){
                judgelabel.setText(faileone.toString()+" Failed");
                judgelabel.setForeground(Color.red);
            }else{
                judgelabel.setText("Succeed");
                judgelabel.setForeground(new Color(66, 142, 58, 226));
            }
        }
    };
    ActionListener insertcontrolleraction=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] users=chosen.getText().split("\n");
            ArrayList<Long> faileone=new ArrayList<Long>();
            for(Object i:users){
                try {
                    nowuser.insertController(Long.valueOf(i.toString()));
                } catch (Exception exception) {
                    faileone.add(Long.valueOf(i.toString()));
                    exception.printStackTrace();
                }
            }
            if(!faileone.isEmpty()){
                judgelabel.setText(faileone.toString()+" Failed");
                judgelabel.setForeground(Color.red);
            }else{
                judgelabel.setText("Succeed");
                judgelabel.setForeground(new Color(66, 142, 58, 226));
            }
        }
    };
    ActionListener backListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                nowuser.getout();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            myframe.dispose();
            new Loadin();
        }
    };
    ActionListener ModifyAction=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            box.removeAll();
            box.repaint();
            box.revalidate();
            Container j=box.getParent();
            j.removeAll();
            j.repaint();
            j.revalidate();
            j.setBounds(myframe.getWidth() * 9 / 21, (int) myframe.getHeight() * 4 / 7, 350, 250);
            j.setLayout(new GridLayout(1,1));
            try {
                j.add(new Controllermodify(myframe,nowuser).regispanel);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    };
}
