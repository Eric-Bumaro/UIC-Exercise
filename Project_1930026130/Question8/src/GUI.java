
public class GUI{
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Bank bank=new Bank("UIC Bank");//a thread
                try {
					bank.addAccount(new StudentAccount("Philippe",1000));
					bank.addAccount(new StudentAccount("Eric",1500));
				} catch (NotEnoughMoneyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                ControllerSimple controllerSimple=new ControllerSimple(bank);
                ViewSimple viewSimple=new ViewSimple(bank,controllerSimple);
            }
        });
    }
}