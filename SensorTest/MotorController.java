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
        System.out.println(" Going Left ! ");
        rightWheel.forward();
        leftWheel.brake();
        this.isRobotArrested = false;
    }

    public void turnRight() {
        System.out.println(" Going Right ! ");
        rightWheel.brake();
        leftWheel.forward();
        this.isRobotArrested = false;
    }

    public void stop() {
        System.out.println(" Stop ! ");
        rightWheel.brake();
        leftWheel.brake();
        this.isRobotArrested = true;
    }

    public Boolean isRobotArrested() {
        return this.isRobotArrested;
    }
}