import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Fabrica {
	private Random random;
	private String[] premiosInstantaneos;
	private String[] paisesParticipantes;
	private int lugaresPorPais;
	private String[] listadoDeMundialesTop10;
	private Map<String, String[]> balonYPaisPorMundialTop10;
	private Map<String, Integer> ranking;

	public Fabrica() {
		random = new Random(System.currentTimeMillis());
		lugaresPorPais = 12;
		paisesParticipantes = generarPaisesClasificados();
		listadoDeMundialesTop10 = generarListadoDeMundiales();
		balonYPaisPorMundialTop10 = generarPaisesPorMundial();
		ranking = generarRanking();
		premiosInstantaneos = generarPremiosParaSorteoInstantaneo();
	
	}
	
	////////////////////////////////////////////////////////////////////////
	// NOTA: Deben implementar los siguientes metodos segun su modelo     //
	//       Aprovechando la informacion referida al mundial facilitada   //
	//       por la catedra.                                              //
	//       Solamente deberian deberan usar las variables de clases      //
	//       y el metodo "calcularValorBase" para saber que valor base    //
	//       tendrÃƒÂ¡ una figurita en particula.                            //
	////////////////////////////////////////////////////////////////////////

	List<Figurita> generarSobre() {
		ArrayList<Figurita> sobre = new ArrayList<Figurita>();
		int j=0;
		while(j<4) {
			String pais = paisesParticipantes[random(0,32)];
		    int numJug =random(0,13);
		    int ID=generarNumID(pais,numJug);
		    int valorBase= generarValorBase(pais,numJug);
			sobre.add(new Figurita(ID,valorBase,pais,numJug)); // va agregando figuritas al sobre
			j++;
		}
		return sobre; /// retorna el sobre con 4 figuritas
	}		

	

	

	public  int random(int min,int max) {
		  return (int) (Math.random() * (max - min) + min);
		}

	List<FiguritaTop10> generarSobreTop10() {
		int i =0;
		List <FiguritaTop10> sobre = new ArrayList<FiguritaTop10>();
		while(i<4) {
			String sede = generarListadoDeMundiales()[random(0,10)];
			int pos=random(0,1);
			String balon = balonGanado(pos);
			String paisjug = generarPaisesPorMundial().get(sede)[pos];
			sobre.add(new FiguritaTop10(i, i, sede,paisjug,balon)) ;
			i++;
		}
		return sobre;
	}


	private String balonGanado(int pos) {
		if(pos==0) {
			return "oro";
		}return "plata";
	}

	///////////////////////////////////////////////////////
	///////////// METODOS FACILITADOS POR LA CATEDRA //////
	///////////////////////////////////////////////////////
	
	// Dado el pais y numero de jugador de una figurita calcula
	// cual es su valor base simbobilo.
	public int generarNumID(String pais, int numero) {
		return (ranking.get(pais)*12)+numero;   // el multiplicar x 12 asegura que el numero sea unico;
	}
	
	
	public int generarValorBase(String pais, int numero) {
		if(ranking.get(pais).intValue()<10){
			return 100+numero;
		}
		else{
			return 50+numero;
		}   
	}

	public String[] generarPremiosParaSorteoInstantaneo() {
		return new String[]{
				"Una pelota","1 Sobre Gratis", "Una camiseta"
			};
	}

	public String[] generarPaisesClasificados() {
		return new String[]{ 
				"Alemania",   "Arabia SaudÃƒÂ­",   "Argentina",          "Australia", 
				"Brasil",     "BÃƒÂ©lgica",        "CamerÃƒÂºn",            "CanadÃƒÂ¡", 
				"Costa Rica", "Croacia",        "Dinamarca",          "Ecuador",
				"EspaÃƒÂ±a",     "Estados Unidos", "Francia",            "Gales",
				"Ghana",      "Inglaterra",     "IrÃƒÂ¡n",               "JapÃƒÂ³n", 
				"Marruecos",  "MÃƒÂ©xico",         "PaÃƒÂ­ses Bajos",       "Polonia",
				"Portugal",   "Qatar",          "RepÃƒÂºblica de Corea", "Senegal", 
				"Serbia",     "Suiza",          "TÃƒÂºnez",              "Uruguay" 
			};
	}

	public String[] generarListadoDeMundiales() {
		return new String[] { 
				"EspaÃƒÂ±a '82",    "MÃƒÂ©xico '86", "Italia '90",  "Estados Unidos '94",
				"Francia '98",   "Corea del Sur y JapÃƒÂ³n '02", "Alemania '06", 
				"SudÃƒÂ¡frica '10", "Brasil '14", "Rusia '18" };
	}

	public Map<String, String[]> generarPaisesPorMundial() {
		Map<String, String[]> ret = new HashMap<>();
		ret.put("EspaÃƒÂ±a '82", new String[] { "Italia", "Brasil" });
		ret.put("MÃƒÂ©xico '86",  	 	new String[] { "Argentina", "Alemania" });
		ret.put("Italia '90", new String[] { "Italia", "Alemania" });
		ret.put("Estados Unidos '94", new String[] { "Brasil", "Italia" });
		ret.put("Francia '98", new String[] { "Brasil", "Croacia" });
		ret.put("Corea del Sur y JapÃƒÂ³n '02", new String[] { "Alemania", "Brasil" });
		ret.put("Alemania '06", new String[] { "Francia", "Italia" });
		ret.put("SudÃƒÂ¡frica '10", new String[] { "Uruguay", "PaÃƒÂ­ses Bajos" });
		ret.put("Brasil '14", new String[] { "Argentina", "Alemania" });
		ret.put("Rusia '18", new String[] { "Croacia", "BÃƒÂ©lgica" });
		return ret;
	}
	
	public Map<String, Integer> generarRanking() {
		Map<String, Integer> ret = new HashMap<>();
		ret.put("Brasil",1);
		ret.put("BÃƒÂ©lgica",2);
		ret.put("Argentina",3);
		ret.put("Francia",4);
		ret.put("Inglaterra",5);
		ret.put("Italia",6);
		ret.put("EspaÃƒÂ±a",7);
		ret.put("PaÃƒÂ­ses Bajos",8);
		ret.put("Portugal",9);
		ret.put("Dinamarca",10);
		ret.put("Alemania",11);
		ret.put("Croacia",12);
		ret.put("MÃƒÂ©xico",13);
		ret.put("Uruguay",14);
		ret.put("Suiza",15);
		ret.put("Estados Unidos",16);
		ret.put("Colombia",17);
		ret.put("Senegal",18);
		ret.put("Gales",19);
		ret.put("IrÃƒÂ¡n",20);
		ret.put("Serbia",21);
		ret.put("Marruecos",22);
		ret.put("PerÃƒÂº",23);
		ret.put("JapÃƒÂ³n",24);
		ret.put("Suecia",25);
		ret.put("Polonia",26);
		ret.put("Ucrania",27);
		ret.put("RepÃƒÂºblica de Corea",28);
		ret.put("Chile",29);
		ret.put("TÃƒÂºnez",30);
		ret.put("Costa Rica",31);
		ret.put("Nigeria",32);
		ret.put("Rusia",33);
		ret.put("Austria",34);
		ret.put("RepÃƒÂºblica Checa",35);
		ret.put("HungrÃƒÂ­a",36);
		ret.put("Argelia",37);
		ret.put("Australia",38);
		ret.put("Egipto",39);
		ret.put("Escocia",40);
		ret.put("CanadÃƒÂ¡",41);
		ret.put("Noruega",42);
		ret.put("CamerÃƒÂºn",43);
		ret.put("Ecuador",44);
		ret.put("TurquÃƒÂ­a",45);
		ret.put("Mali",46);
		ret.put("Paraguay",47);
		ret.put("Costa de Marfil",48);
		ret.put("RepÃƒÂºblica de Irlanda",49);
		ret.put("Qatar",50);
		ret.put("Arabia SaudÃƒÂ­",51);
		ret.put("Grecia",52);
		ret.put("RumanÃƒÂ­a",53);
		ret.put("Burkina Faso",54);
		ret.put("Eslovaquia",55);
		ret.put("Finlandia",56);
		ret.put("Venezuela",57);
		ret.put("Bosnia y Herzegovina",58);
		ret.put("Irlanda del Norte",59);
		ret.put("PanamÃƒÂ¡",60);
		ret.put("Ghana",61);
		ret.put("Islandia",62);
		ret.put("Eslovenia",63);
		ret.put("Jamaica",64);
		ret.put("Macedonia del Norte",65);
		ret.put("Albania",66);
		ret.put("SudÃƒÂ¡frica",67);
		ret.put("Irak",68);
		ret.put("Montenegro",69);
		ret.put("Emiratos Ãƒï¿½rabes Unidos",70);
		ret.put("Cabo Verde",71);
		ret.put("Bulgaria",72);
		ret.put("RD del Congo",73);
		ret.put("El Salvador",74);
		ret.put("OmÃƒÂ¡n",75);
		ret.put("Israel",76);
		ret.put("UzbekistÃƒÂ¡n",77);
		ret.put("Georgia",78);
		ret.put("RP China",79);
		ret.put("Honduras",80);
		ret.put("GabÃƒÂ³n",81);
		ret.put("Bolivia",82);
		ret.put("Guinea",83);
		ret.put("Jordania",84);
		ret.put("BahrÃƒÂ©in",85);
		ret.put("CuraÃƒÂ§ao",86);
		ret.put("HaitÃƒÂ­",87);
		ret.put("Zambia",88);
		ret.put("Uganda",89);
		ret.put("Siria",90);
		ret.put("BenÃƒÂ­n",91);
		ret.put("Luxemburgo",92);
		ret.put("Armenia",93);
		ret.put("Palestina",94);
		ret.put("RepÃƒÂºblica Kirguisa",95);
		ret.put("Vietnam",96);
		ret.put("Bielorrusia",97);
		ret.put("Guinea Ecuatorial",98);
		ret.put("LÃƒÂ­bano",99);
		ret.put("Congo",100);
		ret.put("Kenia",101);
		ret.put("Madagascar",102);
		ret.put("Mauritania",103);
		ret.put("Trinidad y Tobago",104);
		ret.put("Nueva Zelanda",105);
		ret.put("India",106);
		ret.put("Kosovo",107);
		ret.put("TayikistÃƒÂ¡n",108);
		ret.put("Estonia",109);
		ret.put("Chipre",110);
		ret.put("Tailandia",111);
		ret.put("RDP de Corea",112);
		ret.put("KazajstÃƒÂ¡n",113);
		ret.put("Mozambique",114);
		ret.put("Namibia",115);
		ret.put("Guinea-BissÃƒÂ¡u",116);
		ret.put("Sierra Leona",117);
		ret.put("Guatemala",118);
		ret.put("Angola",119);
		ret.put("Libia",120);
		ret.put("NÃƒÂ­ger",121);
		ret.put("Islas Feroe",122);
		ret.put("AzerbaiyÃƒÂ¡n",123);
		ret.put("Malaui",124);
		ret.put("Zimbabue",125);
		ret.put("Gambia",126);
		ret.put("Togo",127);
		ret.put("SudÃƒÂ¡n",128);
		ret.put("Comoras",129);
		ret.put("Tanzania",130);
		ret.put("Antigua y Barbuda",131);
		ret.put("RepÃƒÂºblica Centroafricana",132);
		ret.put("Filipinas",133);
		ret.put("Letonia",134);
		ret.put("TurkmenistÃƒÂ¡n",135);
		ret.put("Islas SalomÃƒÂ³n",136);
		ret.put("Ruanda",137);
		ret.put("EtiopÃƒÂ­a",138);
		ret.put("Surinam",139);
		ret.put("San CristÃƒÂ³bal y Nieves",140);
		ret.put("Burundi",141);
		ret.put("Nicaragua",142);
		ret.put("Esuatini",143);
		ret.put("Lituania",144);
		ret.put("Hong Kong",145);
		ret.put("Malasia",146);
		ret.put("Lesoto",147);
		ret.put("Botsuana",148);
		ret.put("Kuwait",149);
		ret.put("Liberia",150);
		ret.put("Andorra",151);
		ret.put("Indonesia",152);
		ret.put("RepÃƒÂºblica Dominicana",153);
		ret.put("Maldivas",154);
		ret.put("Yemen",155);
		ret.put("AfganistÃƒÂ¡n",156);
		ret.put("Chinese Taipei",157);
		ret.put("Myanmar",158);
		ret.put("PapÃƒÂºa Nueva Guinea",159);
		ret.put("Singapur",160);
		ret.put("Nueva Caledonia",161);
		ret.put("TahitÃƒÂ­",162);
		ret.put("Fiyi",163);
		ret.put("Vanuatu",164);
		ret.put("SudÃƒÂ¡n del Sur",165);
		ret.put("Barbados",166);
		ret.put("Cuba",167);
		ret.put("Malta",168);
		ret.put("Bermudas",169);
		ret.put("Puerto Rico",170);
		ret.put("Guyana",171);
		ret.put("Santa LucÃƒÂ­a",172);
		ret.put("Granada",173);
		ret.put("Moldavia",174);
		ret.put("Nepal",175);
		ret.put("Belice",176);
		ret.put("Camboya",177);
		ret.put("San Vicente y las Granadinas",178);
		ret.put("Montserrat",179);
		ret.put("Mauricio",180);
		ret.put("Chad",181);
		ret.put("Macao",182);
		ret.put("Mongolia",183);
		ret.put("Dominica",184);
		ret.put("ButÃƒÂ¡n",185);
		ret.put("Santo TomÃƒÂ© y PrÃƒÂ­ncipe",186);
		ret.put("Laos",187);
		ret.put("Samoa Estadounidense",188);
		ret.put("Islas Cook",189);
		ret.put("BrunÃƒÂ©i Darussalam",190);
		ret.put("Samoa",191);
		ret.put("Bangladesh",192);
		ret.put("Yibuti",193);
		ret.put("PakistÃƒÂ¡n",194);
		ret.put("Islas CaimÃƒÂ¡n",195);
		ret.put("Liechtenstein",196);
		ret.put("Tonga",197);
		ret.put("Timor Oriental",198);
		ret.put("Seychelles",199);
		ret.put("Eritrea",200);
		ret.put("Aruba",201);
		ret.put("Bahamas",202);
		ret.put("Somalia",203);
		ret.put("Gibraltar",204);
		ret.put("Guam",205);
		ret.put("Turcas y Caicos",206);
		ret.put("Sri Lanka",207);
		ret.put("Islas VÃƒÂ­rgenes Estadounidenses",208);
		ret.put("Islas VÃƒÂ­rgenes BritÃƒÂ¡nicas",209);
		ret.put("Anguila",210);
		ret.put("San Marino",211);
		return ret;
	}

}
