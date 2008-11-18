package proje.lib;

import irfan.grafik.Koordinat;
import irfan.grafik.Nokta;
import irfan.grafik.Ucgen;

public class KoordinatSistemi extends Koordinat {

	private Ucgen ucgen = new Ucgen();
	private Nokta nokta = new Nokta();
	
	private Ucgen yeniUcgen = new Ucgen();

	private double aci = 0.0;
	
	public KoordinatSistemi() { 
		
		setBilgi("Üçgenin 1. noktasını belirleyin.");
	}
	
	public Ucgen getUcgen() { return ucgen; }
	public Nokta getNokta() { return nokta; }

	public Ucgen getYeniUcgen() { return yeniUcgen; }
	
	public void mouseClicked(Nokta nokta) {

		if (isGecerliTiklama(nokta)) tiklandi(nokta);
	}
	
	private void tiklandi(Nokta nokta) {
		
		Nokta noktaKoordinat = nokta.getAsKoordinat(getOrjin());
		
		if (getTiklamaSayisi() == 1) {
			
			ucgen.setA(noktaKoordinat);
			ucgen.getA().setCizilsin(true);
			
			setBilgi("Üçgenin 2. noktasını belirleyin.");
			
			incTiklamaSayisi();
		} else if (getTiklamaSayisi() == 2) {
			
			ucgen.setB(noktaKoordinat);
			ucgen.getB().setCizilsin(true);
			
			setBilgi("Üçgenin 3. noktasını belirleyin.");
			
			incTiklamaSayisi();
		} else if (getTiklamaSayisi() == 3) {
			
			ucgen.setC(noktaKoordinat);
			ucgen.getC().setCizilsin(true);
			
			ucgen.setCizilsin(true);
			
			setBilgi("Üçgenin dönerken referans alacağı noktasıyı belirleyin.");
			
			incTiklamaSayisi();
		} else if (getTiklamaSayisi() == 4) {
			
			yeniUcgen.setABCFromUcgen(ucgen);
			
			yeniUcgen.getA().setCizilsin(true);
			yeniUcgen.getB().setCizilsin(true);
			yeniUcgen.getC().setCizilsin(true);
			
			yeniUcgen.setCizilsin(true);
			
			this.nokta.setLocation(noktaKoordinat);
			this.nokta.setCizilsin(true);
			
			incTiklamaSayisi();
		}
	}
	
	public void setAci(double aci) {
		
		this.aci = aci;
	}
	
	public double getAci() { return aci; }

	public void yeniUcgeniCevir() {
		
		yeniUcgen.setABCFromUcgen(ucgen);
		
		yeniUcgen.konumDegistir(nokta, new Nokta(0, 0));
		yeniUcgen.cevir(getAci());
		yeniUcgen.konumDegistir(new Nokta(0, 0), nokta);
	} 

}
