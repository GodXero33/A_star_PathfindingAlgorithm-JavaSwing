package dev.godxero.animate;

import javax.swing.*;

public class Main {
	public static void main (String[] args) {
		JFrame frame = new JFrame("Canvas Animation");
		CanvasPanel canvas = new CanvasPanel(frame);

		frame.add(canvas);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);

		new Animator(canvas).start();
	}
}
