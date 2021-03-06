import java.io.IOException;
import java.io.InterruptedIOException;

public class ObstacleAvoidanceController extends Thread{

    private static final int MIN_DISTANCE_ALLOWED = 20;
    private boolean obstacle = false;
    private Sonar sonar;
    private FollowPathController pathController;

    public ObstacleAvoidanceController() {
        this.sonar = new Sonar();
        this.pathController = new FollowPathController();
    }

    @Override
    public void run() {
        try {
            while (true) {
                boolean obs = hasObstacle();
                if ( this.obstacle != obs) { 
                    this.obstacle = obs;
                    pathController.updateObstacleState(this.obstacle);
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean hasObstacle() {
        float distance = this.sonar.measureDistance(); 
        System.out.println("distance: " + distance);
        if (distance <= MIN_DISTANCE_ALLOWED ) {
            System.out.println("Obstacle, stop ! ");
            return true;
        } else {
            return false;
        }
    }

    public void startMoving() {
        this.pathController.start();
    }

    public boolean isStarted() {
        return this.pathController.isStarted();
    }
}