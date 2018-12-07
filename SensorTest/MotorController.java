

public class MotorController
{

    private boolean isDxBlack;
    private boolean isSxBlack;
   
    public MotorController ()
    {
        
    }

    //TODO: ENUM per DX e SX 
    public void setLineState(String name, boolean isBlack){
        if (name == "Dx") {
            this.isDxBlack = isBlack;
        } else if (name == "Sx"){
            this.isSxBlack = isBlack;
        }

        this.checkStatus();
    }

    private void checkStatus() {
        if ( this.isDxBlack && this.isSxBlack ){
            System.out.println(" STOOOOOOOOOOOOOP ! ");
        } else if ( this.isSxBlack) {
            System.out.println(" Going Dx ! ");
        } else if ( this.isDxBlack) {
            System.out.println(" Going Sx ! ");
        } else {
            System.out.println(" Going Forward ! ");
        }
    }


    

}