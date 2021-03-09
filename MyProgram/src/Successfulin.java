import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class Successfulin {
    JPanel successfulpan=new JPanel();
    JPanel b=new JPanel();
    JPanel c=new JPanel();
    JLabel successful=new JLabel("Successfully register in!",SwingConstants.CENTER);
    JLabel yourid=new JLabel();
    JButton back=new JButton("Back to load");
    JLabel register_time=new JLabel();
    Successfulin() throws Exception{
        yourid.setForeground(new Color(234, 27, 27));
        ConnectDB a=new ConnectDB();
        yourid.setText("!!!Remember Your id:"+Long.toString(a.getnewoneid()));
        register_time.setText("Register time:"+a.getnewonetime());
        a.getout();
        b.setLayout(new GridLayout(3,1));
        back.addActionListener(backaction);
        b.setOpaque(false);
        c.setOpaque(false);
        successfulpan.setLayout(new GridLayout(2,1));
        successful.setForeground(new Color(13, 50, 123));
        register_time.setForeground(new Color(25, 76, 153));
        b.add(successful);
        b.add(yourid);
        b.add(register_time);
        c.add(back);
        successfulpan.setOpaque(false);
        successfulpan.add(b);
        successfulpan.add(c);
    }
    ActionListener backaction=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame b=(JFrame)successfulpan.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
            b.dispose();
            new Loadin();
        }
    };
}
