import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;


public class AlbumDelMundial implements IAlbumDelMundial {
	
	private HashMap<Integer,Usuario>usuario;
	private Fabrica f = new  Fabrica();
	

	public AlbumDelMundial() { //constructor// 
		super();
		usuario = new HashMap<Integer,Usuario>();
	}
	
	
	@Override
	public int registrarParticipante(int dni, String nombre, String tipoAlbum) {
		if(!usuario.containsKey(dni) && tipoAlbum(tipoAlbum)) {
			usuario.put(dni,new Usuario(dni,nombre,tipoAlbum));
			return dni;
			}else{
		 throw new RuntimeException("el usuario ya esta Registrado o el tipo de album es incorrecto");
		}
	}

	@Override
	public void comprarFiguritas(int dni) {
		if(EstaRegistrado(dni)) {
				List<Figurita> sobre = f.generarSobre();
				for(Figurita fig : sobre) {
				usuario.get(dni).agregarFig(fig);
				}
			}else {
		throw new RuntimeException("el participante no esta Registrado"); 
			}
	}
	@Override
	public void comprarFiguritasTop10(int dni) {
		if(EstaRegistrado(dni)) {
			if(usuario.get(dni).getAlbum() instanceof AlbumExtendido) {
				AlbumExtendido album = (AlbumExtendido)usuario.get(dni).getAlbum();
				List<FiguritaTop10> sobre = f.generarSobreTop10();
				for(FiguritaTop10 figu : sobre) {
					if(!album.top10.containsKey(figu.getCodID())) {
						album.top10.put(figu.getCodID(),figu);
					}
				}
				
			} throw new RuntimeException("el tipo de album no es de tipo Extendido");
		} new RuntimeException("el usuario no esta registrado");
	}

	@Override
	public void comprarFiguritasConCodigoPromocional(int dni) {
		
		if(EstaRegistrado(dni)) {   /// pregunta si contiene la clave dni
			if(usuario.get(dni).getAlbum() instanceof AlbumWeb){	// pregunta si es de tipo album web
				AlbumWeb album = (AlbumWeb)usuario.get(dni).getAlbum(); // castea
				if(!album.isCodUsado()) { // if el codigo no fue utilizado agrega las figuritas
					List<Figurita> sobre = f.generarSobre();
					for(Figurita fig : sobre){ // genera el sobre y lo recorre
						usuario.get(dni).agregarFig(fig); // agrega fig
						}
					album.setCodUsado(true);
					}else {
						throw new RuntimeException("el codigo promocional ya fue utilizado");
					}
				}else {
			 throw new RuntimeException("el album de este usuario no es de tipo AlbumWeb");
			}
		}else {
			throw new RuntimeException("no esta registrado");
		}
		}

	@Override
	public List<String> pegarFiguritas(int dni) {
		List<String> listaFig = new ArrayList<String>();
		if(EstaRegistrado(dni)) {
		   for(Figurita i :usuario.get(dni).getFiguritasDelJug()) {
			   if(!usuario.get(dni).getAlbum().getPegadas().containsKey(i.getNumeroID())) {
				   usuario.get(dni).getAlbum().pegadas.put(i.getNumeroID(),new Figurita(i.getNumeroID(), i.getValorBase(), i.getNombrePais(), i.getNumeroJugador()));
				   listaFig.add(i.getNumeroJugador()  +" "+ i.getNombrePais());
			   }else{
				   usuario.get(dni).guardarFiguritasRepetidas(i);
			   }
		   }
		   return listaFig; 
		}
		else {
			throw new RuntimeException("no esta registrado");
		}
	}

	@Override
	public boolean llenoAlbum(int dni) {
		if(EstaRegistrado(dni)) {
			return usuario.get(dni).getAlbum().estaCompleto();
		}else{
			throw new RuntimeException();
		}
	}


	@Override/// 
	public String aplicarSorteoInstantaneo(int dni) {
		// TODO Auto-generated method stub
		String mensaje ="el album ya fue usado";
			if (EstaRegistrado(dni)){
				if(tieneAlbumTradicional(dni)) {
					AlbumTradicional album = (AlbumTradicional)usuario.get(dni).getAlbum();
					if(album.isUsoCodigo()==false) {
						 String premio = f.generarPremiosParaSorteoInstantaneo()[f.random(0, 3)];
						return premio;	
						}
					else {
							throw new RuntimeException("el codigo ya fue usado");
						}
					}
				else {
					throw new RuntimeException("no tiene album Tradicional");
				}
			}else {
				throw new RuntimeException("no esta Registrado;");
		}
			
	}
	
	private boolean tieneAlbumTradicional(int dni) {
		if( usuario.get(dni).getAlbum() instanceof AlbumTradicional) {
			return true;
		}else{ 
			return false;
		}
	}


	@Override
	public int buscarFiguritaRepetida(int dni) {
		// TODO Auto-generated method stub
		if(EstaRegistrado(dni)) {
			if(usuario.get(dni).getRepetidas().isEmpty()){
				return -1;
			}
			for(int i :usuario.get(dni).getRepetidas().keySet()) {
				return i;
			}
		}
		return 0;
	}

	@Override
	public boolean intercambiar(int dni, int codFigurita) {
		if(!EstaRegistrado(dni)){
			throw new RuntimeException();
			}
		else if(usuario.get(dni).getAlbum().pegadas.isEmpty()) {
			throw new RuntimeException();
		}/////recorro usuarios//////
		pegarFiguritas(dni);
		Figurita fig_a_cambiar = usuario.get(dni).getRepetidas().get(codFigurita); 
		for(int i :usuario.keySet()){
			if(dni!=i && tieneMismoTipoAlbum(dni,i)) {
				pegarFiguritas(i);
				if(tieneRepetidas(i)) {
					Figurita rep =traerUnaRepetida(i);
					if(usuario.get(dni).getAlbum().pegadas.containsKey(rep.getNumeroID())
							||usuario.get(dni).getRepetidas().containsKey(rep.getNumeroID())) {
						throw new RuntimeException("el usuario ya cuanta con esta figurita");
					}else {
						usuario.get(dni).getAlbum().pegadas.put(rep.getNumeroID(), rep);
						usuario.get(i).getAlbum().pegadas.put(fig_a_cambiar.getNumeroID(), fig_a_cambiar);
						usuario.get(dni).getRepetidas().remove(fig_a_cambiar.getNumeroID());
						usuario.get(i).getRepetidas().remove(rep.getNumeroID());
						return true;
					}
				}else {
					return true;
				}
			}
		}
		
	return false;
	}
		

	private boolean tieneMismoTipoAlbum(int dni, int i) {
		return usuario.get(dni).getAlbum().getClass().equals(usuario.get(i).getAlbum().getClass());
	}

	@Override
	public boolean intercambiarUnaFiguritaRepetida(int dni) {
		// TODO Auto-generated method stub
		if(!EstaRegistrado(dni)) {
			throw new RuntimeException("el participante no esta registrado");
			}
		else {
				pegarFiguritas(dni);
				if(usuario.get(dni).getAlbum().getPegadas().isEmpty()) {
					return false;
				}
				else {
					if(!usuario.get(dni).getRepetidas().isEmpty()) {
					Figurita fig=traerUnaRepetida(dni);
					return intercambiar(dni,fig.getNumeroID());
					}
					else {
						return true;
					}
				}
			}
		}
	
	
	private Figurita traerUnaRepetida(int dni) {
		int i=0;
		for(int fig : usuario.get(dni).getRepetidas().keySet()) {
			i=fig;
		}
		return usuario.get(dni).getRepetidas().get(i);
		
	}


	private boolean tieneRepetidas(int dni) {
		return !usuario.get(dni).getRepetidas().isEmpty();
	}
	

	@Override
	public String darNombre(int dni) {
		String nombre;
		if(EstaRegistrado(dni)) {
			nombre= usuario.get(dni).getNombre();
			return nombre;
		}else {
			throw new RuntimeException("el dni no esta registrado");
		}
	}

	@Override
	public String darPremio(int dni) {
		if(EstaRegistrado(dni)) {
			if(usuario.get(dni).getAlbum().estaCompleto()) {
				return usuario.get(dni).getAlbum().premio;
			}else{
				throw new RuntimeException("no tiene completo el album");
			}
		}else{
			throw new RuntimeException("no esta registrado");
		}
	}
		

	@Override
	public String listadoDeGanadores() {
		String cadena = new String ();
		for(int i:usuario.keySet()) {
			int cantFig=0;
			for(int j: usuario.get(i).getAlbum().pegadas.keySet()) {
				cantFig=cantFig+1;
			}
				if(cantFig>220) {
					cadena= "nombre" + " " +  usuario.get(i).getNombre()+ " " + "premio" + " " + llenoAlbum(i)+ " "; 
				}
			}return cadena;
		}
	

	@Override
	public List<String> participantesQueCompletaronElPais(String nombrePais) {
		ArrayList<String> lista = new ArrayList<String>();
		for (int u:usuario.keySet()) {
			if(usuario.get(u).getAlbum().DevolverCompletoPais(nombrePais)) {
			lista.add(usuario.get(u).getNombre());
			}
		}
		return lista;
	}
	
	private boolean tipoAlbum(String tipoAlbum) {
		// TODO Auto-generated method stub
		if(tipoAlbum.equals("Web")) {
			return true;
		}
		else if(tipoAlbum.equals("Tradicional")) {
			return true;
		}
		else if(tipoAlbum.equals("Extendido")) {
			return true;
		}
		new Exception("el tipo no es valido");
		return false;
	}
 
	private boolean EstaRegistrado(int dni) {
		if(usuario.containsKey(dni)) {
			return true;
		}else {
		throw new RuntimeException("no esta Registrado");
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Album Del Mundial";
	}
}
