import java.awt.event.*;

import javax.swing.*;

public abstract class View<T extends Controller> extends JFrame implements ModelListener{
    protected Bank m;
    protected T c;

    public View(Bank m, T c){
        this.m = m;
        this.c = c;
        this.setVisible(true);
        this.setSize(400,600);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	dispose();
            	try {
					c.shutdown();//call the shut down function
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            }
        });
    }
    @Override
    
    public abstract void update();
}
