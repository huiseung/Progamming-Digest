"""
from collections import deque

def solution(prices):
    q = deque(prices)
    answer = []
    while q:
        target = q.popleft()
        count = 0
        for num in q:
            count += 1
            if target > num:
                break
        answer.append(count) 
    return answer
"""

def solution(prices):
    answer = []
    length = len(prices)
    idx = 0
    while idx < length:
        target = prices[idx]
        count = 0
        for numIdx in range(idx+1, length):
            num = prices[numIdx]
            count += 1
            if target > num:
                break
        answer.append(count)
        idx += 1 
    return answer

