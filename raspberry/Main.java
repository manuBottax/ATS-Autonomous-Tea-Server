import com.pi4j.io.gpio.*;
import hardware_management.*;

public class Main
{
    
    public static void main(String args[]) throws InterruptedException{  
         
         LocationController location = new LocationController();

        while (! location.getLocation().equals("CHARGE STATION")){   
            System.out.println("Robot cannot start outside charge station");     
        }

        BehaviourHandler behaviour = new BehaviourHandler(location);

        ChargeController controller = new ChargeController(behaviour);

        controller.start();
        
        while(true){
            Thread.sleep(30);   
        }
        
    }
}
