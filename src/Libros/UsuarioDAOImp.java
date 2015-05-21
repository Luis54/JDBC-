package Libros;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImp implements UsuarioDAO{
	
	Connection conexion = Conexion.getConexion("Libros");
	
	@Override
	public List<UsuarioDTO> getUsuarios() {
		UsuarioDTO usuario = null;
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		//sentencia sql
		String sql="select * from usuario";
		
		try {
			Statement s = conexion.createStatement();
			ResultSet resultado = s.executeQuery(sql);
			while(resultado.next()){
				String nombre = resultado.getString("nombre");
				String dni = resultado.getString("dni");
				usuario = new UsuarioDTO(nombre, dni);
				lista.add(usuario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void addUsuario(UsuarioDTO u) {
		// TODO Auto-generated method stub
		getUsuarios().add(u);
	}

	@Override
	public void eliminarUsuario(UsuarioDTO u) {
		// TODO Auto-generated method stub
		getUsuarios().remove(u);
	}

	@Override
	public void actualizarUsuario(UsuarioDTO u) {
		// TODO Auto-generated method stub
		
	}

}
