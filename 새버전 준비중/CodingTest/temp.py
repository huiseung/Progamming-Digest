def printMatrix(matrix, msg=""):
    print(msg)
    for row in matrix:
        print(row)
    print()

def fillMatrix(N):
    board = [[0 for _ in range(N)] for _ in range(N)]
    num = 1
    r = N//2
    c = N//2
    step = 1
    board[r][c] = num
    if N == 1:
        return board
    #
    # 1=0, 3=2, 5=3, 7=4
    while step <= N//2+1:
        step += 1
        r -= 1
        for i in range(step):
            num += 1
            board[r][c+i] = num
        #
        c = c + step - 1
        r += 1
        for i in range(step):
            num += 1
            board[r+i][c] = num
        #
        r = r + step - 1
        c -= 1
        for i in range(step):
            num += 1
            board[r][c-i] = num
        #
        r -= 1
        c = c - step +1
    return board


N = 5
printMatrix(fillMatrix(N))