import java.util.ArrayList;

public class Bank{
	 private String name;
	 private ArrayList<IAccount> accounts;
	    public Bank(String name){
	        this.name=name;
	        accounts=new ArrayList<IAccount>();//instantiate
	    }
	    public void addAccount(IAccount account){
	    	accounts.add(account);//add new one
	    }
	    public int totalMoney(){
	        int total = 0;
	        for(IAccount i:accounts){
	            total+=i.getMoney();//the total debt
	        }
	        return total;
	    }
	    public int getMoney(String name) throws UnknownCustomerException {
	        for(IAccount i:accounts){
	            if(i.getName().equals(name)){//judge
	                return i.getMoney();
	            }
	        }
	        throw new UnknownCustomerException("Customer "+name+" unknown");
	    }
	    public void withdraw(String name,int amount) throws UnknownCustomerException, NotEnoughMoneyException {
	        for(IAccount i:accounts){
	            if(i.getName().equals(name)){
	                i.withdraw(amount);//the pay method of corresponding value
	                return;
	            }
	        }
	        throw new UnknownCustomerException("Customer "+name+" unknown");
	    }
	    public static void testBank() throws UnknownCustomerException, NotEnoughMoneyException {
	    	System.out.println("---test Bank---");
	    	Bank bank=new Bank("FO");
	        StudentAccount student1=new StudentAccount("Eric",5000);
	        StudentAccount student2=new StudentAccount("Eric1",5000);
	        bank.addAccount(student1);
	        bank.addAccount(student2);
	        System.out.println(bank.accounts.size()==2);
	        System.out.println(bank.getMoney("Eric")==5000);
	        System.out.println(bank.totalMoney()==10000);
	        try {
	        	bank.getMoney("A");
	        }catch(UnknownCustomerException e){
	            System.out.println(e.getMessage().equals("Customer A unknown"));
	        }
	        try {
	        	bank.withdraw("A",1000);
	        }catch(UnknownCustomerException e){
	            System.out.println(e.getMessage().equals("Customer A unknown"));
	        }
	        try{
	        	bank.withdraw("Eric",12000);
	        }catch(NotEnoughMoneyException e){
	            System.out.println(e.getMessage().equals("Cannot withdraw 12000 yuan from account,only 5000 yuan is available"));
	        }
	        bank.withdraw("Eric",2000);
	        System.out.println(bank.totalMoney()==8000);
	    }
}
