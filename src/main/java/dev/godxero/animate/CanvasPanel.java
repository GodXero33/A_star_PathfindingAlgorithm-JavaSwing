package dev.godxero.animate;

import javax.swing.*;
import java.awt.*;

public class CanvasPanel extends JPanel {
	private final Drawer drawer = new Drawer(this);

	@Override
	protected void paintComponent (Graphics g) {
		super.paintComponent(g);
		drawer.draw(g);
	}

	public Drawer getDrawer () {
		return drawer;
	}
}
