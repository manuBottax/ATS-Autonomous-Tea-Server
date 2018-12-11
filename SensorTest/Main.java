

import com.pi4j.io.gpio.*;


public class Main
{
    
    public static void main(String args[]) throws InterruptedException{  

        MotorController motorController = new MotorController();
        
        IRModule dx = new IRModule(RaspiPin.GPIO_15, "Dx", motorController);
        IRModule sx = new IRModule(RaspiPin.GPIO_16, "Sx", motorController);

        Motor rightWheel = new Motor("right");
        Motor leftWheel = new Motor("left");

        SerialReader reader = new SerialReader();
        Button button = new Button();
        
        while(true){

            System.out.println("Wheel test 1 !");

            System.out.println("Going Forward ! ");

            rightWheel.forward();
            leftWheel.forward();

            Thread.sleep(5000);

            System.out.println("stop ! ");

            rightWheel.brake();
            leftWheel.brake();

            Thread.sleep(3000);

            System.out.println("Going Backward ! ");

            rightWheel.backward();
            leftWheel.backward();
            Thread.sleep(5000);

            System.out.println("turning left ! ");

            rightWheel.brake();
            leftWheel.brake();
            Thread.sleep(1000);
            rightWheel.forward();
            leftWheel.backward();
            Thread.sleep(5000);

            System.out.println("turning right ! ");

            rightWheel.brake();
            leftWheel.brake();
            Thread.sleep(1000);
            rightWheel.backward();
            leftWheel.forward();
            Thread.sleep(5000);

            System.out.println("going left ! ");

            rightWheel.brake();
            leftWheel.brake();
            Thread.sleep(1000);
            rightWheel.forward();
            leftWheel.brake();
            Thread.sleep(5000);


            System.out.println("going right ! ");

            rightWheel.brake();
            leftWheel.brake();
            Thread.sleep(1000);
            rightWheel.brake();
            leftWheel.forward();
            Thread.sleep(5000);

            // System.out.println("Waiting for a client !");

            // while(! button.isPressed()) { Thread.sleep(100); }

            // System.out.println("A client is arrived !");
            // button.setPressed(false);
            // System.out.println("Going to the client !");

            // Thread.sleep(15000);

            // System.out.println("At table, waiting for orders !");

            // while(! button.isPressed()) { Thread.sleep(100); }

            // System.out.println("pressed button ! The client has choose his tea");
            // button.setPressed(false);
            // System.out.println("Going to bar !");

            // Thread.sleep(15000);

            // System.out.println("At bar, waiting for preparation !");

            // while(!button.isPressed()) { Thread.sleep(100); }

            // System.out.println("pressed button ! they give me some tea ");
            // button.setPressed(false);
            // System.out.println("Going back to client !");

            // Thread.sleep(15000);

            // System.out.println("At client, waiting for him to retrieve his tea !");

            // while(!button.isPressed()) { Thread.sleep(100); }

            // System.out.println("pressed button ! the client receive the tea");
            // button.setPressed(false);
            // System.out.println("Coming back to start !");
        }
        
    }
}
