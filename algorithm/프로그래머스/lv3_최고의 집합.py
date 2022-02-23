"""
자연수 n개로 이루어진 집합 중
원소 합이 s, 원소 곱이 최대인 집합 찾기

원소들을 오름차순으로 리스트로 리턴

만들수 없을땐 [-1] 리턴

n <= 10,000
s <= 100,000,000
"""

def solution(n, s):
    answer = []
    if n > s:
        return [-1]
    for _ in range(n):
        answer.append(s//n)
    for i in range(n-1, n-1-s%n, -1):
        answer[i] += 1
    return answer