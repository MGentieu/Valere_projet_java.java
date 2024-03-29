import java.sql.*;
import java.util.ArrayList;

public class Connexion {


        /**
         * Attributs prives : connexion JDBC, statement, ordre requete et resultat
         * requete
         */
        private final Connection conn;
        private final Statement stmt;
        private ResultSet rset;
        private ResultSetMetaData rsetMeta;
        /**
         * ArrayList public pour les tables
         */
        public ArrayList<String> tables = new ArrayList<>();
        /**
         * ArrayList public pour les requêtes de sélection
         */
        public ArrayList<String> requetes = new ArrayList<>();
        /**
         * ArrayList public pour les requêtes de MAJ
         */
        public ArrayList<String> requetesMaj = new ArrayList<>();

        /**
         * Constructeur avec 3 paramètres : nom, login et password de la BDD locale
         *
         * @param nameDatabase
         * @param loginDatabase
         * @param passwordDatabase
         * @throws java.sql.SQLException
         * @throws java.lang.ClassNotFoundException
         */
        public Connexion(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException{
            // chargement driver "com.mysql.jdbc.Driver"
            Class.forName("com.mysql.cj.jdbc.Driver");

            String urlDatabase = "jdbc:mysql://localhost:3306/"+ nameDatabase;
            //création d'une connexion JDBC à la base
            conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

            // création d'un ordre SQL (statement)
            stmt = conn.createStatement();
        }
    public  Statement getStmt(){
        return stmt;
    }
    public ResultSet getRset(){return rset;}
    public void setRset(ResultSet rset){
        this.rset = rset;
    }
    public ResultSetMetaData getRsetMeta(){
        return rsetMeta;
    }
    public void setRsetMeta(ResultSetMetaData rsetMeta){
        this.rsetMeta = rsetMeta;
    }
    /**
     * Méthode qui ajoute la table en parametre dans son ArrayList
     *
     * @param table
     */
    public void ajouterTable(String table) {
        tables.add(table);
    }

    /**
     * Méthode qui ajoute la requete de selection en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequete(String requete) {
        requetes.add(requete);
    }

    /**
     * Méthode qui ajoute la requete de MAJ en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequeteMaj(String requete) {
        requetesMaj.add(requete);
    }

    /**
     * Méthode qui retourne l'ArrayList des champs de la table en parametre
     *
     * @param table
     * @return
     * @throws java.sql.SQLException
     */

    public ArrayList remplirChampsTable(String table) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery("select * from " + table);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        String champs = "";
        // Ajouter tous les champs du resultat dans l'ArrayList
        for (int i = 0; i < nbColonne; i++) {
            champs = champs + " " + rsetMeta.getColumnLabel(i + 1);
        }

        // ajouter un "\n" à la ligne des champs
        champs = champs + "\n";

        // ajouter les champs de la ligne dans l'ArrayList
        liste.add(champs);

        // Retourner l'ArrayList
        return liste;
    }

    public void executeUpdate(String requeteMaj) throws SQLException {
        stmt.executeUpdate(requeteMaj);
    }
    public void close() {
        try {
            if (rset != null) {
                rset.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
