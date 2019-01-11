import java.util.Observer;

public class MotorController
{
    private Motor rightWheel = new Motor("right");
    private Motor leftWheel = new Motor("left");

    private Boolean isRobotArrested;
   
    public MotorController () {
        this.isRobotArrested = false;
    }

    public void goForward() {
        
        System.out.println(" Going Forward ! ");
        rightWheel.forward();
        leftWheel.forward();
        this.isRobotArrested = false;
    }

    public void goBackward() {

            System.out.println(" Going Backward ! ");
           
            rightWheel.backward();
            leftWheel.backward();
            this.isRobotArrested = false;
        
    }

    public void turnLeft() {
        if (! this.isRobotArrested) {
            System.out.println(" Going Left ! ");

            rightWheel.forward();
            leftWheel.brake();
        }
    }

    public void turnRight() {
        if (! this.isRobotArrested  ) {
            System.out.println(" Going Right ! ");

            leftWheel.forward();
            rightWheel.brake();
        }
    }

    public void stop() {
        this.isRobotArrested = true;
        System.out.println(" Stop ! ");
        rightWheel.brake();
        leftWheel.brake();
    }

    public Boolean isRobotArrested() {
        return this.isRobotArrested;
    }
}