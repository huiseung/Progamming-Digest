
"""
2*1 직사각형을
2*n 직사각형에 채우는 경우의 수

다이나믹 프로그래밍
a(n) = a(n-1) + a(n-2)
a(1) = 1
a(2) = 2
a(3) = 3
a(4) = a(3) + a(2)

"""

def solution(n):
    bigNumber= 1000000007
    dp = [0 for _ in range(n+1)]
    dp[1] = 1
    dp[2] = 2
    dp[3] = 3
    for i in range(3, n+1):
        dp[i] = (dp[i-1]+dp[i-2])%bigNumber
    answer = dp[n]
    return answer