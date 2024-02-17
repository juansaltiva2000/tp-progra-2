import java.util.ArrayList;
import java.util.HashMap;

public class AlbumTradicional extends Album{
	private int codigoSorteo;
	private boolean usoCodigo;

	public AlbumTradicional(int CodId,int ParaSorteo){
		super(CodId);
		this.codigoSorteo = ParaSorteo;
		this.usoCodigo = false;
		this.estaCompleto=false;
		this.premio="pelota";
		this.pegadas=new HashMap<Integer,Figurita>();
	}

	
	
	public boolean isUsoCodigo() {
		return usoCodigo;
	}



	public void setUsoCodigo(boolean usoCodigo) {
		this.usoCodigo = usoCodigo;
	}



	public int getCodigoSorteo() {
		return codigoSorteo;
	}


	public void setCodigoSorteo(int codigoSorteo) {
		this.codigoSorteo = codigoSorteo;
	}


	@Override
	public HashMap<Integer, Figurita> getPegadas() {
		return pegadas;
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


	@Override
	protected boolean estaCompleto() {
		// TODO Auto-generated method stub
		return super.estaCompleto();
	}

	public String getPremio(){
		return this.premio;
	}

	
	
      


}
