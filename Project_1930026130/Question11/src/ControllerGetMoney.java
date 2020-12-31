
public class ControllerGetMoney extends Controller{

    public ControllerGetMoney(Bank m) {
        super(m);
    }
    public String getMoney(String name) throws UnknownCustomerException {
        return String.valueOf(this.m.getMoney(name));
    }
}

