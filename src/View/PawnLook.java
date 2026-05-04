package View;

import boardifier.model.GameElement;
import boardifier.view.ConsoleColor;
import boardifier.view.ElementLook;
import model.Pawn;

public class PawnLook extends ElementLook {

    public PawnLook(GameElement element) {
        super(element, 1, 1);
    }

    protected void render() {

        Pawn pawn = (Pawn)element;
        if (pawn.getColor() == Pawn.BLACK) {
            shape[0][0] = ConsoleColor.WHITE + ConsoleColor.BLACK_BACKGROUND + pawn.getNumber() + ConsoleColor.RESET;
        }
        else {
            shape[0][0] = ConsoleColor.BLACK + ConsoleColor.RED_BACKGROUND + pawn.getNumber() + ConsoleColor.RESET;
        }
    }
}