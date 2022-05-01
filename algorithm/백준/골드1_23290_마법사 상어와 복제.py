"""
nRow, nCol
물고기 M마리 
####
현 시점 저장
####
물고기 이동: 상어있는 곳, 물고기 냄새나는 곳 이동 불가
현재 칸에서 이동방향으로 이동 불가시 반시계로 45 회전
아예 불가면 제자리에 있는다
###
상어 상하좌우로 3칸 이동, 중복 없이 3칸 이동 불가한 길은 안감, 물고기를 많이 먹을 수 있는 길 택
경로 후보를 만든 후 그중 사전상 앞에 오는걸 택
###
두턴 전에 물고기 냄새는 사라진다
####
저장 시점 위치에 물고기 추가 방향을 그대로 가짐
####

"""

"""

5 1
4 3 5
1 3 5
2 4 2
2 1 6
3 4 4
4 2

9
"""


direction8 = [(0, -1), (-1, -1), (-1, 0), (-1, 1), 
              (0, 1), (1, 1), (1, 0), (1, -1)]
direction4 = [(-1, 0), (0, -1), (0, 1), (1, 0)] # 상, 좌, 우, 하

nRow = 4
nCol = 4

M, T = map(int, input().split())

smell = [[0 for _ in range(nCol)] for _ in range(nRow)]
board = [[[] for _ in range(nCol)] for _ in range(nRow)]
for idx in range(M):
    r, c, d = map(int, input().split())
    r -= 1
    c -= 1
    d -= 1
    board[r][c].append(d)
sr, sc = map(int, input().split())
shark = (sr-1, sc-1)


def solution(board, smell, shark, T):
    answer = 0
    return answer

print(solution(board, smell, shark, T))