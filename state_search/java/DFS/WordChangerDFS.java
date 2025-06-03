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

public class WordChangerDFS {

    // DFS untuk menemukan jalur dari start ke goal
    public static List<String> dfsChange(String start, String goal) {
        Stack<State> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        stack.push(new State(start, "Start", null));

        while (!stack.isEmpty()) {
            State node = stack.pop();

            // Jika sudah dikunjungi, skip
            if (visited.contains(node.word)) continue;
            visited.add(node.word);

            // Jika tujuan ditemukan
            if (node.word.equals(goal)) {
                return reconstructPath(node); // Goal found, reconstruct path
            }

            // Menambahkan tetangga yang ditemukan ke dalam stack
            for (String neighbor : generateNeighbors(node.word)) {
                stack.push(new State(neighbor, "Change to " + neighbor, node));
            }
        }

        return null; // Jika tidak ada solusi ditemukan
    }

    // Generate neighbors berdasarkan graf yang sesuai
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
        String goal = "F";

        List<String> dfsSolution = dfsChange(start, goal);

        System.out.println("Solusi mengubah kata dari A ke F dengan DFS:");
        if (dfsSolution != null) {
            for (String step : dfsSolution) {
                System.out.println(step);
            }
            System.out.println("Jumlah langkah: " + dfsSolution.size());
        } else {
            System.out.println("Tidak ada solusi ditemukan.");
        }
    }
}
