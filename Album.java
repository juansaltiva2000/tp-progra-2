import java.util.HashMap;

public abstract class Album {
	protected int codigoID;
	protected String premio;
	protected boolean estaCompleto;
	protected HashMap<Integer,Figurita> pegadas;
	
	
	
	
	public Album(int codigoID) {
		super();
		this.codigoID = codigoID;
		this.estaCompleto=false;
		pegadas = new HashMap<Integer,Figurita>();
	}


	protected abstract HashMap<Integer, Figurita> getPegadas();

	protected abstract boolean DevolverCompletoPais(String pais);


	protected boolean estaCompleto() {
			int figTotal=0;
			for(int i : pegadas.keySet()) {
				figTotal++;
				}
			return figTotal>200 && figTotal<284;
	}


	abstract protected String getPremio();


	
	

	


	
}
