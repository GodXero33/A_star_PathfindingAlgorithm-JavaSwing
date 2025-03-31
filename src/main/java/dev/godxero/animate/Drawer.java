package dev.godxero.animate;

import javax.swing.*;
import java.awt.*;

public class Drawer {
	private final int cellWidth;
	private final int cellHeight;
	private final int cols;
	private final int rows;
	private final int width;
	private final int height;
	private final Node[][] grid;

	public Drawer (JFrame parentFrame) {
		this.width = 500;
		this.height = 500;
		this.cols = 5;
		this.rows = 5;
		this.cellWidth = this.width / this.cols;
		this.cellHeight = this.height / this.rows;
		this.grid = new Node[this.rows][this.cols];

		parentFrame.getContentPane().setPreferredSize(new Dimension(this.width, this.height));
		parentFrame.pack();
		this.generateGrid();
	}

	private void generateGrid () {
		for (int x = 0; x < this.cols; x++)
			for (int y = 0; y < this.rows; y++)
				this.grid[y][x] = new Node(x, y, 0);
	}

	public void draw (Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.width, this.height);

		g.setColor(Color.CYAN);

		for (int x = 0; x < this.cols; x++)
			for (int y = 0; y < this.rows; y++)
				this.grid[y][x].draw(g, this.cellWidth, this.cellHeight);
	}

	public void update () {

	}
}
