"""


주사위가 이동 방향으로 한칸 이동, 이동 불가시 반대 방향으로 한칸 이동



주사위 밑면 숫자 > 도착칸 (r, c)에 숫자 - 이동방향 90도 시계방향 회전
주사위 밑면 숫자 < 도착칸 (r, c)에 숫자 - 이동방향 90도 반시계방향 회전
주사위 밑면 숫자 = 도착칸 (r, c)에 숫자 - 이동방향 유지

도착칸 (r, c)에서 얻을 수 있는 점수 계산
(r, c)에서 동서남북으로 이동해 갈 수 있는 (r, c)에 적힌 숫자와 같은 칸의 수

k: 주사위 이동 횟수
return: 점수 합
"""

"""
4 5 1
4 1 2 3 3
6 1 1 3 3
5 6 1 3 2
5 5 6 5 5

4
"""

nRow, nCol, k = map(int, input().split())
direction = [(-1, 0), (0, 1), (1, 0), (0, -1)]
board = []
for _ in range(nRow):
    row = list(map(int, input().split()))
    board.append(row)

def move(now_position, now_direction):
    r, c = now_position
    dr, dc = direction[now_direction]
    nr = r+dr
    nc = c+dc
    if 0 <= nr < nRow and 0<= nc < nCol:
        now_position = (nr, nc)
        now_direction = now_direction
    elif nr < 0:
        now_position = (1, nc)
        now_direction = 2
    elif nr >= nRow:
        now_position = (r-1, nc)
        now_direction = 0
    elif nc < 0:
        now_position = (nr, 1)
        now_direction = 1
    elif nc <= nCol:
        now_position = (nr, c-1)
        now_direction = 3           
    return now_position, now_direction

def rolling(dice, now_direction):
    if now_direction == 0:
        dice["front"], dice["back"], dice["top"], dice["bottom"] = dice["bottom"], dice["top"], dice["front"], dice["back"]
    elif now_direction == 2:
        dice["front"], dice["back"], dice["top"], dice["bottom"] = dice["top"], dice["bottom"], dice["back"], dice["front"]
    elif now_direction == 1:
        dice["right"], dice["left"], dice["top"], dice["bottom"] = dice["top"], dice["bottom"], dice["left"], dice["right"] 
    elif now_direction == 3:
        dice["right"], dice["left"], dice["top"], dice["bottom"] = dice["bottom"], dice["top"], dice["right"], dice["left"]
    return dice

def setDirection(board_num, bottom_num, now_direction):
    if board_num < bottom_num:
        now_direction = (now_direction+1)%4
    elif board_num > bottom_num:
        if now_direction == 0:
            now_direction = 3
        else:
            now_direction = now_direction-1
    elif board_num == bottom_num:
        now_direction = now_direction
    return now_direction

def search(board, r, c, visited, board_num, count):
    visited[r][c] = True
    if board[r][c] == board_num:
        count[0] += 1
    else:
        return
    for dr, dc in direction:
        nr = r+dr
        nc = c+dc
        if 0 <= nr < nRow and 0 <= nc < nCol and not visited[nr][nc]:
            search(board, nr, nc, visited, board_num, count)
    return
    
def calcScore(board, board_num, r, c):
    count = [0]
    visited = [[False for _ in range(nCol)] for _ in range(nRow)]
    search(board, r, c, visited, board_num, count)
    #print("count ", count)
    return count[0]*board_num

def solution(board):
    answer = 0
    dice = {"top": 1, "bottom": 6, "right": 3, "left": 4, "front": 5, "back": 2}
    now_direction = 1
    now_position = (0, 0)
    
    for idx in range(k):
        #print(idx)
        now_position, now_direction = move(now_position, now_direction)
        dice = rolling(dice, now_direction)
        r, c = now_position
        board_num = board[r][c]
        #print("board_num ", board_num)
        now_direction = setDirection(board_num, dice["bottom"], now_direction)
        score = calcScore(board, board_num, r, c)
        #print("socre ", score, "now_position ", now_position, "now_direction", now_direction)
        #print("dice ", dice)     
        answer += score
    return answer
    
print(solution(board))

   