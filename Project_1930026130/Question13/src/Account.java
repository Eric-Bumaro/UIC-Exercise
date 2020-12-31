import java.io.*;

public abstract class Account implements IAccount{
	private  static final long serialVersionUID=1L;//the serialVersionUID
    private String name;
    private int money;
    public Account(String name,int money){
        this.name=name;
        this.money=money;
    }
    public String getName(){//get the corresponding value
        return name;
    }
    public int getMoney() {
        return money;
    }
    protected void setMoney(int money) {//set the filed
        this.money = money;
    }
    public abstract void withdraw(int amount) throws NotEnoughMoneyException;
    public static void testAccount(){
        //As it is an abstract class, we cannot test it
        System.out.println("---test Account---");
    }
}
