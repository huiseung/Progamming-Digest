"""

"""


direction8 = [
    (0, -1), (-1, -1), (-1, 0), (-1, 1),
    (0, 1), (1, 1), (1, 0), (1, -1)    
]
direction4 = [(0, -1), (-1, 0), (0, 1), (1, 0)]
nRow = 4
nCol = 4

M, T = map(int, input().split())
board = [[[] for _ in range(nCol)] for _ in range(nRow)]
smell = [[-1 for _ in range(nCol)] for _ in range(nRow)]
for _ in range(M):
    r, c, d = map(int, input().split())
    r-=1
    c-=1
    d-=1
    board[r][c].append([d])
r, c = map(int, input().split())
r-=1
c-=1
shark = (r, c)

def move_fish(board):
    pass

def move_shark(shark, moveFishBoard):
    pass

def solve(board, smell, shark, T):
    for _ in range(T):
        pastBoard = [[row[::]] for row in board]
        #
        moveFishBoard = move_fish(board)
        #
        move_route = move_shark(shark, moveFishBoard)
        #
        r, c = shark
        for d in move_route:
            dr, dc = direction4[d]
            r += dr
            c += dc
            if len(moveFishBoard[r][c]) > 0:
                                
            
        
        
        #
        
        
        #
        
        
    
    