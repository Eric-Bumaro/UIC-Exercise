import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Forgetpass {
    JPanel all=new JPanel();
    JPanel a=new JPanel();
    JPanel b=new JPanel();
    JPanel c=new JPanel();
    JPanel idpanel=new JPanel();
    JPanel passpanel=new JPanel();
    JPanel checkpasspanel=new JPanel();
    JPanel modandecitpanel=new JPanel();
    JLabel idlabel=new JLabel("Your id:");
    JLabel passlabel=new JLabel("New password:");
    JLabel checklabel=new JLabel("Check password:");
    JLabel checkresult=new JLabel();
    JTextArea idtext=new JTextArea(10,1);
    JPasswordField passwordfield=new JPasswordField(20);
    JPasswordField checkfield=new JPasswordField(20);
    JButton modify=new JButton("Modify");
    JButton exit=new JButton("Exit to last page");
    JFrame myframe;
    Forgetpass(JFrame myframe){
        this.myframe=myframe;
        a.setLayout(new GridLayout(2,1));
        b.setLayout(new GridLayout(2,1));
        c.setLayout(new GridLayout(1,1));
        idpanel.setLayout(new GridLayout(1,2));
        passpanel.setLayout(new GridLayout(1,2));
        checkpasspanel.setLayout(new GridLayout(1,2));
        modandecitpanel.setLayout(new GridLayout(2,1));
        idlabel.setOpaque(false);
        idlabel.setForeground(Color.BLUE);
        idpanel.add(idlabel);
        idpanel.add(idtext);
        passlabel.setOpaque(false);
        passlabel.setForeground(Color.BLUE);
        passpanel.add(passlabel);
        passwordfield.setEchoChar('*');
        passpanel.add(passwordfield);
        modify.addActionListener(modifyListener);
        exit.addActionListener(backaction);
        modandecitpanel.add(modify);
        modandecitpanel.add(exit);
        all.setLayout(new GridLayout(3,1));
        idpanel.setOpaque(false);
        passpanel.setOpaque(false);
        checkpasspanel.setOpaque(false);
        checkpasspanel.add(checklabel);
        checkpasspanel.add(checkfield);
        modandecitpanel.setOpaque(false);
        a.add(idpanel);
        a.add(passpanel);
        b.add(checkpasspanel);
        b.add(checkresult);
        c.add(modandecitpanel);
        a.setOpaque(false);
        b.setOpaque(false);
        c.setOpaque(false);
        all.setOpaque(false);
        all.add(a);
        all.add(b);
        all.add(c);
    }
    ActionListener modifyListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ConnectDB nowuser=new ConnectDB();
            if(passwordfield.getText().equals(checkfield.getText())){
                checkresult.setOpaque(false);
                try {
                    nowuser.changepass(passwordfield.getText(),Long.valueOf(idtext.getText()));
                    nowuser.getout();
                } catch (Exception exception) {
                    checkresult.setOpaque(false);
                    checkresult.setText("Input again");
                    checkresult.setForeground(Color.red);
                    passwordfield.setText("");
                    checkfield.setText("");
                    idtext.setText("");
                    exception.printStackTrace();
                }
                all.removeAll();
                all.repaint();
                all.revalidate();
                try {
                    all.setLayout(new GridLayout(1,1));
                    all.add(new Successfulchange(myframe).successfulpan,new Integer(150));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }else{
                try {
                    nowuser.getout();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                checkresult.setOpaque(false);
                checkresult.setText("Input again");
                checkresult.setForeground(Color.red);
                passwordfield.setText("");
                checkfield.setText("");
            }
        }
    };
    ActionListener backaction=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame b=(JFrame)all.getParent().getParent().getParent().getParent().getParent().getParent();
            b.dispose();
            new Loadin();
        }
    };
}
