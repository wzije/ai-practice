package BFS;
import java.util.*;

class WaterState {
    int a, b;
    String move;
    WaterState parent;

    public WaterState(int a, int b, String move, WaterState parent) {
        this.a = a;
        this.b = b;
        this.move = move;
        this.parent = parent;
    }

    public List<WaterState> getChildren(int capA, int capB) {
        List<WaterState> children = new ArrayList<>();
        // Isi A
        children.add(new WaterState(capA, b, "Isi A", this));
        // Isi B
        children.add(new WaterState(a, capB, "Isi B", this));
        // Kosongkan A
        children.add(new WaterState(0, b, "Kosongkan A", this));
        // Kosongkan B
        children.add(new WaterState(a, 0, "Kosongkan B", this));
        // Tuang A ke B
        int total = a + b;
        int newB = Math.min(total, capB);
        int newA = total - newB;
        children.add(new WaterState(newA, newB, "Tuang A ke B", this));
        // Tuang B ke A
        newA = Math.min(total, capA);
        newB = total - newA;
        children.add(new WaterState(newA, newB, "Tuang B ke A", this));

        return children;
    }

    public boolean isGoal(int target) {
        return a == target || b == target;
    }
}

public class WaterJugBFS {

    public static List<String> bfsWaterJug(int capA, int capB, int target) {
        Queue<WaterState> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new WaterState(0, 0, "Start", null));

        while (!queue.isEmpty()) {
            WaterState node = queue.poll();
            String stateKey = node.a + "," + node.b;

            if (visited.contains(stateKey)) {
                continue;
            }
            visited.add(stateKey);

            if (node.isGoal(target)) {
                return reconstructPath(node);
            }

            for (WaterState child : node.getChildren(capA, capB)) {
                queue.add(child);
            }
        }

        return null;
    }

    private static List<String> reconstructPath(WaterState node) {
        List<String> path = new ArrayList<>();
        while (node.parent != null) {
            path.add(node.move + ": (A=" + node.a + ", B=" + node.b + ")");
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        int capA = 4;
        int capB = 3;
        int target = 2;

        List<String> solution = bfsWaterJug(capA, capB, target);

        if (solution != null) {
            System.out.println("Langkah-langkah menyelesaikan masalah Tuang Air dengan BFS:");
            for (String step : solution) {
                System.out.println(step);
            }
        } else {
            System.out.println("Tidak ada solusi ditemukan.");
        }
    }
}
