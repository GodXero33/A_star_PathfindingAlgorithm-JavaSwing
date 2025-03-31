package dev.godxero.animate;

public class NodeArrays {
	private NodeArrays() {}

	public static Node[] push (Node node, Node[] nodes) {
		final Node[] newNodes = new Node[nodes.length + 1];

		System.arraycopy(nodes, 0, newNodes, 0, nodes.length);

		newNodes[nodes.length] = node;

		return newNodes;
	}

	public static Node[] remove (Node node, Node[] nodes) {
		final Node[] newNodes = new Node[nodes.length - 1];
		int offset = 0;

		for (int a = 0; a < nodes.length; a++) {
			if (nodes[a] == node) {
				offset = 1;
				continue;
			}

			newNodes[a - offset] = nodes[a];
		}

		return newNodes;
	}

	public static boolean includes (Node node, Node[] nodes) {
		for (final Node currentNode : nodes) if (currentNode == node) return true;
		return false;
	}

	public static Node[] reverse (Node[] nodes) {
		final int length = nodes.length;
		final Node[] newNodes = new Node[length];

		for (int a = 0; a < length / 2 + 1; a++) {
			newNodes[a] = nodes[length - a - 1];
			newNodes[length - a - 1] = nodes[a];
		}

		return newNodes;
	}
}
