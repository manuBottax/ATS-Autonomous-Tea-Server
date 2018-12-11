import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.RaspiPin;


public class Motor
{
    // instance variables - replace the example below with your own
    private GpioController gpio;
    // private GpioPinPwmOutput speedPin;
    private GpioPinDigitalOutput forwardPin;
    private GpioPinDigitalOutput backwardPin;
    
    // public static final int SLOWEST = 300;
    // public static final int FASTEST = 500;
    // private static final int MIN_SPEED = 0;
    // private static final int MAX_SPEED = 1024;
       
    /**
     * Constructor for objects of class Sonar
     */

     private String position; 
    public Motor(String position)
    {
        this.position = position;
        gpio = GpioFactory.getInstance();
        
        // initialise instance variables
        if (position == "left")
        {
            // speedPin = gpio.provisionPwmOutputPin(RaspiPin.GPIO_23, "SPEED", 0);
            //pin 11
            forwardPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "FORWARD", PinState.LOW);
            //pin 13
            backwardPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "BACKWARD", PinState.LOW);
        }
        else if (position == "right")
        {
            // speedPin = gpio.provisionPwmOutputPin(RaspiPin.GPIO_26, "SPEED", 0);
            //pin 15
            forwardPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, "FORWARD", PinState.LOW);
            //pin 19
            backwardPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "BACKWARD", PinState.LOW);
        }
        // set shutdown state for this input pin
        // speedPin.setShutdownOptions(true);
        forwardPin.setShutdownOptions(true);
        backwardPin.setShutdownOptions(true);
    }

    /**
     * Moves motor forward
     * 
     */
    // public void forward(int speed)
    public void forward()
    {
    //    speed = adjustSpeed(speed);
    //    if (speed == MIN_SPEED)
    //    {
        //    brake();
    //    }
    //    else
    //    {
           forwardPin.high();
           backwardPin.low();
        //    speedPin.setPwm(speed);
    //    }
       
    }   
    
    /**
     * Moves motor backward
     */
    // public void backward(int speed)
    public void backward()
    {
    //    speed = adjustSpeed(speed);
    //    if (speed == MIN_SPEED)
    //    {
        //    brake();
    //    }
    //    else
    //    {
           forwardPin.low();
           backwardPin.high();
        //    speedPin.setPwm(speed);
    //    }
    }
    
    /**
     * Moves motor backward
     */
    public void brake()
    {
       forwardPin.low();
       backwardPin.low();
    //    speedPin.setPwm(MIN_SPEED);
    }
    
    /**
     * If the speed is equal or lower than the SLOWEST or equal to or higher than the FASTEST,
     * then set the min and max speed of the motor. 
     */
    // private int adjustSpeed(int speed)
    // {
    //     int result = speed;
    //     if (speed >= FASTEST)
    //     {
    //         result = MAX_SPEED;
    //     }
    //     else if (speed <= SLOWEST)
    //     {
    //         result = MIN_SPEED;
    //     }
    //     return result;
    // }
    
    // public static enum MotorType 
    // {
    //     LEFT, RIGHT
    // };
}