"""

"""
from collections import deque

direction8 = [(0, -1), (-1, -1), (-1, 0), (-1, 1), 
              (0, 1), (1, 1), (1, 0), (1, -1)]
direction4 = [(-1, 0), (0, -1), (0, 1), (1, 0)] # 상, 좌, 우, 하

nRow = 4
nCol = 4

M, T = map(int, input().split())

smell = [[-1 for _ in range(nCol)] for _ in range(nRow)]
board = [[[] for _ in range(nCol)] for _ in range(nRow)]
for idx in range(M):
    r, c, d = map(int, input().split())
    r -= 1
    c -= 1
    d -= 1
    board[r][c].append(d)
sr, sc = map(int, input().split())
shark = (sr-1, sc-1)

def moveShark(shark, board, depth, visited, eatFish, now_route, maxEatFish):
    global move_route
    if depth == 3:
        #print("now_route ", now_route, "eatFish ", eatFish)
        if eatFish > maxEatFish[0]:
            maxEatFish[0] = eatFish
            move_route = now_route[::]
        return
    #
    r, c = shark
    for d in range(4):
        dr, dc = direction4[d]
        nr = r + dr
        nc = c + dc
        if 0 <= nr < nRow and 0 <= nc < nCol:
            eatFlag = False
            #print((r, c), d)
            if visited[(nr, nc)]:
                eatFlag = True
            if not eatFlag:
                eatFish += len(board[nr][nc])
            visited[(nr, nc)] = True
            #print(visited)
            now_route.append(d)
            moveShark((nr, nc), board, depth+1, visited, eatFish, now_route, maxEatFish)
            if not eatFlag:
                eatFish -= len(board[nr][nc])
                visited[(nr, nc)] = False
            now_route.pop()

def printBoard(matrix):
    for row in matrix:
        print(row)
    print()
    
def solve(board, smell, shark, T):
    answer = 0
    global move_route
    #
    for t in range(T):
        print("step ", t)
        print("step start")
        #printBoard(board)
        #
        fishMoveBoard = [[[] for _ in range(nCol)] for _ in range(nRow)]
        #
        for r in range(nRow):
            for c in range(nCol):
                for d in board[r][c]:
                    canMove = False
                    for i in range(8):
                        dr, dc = direction8[(d-i)%8]
                        nr = r + dr
                        nc = c + dc
                        if 0 <= nr < nRow and 0 <= nc < nCol:
                            if (nr, nc) != shark and smell[nr][nc] < 0:
                                fishMoveBoard[nr][nc].append((d-i)%8)
                                canMove = True
                                break
                    if not canMove:
                        fishMoveBoard[r][c].append(d)
        print("move fish")
        #printBoard(fishMoveBoard)
        #
        maxEatFish = [-1]
        visited = {}
        move_route = []
        for r in range(nRow):
            for c in range(nCol):
                visited[(r, c)] = False
        #
        moveShark(shark, fishMoveBoard, 0, visited, 0, [], maxEatFish)
        print("shark")
        print("before ", shark)                  
        print("move_route ", move_route)
        r, c = shark
        for d in move_route:
            dr, dc = direction4[d]
            r += dr
            c += dc
            if len(fishMoveBoard[r][c]) > 0:
                fishMoveBoard[r][c] = []
                smell[r][c] = 2
        shark = (r, c)
        print("after ", shark)
        #
        for r in range(nRow):
            for c in range(nCol):
                if smell[r][c] >= 0:
                    smell[r][c] -= 1
                for d in fishMoveBoard[r][c]:
                    board[r][c].append(d)
        print("smell")
        #printBoard(smell)
        print("step end")
        #printBoard(board)                  
    #
    for r in range(nRow):
        for c in range(nCol):
            answer += len(board[r][c])
    return answer

print(solve(board, smell, shark, T))