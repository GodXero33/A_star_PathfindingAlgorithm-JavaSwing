package dev.godxero.animate;

import java.awt.*;

public class Drawer {
	private int x = 50;
	private int y = 50;
	private final CanvasPanel canvas;

	public Drawer (CanvasPanel canvas) {
		this.canvas = canvas;
	}

	public void draw (Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, 50, 50);
	}

	public void update () {
		int width = canvas.getWidth();
		int height = canvas.getHeight();

		x += 2;

		if (x > width) x = -50;
	}
}
