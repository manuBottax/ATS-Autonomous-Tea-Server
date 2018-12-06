import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;

import java.io.IOException;

public class SerialReader {

	public SerialReader() throws InterruptedException, NumberFormatException {


		// create an instance of the serial communications class
		final Serial serial = SerialFactory.createInstance();

		// create and register the serial data listener
		serial.addListener(new SerialDataListener()
        {
            @Override
            public void dataReceived(SerialDataEvent event)
            {
                    String data = event.getData();

                    System.out.println("Arduino dice: " + data);
                    System.out.println();
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
}