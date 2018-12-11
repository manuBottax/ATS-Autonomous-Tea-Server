
import com.pi4j.io.gpio.event.*;
import com.pi4j.io.gpio.*;

public class IRModule
{

    private Directions direction;
    private FollowPathController followPathController;
   
    public IRModule(Pin pin, Directions direction, FollowPathController controller)
    {
        System.out.println("IR Module Initialization (" + direction.getDirection() + ") !");

        this.direction = direction;
        this.followPathController = controller;
        
        final GpioController gpio = GpioFactory.getInstance();
        
        System.out.println("Initializing IR on pin " + pin);
        final GpioPinDigitalInput ir = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);
        
        GpioPinListenerDigital listener = new GpioPinListenerDigital() {
            
            @Override
            public void handleGpioPinDigitalStateChangeEvent( GpioPinDigitalStateChangeEvent event) {
                //System.out.println(" ---> IR Sensor change : " + event.getPin() + " => " + event.getState());
                // System.out.println("event from pin : " + event.getPin());
                if (event.getState() == PinState.HIGH){
                    System.out.println("Sensor " + direction.getDirection() + " ->  Line : Black" );
                    followPathController.setLineState(direction, true);
                } else {
                    System.out.println("Sensor " + direction.getDirection() + " -> Line : White" );
                    followPathController.setLineState(direction, false);
                }
            }
            
        };
        
        ir.addListener(listener);
        //ir.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        
        System.out.println("IR Module initialization completed!");
    }
}
