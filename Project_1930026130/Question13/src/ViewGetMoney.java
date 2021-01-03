
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class ViewGetMoney extends View<ControllerGetMoney>{
    private JTextField t=new JTextField();
    public ViewGetMoney(Bank m, ControllerGetMoney c) {
        super(m, c);
        this.setLayout(new GridLayout(2,1));
        t.setForeground(new Color(183, 186, 191));//change the color
        t.setText("Type a customer name here");//the origin text
        this.add(t);
        JButton tell_me_the_money=new JButton("Tell me the money");
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
        t.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent e) {//mouse click client
			    if (e.getButton() == MouseEvent.BUTTON1)// left button of mouse
			    {
			      if(t.getText().equals("Type a customer name here")) {
			    	  t.setText("");//clear
			    	  t.setForeground(Color.BLACK);
			      }
			    } 
        	}
        });
        this.setTitle("View Money");//the corresponding outlook
        this.setSize(new Dimension(400,200));//set the location
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        double width = dimension.getWidth() / 2;
        double height = dimension.getHeight() / 2;
        this.setLocation((int)width-400,(int)height-200);

    }
    @Override
    public void update() {
//nothing to say
    }
}
