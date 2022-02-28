
"""
nRow, nCol
[0][0] 시작
0: 벽
1: 길
사방 이동
[nRow-1][nCol-1] 도착 최단거리 리턴, 도착 불가시 -1
"""

from collections import deque


def solution(maps):
    answer = 0
    nRow = len(maps)
    nCol = len(maps[0])
    end = (nRow-1, nCol-1)
    visited = [[False for _ in range(nCol)] for _ in range(nRow)]
    q = deque()
    direction = [
        (-1, 0), (0, 1), (1, 0), (0, -1)
    ]
    q.append((0, 0))
    while q:
        length = len(q)
        answer += 1
        for _ in range(length):
            r, c = q.popleft()
            for dr, dc in direction:
                nr = r+dr
                nc = c+dc
                if 0<= nr < nRow and 0 <= nc < nCol and maps[nr][nc]:
                    if not visited[nr][nc]:
                        if (nr, nc) == end:
                            return answer+1
                        visited[nr][nc] = True
                        q.append((nr, nc))
    answer = -1
    return answer