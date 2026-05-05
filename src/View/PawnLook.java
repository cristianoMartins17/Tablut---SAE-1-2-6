package View;

import boardifier.model.GameElement;
import boardifier.view.ConsoleColor;
import boardifier.view.ElementLook;
import model.TablutPawn;

import java.util.HashMap;
import java.util.Map;

public class PawnLook extends ElementLook {

    private static String[] colors = new String[] {ConsoleColor.YELLOW_BACKGROUND, ConsoleColor.GREEN_BACKGROUND, ConsoleColor.WHITE_BACKGROUND};
    private static char[] display = new char[] {'M', 'S', 'R'};
    public PawnLook(GameElement element) {
        super(element, 1, 1);
    }

    protected void render() {

        TablutPawn pawn = (TablutPawn)element;
        shape[0][0] =     colors[pawn.getColor()] + display[pawn.getColor()]  + ConsoleColor.RESET;
    }
}