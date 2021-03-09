import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Successfulchange {
    JPanel successfulpan=new JPanel();
    JPanel b=new JPanel();
    JPanel c=new JPanel();
    JLabel successful=new JLabel("Successfully change password!",SwingConstants.CENTER);
    JLabel remember=new JLabel();
    JButton back=new JButton("Back to load");
    JFrame myframe=new JFrame();
    Successfulchange(JFrame myframe) throws Exception{
        this.myframe=myframe;
        remember.setForeground(new Color(234, 27, 27));
        remember.setText("!!!Remember Your password");
        b.setLayout(new GridLayout(2,1));
        back.addActionListener(backaction);
        b.setOpaque(false);
        c.setOpaque(false);
        successfulpan.setLayout(new GridLayout(2,1));
        successful.setForeground(new Color(13, 50, 123));
        b.add(successful);
        b.add(remember);
        c.add(back);
        successfulpan.setOpaque(false);
        successfulpan.add(b);
        successfulpan.add(c);
    }
    ActionListener backaction=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            myframe.dispose();
            new Loadin();
        }
    };
}
