import java.awt.Dimension;
import java.awt.Toolkit;

public class ViewHistory extends View<ControllerHistory>{
    public ViewHistory(Bank m, ControllerHistory c) {
        super(m, c);//the father constructor
        this.setTitle("View History");
        add(new HistoryPanel(m));
        this.setSize(new Dimension(400,200));//set the location
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        double width = dimension.getWidth() / 2;
        double height = dimension.getHeight() / 2;
        this.setLocation((int)width,(int)height);
    }

    @Override
    public void update() {
       super.repaint();
    }
}
