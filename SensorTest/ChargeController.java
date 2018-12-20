
public class ChargeController {

    private static final int MIN_CHARGE_REQUIRED = 50;
    private int charge_available;

    private BehaviourHandler behaviour ;

    public ChargeController(BehaviourHandler behaviour) {
        this.behaviour = behaviour;
        // starting charge are casual, between 50 and 100;
        this.charge_available = (int)(Math.random() * 50 ) + 50;
        
    }

    public void start() {

        System.out.println("Available Charge: " + this.charge_available );

        if (this.charge_available > MIN_CHARGE_REQUIRED){
            boolean complete = this.behaviour.serveTea();
            if(complete) {
                this.start();
            }
        } else {
            charge();
        }

    }

    private void charge(){
        System.out.println("Not Enough Charge available. ");
        System.out.print("Charging ");
        
        try {
            for (int i = 0; i < 5; i ++){
                System.out.print(".");
                Thread.sleep(1000);
            }
            
        } catch (InterruptedException ex) {}
        
        this.charge_available += 50;

        System.out.print("Charging completed !");

        start();
        
    }


}
 