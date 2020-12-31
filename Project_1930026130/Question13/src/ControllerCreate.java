
public class ControllerCreate extends Controller{

    public ControllerCreate(Bank m) {
        super(m);
    }
    public String create(String name,String amount,int type){
        try {
            if(type==0){
                m.addAccount(new CreditAccount(name,Integer.parseInt(amount)));
            }
            if(type==1){
                m.addAccount(new StudentAccount(name,Integer.parseInt(amount)));
            }
            return "";
        } catch (NotEnoughMoneyException e1) {
            return e1.getMessage();
        }catch (NumberFormatException e2) {
            return e2.getMessage();
        }
    }
}
