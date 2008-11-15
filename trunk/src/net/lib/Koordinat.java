package net.lib;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

public class Koordinat extends JPanel {

	private static final long serialVersionUID = -1068827460511809112L;

	private final int kareAraligi = 16;
	
	private final int sol = kareAraligi;
	private final int ust = kareAraligi;
	private int sag;
	private int alt;

	private String bilgi = "Simetrisi çizilecek üçgen için 1. noktayı çiz.";
	
	private Point u1 = new Point();
	private Point u2 = new Point();
	private Point u3 = new Point();
	
	private Point p1 = new Point();
	
	private boolean ucgenCizilsin = false;
	private boolean noktaCizilsin = false;
	private boolean yeniUcgenCizilsin = false;
	
	private boolean u1Cizilsin = false;
	private boolean u2Cizilsin = false;
	private boolean u3Cizilsin = false;
	
	private boolean p1Cizilsin = false;
	
	private int tiklamaSayisi = 1;
	
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
		
		g.setColor(Color.RED);
		
		if (ucgenCizilsin) {
			
			g.drawLine(u1.x, u1.y, u2.x, u2.y);
			g.drawLine(u2.x, u2.y, u3.x, u3.y);
			g.drawLine(u3.x, u3.y, u1.x, u1.y);
			
		}
		
		if (u1Cizilsin) g.fillRect(u1.x - 2, u1.y - 2, 5, 5);
		if (u2Cizilsin) g.fillRect(u2.x - 2, u2.y - 2, 5, 5);
		if (u3Cizilsin) g.fillRect(u3.x - 2, u3.y - 2, 5, 5);
		
		g.setColor(Color.BLUE);
		
		if (p1Cizilsin) g.fillRect(p1.x - 2, p1.y - 2, 5, 5);
	}

	public void componentResized(ComponentEvent e) {
		
		sag = ((int) ((getWidth() - sol) / kareAraligi)) * kareAraligi;
		alt = ((int) ((getHeight() - ust) / kareAraligi)) * kareAraligi;
		
		repaint();
	}

	public void mouseClicked(MouseEvent e) {

		if (isGecerliTiklama(e.getPoint())) {
			
			if (tiklamaSayisi == 1) {
				
				u1.setLocation(e.getPoint());
				tiklamaSayisi++;
				u1Cizilsin = true;
			} else if (tiklamaSayisi == 2) {
				
				u2.setLocation(e.getPoint());
				tiklamaSayisi++;
				u2Cizilsin = true;
			} else if (tiklamaSayisi == 3) {
				
				u3.setLocation(e.getPoint());
				tiklamaSayisi++;
				u3Cizilsin = true;
				ucgenCizilsin = true;
			} else if (tiklamaSayisi == 4) {
				
				p1.setLocation(e.getPoint());
				tiklamaSayisi++;
				p1Cizilsin = true;
			}
			
			bilgi = "Tıklama sayısı: " + tiklamaSayisi;
			
			repaint();
		}
	}

	private boolean isGecerliTiklama(Point p) {
		
		return ((p.x > sol) && (p.x < sag) && (p.y > ust) && (p.y < alt));
	}

	public String getBilgi() {
		
		return bilgi;
	}

	
}
