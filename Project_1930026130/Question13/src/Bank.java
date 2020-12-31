import java.io.*;
import java.util.*;

public class Bank{
    private String name;
    private ArrayList<IAccount> accounts;
    private ArrayList<ModelListener> listeners;
    private ArrayList<Integer> history=new ArrayList<Integer>();//instantiate
    private File file;
    ObjectInputStream reader;
    public Bank(String name) throws Exception{
        this.name=name;
        file=new File(name+".bin");
        file.createNewFile();//create if it is not exist
        try {
            reader=new ObjectInputStream(new FileInputStream(file));//the input steam
			Object o=reader.readObject();//get them from the file
			accounts = (ArrayList<IAccount>)o;
			o=reader.readObject();
			history = (ArrayList<Integer>)o;
			reader.close();
			}catch(EOFException e) {
				accounts=new ArrayList<IAccount>();//instantiate
				history.add(0);
			}
        listeners=new ArrayList<ModelListener>();
    }
    public void addAccount(IAccount account){

        accounts.add(account);//add new one
        this.history.add(this.totalMoney());//add the new recording
        notifyListener();
    }
    public int totalMoney(){
        int total = 0;
        for(IAccount i:accounts){
            total+=i.getMoney();//the total debt
        }
        return total;
    }
    public void addListener(ModelListener modelListener){
        listeners.add(modelListener);//add a new Listener
    }
    private void notifyListener(){
        for(ModelListener i:listeners){
            i.update();
        }
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
                this.history.add(this.totalMoney());//add the new recording
                notifyListener();
                return;
            }
        }
        throw new UnknownCustomerException("Customer "+name+" unknown");
    }
    public ArrayList<Integer> getHistory() {
        return history;
    }
    public void saveData() throws IOException {
		ObjectOutputStream ois=new ObjectOutputStream(new FileOutputStream(file));//the output stream
		ois.writeObject(accounts);//into the file
		ois.writeObject(history);
		ois.close();
    }
    public static void testBank() throws Exception {
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
