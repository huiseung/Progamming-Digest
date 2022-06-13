"""
N은 4의 배수, 4<= N <=100
"""

#N, K = map(int, input().split())
#board = [[0 for _ in range(N)] for _ in range(N)]
# for idx, fish in enumerate(list(map(int, input().split()))):
#     board[-1][idx] = fish    

from pygments import highlight


N, K = 8, 1
board = [[-1 for _ in range(N)] for _ in range(N)]
for idx, fish in enumerate([i for i in range(N)]):
    board[-1][idx] = fish


def printMatrix(matrix, msg=""):
    print(msg)
    for row in matrix:
        print(row)
    print()

def addFish(board, N):
    minVal = float('inf')
    for i in range(N):
        minVal = min(board[-1][i], minVal)
    for i in range(N):
        if board[-1][i] == minVal:
            board[-1][i] += 1            
    return

def rolling(board):
    pivot = 0
    hight = 1
    width = 1
    s = 0
    while True:
        print("[up] pivot ", pivot, "width ", width, "hight ", hight)
        if N - (pivot+2*width) < 0:
            break 
        for i in range(width):
            for h in range(hight):
                board[-(h+2)][pivot+width+i] = board[-(h+1)][pivot+i]
                board[-(h+1)][pivot+i] = -1
        printMatrix(board, "step "+ str(s)+" up")
        
        pivot += width
        hight += 1
        print("[rolling] pivot ", pivot, "width ", width, "hight ", hight)
        if N - (pivot+width+hight) <0:
            break
        for i in range(width):
            for h in range(hight):
                print((-(h+1), i), "->", (-width+i, pivot+h))
                board[-width+i][pivot+h] = board[-(h+1)][i]
                board[-(hight+1)][i] = -1
        printMatrix(board, "step "+ str(s)+" rolling")
        s+= 1
        if s == 1:
            break
        
    return

def folding(board):
    return

def flating(board):
    return

def moveFish(board):
    return

def solve(N, K, board):
    answer = 0
    #addFish(board, N)
    #printMatrix(board, "addFish")
    rolling(board)
    printMatrix(board, "rolling finish")
    
    # while True:
    #     addFish(board)
    #     rolling(board)
        # moveFish(board)
        # flating(board)
        # folding(board)
        # moveFish(board)
        # flating(board)
        # answer += 1
        # if calcDif(board) <= K:
        #     break
    return answer

print(solve(N, K, board))