import com.pi4j.io.gpio.RaspiPin;


public class FollowPathController
{

    

    private MotorController motorController;
    private IRModule irRight;
    private IRModule irLeft;

    private boolean isRightSensorDetectingBlack = false;
    private boolean isLeftSensorDetectingBlack = false;

    private boolean started;
    private boolean hasObstacle;

    public FollowPathController() 
    {
        this.motorController = new MotorController();
        this.irRight = new IRModule(RaspiPin.GPIO_15, Directions.RIGHT, this);
        this.irLeft = new IRModule(RaspiPin.GPIO_16, Directions.LEFT, this);
        // this.irRight.start();
        // this.irLeft.start();



        // this.started = false;
        this.hasObstacle = false;
    }

    public void updateObstacleState(boolean hasObstacle) {
        // System.out.println("sono in update obstacle. C'è un ostacolo? " + hasObstacle);
        this.hasObstacle = hasObstacle;
        if (this.hasObstacle) {
            this.waitObstacle();
        } else { 
            this.checkStatus();
        } 
    }

    public void start() {
        if ( ! this.hasObstacle){
            this.motorController.goForward();
            this.started = true;
        } 
    }

    public void stop() {
        System.out.println("Stopping the robot.");
        this.motorController.stop();
        this.started = false;
    }

    public void waitObstacle() {
        System.out.println("Waiting for obstacle removing ");
        this.motorController.stop();
    }


    public void setLineState(Directions dir, boolean isBlack){
        if (dir == Directions.RIGHT) {
            this.isRightSensorDetectingBlack = isBlack;
        } else if (dir == Directions.LEFT){
            this.isLeftSensorDetectingBlack = isBlack;
        }
        this.checkStatus();
    }

    // private void checkStatus() {
    //     if ( this.isRightSensorDetectingBlack && this.isLeftSensorDetectingBlack ){
    //         this.motorController.stop();
    //     } else if ( this.isLeftSensorDetectingBlack) {
    //         this.motorController.turnRight();
    //     } else if ( this.isDisRightSensorDetectingBlackxBlack) {
    //         this.motorController.turnLeft();
    //     } else {
    //         this.motorController.goForward();
    //     }
    // } 

    private void checkStatus() {
        // System.out.println("Started ? " + this.started);
        if(this.started && ! this.hasObstacle){
            if (this.isRightSensorDetectingBlack && this.isLeftSensorDetectingBlack ){
                this.stop();
            } else if ( this.isLeftSensorDetectingBlack) {
                this.motorController.turnLeft();
            } else if ( this.isRightSensorDetectingBlack) {
                this.motorController.turnRight();
            } else {
                this.motorController.goForward();
            }
        }
    } 

    public boolean isStarted() {
        return this.started;
    }

}