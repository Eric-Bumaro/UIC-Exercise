
public class ControllerWithdraw extends Controller{

    public ControllerWithdraw(Bank m) {
        super(m);
    }
    public String withdraw(String name,String amount){
        try {
            m.withdraw(name,Integer.parseInt(amount));
            return "";
        } catch (UnknownCustomerException e1) {
            return e1.getMessage();
        } catch (NotEnoughMoneyException e2) {
            return e2.getMessage();
        }catch (NumberFormatException e3) {
            return e3.getMessage();
        }
    }
}
