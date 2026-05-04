package model;

public class Plateau {
    public static final int TAILLE = 9;
    public char[][] plateau;




    public Plateau() {
        this.plateau = new char[TAILLE][TAILLE];
        initPlateau();
    }

    public void initPlateau() {
        //cases vides
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                this.plateau[i][j] = '.';
            }
        }

        //roi
        this.plateau[4][4] = 'R';

        //coin haut gauche
        this.plateau[0][0] = 'X';
        //coin haut droite
        this.plateau[0][8] = 'X';
        //coin bas gauche
        this.plateau[8][0] = 'X';
        //coin bas droite
        this.plateau[8][8] = 'X';

        //pièces noires
        for (int j = 3; j <= 5; j++) {
            this.plateau[0][j] = 'N';
        }
        this.plateau[1][4] = 'N';

        for (int i = 3; i <= 5; i++) {
            this.plateau[i][0] = 'N';
        }
        this.plateau[4][1] = 'N';

        for (int i = 3; i <= 5; i++) {
            this.plateau[i][8] = 'N';

        }
        this.plateau[4][7] = 'N';

        for (int j = 3; j <= 5; j++) {
            this.plateau[8][j] = 'N';
        }
        this.plateau[7][4] = 'N';

        //pièce blanche
        for (int i = 2; i <= 3; i++) {
            this.plateau[i][4] = 'B';
        }

        for (int j = 2; j <= 3; j++) {
            this.plateau[4][j] = 'B';
        }

        for (int j = 5; j <= 6; j++) {
            this.plateau[4][j] = 'B';
        }

        for (int i = 5; i <= 6; i++) {
            this.plateau[i][4] = 'B';
        }
    }

    public boolean estDansLePlateau(int i, int j){
        return 0 <= i && i < TAILLE && 0 <= j && j < TAILLE;
    }

    public String deplacerPion(int iDepart, int jDepart, int iArriver, int jArriver){
        int direction;
        if(this.plateau[iDepart][jDepart] == '.'){
            return "Case de départ choisie ne contient pas de pion. Vérifiez les coordonées s'il vous plait \n";
        }
        if (estDansLePlateau(iArriver, jArriver) && estDansLePlateau(iDepart, jDepart)) { //cases dans le plateau
            if (iDepart == iArriver || jDepart == jArriver) { //deplacement uniquement horizontal / vertical

                if (jDepart == jArriver) { //verifier que les cases soit vide entre départ et arriver

                    if (iArriver > iDepart) { //direction soit = 1 ou =-1 pour le sens du déplacement
                        direction = 1;
                    } else {
                        direction = -1;
                    }

                    for (int i = iDepart + direction; i != iArriver; i += direction) {
                        if (this.plateau[i][jDepart] != '.') {
                            return "Chemin de la case d'arriver à la case de départ n'est pas vide. Vérifiez les coordonées s'il vous plait \n";
                        }
                    }
                }

                if (iDepart == iArriver) { //verifier que les cases soit vide entre départ et arriver

                    if (jArriver > jDepart) { //direction soit = 1 ou =-1 pour le sens du déplacement
                        direction = 1;
                    } else {
                        direction = -1;
                    }

                    for (int j = jDepart + direction; j != jArriver; j += direction) {
                        if (this.plateau[iDepart][j] != '.') {
                            return "Chemin n'est pas vide de la case d'arriver à la case de départ. Vérifiez les coordonées s'il vous plait \n";
                        }
                    }
                }

                if (this.plateau[iArriver][jArriver] == '.') { //case arriver vide?
                    this.plateau[iArriver][jArriver] = this.plateau[iDepart][jDepart];
                    this.plateau[iDepart][jDepart] = '.';

                    System.out.println(this);

                    return "\nDéplacement réussi \n";
                }
                return "Case d'arriver n'est pas vide. Vérifiez les coordonées s'il vous plait \n";
            }
            return "Déplacement illégal. Les pions se déplace uniquement de manière horizontal ou vertical \n";
        }
        return "Cases de départ ou d'arriver pas dans le plateau. Vérifiez les coordonées s'il vous plait \n";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("  0 1 2 3 4 5 6 7 8\n");

        for (int i = 0; i < TAILLE; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < TAILLE; j++) {
                sb.append(plateau[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
