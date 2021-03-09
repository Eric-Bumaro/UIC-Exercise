import java.awt.*;
import java.sql.SQLException;

public class MyProgram {
    public static void main(String[] args) throws Exception {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(dimension.getHeight()+" "+dimension.getWidth());
    }
}