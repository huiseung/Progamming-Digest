"""
N가지 동전종류로 최소한의 갯수로 K 만들기 
"""

def solution(coins, K):
    answer = 0
    descendingCoins = coins[::-1]
    cumul = 0
    idx = 0
    while cumul < K:
        coin = descendingCoins[idx]
        if cumul + coin <= K:
            answer += 1
            cumul += coin
        elif cumul + coin > K:
            idx += 1            
    return answer

N, K = map(int, input().split())
coins = []
for _ in range(N):
    coins.append(int(input()))
print(solution(coins, K))