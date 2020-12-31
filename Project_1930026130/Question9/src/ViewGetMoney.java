import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ViewGetMoney extends View<ControllerGetMoney>{
    private JTextField t=new JTextField("Type a customer name here");
    public ViewGetMoney(Bank m, ControllerGetMoney c) {
        super(m, c);
        this.setLayout(new GridLayout(2,1));
        this.add(t);
        JButton tell_me_the_money=new JButton("Tell me the money");
        m.addListener(this);
        this.add(tell_me_the_money);
        tell_me_the_money.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {//the message dialog
                    JOptionPane.showMessageDialog(ViewGetMoney.this,c.getMoney(t.getText()),"Message",JOptionPane.INFORMATION_MESSAGE);
                } catch (UnknownCustomerException e1) {
                    JOptionPane.showMessageDialog(ViewGetMoney.this,e1.getMessage(),"Message",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        this.setTitle("View Money");//the corresponding outlook
        this.setSize(600,400);
        this.setLocation(600,400);

    }
    @Override
    public void update() {
//nothing to say
    }
}
