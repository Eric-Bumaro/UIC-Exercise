
public class GUI{
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                    	Bank bank=new Bank("UIC Bank");//a thread
//                        try {
//        					bank.addAccount(new StudentAccount("Eric",2000));
////        					bank.addAccount(new StudentAccount("Philippe",1500));
//        				} catch (NotEnoughMoneyException e) {
//        					// TODO Auto-generated catch block
//        					e.printStackTrace();
//        				}
                        ControllerGetMoney controller=new ControllerGetMoney(bank);//the previous
                        ViewGetMoney viewSimple=new ViewGetMoney(bank,controller);
                        ControllerWithdraw controller1=new ControllerWithdraw(bank);
                        ViewWithdraw viewWithdraw=new ViewWithdraw(bank,controller1);
                        ControllerCreate controller2=new ControllerCreate(bank);
                        ViewCreate viewCreate=new ViewCreate(bank,controller2);
                        ControllerHistory controller3=new ControllerHistory(bank);
                        ViewHistory viewHistroy=new ViewHistory(bank,controller3);
            }
        });
    }
}