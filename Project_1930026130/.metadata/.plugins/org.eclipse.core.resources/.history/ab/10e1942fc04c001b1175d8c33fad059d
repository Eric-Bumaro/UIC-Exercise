

public class ViewHistory extends View<ControllerHistory>{
    public ViewHistory(Bank m, ControllerHistory c) {
        super(m, c);//the father constructor
        this.setTitle("View History");
        m.addListener(this);
        add(new HistoryPanel(m));
    }

    @Override
    public void update() {
    	System.out.println(m.totalMoney());
       super.repaint();
    }
}
