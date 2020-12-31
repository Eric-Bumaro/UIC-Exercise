import java.io.*;

public interface IAccount extends Serializable{//implements the interface
    public String getName();
    public int getMoney();
    public void withdraw(int amount) throws NotEnoughMoneyException;
}
