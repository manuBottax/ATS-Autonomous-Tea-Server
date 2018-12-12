public class LocationController
{

    SerialReader serialReader = new SerialReader();
    // FollowPathController followPathController = new FollowPathController();
   
    public LocationController () {}

    //TODO: fare una enum di tutte le locations
    public String getLocation() {
        String color = this.serialReader.getLastColor();
        color = color.replace("\n", "").replace("\r", "");
        System.out.println("Checking location for color:" + color + ":");
        switch(color) {
            case "RED": 
                System.out.println("I am at the bar !");
                return "BAR";
            case "BLUE": 
                System.out.println("I am at the charge station !");
                return "CHARGE STATION";
            case "GREEN": 
                System.out.println("I am to the client !");
                return "CLIENT'S TABLE";
            default:
                System.out.println("Color not handled : " + color);
                return "UNKNOWN";
        }
    }

}