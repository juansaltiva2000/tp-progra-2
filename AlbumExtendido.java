import java.util.HashMap;

public class AlbumExtendido extends Album {
	HashMap<Integer,FiguritaTop10> top10;


	public AlbumExtendido(int codID) {
		// TODO Auto-generated constructor stub
		super(codID);
		this.pegadas = new HashMap<Integer,Figurita>();
		this.top10= new HashMap<Integer,FiguritaTop10>();
		this.premio= "pelota Y viaje";
	}


	public HashMap<Integer, FiguritaTop10> getTop10() {
		return top10;
	}


	public void setTop10(HashMap<Integer, FiguritaTop10> top10) {
		this.top10 = top10;
	}
	
	@Override
	public HashMap<Integer, Figurita> getPegadas() {
		return pegadas;
	}
	
	public String getPremio(){
		return this.premio;
	}


	@Override
	public boolean DevolverCompletoPais(String nombrePais) {
		// TODO Auto-generated method stub
		int cont=0;
		for(Figurita f :pegadas.values()) {
			if(f.getNombrePais()== nombrePais) {
				cont++;
			}
		}return cont == 12;
	}
	
	protected boolean estaCompleto() {
		// TODO Auto-generated method stub
		return super.estaCompleto();
	}
	

}
