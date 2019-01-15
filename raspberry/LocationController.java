public class LocationController
{

    private SerialReader serialReader = new SerialReader();
   
    public LocationController () {}

    public String getLocation() {
        String color = this.serialReader.getLastColor();
        color = color.replace("\n", "").replace("\r", "");
        System.out.println("Checking location for color:" + color + ":");
        switch(color) {
            case "R": 
                System.out.println("I am at the bar !");
                return "BAR";
            case "B": 
                System.out.println("I am at the charge station !");
                return "CHARGE STATION";
            case "G": 
                System.out.println("I am to the client !");
                return "CLIENT'S TABLE";
            default:
                System.out.println("Color not handled : " + color);
                return "UNKNOWN";
        }
    }

}