import heapq

def uniform_cost_search(graph, start, goal):
    visited = set()
    queue = [(0, start, [])]  # (biaya total, simpul saat ini, jalur yang dilalui)

    while queue:
        cost, node, path = heapq.heappop(queue)

        if node in visited:
            continue

        visited.add(node)
        path = path + [node]

        if node == goal:
            return path, cost

        for neighbor, edge_cost in graph.get(node, []):
            if neighbor not in visited:
                heapq.heappush(queue, (cost + edge_cost, neighbor, path))

    return None, float("inf")


# Definisi graf sesuai gambar
graph = {
    'A': [('B', 9), ('C', 15), ('D', 20)],
    'B': [('A', 9), ('D', 15), ('E', 13)],
    'C': [('A', 15), ('D', 13), ('F', 25)],
    'D': [('A', 20), ('B', 15), ('C', 13), ('E', 10), ('F', 16)],
    'E': [('B', 13), ('D', 10), ('F', 10)],
    'F': [('C', 25), ('D', 16), ('E', 10)]
}

# Pencarian dari A ke F
path, total_cost = uniform_cost_search(graph, 'A', 'F')

print("Jalur Terpendek:", " → ".join(path))
print("Total Biaya:", total_cost)