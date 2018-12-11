

public class MotorController
{

    private boolean isDxBlack;
    private boolean isSxBlack;

    Motor rightWheel = new Motor("right");
    Motor leftWheel = new Motor("left");
   
    public MotorController ()
    {

    }

    public void start() {
        System.out.println(" Going Forward ! ");
        rightWheel.forward();
        leftWheel.forward();
    }

    //TODO: ENUM per DX e SX 
    public void setLineState(String name, boolean isBlack){
        if (name == "Dx") {
            // System.out.println("la desta è nera !");
            this.isDxBlack = isBlack;
        } else if (name == "Sx"){
            // System.out.println("la sinistra è nera !");
            this.isSxBlack = isBlack;
        }

        this.checkStatus();
    }

    // private void checkStatus() {
    //     // System.out.println("la desta è nera?" + this.isDxBlack);
    //     // System.out.println("la sinistra è nera?" + this.isSxBlack);
    //     if ( this.isDxBlack && this.isSxBlack ){
    //         System.out.println("Stop !");
    //         rightWheel.brake();
    //         leftWheel.brake();
    //     } else if ( this.isSxBlack) {
    //         System.out.println("la sinistra è nera quindi Going Dx ! ");
    //         leftWheel.forward();
    //         rightWheel.brake();
    //     } else if ( this.isDxBlack) {
    //         System.out.println("la destra è nera quindi Going Sx ! ");
    //         rightWheel.forward();
    //         leftWheel.brake();
    //     } else {
    //         System.out.println(" non vedo nulla di nero quindi Going Forward ! ");
    //         rightWheel.forward();
    //         leftWheel.forward();
    //     }
    // }


 

    private void checkStatus() {
        if ( this.isDxBlack && this.isSxBlack ){
            System.out.println("Stop !");
            rightWheel.brake();
            leftWheel.brake();
        } else if ( this.isSxBlack) {
            System.out.println(" Going Sx ! ");
            rightWheel.forward();
            leftWheel.brake();


        } else if ( this.isDxBlack) {
            System.out.println(" Going Dx ! ");
            leftWheel.forward();
            rightWheel.brake();
        } else {
            System.out.println(" Going Forward ! ");
            rightWheel.forward();
            leftWheel.forward();
        }
    }




    

}