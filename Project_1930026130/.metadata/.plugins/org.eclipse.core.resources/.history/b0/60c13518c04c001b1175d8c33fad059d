import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ViewCreate extends View<ControllerCreate>{
    private JTextField t1=new JTextField("Type a new Customer name here");
    private JTextField t2=new JTextField("Type an amount of money here");
    private JComboBox<String> cb=new JComboBox<String>();

    public ViewCreate(Bank m, ControllerCreate c) {
        super(m, c);
        this.setLayout(new GridLayout(4,1));//set the layout
        cb.addItem("credit index");
        cb.addItem("student account");
        this.add(t1);
        this.add(t2);
        this.add(cb);
        JButton create=new JButton("Create");
        this.add(create);
        this.setSize(600,400);
        this.setLocation(600,400);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str=c.create(t1.getText(),t2.getText(),cb.getSelectedIndex());
                if(!str.equals("")){//judge if it is empty
                    JOptionPane.showMessageDialog(ViewCreate.this,str,"Message",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

    }

    @Override
    public void update() {
        //nothing to do
    }
}
