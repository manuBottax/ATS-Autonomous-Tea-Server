

import com.pi4j.io.gpio.*;


public class Main
{
    
    public static void main(String args[]) throws InterruptedException{        
 
        IRModule dx = new IRModule(RaspiPin.GPIO_14);
        IRModule sx = new IRModule(RaspiPin.GPIO_15);
        // SerialReader reader = new SerialReader();
        Button button = new Button();
        
        while(true){

            System.out.println("Sto andando avanti !");

            Thread.sleep(5000);

            System.out.println("Sono al tavolo del cliente, aspetto !");

            while(! button.isPressed()) { Thread.sleep(100); }

            System.out.println("pressed button ! He has choose his tea");
            button.setPressed(false);
            System.out.println("Torno indietro !");

            Thread.sleep(5000);

            System.out.println("Sono al bar, aspetto !");

            while(!button.isPressed()) { Thread.sleep(100); }

            System.out.println("pressed button ! Mi hanno dato il thè");
            button.setPressed(false);
            System.out.println("Torno indietro !");

            Thread.sleep(5000);

            System.out.println("Sono dal cliente, aspetto !");

            while(!button.isPressed()) { Thread.sleep(100); }

            System.out.println("pressed button ! Mi hanno preso il thè");
            button.setPressed(false);
            System.out.println("Torno indietro a ricaricarmi !");
        }
        
    }
}
