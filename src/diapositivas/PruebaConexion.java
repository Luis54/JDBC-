package diapositivas;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class PruebaConexion {
	
	public static final String DB_URL = "jdbc:sqlite:/home/matinal/sqlite/Libros";
	public static final String DRIVER = "org.sqlite.JDBC";
	
	public static void main(String[] args) {
		Connection conexion = null;
		try {
			Class.forName(DRIVER);
			//vamos a permitir la integridad referencial en sqlite
			//debemos establecer PRAGMA foreign_key=ON
			SQLiteConfig configuracion = new SQLiteConfig();
			configuracion.enforceForeignKeys(true);
			conexion = DriverManager.getConnection(DB_URL,configuracion.toProperties());
			System.out.println("Conectado a la BD.......");
			//Thread.sleep(3000); //simulo operaciones con la BD (3s en espera)
			//vamos a consultar la tabla libro,que tiene entre otros campos
			//nombre,autor,idcategoria
			//creamos la consulta como String 
			String sql = "select * from librosCategorias";
			//Creamos un objeto statement
			Statement statement = conexion.createStatement();
			//recibimos los datos de la consulta en objeto resultsSet
			ResultSet resultset = statement.executeQuery(sql);
			
			//recorremos el resulset
			while(resultset.next()){
				String nombre = resultset.getString("nombre");
				String autor = resultset.getString("autor");
				String categoria = resultset.getString(4);//es posible pasar el numero de columna empezando por el 1
				System.out.printf("%35s %15s %15s%n",nombre,autor,categoria);
			}
			sql = "update usuario set nombre='Alberto' where id=2";
			int filasafectadas = statement.executeUpdate(sql);
			System.out.println("Numero de filas afectadas: "+filasafectadas);
			
			//vamos a borrar el usuario de id = 6
			sql = "delete from usuario where id=6";
			int filasBorradas = statement.executeUpdate(sql);
			System.out.println("Filas Borradas: "+filasBorradas);
			//vamos a usar PreparedStatement,vamos a consultar los 5 primeros libros
			sql="select * from libro where id=?";
			PreparedStatement praparedStatemente = conexion.prepareStatement(sql);
			for (int i = 0; i < 6; i++) {
				praparedStatemente.setInt(1, i);
				ResultSet resulset = praparedStatemente.executeQuery();
				while(resulset.next()){
					String nombre = resulset.getString("nombre");
					String editorial = resulset.getString("editorial");
					System.out.printf("%15s %15s%n",nombre,editorial);
				}
			}
			//vamos agrupar sentencias sql usuando Batch
			sql="insert into usuario (nombre,dni) values (?,?)";
			PreparedStatement prepareStatemente = conexion.prepareStatement(sql);
			prepareStatemente.setString(1, "Luis");
			prepareStatemente.setString(2, "dni1");
			prepareStatemente.addBatch();
			sql="update usuario set dni=? where nombre=?";
			PreparedStatement prepareStatemente2 = conexion.prepareStatement(sql);
			prepareStatemente2.setString(1, "202020");
			prepareStatemente2.setString(2, "Luis");
			prepareStatemente2.addBatch();
			int[] affectedRecocrods = prepareStatemente2.executeBatch();
			System.out.println(affectedRecocrods.length);
			
			
			//vamos a hacer transacciones
			//primero preparamos la base de datos
			conexion.setAutoCommit(false);
			sql="insert into usuario (nombre,dni) values ('joaquin','dni3')";
			String sql2="insert into usuario (nombre,dni) values ('Luis','dni4')";
			statement.execute(sql);
			statement.execute(sql2);
			conexion.commit();

			System.out.println("Desconectado la BD");
			conexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	

}
