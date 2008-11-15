package net;

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

import net.lib.Koordinat;


public class UcgenDondurme {

	private static final long serialVersionUID = 1125126325770918600L;

	private static Koordinat koordinat;
	private static JSlider slider;
	private static JLabel bilgiPaneli;
	
	public static void main(String[] args) {
		
		createAndShowGUI();
	}
	
	private static void createAndShowGUI() {
		
		JFrame frame = new JFrame("Üçgen Döndermece");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new BorderLayout());
		
		koordinat = new Koordinat();
		
		koordinat.addMouseListener(new MouseListener() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				
				koordinat.mouseClicked(e);
				
				bilgiPaneli.setText(koordinat.getBilgi());
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
				
				koordinat.componentResized(e);
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
				
				koordinat.donder((double)(slider.getValue()));
			}
		});
		
		bilgiPaneli = new JLabel(koordinat.getBilgi(), JLabel.CENTER);
		
		frame.add(koordinat, BorderLayout.CENTER);
		frame.add(slider, BorderLayout.NORTH);
		frame.add(bilgiPaneli, BorderLayout.SOUTH);
		
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
}
