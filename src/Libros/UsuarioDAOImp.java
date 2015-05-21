package Libros;


import java.sql.Connection;
import java.sql.PreparedStatement;
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
		List<UsuarioDTO> lista = getUsuarios();
		if(lista.contains(u))System.out.println("El usuario " + u.getNombre() + " ya esta en la  lista");
		else{
		String sql = "insert into usuario (nombre,dni) values (?,?)";
		try {
			PreparedStatement p = conexion.prepareStatement(sql);
			p.setString(1, u.getNombre());
			p.setString(2, u.getDni());
			p.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Usuario "+u.getDni()+ " añadido correctamtne");
	}	
		
}

	@Override
	public void eliminarUsuario(UsuarioDTO u) {
		// TODO Auto-generated method stub
		String sql = "delete from usuario where dni=?";
		try {
			PreparedStatement s = conexion.prepareStatement(sql);
			s.setString(1, u.getDni());
			s.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarUsuario(UsuarioDTO u) {
		// TODO Auto-generated method stub
		String sql = "update usuario set nombre=? where dni=?";
		try {
			PreparedStatement s = conexion.prepareStatement(sql);
			s.setString(1, u.getNombre());
			s.setString(2, u.getDni());
			s.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
