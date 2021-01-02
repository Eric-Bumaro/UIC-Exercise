import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ViewCreate extends View<ControllerCreate>{
    private JTextField t1=new JTextField();
    private JTextField t2=new JTextField();
    private JComboBox<String> cb=new JComboBox<String>();

    public ViewCreate(Bank m, ControllerCreate c) {
        super(m, c);
        m.addListener(this);
        this.setLayout(new GridLayout(4,1));//set the layout
        cb.addItem("credit index");
        cb.addItem("student account");
        t1.setForeground(new Color(183, 186, 191));//set the color
        t1.setText("Type a customer name here");//origin text
        t2.setForeground(new Color(183, 186, 191));
        t2.setText("Type an amount of money here");
        this.add(t1);
        this.add(t2);
        this.add(cb);
        JButton create=new JButton("Create");
        this.add(create);
        this.setTitle("View Create");//the corresponding outlook
        this.setSize(new Dimension(400,200));//set the location
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        double width = dimension.getWidth() / 2;
        double height = dimension.getHeight() / 2;
        this.setLocation((int)width-400,(int)height);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str=c.create(t1.getText(),t2.getText(),cb.getSelectedIndex());
                if(!str.equals("")){//judge if it is empty
                    JOptionPane.showMessageDialog(ViewCreate.this,str,"Message",JOptionPane.INFORMATION_MESSAGE);
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
        //nothing to do
    }
}
