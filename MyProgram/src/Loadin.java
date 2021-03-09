import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Loadin {
    JFrame myframe;
    JLayeredPane layeredPane;
    JPanel userpanel=new JPanel();
    JTextField username=new JTextField(10);
    JPanel a=new JPanel();
    JPanel b=new JPanel();
    JPanel c=new JPanel();
    JLabel userpassword=new JLabel("Your password:",JLabel.CENTER);
    JLabel userid=new JLabel("Your id:",JLabel.CENTER);
    JLabel wrongpw=new JLabel();
//    JTextArea password=new JTextArea(1,15);
    JPasswordField password=new JPasswordField(15);
    JButton forgetpw=new JButton("Forget password");
    JButton loadinnew=new JButton("Register");
    JButton loadin=new JButton("get in");
    Loadin(){
        myframe=new JFrame("User interface");
        myframe.setLayout(new GridLayout(1, 1));
        ImageIcon icon=new ImageIcon("Icon.jpg");  //xxx代表图片存放路径，2.png图片名称及格式
        myframe.setIconImage(icon.getImage());
        layeredPane = new JLayeredPane();
        layeredPane = new JLayeredPane() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("background.jpg");
                image.setImage(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
                g.drawImage(image.getImage(), 0, 0,this);
            }
        };
        myframe.setLocation(0,0);
        myframe.setSize(1100,600);//1100 600
        setUsernamepanel();
        layeredPane.add(userpanel,new Integer(100));
        myframe.add(layeredPane);
        myframe.setVisible(true);
        myframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    void setUsernamepanel(){
        password.setEchoChar('*');
        wrongpw.setHorizontalAlignment(SwingConstants.CENTER);
        a.setLayout(new GridLayout(3,1));
        b.setLayout(new GridLayout(2,2));
        c.setLayout(new GridLayout(2,1));
        userpanel.setSize(new Dimension(myframe.getWidth()/6,myframe.getHeight()/15));
        userpanel.setLayout(new GridLayout(3,1));
        userid.setVisible(true);
        userid.setForeground(Color.WHITE);
        userid.setOpaque(false);
        userpassword.setVisible(true);
        userpassword.setForeground(Color.blue);
        userpassword.setOpaque(false);
        wrongpw.setOpaque(false);
        wrongpw.setForeground(Color.RED);
        forgetpw.setForeground(Color.BLACK);
        forgetpw.setBounds(new Rectangle(6,2));
        loadinnew.setForeground(Color.BLACK);
        loadinnew.setBounds(new Rectangle(6,2));
        loadin.setForeground(Color.BLUE);
        loadin.setBounds(new Rectangle(6,2));
        loadin.addActionListener(loadinsucc);
        loadinnew.addActionListener(registoraction);
        forgetpw.addActionListener(forgetaction);
        setButton(forgetpw);
        setButton(loadinnew);
        c.add(forgetpw);
        c.add(loadinnew);
        c.setOpaque(false);
        a.add(userid);
        a.add(username);
        a.add(wrongpw);
        b.add(userpassword);
        b.add(password);
        b.add(loadin);
        a.setSize(new Dimension(20,6));
        a.setOpaque(false);
        b.setSize(new Dimension(20,6));
        b.setOpaque(false);
//        userpanel.setBounds(myframe.getWidth()*10/21+myframe.getX()-10,(int) myframe.getHeight()*2/3+myframe.getY()-10,250,200);
        userpanel.setBounds(myframe.getWidth()*9/21,(int) myframe.getHeight()*4/7,250,200);
//        userpanel.setSize(250,200);
        userpanel.setOpaque(false);
        userpanel.add(a);
        userpanel.add(b);
        userpanel.add(c);
        userpanel.setVisible(true);
    }
    void setButton(JButton a){
        a.setMargin(new Insets(0,0,0,0));//将边框外的上下左右空间设置为0
        a.setIconTextGap(0);//将标签中显示的文本和图标之间的间隔量设置为0
        a.setBorderPainted(false);//不打印边框
        a.setBorder(null);//除去边框
        a.setContentAreaFilled(false);
    }
    ActionListener registoraction=new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            userpanel.removeAll();
            userpanel.repaint();
            userpanel.revalidate();
            userpanel.setBounds(myframe.getWidth()*9/21,(int) myframe.getHeight()*4/7,250,600);
            userpanel.add(new Register(myframe).regispanel,new Integer(150));
        }
    };
    ActionListener forgetaction=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            userpanel.removeAll();
            userpanel.repaint();
            userpanel.revalidate();
            userpanel.setBounds(myframe.getWidth()*9/21,(int) myframe.getHeight()*4/7,250,450);
            userpanel.add(new Forgetpass(myframe).all,new Integer(150));
        }
    };
    ActionListener loadinsucc=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ConnectDB user=new ConnectDB(Long.valueOf(username.getText()),password.getText());
//                System.out.print(Long.valueOf(username.getText())+"  "+password.getText());
                if(user.testpassword()){
                    if(!user.judge()) {
                        userpanel.removeAll();
                        userpanel.repaint();
                        userpanel.revalidate();
                        userpanel.setBounds(myframe.getWidth() * 9 / 21, (int) myframe.getHeight() * 4 / 7, 350, 600);
                        userpanel.add(new CommonUser(user, myframe).all, new Integer(150));
                    }else{
                        userpanel.removeAll();
                        userpanel.repaint();
                        userpanel.revalidate();
                        userpanel.setBounds(myframe.getWidth() * 7/ 21, (int) myframe.getHeight() * 2 / 7, 650, 400);
                        userpanel.setLayout(new GridLayout(1,1));
                        //                        userpanel.setBounds(new Rectangle(900,600));
                        userpanel.add(new Controller(user, myframe).box, new Integer(150));
                    }
                }
                else{
                    wrongpw.setText("Wrong id or password");
                    username.setText("");
                    password.setText("");
                    user.getout();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }};
}
