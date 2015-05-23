import controller.api.Controller;
import controller.impl.Swing_View_Beat_Controller;
import controller.impl.Swing_View_Heart_Controller;
import model.api.Beat;
import model.impl.Default_Beat;
import model.impl.Default_Heart;

public class Main {
    public static void main(String[] args) {
//        Beat beat = new Default_Beat();
//        Controller controller = new Swing_View_Beat_Controller(beat);

        Controller controller = new Swing_View_Heart_Controller(new Default_Heart());
    }
}
