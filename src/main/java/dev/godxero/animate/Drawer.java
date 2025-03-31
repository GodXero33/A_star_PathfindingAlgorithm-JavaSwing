package dev.godxero.animate;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Drawer {
	private static final Color[] COLORS = {
		Color.WHITE,
		Color.BLACK,
		Color.GRAY
	};
	private static final double COST_DIAGONAL = 1.41;
	private static final double COST_STRAIGHT = 1.0;

	private final int cellWidth;
	private final int cellHeight;
	private final int cols;
	private final int rows;
	private final int width;
	private final int height;
	private final Node[][] grid;
	private Node[] openedList;
	private Node[] closedList;
	private Node[] path;
	private Node start;
	private Node end;
	private final boolean diagonal;
	private boolean solved;
	private final Set<Integer> walls;

	public Drawer (JFrame parentFrame) {
		this.width = 1000;
		this.height = 1000;
		this.cols = 50;
		this.rows = 50;
		this.cellWidth = this.width / this.cols;
		this.cellHeight = this.height / this.rows;
		this.grid = new Node[this.rows][this.cols];
		this.openedList = new Node[0];
		this.closedList = new Node[0];
		this.path = new Node[0];
		this.diagonal = false;
		this.solved = false;
		this.walls = new HashSet<>();

		this.walls.add(1);
		this.walls.add(2);

		parentFrame.getContentPane().setPreferredSize(new Dimension(this.width, this.height));
		parentFrame.pack();
		this.generateGrid();

		this.openedList = NodeArrays.push(this.start, this.openedList);
	}

	private void generateGrid () {
		for (int x = 0; x < this.cols; x++)
			for (int y = 0; y < this.rows; y++)
				this.grid[y][x] = new Node(x, y);

		for (int x = 0; x < this.cols; x++) {
			for (int y = 0; y < this.rows; y++) {
				this.grid[y][x].calculateNeighbors(this.grid, this.diagonal);

				if (Math.random() > 0.8) this.grid[y][x].setValue((int) (Math.random() * 2 + 1));
			}
		}

		this.start = this.grid[0][0];
		this.end = this.grid[this.rows - 1][this.cols - 1];

		this.start.setValue(0);
		this.end.setValue(0);
	}

	public void draw (Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.width, this.height);

		this.drawGrid(g);

		if (this.solved) {
			this.drawPath(g);
			return;
		}

		g.setColor(Color.RED);

		for (final Node node : this.openedList) this.drawNode(g, node.getX(), node.getY());

		g.setColor(Color.GREEN);

		for (final Node node : this.closedList) this.drawNode(g, node.getX(), node.getY());

		this.drawPath(g);

		g.setColor(Color.BLUE);
		this.drawNode(g, this.start.getX(), this.start.getY());
		g.setColor(Color.ORANGE);
		this.drawNode(g, this.end.getX(), this.end.getY());
	}

	private void drawPath (Graphics g) {
		g.setColor(Color.MAGENTA);

		for (final Node node : this.path) this.drawNode(g, node.getX(), node.getY());
	}

	private void drawGrid (Graphics g) {
		for (int x = 0; x < this.cols; x++) {
			for (int y = 0; y < this.rows; y++) {
				g.setColor(Drawer.COLORS[this.grid[y][x].getValue()]);
				this.drawNode(g, x, y);
			}
		}
	}

	private void drawNode (Graphics g, int x, int y) {
		g.fillRect(x * this.cellWidth + 1, y * this.cellHeight + 1, this.cellWidth - 2, this.cellHeight - 2);
	}

	public void update () {
		if (this.solved) return;

		if (this.openedList.length == 0) {
			this.solved = true;
			System.out.println("No solution");
			return;
		}

		int lowestFScoreIndexInOpenedList = 0;
		double lowestFScore = this.openedList[lowestFScoreIndexInOpenedList].getF();

		for (int a = 0; a < this.openedList.length; a++) {
			if (this.openedList[a].getF() < lowestFScore) lowestFScoreIndexInOpenedList = a;
		}

		final Node currentNode = this.openedList[lowestFScoreIndexInOpenedList];
		final Node[] neighbors = currentNode.getNeighbors();

		for (final Node neighbor : neighbors) {
			if (NodeArrays.includes(neighbor, this.closedList) || this.walls.contains(neighbor.getValue())) continue;

			final boolean isDiagonalNeighbor = currentNode.getX() != neighbor.getX() && currentNode.getY() != neighbor.getY();
			final boolean isNeighborInOpenedList = NodeArrays.includes(neighbor, this.openedList);
			final double tmpG = currentNode.getG() + (isDiagonalNeighbor ? Drawer.COST_DIAGONAL : Drawer.COST_STRAIGHT);
			boolean isNewPath = !isNeighborInOpenedList || tmpG < neighbor.getG();

			if (!isNeighborInOpenedList) this.openedList = NodeArrays.push(neighbor, this.openedList);

			if (!isNewPath) continue;

			neighbor.setG(tmpG);
			neighbor.setH(this.heuristic(neighbor, this.end));
			neighbor.setF(neighbor.getG() + neighbor.getH());
			neighbor.setPrevious(currentNode);
		}

		if (currentNode == this.end) {
			this.solved = true;
			this.solvePath();
			return;
		}

		this.openedList = NodeArrays.remove(currentNode, this.openedList);
		this.closedList = NodeArrays.push(currentNode, this.closedList);
	}

	private void solvePath () {
		Node currentNode = this.end;
		this.path = NodeArrays.push(currentNode, this.path);

		while (currentNode.getPrevious() != null) {
			currentNode = currentNode.getPrevious();
			this.path = NodeArrays.push(currentNode, this.path);
		}

		this.path = NodeArrays.reverse(this.path);

		this.printPath();
	}

	private void printPath () {
		final StringBuilder builder = new StringBuilder();

		builder.append("\nStart:\n").append(this.start.toString()).append("\n\nPath:\n");

		for (final Node node : this.path) builder.append(node.toString()).append("\n");

		builder.append("\nEnd:\n").append(this.end.toString()).append("\n\nDistance: ").append(this.path.length).append("\n");

		System.out.println(builder);
	}

	private double heuristic (Node a, Node b) {
		return Math.hypot((double) a.getX() - b.getX(), (double) a.getY() - b.getY());
	}
}
