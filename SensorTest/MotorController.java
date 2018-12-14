public class MotorController
{
    Motor rightWheel = new Motor("right");
    Motor leftWheel = new Motor("left");

    Boolean isRobotArrested;
   
    public MotorController () {}

    public void goForward() {
        System.out.println(" Going Forward ! ");
        rightWheel.forward();
        leftWheel.forward();
        this.isRobotArrested = false;
    }

    public void goBackward() {
        System.out.println(" Going Forward ! ");
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
        // this.isRobotArrested = false;
    }

    public void turnRight() {
        if (! this.isRobotArrested) {
            System.out.println(" Going Right ! ");
            leftWheel.forward();
            rightWheel.brake();
        }
        // this.isRobotArrested = false;
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