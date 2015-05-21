package diapositivas;

public class TestSingleton {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//creamos objetos Singleton:
		Singleton s = Singleton.getOBJETO_UNICO();
		Singleton s2 = Singleton.getOBJETO_UNICO();

		System.out.println(s.equals(s2)); //ambos apuntan a la misma posicion de memoria,solo hay un objeto en realidad
	}

}
class Singleton{
	private static Singleton OBJETO_UNICO = new Singleton();
	//constructor privado
	
	private Singleton(){}

	public static Singleton getOBJETO_UNICO() {
		return OBJETO_UNICO;
	}
	
	
}
