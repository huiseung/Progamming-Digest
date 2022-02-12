"""
n개의 서로 다른 숫자 배열
인접한 두 숫자 중 하나를 제거, 빈 공간이 없게 압축
더 작은 숫자를 제거하는 행위는 한 번만 가능
하나에 숫자가 남을때까지 제거 반복
하나로 남길 수 있는 숫자 갯수 리턴
"""

def solution(a):
    answer = 0
    boolList = [0 for _ in range(len(a))]
    minStart, minEnd = float('inf'), float('inf')
    for i in range(len(a)):
        startNum = a[i]
        endNum = a[-1-i]
        if startNum < minStart:
            minStart = startNum
            boolList[i] = 1
        if endNum < minEnd:
            minEnd = endNum
            boolList[-1-i] = 1
        print(boolList)
    answer = sum(boolList)
    return answer

print(solution([9, -1, -5]))