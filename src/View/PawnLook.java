package View;

import boardifier.model.GameElement;
import boardifier.view.ConsoleColor;
import boardifier.view.ElementLook;
import model.TablutPawn;

public class PawnLook extends ElementLook {

    public PawnLook(GameElement element) {
        super(element, 1, 1);
    }

    protected void render() {

        TablutPawn pawn = (TablutPawn)element;
        shape[0][0] = pawn.getColor() + ConsoleColor.WHITE_BACKGROUND + pawn.getNumber() + ConsoleColor.RESET;
    }
}