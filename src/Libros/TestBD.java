package Libros;

import java.util.List;

public class TestBD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UsuarioDAOImp u = new UsuarioDAOImp();
		List<UsuarioDTO> lista = u.getUsuarios();
		System.out.println(lista);
		Conexion.desconectar();
	}

}
