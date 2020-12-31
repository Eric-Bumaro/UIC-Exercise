import javax.swing.*;

public class ViewSimple extends JFrame implements ModelListener{
    private Bank m;
    private ControllerSimple c;
    private JLabel label;
    public ViewSimple(Bank m,ControllerSimple c){
        this.m=m;//the corresponding filed
        this.c=c;
        m.addListener(this);
        label=new JLabel("total money: "+m.totalMoney());
        this.add(label);
        this.setTitle("View imple");//the corresponding outlook
        this.setSize(600,400);
        this.setLocation(600,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
    }
    @Override
    public void update() {
        label.setText("total money: "+m.totalMoney());//update the text
    }
}