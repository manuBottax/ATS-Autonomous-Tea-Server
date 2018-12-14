import com.pi4j.io.gpio.RaspiPin;

public class FollowPathController
{

    private MotorController motorController;
    private IRModule irRight;
    private IRModule irLeft;

    private boolean isRightSensorDetectingBlack;
    private boolean isLeftSensorDetectingBlack;

    private boolean started;

    public FollowPathController(MotorController motorContr) 
    {
        this.motorController = motorContr;
        this.irRight = new IRModule(RaspiPin.GPIO_15, Directions.RIGHT, this);
        this.irLeft = new IRModule(RaspiPin.GPIO_16, Directions.LEFT, this);

        // this.started = false;
    }

    public void start() {
        this.motorController.goForward();
        this.started = true;
    }

    public void stop() {
        System.out.println("Stopping the robot.");
        this.motorController.stop();
        this.started = false;

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
        if(this.started){
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

}