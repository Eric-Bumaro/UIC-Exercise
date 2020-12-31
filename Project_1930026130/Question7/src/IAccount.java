
public interface IAccount {
    public String getName();
    public int getMoney();
    public void withdraw(int amount) throws NotEnoughMoneyException;
}
