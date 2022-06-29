N = 0
S = 0
aList = []
    
from itertools import combinations

def solution(aList, N, S):
    answer = 0
    for r in range(1, N+1):
        for cand in combinations(aList, r):
            if sum(cand) == S:
                answer += 1
    return answer


N, S = map(int, input().split())
aList = list(map(int,input().split()))
print(solution(aList, N, S))