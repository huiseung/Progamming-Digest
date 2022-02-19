"""
S: 직진
L: 좌회전
R: 우회전
벽에 다을시 반대쪽으로 나옴
격자위에서 생길수 있는 모든 사이클에 각각에 경로 길이를 계산 
"""


def move(matrix, state):
    direction = {
        0: (-1, 0),
        1: (0, 1),
        2: (1, 0),
        3: (0, -1)
    }
    r, c, d = state
    visited[r][c][d] = True
    dr, dc = direction[d]
    nr = r + dr
    nc = c + dc
    if nr < 0:
        nr = nRow-1
    elif nr >= nRow:
        nr = 0
    if nc < 0:
        nc = nCol-1
    elif nc >= nCol:
        nc = 0
    cmd = matrix[nr][nc]
    if cmd == "S":
        nd = d    
    elif cmd == "L":
        nd = (d+3)%4
    elif cmd == "R":
        nd = (d+1)%4
    return (nr, nc, nd)

def isVisit(state):
    r, c, d = state
    return visited[r][c][d]

def search(matrix, start, state, length):
    nextState = move(matrix, state)
    if nextState == start:
        answer.append(length)
        return
    if not isVisit(nextState):
        search(matrix, start, nextState, length+1)

def printMatrix(matrix):
    for row in matrix:
        print(row)
    print()
    
def solution(grid):
    global nRow, nCol
    global answer, visited
    answer = []
    nRow = len(grid)
    nCol = len(grid[0])
    # 0상, 1우, 2하, 3좌
    visited = [[[False]*4 for _ in range(nCol)] for _ in range(nRow)]
    
    for r in range(nRow):
        for c in range(nCol):
            for d in range(4):
                start = (r, c, d)
                state = (r, c, d)
                search(grid, start, state, 1)
    return answer

print(solution(["R","R"]))