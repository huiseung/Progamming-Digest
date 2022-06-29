graph = {
    "ICN": ["BBB"],
    "BBB": ["AAA", "CCC"],
    "CCC": ["BBB"],
    "AAA": []
}

visited = {}
for key, value in graph.items():
    for v in value:
        visited[(key, v)] = False

def dfs(graph, node, visited, route):
    for adj in graph[node]:
        if not visited[(node, adj)]:
            visited[(node, adj)]=True
            route.append(adj)
            dfs(graph, adj, visited, route)

root = "ICN"
route = [root]
dfs(graph, root, visited, route)
print(route)