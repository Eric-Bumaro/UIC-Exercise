
public class CreditAccount extends Account{
    public CreditAccount(String name, int money) {
        super(name, money);//the father constructor
    }
    @Override
    public void withdraw(int amount) {
        super.setMoney(super.getMoney()-amount);//the father function
    }
    public static void testCreditAccount(){
    	CreditAccount creditAccount1=new CreditAccount("Eric",500);
        System.out.println("---test CreditAccount---");
        System.out.println(creditAccount1.getName()=="Eric");
        System.out.println(creditAccount1.getMoney()==500);
        creditAccount1.setMoney(1000);
        System.out.println(creditAccount1.getMoney()==1000);
        creditAccount1.withdraw(100);
        System.out.println(creditAccount1.getMoney()==900);
    }
}
