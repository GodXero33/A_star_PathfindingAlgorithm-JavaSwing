package dev.godxero.animate;

import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@ToString
public class Node {
	private int x;
	private int y;
	private int value;
	private double f;
	private double g;
	private double h;
	private Node previous;
	private Node[] neighbors;

	public Node (int x, int y) {
		this.x = x;
		this.y = y;
		this.value = 0;
		this.f = 0;
		this.g = 0;
		this.h = 0;
		this.neighbors = new Node[0];
	}

	public void calculateNeighbors (Node[][] grid, boolean diagonal) {
		if (this.x > 0) this.neighbors = NodeArrays.push(grid[this.y][this.x - 1], this.neighbors);
		if (this.x < grid[0].length - 1) this.neighbors = NodeArrays.push(grid[this.y][this.x + 1], this.neighbors);
		if (this.y > 0) this.neighbors = NodeArrays.push(grid[this.y - 1][this.x], this.neighbors);
		if (this.y < grid.length - 1) this.neighbors = NodeArrays.push(grid[this.y + 1][this.x], this.neighbors);

		if (!diagonal) return;

		if (this.x > 0 && this.y > 0) this.neighbors = NodeArrays.push(grid[this.y - 1][this.x - 1], this.neighbors);
		if (this.x < grid[0].length - 1 && this.y > 0) this.neighbors = NodeArrays.push(grid[this.y - 1][this.x + 1], this.neighbors);
		if (this.x > 0 && this.y < grid.length - 1) this.neighbors = NodeArrays.push(grid[this.y + 1][this.x - 1], this.neighbors);
		if (this.x < grid[0].length - 1 && this.y < grid.length - 1) this.neighbors = NodeArrays.push(grid[this.y + 1][this.x + 1], this.neighbors);
	}

	@Override
	public String toString () {
		return "x: " + this.x + ", y: " + this.y;
	}
}
