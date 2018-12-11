
import com.pi4j.io.gpio.event.*;
import com.pi4j.io.gpio.*;

public class IRModule
{

    private String name;
    private MotorController controller;
   
    public IRModule(Pin pin, String name, MotorController controller)
    {
        System.out.println("IR Module Initialization (" + name + ") !");

        this.name = name;
        this.controller = controller;
        
        final GpioController gpio = GpioFactory.getInstance();
        
        System.out.println("Initializing IR on pin " + pin);
        final GpioPinDigitalInput ir = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);
        
        GpioPinListenerDigital listener = new GpioPinListenerDigital() {
            
            @Override
            public void handleGpioPinDigitalStateChangeEvent( GpioPinDigitalStateChangeEvent event) {
                if (event.getState() == PinState.HIGH){
                    System.out.println("Sensor " + name + " ->  Line : Black" );
                    controller.setLineState(name, true);
                } else {
                    System.out.println("Sensor " + name + " -> Line : White" );
                    controller.setLineState(name, false);
                }
            }
            
        };
        
        ir.addListener(listener);
        
        System.out.println("IR Module initialization completed!");
    }
    

}
