"""
0,0 에서 시작
U, R, D, L

중복 경로 제외

벽밖 이동은 무시
숨은 정보: 경로 "길이"이기 때문에 같은 길이면 방향은 달라도 같은 걸로 취급
"""

def solution(dirs):
    answer = 0
    load = set()
    direction = {
        "U": (-1, 0),
        "R": (0, 1),
        "D": (1, 0),
        "L": (0, -1)
    }
    cy = 0
    cx = 0
    for d in dirs:
        dy, dx = direction[d]
        ny = cy + dy
        nx = cx + dx
        if -5 <= ny <= 5 and -5 <= nx <= 5:
            load.add((cy, cx, ny, nx))
            load.add((ny, nx, cy, cx))
            cy = ny
            cx = nx
    answer = len(load)//2
    return answer