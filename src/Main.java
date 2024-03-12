import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try {
            // Création d'une nouvelle connexion à la base de données
            Connexion connexion = new Connexion("ece_shopping", "root", "");
            //u.validerPanier();
            /*
            String requete = "UPDATE livre SET stock = 3 WHERE identifiant = 1;";
            connexion.executeUpdate(requete);
            requete = "UPDATE livre SET stock = 3 WHERE identifiant = 2;";
            connexion.executeUpdate(requete);
            requete = "SELECT * FROM client WHERE id = 1;";
            */

            connexion.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}