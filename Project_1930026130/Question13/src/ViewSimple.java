import javax.swing.*;

public class ViewSimple extends View implements ModelListener{
    private JLabel label;
    public ViewSimple(Bank m,Controller c){
        super(m,c);
        label=new JLabel("total money: "+m.totalMoney());
        m.addListener(this);
        this.add(label);
        this.setTitle("View imple");//the corresponding outlook
        this.setSize(600,400);
        this.setLocation(600,400);
        this.pack();
    }
    @Override
    public void update() {
        label.setText("total money: "+m.totalMoney());//update the text
    }
}