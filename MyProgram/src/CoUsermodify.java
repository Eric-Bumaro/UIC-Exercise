import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoUsermodify {
    JFrame myframe;
    JPanel regispanel=new JPanel();
    private JPanel newIDnamepanel=new JPanel();
    private JPanel passandcheckpanel=new JPanel();
    private JPanel buttongroup=new JPanel();
    private JPanel namepanel=new JPanel();
    JLabel yourname=new JLabel("Your name:");
    JLabel yourpassword=new JLabel("Your password:");
    JLabel checkpassword=new JLabel("Check password:");
    JButton registerin=new JButton("Register in");
    JButton exitout=new JButton("Exit to the last page");
    JLabel checkresult=new JLabel();
    JPasswordField inputpassword=new JPasswordField(20);
    JPasswordField checkpasswordarea=new JPasswordField(20);
    JTextArea namearea=new JTextArea(10,1);
    ConnectDB nowuser=new ConnectDB();
    CoUsermodify(JFrame myframe,ConnectDB nowuser){
        this.myframe=myframe;
        this.nowuser=nowuser;
        regispanel.setLayout(new GridLayout(6,1));
        checkresult.setSize(20,7);
        buttongroup.setLayout(new GridLayout(1,1));
        newIDnamepanel.setLayout(new GridLayout(2,1));
        passandcheckpanel.setLayout(new GridLayout(2,1));
        namepanel.setLayout(new GridLayout(1,2));
        namepanel.add(yourname);
        namepanel.add(namearea);
        namepanel.setOpaque(false);
        newIDnamepanel.add(yourpassword);
        newIDnamepanel.add(inputpassword);
        inputpassword.setEchoChar('*');
        newIDnamepanel.setOpaque(false);
//        passandcheckpanel.add(checkresult);
        passandcheckpanel.add(checkpassword);
        passandcheckpanel.add(checkpasswordarea);
        checkpasswordarea.setEchoChar('*');
//        passandcheckpanel.add(registerin);
        buttongroup.add(checkresult);
        buttongroup.add(registerin);
        registerin.addActionListener(checkandregister);
        buttongroup.add(exitout);
        passandcheckpanel.setOpaque(false);
        buttongroup.setOpaque(false);
//        newIDnamepanel.setPreferredSize(new Dimension(30,6));
//        passandcheckpanel.setPreferredSize(new Dimension(30,6));
        passandcheckpanel.setVisible(true);
        newIDnamepanel.setVisible(true);
        exitout.addActionListener(backaction);
        regispanel.add(namepanel);
        regispanel.add(newIDnamepanel);
        regispanel.add(passandcheckpanel);
        regispanel.add(buttongroup);
        regispanel.add(registerin);
        regispanel.add(exitout);
        //regispanel.setBounds(myframe.getX()+myframe.getWidth()*11/21,myframe.getY()+myframe.getHeight()*3/7,200,450);
        regispanel.setOpaque(false);
        regispanel.setVisible(true);
    }

    void setUsernamepanel() {

    }
    ActionListener checkandregister=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(inputpassword.getText().equals(checkpasswordarea.getText())){
                checkresult.setOpaque(false);
                try {
                    nowuser.changeinfor(namearea.getText(),inputpassword.getText());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                regispanel.removeAll();
                regispanel.repaint();
                regispanel.revalidate();
                try {
                    regispanel.setLayout(new GridLayout(1,1));
                    regispanel.add(new Successfulchange(myframe).successfulpan,new Integer(150));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }else{
                checkresult.setOpaque(false);
                checkresult.setText("Input again");
                checkresult.setForeground(Color.red);
                inputpassword.setText("");
                checkpasswordarea.setText("");
            }
        }
    };
    ActionListener backaction=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            regispanel.removeAll();
            regispanel.repaint();
            regispanel.revalidate();
            try {
                regispanel.setLayout(new GridLayout(1,1));
                regispanel.add(new CommonUser(nowuser,myframe).all);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    };
}
