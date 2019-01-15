import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.RaspiPin;


public class Motor
{

    private GpioController gpio;
    private GpioPinPwmOutput speedPin;
    private GpioPinDigitalOutput forwardPin;
    private GpioPinDigitalOutput backwardPin;
    
    private final static int SPEED = 900;
    private String position; 

    public Motor(String position)
    {
        this.position = position;
        gpio = GpioFactory.getInstance();

        if (position == "left")
        {
            speedPin = gpio.provisionPwmOutputPin(RaspiPin.GPIO_24, "SPEED", 0);
            //pin 11
            forwardPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "FORWARD", PinState.LOW);
            //pin 13
            backwardPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "BACKWARD", PinState.LOW);
        }

        else if (position == "right")
        {
            speedPin = gpio.provisionPwmOutputPin(RaspiPin.GPIO_01, "SPEED", 0);
            //pin 15
            forwardPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, "FORWARD", PinState.LOW);
            //pin 19
            backwardPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "BACKWARD", PinState.LOW);
        }

        speedPin.setShutdownOptions(true);
        forwardPin.setShutdownOptions(true);
        backwardPin.setShutdownOptions(true);
    }

    /**
     * Moves motor forward
     * 
     */
    public void forward()
    {
           forwardPin.high();
           backwardPin.low();
           speedPin.setPwm(SPEED);
    }   
    
    /**
     * Moves motor backward
     */
    public void backward()
    {
           forwardPin.low();
           backwardPin.high();
           speedPin.setPwm(SPEED);
    }
    
    /**
     * Moves motor backward
     */
    public void brake()
    {
       forwardPin.low();
       backwardPin.low();
       speedPin.setPwm(0);
    }

}