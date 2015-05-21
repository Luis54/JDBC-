package Libros;

import java.util.List;

public class TestBD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UsuarioDAOImp u = new UsuarioDAOImp();
		List<UsuarioDTO> lista = u.getUsuarios();
		System.out.println(lista);
		UsuarioDTO u1 = new UsuarioDTO("Luis", "15f13185X");
		UsuarioDTO u2 = new UsuarioDTO("Cambiado", "15f13185X");
		u.addUsuario(u1);
		u.eliminarUsuario(u1);
		u.actualizarUsuario(u2);
		Conexion.desconectar();
	}

}
