public class MotorController
{

    private boolean isRightSensorDetectingBlack;
    private boolean isLeftSensorDetectingBlack;

    Motor rightWheel = new Motor("right");
    Motor leftWheel = new Motor("left");
   
    public MotorController () {}

    public void goForward() {
        System.out.println(" Going Forward ! ");
        rightWheel.forward();
        leftWheel.forward();
    }

    public void goBackward() {
        System.out.println(" Going Forward ! ");
        rightWheel.backward();
        leftWheel.backward();
    }

    public void turnLeft() {
        System.out.println(" Going Left ! ");
        rightWheel.forward();
        leftWheel.brake();
    }

    public void turnRight() {
        System.out.println(" Going Right ! ");
        rightWheel.brake();
        leftWheel.forward();
    }

    public void stop() {
        System.out.println(" Stop ! ");
        rightWheel.brake();
        leftWheel.brake();
    }
}