public enum Directions 
{
    RIGHT("right"),
    LEFT("left");

    private String direction;

    public Directions(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return this.direction;
    }
}