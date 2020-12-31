

public class ViewHistory extends View<ControllerHistory>{
    public ViewHistory(Bank m, ControllerHistory c) {
        super(m, c);//the father constructor
        m.addListener(this);
        add(new HistoryPanel(m));
    }

    @Override
    public void update() {
       repaint();
    }
}
