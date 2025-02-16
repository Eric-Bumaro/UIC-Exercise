import javax.swing.*;

public abstract class View<T extends Controller> extends JFrame implements ModelListener{
    protected Bank m;
    protected T c;

    public View(Bank m, T c){
        this.m = m;
        this.c = c;
        this.setVisible(true);
        m.addListener(this);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400,600);
    }
    @Override
    public abstract void update();
}
