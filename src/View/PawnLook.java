package View;

import boardifier.model.GameElement;
import boardifier.view.ConsoleColor;
import boardifier.view.ElementLook;
import model.TablutPawn;

public class PawnLook extends ElementLook {

    private static String[] backgrounds = new String[] {ConsoleColor.GREEN_BACKGROUND, ConsoleColor.YELLOW_BACKGROUND,  ConsoleColor.WHITE_BACKGROUND};
    private static char[] display = new char[] {'M', 'S', 'R'};
    public PawnLook(GameElement element) {
        super(element, 1, 1);
    }

    protected void render() {

        TablutPawn pawn = (TablutPawn)element;
        shape[0][0] =   ConsoleColor.BLACK +   backgrounds[pawn.getColor()] + display[pawn.getColor()]  + ConsoleColor.RESET;
    }
}