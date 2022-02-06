"""
n <= 1000
m <= 1000
"""
n = 0
m = 0
T = 0

T = int(input())
n = int(input())
aList = list(map(int, input().split()))
m = int(input())
bList = list(map(int, input().split()))

def solution(aList, bList):
    answer = 0
    global T, n, m
    aDict = {}
    bDict = {}
    for start in range(n):
        for end in range(start+1, n+1):
            subList = aList[start:end]
            key = sum(subList)
            if key not in aDict:
                aDict[key] = 0
            aDict[key] += 1
    for start in range(m):
        for end in range(start+1, m+1):
            subList = bList[start:end]
            key = sum(subList)
            if key not in bDict:
                bDict[key] = 0
            bDict[key] += 1
    for key in aDict.keys():
        opKey = T-key
        if opKey in bDict:
            answer += aDict[key]*bDict[opKey]            
    return answer

print(solution(aList, bList))