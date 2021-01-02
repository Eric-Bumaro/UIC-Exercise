import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class ViewSimple extends JFrame implements ModelListener{
    private Bank m;
    private ControllerSimple c;
    private JLabel label;
    public ViewSimple(Bank m,ControllerSimple c){
        this.m=m;//the corresponding filed
        this.c=c;
        m.addListener(this);
        label=new JLabel("total money: "+m.totalMoney(), label.CENTER);
        this.add(label);
        this.setTitle("View imple");//the corresponding outlook
        this.setPreferredSize(new Dimension(800,100));//set the location
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        double width = dimension.getWidth() / 2;
        double height = dimension.getHeight() / 2;
        this.setLocation((int)width-400,(int)height-300);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
    }
    @Override
    public void update() {
        label.setText("total money: "+m.totalMoney());//update the text
    }
}