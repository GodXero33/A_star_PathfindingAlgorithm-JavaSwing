# A* Algorithm Visualization

This project implements the A* (A-star) pathfinding algorithm using Java Swing. The algorithm searches for the shortest path between the top-left and bottom-right corners of a grid, while considering obstacles and the option of diagonal movement. This project currently focuses on the animation and algorithm logic.

### Visual Demonstration  

A recorded animation of the algorithm in action is available:  
[View GIF](/animation.gif)

This keeps it simple and directs users to the file without embedding it visibly in the README. Let me know if you need further tweaks! 🚀

## Features

- **Random Grid Generation:** Upon running the application, a random grid map is generated with some cells marked as obstacles.
- **A&#42; Pathfinding:** The algorithm attempts to find the shortest path from the top-left corner (start) to the 
  bottom-right corner (end), avoiding obstacles.
- **Animation:** The grid and the pathfinding process are visualized in real-time using Java Swing's `Canvas` component.

## Setup

1. Clone or download this repository to your local machine.
2. Compile and run the project using your preferred Java IDE.

## How It Works

- **Grid Generation:** The grid consists of cells. Each cell is either free or blocked, with some random obstacles generated on initialization. The start is always at the top-left (0,0), and the end is at the bottom-right (rows-1, cols-1).
- **A&#42; Pathfinding:** The algorithm uses the following approach:
    - **Opened List:** A list of nodes that are being considered for pathfinding.
    - **Closed List:** A list of nodes already evaluated.
    - **Path:** The final path from start to end once the algorithm has found a solution.
- **Diagonal Movement:** The algorithm can consider diagonal movement depending on the setting, which can improve efficiency in certain scenarios.

## Visuals

- **Grid Cells:** Each cell in the grid is represented as a square. The colors represent different states of the nodes:
    - **White:** Regular empty cells.
    - **Black:** Wall/obstacle cells.
    - **Gray:** Wall/obstacle cells.
    - **Red:** Nodes in the opened list.
    - **Green:** Nodes in the closed list.
    - **Blue:** Start node.
    - **Orange:** End node.
    - **Magenta:** Path nodes.

## Future Work

- **GUI Implementation:** Currently, the application runs with a basic animation and grid. A GUI with buttons, controls, and additional features like resizing the grid or changing the algorithm will be added in the future.
- **User Controls:** Allowing users to place their own obstacles or adjust start and end points.

## Development

This project is written in Java, using the Swing framework for visualization. The `Drawer` class is responsible for managing the grid, performing the A* algorithm, and handling the drawing of the grid and pathfinding process.

### Classes Overview
- **`Drawer`**: Handles the grid creation, pathfinding logic (A*), and drawing on the canvas.
- **Other Utility Classes**: These classes manage the node structure, arrays, and the animation timeline.

Created by [**GodXero**](https://github.com/GodXero33). For questions or suggestions, feel free to open an issue.
