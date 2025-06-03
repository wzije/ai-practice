def uniform_cost_search(graph, start, goal):
    visited = set()
    queue = [(0, start, [])]  # (total_cost, current_node, path)

    while queue:
        # Urutkan berdasarkan biaya total terkecil
        queue.sort(key=lambda x: x[0])  
        cost, node, path = queue.pop(0)  # ambil elemen dengan cost terkecil

        if node in visited:
            continue

        visited.add(node)
        path = path + [node]

        if node == goal:
            return path, cost

        for neighbor, edge_cost in graph.get(node, []):
            if neighbor not in visited:
                queue.append((cost + edge_cost, neighbor, path))

    return None, float("inf")


# Definisi graf
graph = {
    'A': [('B', 9), ('C', 15), ('D', 20)],
    'B': [('A', 9), ('D', 15), ('E', 13)],
    'C': [('A', 15), ('D', 13), ('F', 25)],
    'D': [('A', 20), ('B', 15), ('C', 13), ('E', 10), ('F', 16)],
    'E': [('B', 13), ('D', 10), ('F', 10)],
    'F': [('C', 25), ('D', 16), ('E', 10)]
}

# Jalankan UCS dari A ke F
path, total_cost = uniform_cost_search(graph, 'A', 'F')

print("Jalur Terpendek:", " → ".join(path))
print("Total Biaya:", total_cost)