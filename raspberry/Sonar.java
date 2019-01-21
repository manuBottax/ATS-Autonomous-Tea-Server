import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;


public class Sonar
{

    // private final static float SOUND_SPEED = 340.27f;  // speed of sound in m/s at 15°C at sea level
    private final static float SOUND_SPEED = 343.21f;  // speed of sound in m/s at 20°C at sea level
    
    private final static int TRIG_DURATION_IN_MICROS = 10; // trigger duration of 10 micro s
    

    private final static int TIMEOUT = 2100;

    private GpioController gpio;
    private GpioPinDigitalOutput trigger;
    private GpioPinDigitalInput echo;
     
    public Sonar()
    {
        gpio = GpioFactory.getInstance();
        
        //pin 38
        this.trigger = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, PinState.LOW);
        //pin 37
        this.echo = gpio.provisionDigitalInputPin(RaspiPin.GPIO_25, PinPullResistance.PULL_DOWN);
        
        this.trigger.setShutdownOptions(true);
    }

    public float measureDistance() {
        // sense environment for obstacle
        this.triggerSensor();
        this.waitForSignal();
        //compute time
        long duration = this.measureSignal();
        
        return (duration * SOUND_SPEED) / 10000; // return distance in cm 
    }

    /**
     * Put a high on the trig pin for TRIG_DURATION_IN_MICROS
     */
    private void triggerSensor() {
        try {
            this.trigger.high();
            Thread.sleep( 0, TRIG_DURATION_IN_MICROS * 1000 );
            this.trigger.low();
        } catch (InterruptedException ex) {
            System.err.println( "Interrupt during trigger" );
        }
    }
    
    /**
     * Wait for a high on the echo pin
     */
    private void waitForSignal(){
        int countdown = TIMEOUT;
        
        while( this.echo.isLow() && countdown > 0 ) {
            countdown--;
        }
        
        if( countdown <= 0 ) {
            System.err.println( "Timeout waiting for signal start" );
        }
    }
    
    /**
     * @return the duration of the signal in micro seconds
     */
    private long measureSignal() {
        int countdown = TIMEOUT;
        long start = System.nanoTime();
        while( this.echo.isHigh() && countdown > 0 ) {
            countdown--;
        }
        long end = System.nanoTime();
        
        if( countdown <= 0 ) {
            System.err.println( "Timeout waiting for signal end" );
        }
        
        return (long)Math.ceil( ( end - start ) / 1000.0 );  // Return micro seconds
    }


}