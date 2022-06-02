package BdJava;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConexaoPostgreJava {
	
	 private final String url = "jdbc:postgresql://localhost/BDlivrariaUniversitaria";
	 private final String user = "postgres";
	 private final String password = "123456";
	 Connection conn = null;

	 
	 private static final String QUERY = "select * from autor where id_autor =?";
	 private static final String SELECT_ALL_QUERY = "select * from autor";
	 private static final String SELECT_ALL_QUERY_LIVRO = "select * from livro";
	 private static final String SELECT_ALL_QUERY_CLIENTE ="select * from cliente";
	 private static final String QUERY_PEDIDOS = "select * from cliente where nm_cliente = 'Gilberto Ribeiro de Queiroz' or id_cliente = '1'";
	
	 
	 public Connection connect() {
	        
	        try {
	            conn = DriverManager.getConnection(url, user, password);

	            if (conn != null) {
	                System.out.println("Connected to the PostgreSQL server successfully.");
	            } else {
	                System.out.println("Failed to make connection!");
	            }
	            //versão do postgreeSQL
	            Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT VERSION()");
	            if (resultSet.next()) {
	            	System.out.println(resultSet.getString(1));
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }

	        return conn;
	        //conn.close();
	    }
	 
	    public void rLivro() {
	        // Step 1: Establishing a Connection
	        try {
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_QUERY_LIVRO);
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                int editar = rs.getInt("id_editar");
	                String name_autor = rs.getString("nm_autor");
	                System.out.println(editar + " - " + name_autor);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	    }

	    public void rCliente() {
	    	// Step 1: Establishing a Connection
	        try {
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_QUERY_CLIENTE);
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            //preparedStatement.setInt(1, 5);
	            
	            ResultSet rs = preparedStatement.executeQuery();
	            // Step 4: Process the ResultSet object.
	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                int idCliente = rs.getInt("id_cliente");
	                String name_autor = rs.getString("nm_cliente");
	                System.out.println(idCliente + " - " + name_autor);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	    }
	    public void pCliente() {
	        // Step 1: Establishing a Connection
	        try {
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = conn.prepareStatement(QUERY_PEDIDOS);
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                int idCliente = rs.getInt("id_cliente");
	                String name_cliente = rs.getString("nm_cliente");
	                System.out.println(idCliente + " - " + name_cliente);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	    }
	 
	    
	    public static void printSQLException(SQLException ex) {
	            for (Throwable e: ex) {
	                if (e instanceof SQLException) {
	                    e.printStackTrace(System.err);
	                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                    System.err.println("Message: " + e.getMessage());
	                    Throwable t = ex.getCause();
	                    while (t != null) {
	                        System.out.println("Cause: " + t);
	                        t = t.getCause();
	                    }
	                }
	            }
	        }
	    /**
	     * @param args the command line arguments
	     */
	    public static void main(String[] args) {
	    	
	    	Scanner ler = new Scanner (System.in);
	    	ConexaoPostgreJava app = new ConexaoPostgreJava();
	        app.connect();
	        /*System.out.println("\nRealizando o select na tabela Autor");
	        app.getAllUsers();
	        System.out.println("\nRealizando o select na tabela Autor pelo ID");
	        app.getUserById();*/
	        
	        
	        
	        int menu=0;
	        
	        do {
	        
	        System.out.println("------------");
	        System.out.println("    MENU    ");
	        System.out.println("------------");
	        System.out.println("1 - Pesquisar livro" + "\n2 - Pesquisar cliente" + "\n3 - Pesquisar os pedidos de um cliente" + "\n4 - Sair");
	        System.out.println("------------");
	        
	        menu = ler.nextInt();
	        
	        switch (menu) {
	        
	        case 1:
	        	System.out.println("\nSegue todos os livros listados: ");
	        	app.rLivro();
	        break;	
	        	
	        case 2:
	        	System.out.println("\nSegue todos os clientes listados ");
	        	app.rCliente();
	        	break;
	        	
	        case 3:
	        	System.out.println("\nDigite seu nome ou id ");
	        	app.pCliente();
	        	break;

	        default:
	        	System.out.println("Comando inválido. Tente novamente.");
	        
	        }	        
	    }
	        
	        while (menu !=4) ; 

	    }
	
	
}
