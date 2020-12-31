import java.io.IOException;

public class Controller{
    protected Bank m;
    public Controller(Bank m){//assign value to the filed
        this.m=m;
    }
    protected void shutdown() throws Exception {
    	m.saveData();
    	System.exit(0);
    }
}
