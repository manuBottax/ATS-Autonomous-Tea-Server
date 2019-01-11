import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;

import java.io.IOException;

public class SerialReader {

    private String currentColor = "WHITE";

	public SerialReader() {

		// create an instance of the serial communications class
		final Serial serial = SerialFactory.createInstance();

		// create and register the serial data listener
		serial.addListener(new SerialDataListener()
        {
            @Override
            public void dataReceived(SerialDataEvent event)
            {
                    String data = event.getData();
                    if (! data.equals(currentColor)){
                        System.out.println("Color detected: " + data);
                        currentColor = data;
                    }
            }            
        });

		try {
            // open the default serial port provided on the GPIO header
            String port = "/dev/ttyACM0";
            int br = 9600;
            System.out.println("Opening port [" + port + "] @ " + br + " br" );

            serial.open(port, br);

			System.out.println("-> Serial port is opened, listening");
            
		} catch (SerialPortException ex) {
			System.out.println(" ==>> SERIAL SETUP FAILED : " + ex.getMessage());
			return;
		}
	}

    public String getLastColor() {
        this.currentColor = null;

        while( this.currentColor == null) {
            try {
                Thread.sleep(100);
            } catch( InterruptedException ex) { }
        }
        return this.currentColor;
    }
}