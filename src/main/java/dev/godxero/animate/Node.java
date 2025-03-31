package dev.godxero.animate;

import lombok.*;

import java.awt.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Node {
	private int x;
	private int y;
	private int value;

	public void draw (Graphics g, int w, int h) {
		g.setColor(Color.CYAN);
		g.fillRect(this.x * w + 1, this.y * h + 1, w - 2, h - 2);
	}
}
