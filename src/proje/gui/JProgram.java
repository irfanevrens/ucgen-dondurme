package proje.gui;

import java.awt.BorderLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JProgram extends JFrame {

	private static final long serialVersionUID = -5555442476170808347L;

	private JKoordinatSistemi koordinat;
	private JSlider slider;
	private JLabel bilgiPaneli;
	
	public JProgram() {
		
		super("Doğruya Göre Simetri");
		
		setLayout(new BorderLayout());
		
		koordinat = new JKoordinatSistemi();
		
		koordinat.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				koordinat.mouseClicked(e);
				
				bilgiPaneli.setText(koordinat.getKoordinatSistemi().getBilgi());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		koordinat.addComponentListener(new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent e) {
				
				koordinat.ebatlarDegisti();
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		slider = new JSlider(0, 360, 0);
		
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				
				koordinat.ucgeniCevir((double)(slider.getValue()));
			}
		});
		
		bilgiPaneli = new JLabel(koordinat.getKoordinatSistemi().getBilgi(), JLabel.CENTER);
		
		add(koordinat, BorderLayout.CENTER);
		add(slider, BorderLayout.NORTH);
		add(bilgiPaneli, BorderLayout.SOUTH);
		
		pack();
	}
}
