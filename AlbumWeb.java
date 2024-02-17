import java.util.HashMap;

public class AlbumWeb extends Album {

	private int codigoPromocional;
	private boolean codUsado;

	public AlbumWeb(int CodId,int CodPromocional) {
		super(CodId);
		this.codigoPromocional=CodPromocional;
		this.premio="camiseta";
		this.pegadas=new HashMap<Integer,Figurita>();
	}


	public int getCodigoPromocional() {
		return codigoPromocional;
	}


	public void setCodigoPromocional(int codigoPromocional) {
		this.codigoPromocional = codigoPromocional;
	}


	public boolean isCodUsado() {
		return codUsado;
	}


	public void setCodUsado(boolean codUsado) {
		this.codUsado = codUsado;
	}
	
	
	@Override
	public HashMap<Integer, Figurita> getPegadas() {
		return pegadas;
	}

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
	
	
	public String getPremio(){
		return this.premio;
	}
}
