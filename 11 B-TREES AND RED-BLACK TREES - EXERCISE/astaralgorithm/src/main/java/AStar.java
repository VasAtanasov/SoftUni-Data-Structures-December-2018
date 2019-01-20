import java.util.*;

public class AStar {

    private char[][] maze;
    private PriorityQueue<Node> pQue;
    private Map<Node, Node> parents;
    private Map<Node, Integer> gCost;

    public AStar() {
        this.pQue = new PriorityQueue<>();
        this.parents = new LinkedHashMap<>();
        this.gCost = new LinkedHashMap<>();
    }

    public AStar(char[][] map) {
        this();
        this.maze = map;
    }

    public static int getH(Node current, Node goal) {
        int deltaX = Math.abs(current.getCol() - goal.getCol());
        int deltaY = Math.abs(current.getRow() - goal.getRow());
        return deltaX + deltaY;
    }

    public Iterable<Node> getPath(Node start, Node goal) {
        this.gCost.put(start, 0);
        this.parents.put(start, null);
        this.pQue.enqueue(start);

        while (! this.pQue.isEmpty()) {
            Node current = this.pQue.dequeue();
            if (current.equals(goal)) {
                break;
            }

            List<Node> surroundingNodes = this.getSurroundingNodes(current);
            int newCost = this.gCost.get(current) + 1;

            for (Node node : surroundingNodes) {
                if (! this.gCost.containsKey(node) || newCost < this.gCost.get(node)) {
                    node.setF(newCost + getH(node, goal));
                    this.parents.put(node, current);
                    this.gCost.put(node, newCost);
                    this.pQue.enqueue(node);
                }
            }
        }

        return this.reconstructPath(start, goal);
    }

    private Iterable<Node> reconstructPath(Node start, Node goal) {
        if (! this.parents.containsKey(goal)) {
            return Collections.singletonList(start);
        }

        Node current = parents.get(goal);
        Deque<Node> path = new ArrayDeque<>();
        path.push(current);
        while (! current.equals(start)) {
            path.push(current);
            current = this.parents.get(current);
        }
        path.push(start);
        return path;
    }

    private List<Node> getSurroundingNodes(Node current) {
        List<Node> result = new ArrayList<>();
        int row = current.getRow();
        int col = current.getCol();

        int rowUp = row - 1;
        int rowDown = row + 1;
        int colLeft = col - 1;
        int colRight = col + 1;

        this.addToQueue(result, rowUp, col);
        this.addToQueue(result, rowDown, col);
        this.addToQueue(result, row, colLeft);
        this.addToQueue(result, row, colRight);

        return result;
    }

    private void addToQueue(List<Node> result, int row, int col) {
        if (isInside(row, col) && ! isWall(row, col)) {
            result.add(new Node(row, col));
        }
    }

    private boolean isInside(int row, int col) {
        return row >= 0 && row < this.maze.length &&
                col >= 0 && col < this.maze[row].length;
    }

    private boolean isWall(int row, int col) {
        return this.maze[row][col] == 'W';
    }
}
