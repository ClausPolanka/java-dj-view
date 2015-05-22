import controller.api.Controller;
import controller.impl.Swing_View_Beat_Controller;
import model.api.Beat;
import model.impl.Default_Beat;

public class Main {
    public static void main(String[] args) {
        Beat beat = new Default_Beat();
        Controller controller = new Swing_View_Beat_Controller(beat);
    }
}
