
public class StudentAccount extends Account{
    public StudentAccount(String name, int money) throws NotEnoughMoneyException{
        super(name, money);//the father constructor and debt=-salary
        if(money<0){//judge
            throw new NotEnoughMoneyException("Cannot create student account with negative amount of money");
        }
    }

    @Override
    public void withdraw(int amount) throws NotEnoughMoneyException {
        if(super.getMoney()-amount<0){
            throw new NotEnoughMoneyException("Cannot withdraw "+amount+" yuan from account,only "+super.getMoney()+" yuan is available");
        }else {
            super.setMoney(super.getMoney() - amount);
        }
    }
    public static void testStudentAccount() throws NotEnoughMoneyException  {
        System.out.println("---test StudentAccount---");
        StudentAccount studentAccount1=new StudentAccount("AA",10000);
        try{
        	StudentAccount studentAccount=new StudentAccount("AA",-10000);
        }catch(NotEnoughMoneyException e){
            System.out.println(e.getMessage().equals("Cannot create student account with negative amount of money"));
        }
        System.out.println(studentAccount1.getMoney()==10000);
        System.out.println(studentAccount1.getName()=="AA");
        studentAccount1.withdraw(2000);
        System.out.println(studentAccount1.getMoney()==8000);
        try{
        	studentAccount1.withdraw(12000);
        }catch(NotEnoughMoneyException e){
            System.out.println(e.getMessage().equals("Cannot withdraw 12000 yuan from account,only 8000 yuan is available"));
        }
    }


}
