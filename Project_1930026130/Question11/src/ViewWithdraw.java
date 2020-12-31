import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

public class ViewWithdraw extends View<ControllerWithdraw>{
    private JTextField t1=new JTextField("Type a customer name here");
    private JTextField t2=new JTextField("Type an amount of money here");;

    public ViewWithdraw(Bank m, ControllerWithdraw c) {
        super(m, c);
        this.setLayout(new GridLayout(3,1));//set the layout
        this.add(t1);
        m.addListener(this);
        this.add(t2);
        this.setSize(600,400);
        this.setLocation(600,400);
        JButton withdraw_money=new JButton("Withdraw");
        this.add(withdraw_money);
        withdraw_money.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str=c.withdraw(t1.getText(),t2.getText());
                if(!str.equals("")){//judge if it is empty
                    JOptionPane.showMessageDialog(ViewWithdraw.this,str,"Message",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    @Override
    public void update() {
//do nothing
    }
}
