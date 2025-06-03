
import java.util.*;

class State {
    String word;
    String move;
    State parent;

    public State(String word, String move, State parent) {
        this.word = word;
        this.move = move;
        this.parent = parent;
    }
}

public class WordChangerBFS {

    // BFS untuk menemukan jalur dari start ke goal
    public static List<String> bfsChange(String start, String goal) {
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new State(start, "Start", null));

        while (!queue.isEmpty()) {
            State node = queue.poll();
            if (visited.contains(node.word)) continue; // Skip if already visited
            visited.add(node.word);

            if (node.word.equals(goal)) {
                return reconstructPath(node); // Goal found, reconstruct path
            }

            // Generate neighbors dan tambahkan mereka ke queue
            for (String neighbor : generateNeighbors(node.word)) {
                queue.add(new State(neighbor, "Change to " + neighbor, node));
            }
        }

        return null; // Jika tidak ada solusi ditemukan
    }

    // Menentukan tetangga dari setiap kata
    private static List<String> generateNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        if (word.equals("A")) {
            neighbors.add("B");
            neighbors.add("E");
        }
        if (word.equals("B")) {
            neighbors.add("C");
            neighbors.add("D");
        }
        if (word.equals("E")) {
            neighbors.add("F");
        }
        return neighbors;
    }

    // Rekonstruksi jalur dari goal ke start
    private static List<String> reconstructPath(State node) {
        List<String> path = new ArrayList<>();
        while (node.parent != null) {
            path.add(node.move + ": (" + node.word + ")");
            node = node.parent;
        }
        Collections.reverse(path); // Reverse path to show correct order
        return path;
    }

    public static void main(String[] args) {
        String start = "A";
        String goal = "D";

        List<String> bfsSolution = bfsChange(start, goal);

        System.out.println("Solusi mengubah kata dari A ke F dengan BFS:");
        if (bfsSolution != null) {
            for (String step : bfsSolution) {
                System.out.println(step);
            }
            System.out.println("Jumlah langkah: " + bfsSolution.size());
        } else {
            System.out.println("Tidak ada solusi ditemukan.");
        }
    }
}
