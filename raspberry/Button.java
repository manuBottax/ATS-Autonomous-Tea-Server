import java.util.concurrent.Callable;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;
import com.pi4j.io.gpio.trigger.GpioPulseStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSyncStateTrigger;

public class Button
{

    private boolean pressed = false;
   
    public Button()
    {
        System.out.println("Button Initialization !");
        
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalInput button = gpio.provisionDigitalInputPin(RaspiPin.GPIO_29, PinPullResistance.PULL_DOWN);
        button.setDebounce(250);

        button.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

                // if (button is pressed)
                if(event.getState().isLow()) {
                    setPressed(true);
                }
            }
        });
        
        System.out.println("Button Initialization completed!");
    }

    public void setPressed(boolean value) {
        this.pressed = value;
    }

    public boolean isPressed() {
        return this.pressed;
    }
    

}