import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

public class ViewWithdraw extends View<ControllerWithdraw>{
    private JTextField t1=new JTextField();
    private JTextField t2=new JTextField();

    public ViewWithdraw(Bank m, ControllerWithdraw c) {
        super(m, c);
        this.setLayout(new GridLayout(3,1));//set the layout
        t1.setForeground(new Color(183, 186, 191));//set the color
        t1.setText("Type a customer name here");//origin text
        t2.setForeground(new Color(183, 186, 191));
        t2.setText("Type an amount of money here");
        this.add(t1);
        this.add(t2);
        this.setTitle("View Withdraw");
        this.setSize(new Dimension(400,200));//set the location
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        double width = dimension.getWidth() / 2;
        double height = dimension.getHeight() / 2;
        this.setLocation((int)width,(int)height-200);
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
        t1.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent e) {
			    if (e.getButton() == MouseEvent.BUTTON1)//mouse click client
			    {// left button of mouse
			      if(t1.getText().equals("Type a customer name here")) {
			    	  t1.setText("");
			    	  t1.setForeground(Color.BLACK);
			      }
			    } 
        	}
        });
        t2.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent e) {
			    if (e.getButton() == MouseEvent.BUTTON1)//mouse click client
			    {// left button of mouse
			      if(t2.getText().equals("Type an amount of money here")) {
			    	  t2.setText("");
			    	  t2.setForeground(Color.BLACK);
			      }
			    } 
        	}
        });
    }

    @Override
    public void update() {
//do nothing
    }
}
