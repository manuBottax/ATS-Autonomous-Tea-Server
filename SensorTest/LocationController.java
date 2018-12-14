public class LocationController
{

    private SerialReader serialReader = new SerialReader();
    // FollowPathController followPathController = new FollowPathController();
   
    public LocationController () {}

    //TODO: fare una enum di tutte le locations
    public String getLocation() {
        String color = this.serialReader.getLastColor();
        color = color.replace("\n", "").replace("\r", "");
        // String[] s = color.split("\\(");

        // if (s.length >= 2 &&s[0] != null && s[1] != null){
        // System.out.println("measured rgb: " + s[1]);
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
        // } else {
            // return "UNKNOWN";
        // }
    }

}