import java.io.Serializable;

public class registrierenBean implements Serializable {
	private static final long servialVersionUID = 1L;
	
	private String titel;
	private String nname;
	private String vname;
	private String email;
	private String plz;
	private String straﬂe;
	private String hausnummer;
	private String anmerkung;
	
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getStraﬂe() {
		return straﬂe;
	}
	public void setStraﬂe(String straﬂe) {
		this.straﬂe = straﬂe;
	}
	public String getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
	public String getAnmerkung() {
		return anmerkung;
	}
	public void setAnmerkung(String anmerkung) {
		this.anmerkung = anmerkung;
	}
}