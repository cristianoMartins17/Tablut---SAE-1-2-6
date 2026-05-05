import boardifier.control.Controller;
import boardifier.control.Logger;
import boardifier.model.GameException;
import boardifier.model.GameStageModel;
import boardifier.model.StageElementsFactory;
import boardifier.view.View;
import boardifier.control.StageFactory;
import boardifier.model.Model;
import model.TablutStageFactory;


public static void main(String[] args) {
// enregistrer le stage
    StageFactory.registerModelAndView("tablut", "model.TablutStageModel", "view.TablutStageView");

// créer le modèle global
    Model model = new Model();

// créer la vue
    View view = new View(model);

    Controller controller = new Controller(model, view) {
        public void stageLoop() {
            // vide pour l'instant
        }
    };

    controller.setFirstStageName("tablut");
    try {
        controller.startGame();
        controller.stageLoop();
    } catch (GameException e) {
        System.out.println(e.getMessage());
    }

}

