"""
0,1로 이루어진 n, m 매트릭스
위에 1로만이루어진 가장 큰 정사각형 찾기
n <= 1000
m <= 1000
"""

def solution(matrix):
    answer = 0
    nRow = len(matrix)
    nCol = len(matrix[0])
    #dp[r][c] = matrix[0:r+1][0:c+1] 중 가장 큰 정사각형에 한변에 길이
    dp = [[0 for _ in range(nCol)] for _ in range(nRow)]
    # init
    for r in range(nRow):
        dp[r][0] = matrix[r][0]
    for c in range(nCol):
        dp[0][c] = matrix[0][c]
    # 점화식
    for r in range(1, nRow):
        for c in range(1, nCol):
            if matrix[r][c] == 1:
                val = min(dp[r-1][c-1], dp[r-1][c], dp[r][c-1])+1
                dp[r][c] = val
                answer = max(answer, val)    
    return answer**2

def printMatrix(matrix):
    for row in matrix:
        print(row)
    print()

n, m = map(int, input().split())
matrix = []
for _ in range(n):
    row = list(map(int, list(input())))
    matrix.append(row)
print(solution(matrix))

