package DAO;

public class DAOconfig {
    private static final String USERNAME = "root"; //TODO: alterar
    private static final String PASSWORD = "sobral21"; //TODO: alterar
    private static final String DATABASE = "rasbet";
    private static final String DRIVER = "jdbc:mysql";
    static final String URL = DRIVER+"://localhost:3306/"+DATABASE;
    static final String CREDENTIALS = "?user="+USERNAME+"&password="+PASSWORD;
}
