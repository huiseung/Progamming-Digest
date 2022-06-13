"""
새로운 수 = 가장 작은 수 + (두번째 작은수*2)
배열안 모든 수가 K이상이 될때까지 반복
만들수 있다면 최소 반복 횟수, 만들 수 없다면 -1
"""

import heapq

def solution(scoville, K):
    answer = 0
    q = []
    for e in scoville:
        heapq.heappush(q, e)
    while q[0] < K and len(q) > 1:
        num1 = heapq.heappop(q)
        num2 = heapq.heappop(q)
        heapq.heappush(q, num1+num2*2)
        answer += 1
    if q[0] < K:
        return -1
    return answer