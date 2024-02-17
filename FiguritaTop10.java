
public class FiguritaTop10  {
	private Fabrica f = new Fabrica();
	private int codID;
	private int valorBase;
	private String premioTop10;
	private String sede;
	private String balonRecibido;
	private String paisJug;
	
	public FiguritaTop10(int numero, int valorBase,String sede,String paisjugador,String Balonrecibido) {
		super();
		this.codID= numero;
		this.balonRecibido=Balonrecibido;
		this.sede=sede;
		this.valorBase=valorBase;
		this.paisJug=paisjugador;
		
	}

	public int getCodID() {
		return codID;
	}

	public void setCodID(int codID) {
		this.codID = codID;
	}

	


	
	
	
	
}

