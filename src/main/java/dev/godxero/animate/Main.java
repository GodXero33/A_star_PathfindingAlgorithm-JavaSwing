package dev.godxero.animate;

import javax.swing.*;

public class Main {
	public static void main (String[] args) {
		JFrame frame = new JFrame("Canvas Animation");
		CanvasPanel canvas = new CanvasPanel();

		frame.add(canvas);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		new Animator(canvas).start();
	}
}
