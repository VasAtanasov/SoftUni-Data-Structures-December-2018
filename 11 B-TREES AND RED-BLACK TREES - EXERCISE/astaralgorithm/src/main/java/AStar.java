import java.util.*;

public class AStar {

    private Map<Node, Node> path;
    private char[][] map;
    private PriorityQueue<Node> queue;

    public AStar(char[][] map) {
        this.map = map;
        this.path = new HashMap<>();
        this.queue = new PriorityQueue<>();
    }

    public static int getH(Node current, Node goal) {
        return Math.abs(goal.getCol() - current.getCol()) + Math.abs(goal.getRow() - current.getRow());
    }

    public Iterable<Node> getPath(Node start, Node goal) {
        Node currentNode = start;
        this.queue.enqueue(currentNode);
        List<Node> path = new ArrayList<>();
        while (this.queue.size() != 0) {
            currentNode = this.queue.dequeue();
            if (this.map[currentNode.getRow()][currentNode.getCol()] == '*'){
                break;
            }
            checkAdjacentNode(new Node(currentNode.getRow() + 1, currentNode.getCol()), goal, start, currentNode);
            checkAdjacentNode(new Node(currentNode.getRow() , currentNode.getCol() + 1), goal, start, currentNode);
            checkAdjacentNode(new Node(currentNode.getRow() - 1, currentNode.getCol()), goal, start, currentNode);
            checkAdjacentNode(new Node(currentNode.getRow(), currentNode.getCol() - 1), goal, start, currentNode);
        }
        if (this.path.containsKey(goal)){
            path.add(currentNode);
            while(true){
                if (this.path.containsKey(currentNode)){
                    Node parent = this.path.get(currentNode);
                    path.add(parent);
                    currentNode = parent;
                }else{
                    break;
                }
            }
            Collections.reverse(path);
        }else{
            path.add(null);
        }
        return path;
    }

    private void checkAdjacentNode(Node node, Node goal, Node start, Node parent){
        try{
            if(this.map[node.getRow()][node.getCol()] != 'W' && this.map[node.getRow()][node.getCol()] != 'P' && !this.path.containsKey(node)){
                node.setF(getH(node, goal) + getH(node, start));
                this.queue.enqueue(node);
                this.path.put(node, parent);
            }
        }catch (IndexOutOfBoundsException ie){
        }
    }
}
