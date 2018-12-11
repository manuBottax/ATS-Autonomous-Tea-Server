public enum Directions 
{
    RIGHT("right"),
    LEFT("left");

    private String direction;

    Directions(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return this.direction;
    }
}