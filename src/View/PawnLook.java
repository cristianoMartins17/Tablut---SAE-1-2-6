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
        shape[0][0] = pawn.getColor() + ConsoleColor.WHITE_BACKGROUND + pawn.getNumber() + ConsoleColor.RESET;
    }
}