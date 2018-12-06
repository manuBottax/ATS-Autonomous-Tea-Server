
import com.pi4j.io.gpio.event.*;
import com.pi4j.io.gpio.*;

public class IRModule
{
   
    public IRModule(Pin pin)
    {
        System.out.println("Initialization !");
        
        final GpioController gpio = GpioFactory.getInstance();
        
        System.out.println("Initializing IR on pin " + pin);
        final GpioPinDigitalInput ir = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);
        
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
        //ir.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        
        System.out.println("Initialization completed!");
    }
    

}
