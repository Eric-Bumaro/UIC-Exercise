import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CommonUser {
    JFrame myframe=null;
    JPanel all=new JPanel();
    JPanel middle=new JPanel();
    JPanel bottom=new JPanel();
    JPanel top=new JPanel();
    JLabel loadsucc=new JLabel("Successfully load in!");
    JLabel idlabel=new JLabel();
    JLabel benamelabel=new JLabel("Your name:");
    JLabel namelabel=new JLabel();
    JLabel betimelabel=new JLabel("Your register time:");
    JLabel timelabel=new JLabel();
    JButton modifypassword=new JButton("modify password and name");
    JButton exit=new JButton("Logout");
    ConnectDB theuser=new ConnectDB();
    HashMap perinfor=new HashMap<String,String>();
    CommonUser(ConnectDB theuser,JFrame myframe) throws Exception {
        this.myframe=myframe;
        this.theuser=theuser;
        all.setLayout(new GridLayout(4,1));
        middle.setLayout(new GridLayout(2,2));
        bottom.setLayout(new GridLayout(2,2));
        all.setOpaque(false);
        middle.setOpaque(false);
        bottom.setOpaque(false);
        loadsucc.setOpaque(false);
        loadsucc.setForeground(Color.blue);
        top.setOpaque(false);
        benamelabel.setOpaque(false);
        idlabel.setOpaque(false);
        namelabel.setOpaque(false);
        theuser.getPerinfor();
        perinfor=theuser.perinfor;
        exit.setForeground(new Color(66, 108, 51));
        setButton(exit);
        exit.addActionListener(exitaction);
        idlabel.setText("Your id:"+Long.toString(Long.valueOf((String)perinfor.get((Object)"ID"))));
        namelabel.setText((String)perinfor.get("Name"));
        timelabel.setText((String)perinfor.get("Register_time"));
        top.add(idlabel);
        middle.add(benamelabel);
        middle.add(namelabel);
        middle.add(betimelabel);
        middle.add(timelabel);
        modifypassword.addActionListener(ModifyAction);
        bottom.add(modifypassword);
        bottom.add(exit);
        all.add(loadsucc);
        all.add(top);
        all.add(middle);
        all.add(bottom);
    }
    void setButton(JButton a){
        a.setMargin(new Insets(0,0,0,0));//将边框外的上下左右空间设置为0
        a.setIconTextGap(0);//将标签中显示的文本和图标之间的间隔量设置为0
        a.setBorderPainted(false);//不打印边框
        a.setBorder(null);//除去边框
        a.setContentAreaFilled(false);
    }
    ActionListener exitaction=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                theuser.getout();
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
            all.removeAll();
            all.repaint();
            all.revalidate();
            all.setLayout(new GridLayout(1,1));
            all.add(new CoUsermodify(myframe,theuser).regispanel);
        }
    };
}
