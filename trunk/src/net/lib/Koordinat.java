package net.lib;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Koordinat extends JPanel {

	private static final long serialVersionUID = -1068827460511809112L;

	private final int kareAraligi = 8;
	
	private final int sol = kareAraligi;
	private final int ust = kareAraligi;
	private int sag;
	private int alt;

	private String bilgi = "Üçgenin 1. noktasını belirleyin.";
	
	private Point u1 = new Point();
	private Point u2 = new Point();
	private Point u3 = new Point();
	
	private Point p1 = new Point();
	
	private boolean ucgenCizilsin = false;
	private boolean yeniUcgenCizilsin = false;
	
	private boolean u1Cizilsin = false;
	private boolean u2Cizilsin = false;
	private boolean u3Cizilsin = false;
	
	private boolean p1Cizilsin = false;
	
	private int tiklamaSayisi = 1;
	
	private double aci = 0;
	
	public Koordinat() {
		
		//repaint();
	}
	
	
	@Override
	public void paint(Graphics g) {

		koordinatSifirla(g);
		koordinatCiz(g);
		ucgenCiz(g);
	}
	
	private void koordinatSifirla(Graphics g) {
		
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	private void koordinatCiz(Graphics g) {
		
		g.setColor(Color.BLACK);
		
		g.drawLine(
				getOrtaNokta().x, 
				ust, 
				getOrtaNokta().x, 
				alt);
		
		g.drawLine(
				sol, 
				getOrtaNokta().y, 
				sag, 
				getOrtaNokta().y);
		
		int yatayKareSayisi = (sag - sol) / kareAraligi;
		int dikeyKareSayisi = (alt - ust) / kareAraligi;
		int kareGenisligi = 1;
		
		for (int i = 0; i <= yatayKareSayisi; i++) {
			
			for (int j = 0; j <= dikeyKareSayisi; j++) {
				
				g.fillRect(
						i * kareAraligi + kareAraligi - kareGenisligi / 2, 
						j * kareAraligi + kareAraligi - kareGenisligi / 2, 
						kareGenisligi, 
						kareGenisligi);
				
				
			}
		}
	}

	private Point getOrtaNokta() {
		
		return new Point((sag - sol) / 2 + sol, (alt - ust) / 2 + ust);
	}


	private void ucgenCiz(Graphics g) {
		
		if (ucgenCizilsin) {
			
			doCizgiCizFromKoordinat(u1, u2, g, Color.RED);
			doCizgiCizFromKoordinat(u2, u3, g, Color.RED);
			doCizgiCizFromKoordinat(u3, u1, g, Color.RED);
		}
		
		if (u1Cizilsin) doNoktaCizFromKoordinat(u1, g, Color.RED);
		if (u2Cizilsin) doNoktaCizFromKoordinat(u2, g, Color.RED);
		if (u3Cizilsin) doNoktaCizFromKoordinat(u3, g, Color.RED);
		
		if (p1Cizilsin) doNoktaCizFromKoordinat(p1, g, Color.BLUE);
		
		if (yeniUcgenCizilsin) {
			
			yeniUcgeniCiz(g, Color.ORANGE);
		}
	}

	public void componentResized(ComponentEvent e) {
		
		sag = ((int) ((getWidth() - sol) / kareAraligi)) * kareAraligi;
		alt = ((int) ((getHeight() - ust) / kareAraligi)) * kareAraligi;
		
		repaint();
	}

	public void mouseClicked(MouseEvent e) {

		if (isGecerliTiklama(e.getPoint())) {
			
			Point koordinatNokta = getKoordinatNoktaFromOrjinalNokta(e.getPoint());
			
			if (tiklamaSayisi == 1) {
				
				u1.setLocation(koordinatNokta);
				tiklamaSayisi++;
				u1Cizilsin = true;
				
				bilgi = "Üçgenin 2. noktasını belirleyin.";
			} else if (tiklamaSayisi == 2) {
				
				u2.setLocation(koordinatNokta);
				tiklamaSayisi++;
				u2Cizilsin = true;
				
				bilgi = "Üçgenin 3. noktasını belirleyin.";
			} else if (tiklamaSayisi == 3) {
				
				u3.setLocation(koordinatNokta);
				tiklamaSayisi++;
				u3Cizilsin = true;
				ucgenCizilsin = true;
				
				bilgi = "Üçgenin dönerken referans alacağı noktasıyı belirleyin.";				
			} else if (tiklamaSayisi == 4) {
				
				p1.setLocation(koordinatNokta);
				tiklamaSayisi++;
				p1Cizilsin = true;
				
				yeniUcgenCizilsin = true;
				
				bilgi = "Üçgen dönmeye hazır :)";
			}
			
			repaint();
		}
	}

	private boolean isGecerliTiklama(Point p) {
		
		return ((p.x > sol) && (p.x < sag) && (p.y > ust) && (p.y < alt));
	}

	public String getBilgi() {
		
		return bilgi;
	}

	private Point getKoordinatNoktaFromOrjinalNokta(Point p) {
		
		return new Point(p.x - getOrtaNokta().x, getOrtaNokta().y - p.y);
	}
	
	private Point getOrjinalNoktaFromKoordinatNokta(Point p) {
		
		return new Point(p.x + getOrtaNokta().x, getOrtaNokta().y - p.y);
	}
	
	private void doNoktaCizFromKoordinat(Point p, Graphics g, Color c) {
		
		doNoktaCizFromOrjinal(p, g, c);
	}
	
	private void doNoktaCizFromOrjinal(Point p, Graphics g, Color c) {
		
		g.setColor(c);
		
		g.fillRect(getOrjinalNoktaFromKoordinatNokta(p).x - 2, getOrjinalNoktaFromKoordinatNokta(p).y - 2, 5, 5);
	}
	
	private void doCizgiCizFromKoordinat(Point p1, Point p2, Graphics g, Color c) {
		
		doCizgiCizFromOrjinal(p1, p2, g, c);
	}


	private void doCizgiCizFromOrjinal(Point p1, Point p2, Graphics g, Color c) {
		
		g.setColor(c);
		
		Point p1Orjinal = getOrjinalNoktaFromKoordinatNokta(p1);
		Point p2Orjinal = getOrjinalNoktaFromKoordinatNokta(p2);
		
		g.drawLine(p1Orjinal.x, p1Orjinal.y, p2Orjinal.x, p2Orjinal.y);
	}


	public void donder(double value) {
		
		aci = value * 2 * Math.PI / 360;
		
		bilgi = "Açı: " + aci;
		
		repaint();
	}
	
	private void yeniUcgeniCiz(Graphics g, Color c) {
		
		g.setColor(c);
		
		Point u1Yeni = new Point();
		Point u2Yeni = new Point();
		Point u3Yeni = new Point();
		
		u1Yeni.setLocation(u1.x - p1.x, u1.y - p1.y);
		u1Yeni.setLocation(
				u1Yeni.x * Math.cos(aci) - u1Yeni.y * Math.sin(aci), 
				u1Yeni.x * Math.sin(aci) + u1Yeni.y * Math.cos(aci));
		
		u1Yeni.setLocation(u1Yeni.x + p1.x, u1Yeni.y + p1.y);
		
		u2Yeni.setLocation(u2.x - p1.x, u2.y - p1.y);
		u2Yeni.setLocation(
				u2Yeni.x * Math.cos(aci) - u2Yeni.y * Math.sin(aci), 
				u2Yeni.x * Math.sin(aci) + u2Yeni.y * Math.cos(aci));
		
		u2Yeni.setLocation(u2Yeni.x + p1.x, u2Yeni.y + p1.y);
		
		u3Yeni.setLocation(u3.x - p1.x, u3.y - p1.y);
		u3Yeni.setLocation(
				u3Yeni.x * Math.cos(aci) - u3Yeni.y * Math.sin(aci), 
				u3Yeni.x * Math.sin(aci) + u3Yeni.y * Math.cos(aci));
		
		u3Yeni.setLocation(u3Yeni.x + p1.x, u3Yeni.y + p1.y);
		
		doNoktaCizFromKoordinat(u1Yeni, g, c);
		doNoktaCizFromKoordinat(u2Yeni, g, c);
		doNoktaCizFromKoordinat(u3Yeni, g, c);
		
		doCizgiCizFromKoordinat(u1Yeni, u2Yeni, g, c);
		doCizgiCizFromKoordinat(u2Yeni, u3Yeni, g, c);
		doCizgiCizFromKoordinat(u3Yeni, u1Yeni, g, c);
	}
}
