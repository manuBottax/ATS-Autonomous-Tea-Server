
import com.pi4j.io.gpio.event.*;

import com.pi4j.io.gpio.*;

public class IRModuleSync extends Thread{

    private Directions direction;
    private FollowPathController followPathController;

    private final GpioController gpio;
    private final GpioPinDigitalInput ir;

    private boolean currentIsBlack = false;

    private int blackOccurance = 0;
   
    public IRModuleSync(Pin pin, Directions dir, FollowPathController controller)
    {
        System.out.println("IR Module Initialization (" + dir.getDirection() + ") !");

        this.direction = dir;
        this.followPathController = controller;
        this.gpio = GpioFactory.getInstance();        
        System.out.println("Initializing IR on pin " + pin);
        this.ir = this.gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);
        System.out.println("IR Module initialization completed!");
    }

    @Override
    public void run() {

        while(true){

            if (this.ir.getState() == PinState.HIGH && !this.currentIsBlack){
                System.out.println(this.direction.getDirection() + " c'è del nero");
                this.blackOccurance++;
                for ( int i = 0; i < 2; i ++) {
                    try {
                        Thread.sleep(10);
                        if (this.ir.isHigh()){
                            this.blackOccurance++;
                        }
                    } catch ( InterruptedException ex) {}
                }

                if ( this.blackOccurance >= 2) {
                    System.out.print("Black Occurence : " + this.blackOccurance + " | ");
                    System.out.println("Sensor " + this.direction.getDirection() + " ->  Line : Black" );
                    followPathController.setLineState(this.direction, true);
                    this.currentIsBlack = true;
                    
                } else {
                    System.out.println(  this.direction.getDirection() + " : Mi sono inventato del nero che non c'è ");
                }

                this.blackOccurance = 0;
            } else if ( this.ir.getState() == PinState.LOW && this.currentIsBlack ) {
                System.out.println("Sensor " + this.direction.getDirection() + " -> Line : White" );
                followPathController.setLineState(this.direction, false);
                this.currentIsBlack = false;
            } 

            try {
                Thread.sleep(100);
            } catch ( InterruptedException ex) {}
        }

    }
        
       
}
