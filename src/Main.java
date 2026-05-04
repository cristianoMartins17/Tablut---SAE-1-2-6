import model.Plateau;

public class Main{

    public static void main(String[] args){
        Plateau p1 = new Plateau();
        System.out.println(p1);

        //choix d'une case vide comme case de départ
        System.out.println(p1.deplacerPion(1, 1, 2, 3));

        //déplacement vertical valide (noir de (0,3) vers (2,3))
        System.out.println(p1.deplacerPion(0, 3, 2, 3));

        //déplacement en diagonale (doit être refusé)
        System.out.println(p1.deplacerPion(0, 3, 2, 5));

        //chemin bloqué
        System.out.println(p1.deplacerPion(0, 4, 3, 4));

        //hors du plateau
        System.out.println(p1.deplacerPion(0, 3, -1, 3));

        //déplacement horizontal valide
        System.out.println(p1.deplacerPion(5, 0, 5, 3));
    }

}