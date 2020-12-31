import java.awt.*;
import java.util.*;

import javax.swing.*;

public class HistoryPanel extends JPanel{
    private Bank m;
    public HistoryPanel(Bank m) {
        this.m=m;
    }
    private int historyRange(ArrayList<Integer> history){
    	int temp=this.historyMax(history)-this.historyMin(history);
        return temp>100?temp:100;//judge if it is larger than 100
    }
    private int historyMax(ArrayList<Integer> history){
        int max = 0;
        for(int i:history){
            max= Math.max(i, max);//the max
        }
        return max;
    }
    private int historyMin(ArrayList<Integer> history){
        int min = 0;
        for(int i:history){
            min= Math.min(i, min);//the min
        }
        return min;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//use the function of father
        int min = historyMin(this.m.getHistory());
        int range = historyRange(this.m.getHistory());
        int maxX = getWidth() - 1;
        int maxY = getHeight() - 1;
        int zero = maxY + min * maxY / range;
        g.setColor(new Color(117, 157, 224));
        g.drawLine(0, zero, maxX, zero);
        g.setColor(new Color(236, 68, 92));
        for (int i = 0; i < m.getHistory().size(); i++) {
            if (i > 0) {//line between different points
                g.drawLine(10 * i, zero - (m.getHistory().get(i)) * maxY / range, 10 * (i - 1), zero - (m.getHistory().get(i - 1)) * maxY / range);
            }else if(m.getHistory().size()==1){
                g.drawRect(10 * i, zero - m.getHistory().get(i) * maxY / range,1,1);//a red rectangle
                break;//out of the loop
            }
        }
    }
}