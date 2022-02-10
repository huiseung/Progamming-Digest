"""
0과 1로 이루어진 배열을 압축
압축 영역안에 수가 모두 같다면 한 칸으로 압축
아니라면, 4개의 정삭가형으로 쪼갠후 다시 압축 시도
최종 압축시 0과 1의 개수 리턴
"""

def search(board, corList, r, c, length):
    standard = board[r][c]
    
    isAllSame = True
    for dr in range(length):
        for dc in range(length):
            if board[r+dr][c+dc] != standard:
                isAllSame= False
    if not isAllSame:
        corList += [(r+length//2, c), (r, c+length//2), (r+length//2, c+length//2)]
        search(board, corList, r, c, length//2)
        search(board, corList, r+length//2, c, length//2)
        search(board, corList, r, c+length//2, length//2)
        search(board, corList, r+length//2, c+length//2, length//2)    

def solution(arr):
    answer = []
    corList = [(0, 0)]
    search(arr, corList, 0, 0, len(arr))
    print(corList)    
    nZero = 0
    nOne = 0
    for r, c in corList:
        if arr[r][c] == 1:
            nOne += 1
        elif arr[r][c] == 0:
            nZero += 1
    return [nZero, nOne]
#arr = [[1,1,0,0],[1,0,0,0],[1,0,0,1],[1,1,1,1]]
arr = [[1,1,1,1,1,1,1,1],[0,1,1,1,1,1,1,1],[0,0,0,0,1,1,1,1],[0,1,0,0,1,1,1,1],[0,0,0,0,0,0,1,1],[0,0,0,0,0,0,0,1],[0,0,0,0,1,0,0,1],[0,0,0,0,1,1,1,1]]
print(solution(arr))