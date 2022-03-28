
"""
매 라운드 마다 상대팀->나 순으로 팀원 선정, 홀수 일땐 나는 팀원이 한명 적다
우선권 k개 사용 가능, 사람이 홀수일때 마지막에 우선권 사용시 내가 한명 더 많다
나와 상대팀은 항시 남은 사람중 가장 능력치 높은 사람을 대려간다

팀원 능력치 합 최대 구하기
from itertools import combinations

def solution(abilities, k):
    answer = 0
    if len(abilities) % 2 == 1:
        abilities.append(0)
    abilities = sorted(abilities, reverse=True)
    for cand in combinations([i for i in range(len(abilities)//2)], k):
        candSet = set(cand)
        scoreMe = 0
        scoreYou = 0
        for i in range(0, len(abilities)//2):
            num1 = abilities[2*i]
            num2 = abilities[2*i+1]
            if i in candSet:
                scoreMe += num1
            else:
                scoreMe += num2
        answer = max(scoreMe, answer)
    return answer
    
"""

"""
i원소를 줄인만큼 i+1원소를 늘리거나, i-1원소를 늘릴 수 있다
arr이 brr처럼 되기 위한 최소 시도 횟수
def solution(arr, brr):
    answer = 0
    minusArr = []
    for a, b in zip(arr, brr):
        minusArr.append(b-a)
    n = len(minusArr)
    if minusArr[0] != 0:
        num = minusArr[0]
        minusArr[0] -= num
        minusArr[1] += num 
        answer += 1
    for i in range(1, len(minusArr)-1):
        num = minusArr[i]
        if num != 0:
            minusArr[i] -= num
            minusArr[i+1] += num
            answer += 1
    return answer
    
"""