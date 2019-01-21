public class BehaviourHandler
{

    private LocationController locationController;
    private ObstacleAvoidanceController obstacleController;
    private Button button = new Button();

    private String location;
    private int attempt = 0;
   
    public BehaviourHandler (LocationController l) {
        this.locationController = l;
        this.obstacleController = new ObstacleAvoidanceController();
        this.obstacleController.start();
    }

    public boolean serveTea() {
        try {

            System.out.println(" | Waiting for a client ! | ");
            while(!button.isPressed()) { Thread.sleep(100); }
            Thread.sleep(2000);
            button.setPressed(false);
            System.out.println(" | A client is arrived ! | ");

            System.out.println(" --> Going to the client !");
            goingToTheClient();
        
            System.out.println(" | At table, waiting for orders ! | ");
            while(! button.isPressed()) { Thread.sleep(100); }
            Thread.sleep(2000);
            button.setPressed(false);
            System.out.println(" | The client has choose his tea | ");

            System.out.println(" --> Going to bar !");
            goingToTheBar();

            System.out.println(" | At bar, waiting for preparation ! | ");
            while(!button.isPressed()) { Thread.sleep(100); }
            Thread.sleep(2000);
            button.setPressed(false);
            System.out.println(" | The tea is ready ! | ");

            System.out.println("--> Going back to client to delivery the tea !");
            goingToTheClient();

            System.out.println(" | At client, waiting for him to retrieve his tea ! | ");
            while(!button.isPressed()) { Thread.sleep(100); }
            Thread.sleep(2000);
            button.setPressed(false);
            System.out.println(" | The client has retrieve the tea ! | ");

            System.out.println("--> Going to wait for the client to finish his tea !");
            goingToTheChargeStation();

            System.out.println(" | At charge station, waiting for the client to finish his tea ! | ");
            while(!button.isPressed()) { Thread.sleep(100); }
            Thread.sleep(2000);
            button.setPressed(false);
            System.out.println(" | The client has finish his tea ! | ");

            System.out.println(" --> Going back to client to receive the empty cup !");
            goingToTheClient();

            System.out.println(" | At client, waiting for him to receive his empty cup ! | ");
            while(!button.isPressed()) { Thread.sleep(100); }
            Thread.sleep(2000);
            button.setPressed(false);
            System.out.println(" | Received the empty cup ! | ");
            System.out.println(" | Thank you for choosing us and see you soon ! | ");

            System.out.println(" --> Going to bar !");
            goingToTheBar();

            System.out.println(" | At bar, waiting for give back the empty cup ! | ");
            while(!button.isPressed()) { Thread.sleep(100); }
            Thread.sleep(2000);
            button.setPressed(false);
            System.out.println(" | The empty cup has been taken ! | ");

            System.out.println(" --> Going to wait for a new client !");
            goingToTheChargeStation();

            return true;

        } catch(InterruptedException e) {
            System.err.println("InterruptException detected : " + e);
            return false;
        }
    }

    private void goingToTheClient() {
        System.out.println("Going to client...");
        this.attempt = 0;
        this.obstacleController.startMoving();
        while(this.obstacleController.isStarted()) { 
            try {
                Thread.sleep(100); 
            } catch (InterruptedException ex) {} 
        }
        System.out.println("now isArrested ? " + ! this.obstacleController.isStarted());
        this.location = this.locationController.getLocation();
        System.out.println("location :" + this.location);

        while(!this.location.equals("CLIENT'S TABLE")){
            if(this.location.equals("UNKNOWN")) {
                checkLocation();
                
                System.out.println(this.attempt);
                if ( this.attempt >= 2){
                    
                    this.attempt = 0;
                    goingToTheClient();
                }
            } else {
                goingToTheClient();
            }
        }
    }

    private void checkLocation() {
        System.out.println("Attempt : " + this.attempt);
        if ( this.attempt < 2) {
            try{
                Thread.sleep(500);
                this.location = this.locationController.getLocation();
                if(this.location.equals("UNKNOWN")) {
                    this.attempt = this.attempt +1;
                    checkLocation();
                }
            }   catch(InterruptedException ex) {}
        }
    }

    private void goingToTheBar() {
        System.out.println("Going to bar...");
        this.attempt = 0;
        this.obstacleController.startMoving();
        while(this.obstacleController.isStarted()) { 
            try {
                Thread.sleep(100); 
            } catch (InterruptedException ex) {} 
        }
        this.location = this.locationController.getLocation();
        while(!this.location.equals("BAR")){
            if(this.location.equals("UNKNOWN")) {
                checkLocation();
                if ( this.attempt >= 2){
                    goingToTheBar();
                }
            } else {
                goingToTheBar();
            }
        }
    }

    private void goingToTheChargeStation() {
        System.out.println("Going to charge station...");
        this.attempt = 0;
        this.obstacleController.startMoving();
        while(this.obstacleController.isStarted()) { 
            try {
                Thread.sleep(100); 
            } catch (InterruptedException ex) {} 
        }
        this.location = this.locationController.getLocation();
        while(!this.location.equals("CHARGE STATION")){
            if(this.location.equals("UNKNOWN")) {
                checkLocation();
                if ( this.attempt >= 2){
                    goingToTheChargeStation();
                }
            } else {
                goingToTheChargeStation();
            }
        }
    }

}