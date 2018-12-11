import com.pi4j.io.gpio.*;
import hardware_management.*;

public class Main
{
    
    public static void main(String args[]) throws InterruptedException{  

        Button button = new Button();

        Sonar sonar = new Sonar();

        MotorController motorController = new MotorController();
        FollowPathController followPathController = new FollowPathController(motorController);

        followPathController.start();
        
        while(true){
            Thread.sleep(50);
           
            System.out.println( sonar.measureDistance() );

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
