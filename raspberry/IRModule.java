
import com.pi4j.io.gpio.event.*;

import java.sql.Date;
import java.util.Calendar;

import com.pi4j.io.gpio.*;

public class IRModule
{

    private Directions direction;
    private FollowPathController followPathController;

    private int blackOccurance = 0;
   
    public IRModule(Pin pin, Directions dir, FollowPathController controller)
    {
        System.out.println("IR Module Initialization (" + dir.getDirection() + ") !");

        this.direction = dir;
        this.followPathController = controller;
        
        final GpioController gpio = GpioFactory.getInstance();
        
        System.out.println("Initializing IR on pin " + pin);
        final GpioPinDigitalInput ir = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);
        
        GpioPinListenerDigital listener = new GpioPinListenerDigital() {
            
            @Override
            public void handleGpioPinDigitalStateChangeEvent( GpioPinDigitalStateChangeEvent event) {
                
                if (event.getState() == PinState.HIGH){
                    System.out.println("c'è del nero");
                    blackOccurance++;
                    for ( int i = 0; i < 4; i ++) {
                        try {
                            Thread.sleep(10);
                            if (ir.isHigh()){
                                blackOccurance++;
                            }
                        } catch ( InterruptedException ex) {}
                    }

                    if ( blackOccurance >= 3) {
                        System.out.print("Black Occurence : " + blackOccurance + " | ");
                        System.out.println("Sensor " + direction.getDirection() + " ->  Line : Black" );
                        followPathController.setLineState(direction, true);
                        
                    } else {
                        System.out.println(  direction.getDirection() + " : Mi sono inventato del nero che non c'è ");
                    }

                    blackOccurance = 0;
                    
                } else {
                    System.out.println("Sensor " + direction.getDirection() + " -> Line : White" );
                    followPathController.setLineState(direction, false);
                }
            }
            
        };
        
        ir.addListener(listener);
        
        System.out.println("IR Module initialization completed!");
    }
}
