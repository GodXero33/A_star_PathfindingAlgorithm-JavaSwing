package dev.godxero.animate;

import javax.swing.*;

public class Animator {
	private final CanvasPanel canvas;

	public Animator (CanvasPanel canvas) {
		this.canvas = canvas;
	}

	public void start () {
		this.animate();
	}

	private void animate () {
		canvas.getDrawer().update();
		canvas.repaint();

		try {
			Thread.sleep(16);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}

		SwingUtilities.invokeLater(this::animate);
	}
}
