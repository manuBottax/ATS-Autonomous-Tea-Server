
/**
 * Write a description of class IRModule here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import com.pi4j.io.gpio.event.*;
import com.pi4j.io.gpio.*;

public class IRModule
{
   
    public IRModule()
    {
        System.out.println("Initialization !");
        
        final GpioController gpio = GpioFactory.getInstance();
        
        final GpioPinDigitalInput ir = gpio.provisionDigitalInputPin(RaspiPin.GPIO_14, PinPullResistance.PULL_DOWN);
        
        GpioPinListenerDigital listener = new GpioPinListenerDigital() {
            
            @Override
            public void handleGpioPinDigitalStateChangeEvent( GpioPinDigitalStateChangeEvent event) {
                //System.out.println(" ---> IR Sensor change : " + event.getPin() + " => " + event.getState());
                if (event.getState() == PinState.HIGH){
                    System.out.println(" Line : true" );
                } else {
                    System.out.println(" Line : false" );
                }
            }
            
        };
        
        ir.addListener(listener);
        
        System.out.println("Initialization completed!");
    }
    

}
