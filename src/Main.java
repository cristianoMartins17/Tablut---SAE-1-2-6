import boardifier.control.ActionFactory;
import boardifier.control.ActionPlayer;
import boardifier.control.Controller;
import boardifier.control.StageFactory;
import boardifier.model.*;
import boardifier.model.action.ActionList;
import boardifier.view.View;
import control.TablutController;
import model.TablutStageFactory;

public class Main {
    public static void main(String[] args) {

        StageFactory.registerModelAndView("tablut", "model.TablutStageModel", "View.TablutStageLook");



        Model model = new Model();


        View view = new View(model);


        TablutController controller = new TablutController(model, view);
        controller.setFirstStageName("tablut");

        model.addHumanPlayer("Joueur 1");
        model.addHumanPlayer("Joueur 2");


        controller.setFirstStageName("tablut");
        try {
            controller.startGame();
            controller.stageLoop();
        } catch (GameException e) {
            System.out.println(e.getMessage());
        }



    }
}

