import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Usuario {
	private String nombreUsuario;
	private int dni;
	private Album album;
	private HashMap <Integer,Figurita> figuritasRepetidas;
	private ArrayList <Figurita> figuritasDelJug;
	
	
	public Usuario(int dni,String nombreUsuario,String tipoAlbum) {
		super();
		this.dni = dni;
		this.nombreUsuario = nombreUsuario;
		this.figuritasRepetidas = new HashMap<Integer,Figurita>();
		this.figuritasDelJug = new ArrayList<Figurita>();
		crearAlbum(tipoAlbum);
		
	}
	
	private void crearAlbum(String tipoAlbum2) {
		// TODO Auto-generated method stub
		try {
		if(tipoAlbum2 == "Tradicional") {
			album  = new AlbumTradicional(dni+20, dni);// para darle un codigounico al album le sumo 20 al dni
			}
			else if (tipoAlbum2 == "Web") {
			album = new AlbumWeb(dni+20,dni); //paso el dni como codPromocional y para el sorteo
			} 
			else if (tipoAlbum2 == "Extendido") {
			album  = new AlbumExtendido(dni+20);
			}
		}catch(RuntimeException ex) {
			System.out.println("el tipo de album no existe" + dni);
		}
			
	}

	public void guardarFiguritasRepetidas(Figurita fig) {
		figuritasRepetidas.put(fig.getNumeroID(), fig);
	}
	
	
	public void agregarFig(Figurita f){   
		figuritasDelJug.add(f);
	}
	
	public ArrayList<Figurita> getFiguritasDelJug() {
		return figuritasDelJug;
	}

	boolean intercambiar(int numero) {
		return false;
	}
	
	
	boolean completoAlbum() {
		int cont=0;
		for(int i :album.pegadas.keySet()) {
			cont++;
		}
		return cont==12*32;
	}
	
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public HashMap<Integer, Figurita> getRepetidas() {
		return figuritasRepetidas;
	}
	
	public String getNombre() {
		return nombreUsuario;
	}
}
