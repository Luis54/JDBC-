package diapositivas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class Conexion {
	private static Connection conexion = null;
	private Conexion(){}
	
	public static Connection getConexion(String nombreB){
		String nombreBD = "jdbc:sqlite:"+nombreB;
		SQLiteConfig configuracion = new SQLiteConfig();
		configuracion.enforceForeignKeys(true);
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(nombreBD,configuracion.toProperties());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conexion;
	}
	public static void desconectar(){
		if(conexion !=null){
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
