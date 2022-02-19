"""
1행1열부터 i행i열 까지의 행열에 빈칸을 i로 채우기, 1채우고 2채우고 3채우고...
행 단위로 이어붙여 1차원 배열 arr로 만들기
arr[left:right+1]만 남기기

"""

def makeRow(rIdx, start, end):
    row = []
    startNum = rIdx + 1
    duplicatelength = rIdx+1
    if duplicatelength <= start:
        for num in range(start, end+1):
            row.append(num+1)
    elif duplicatelength > start:
        if duplicatelength <= end:
            row = [startNum]*(duplicatelength-start)
            for num in range(duplicatelength, end+1):
                row.append(num+1)
        elif duplicatelength > end:
            row = [startNum]*(end-start+1)    
    return row
    
def solution(n, left, right):
    answer = []
    leftShare = left//n
    leftRemainder = left%n
    rightShare = right//n
    rightRemainder = right%n
    if leftShare == rightShare:
        for num in makeRow(leftShare, leftRemainder, rightRemainder):  
            answer.append(num)            
    elif leftShare < rightShare:
        for num in makeRow(leftShare, leftRemainder, n-1):
            answer.append(num)
        for rIdx in range(leftShare+1, rightShare):
            for num in makeRow(rIdx, 0, n-1):
                answer.append(num)
        for num in makeRow(rightShare, 0, rightRemainder):
            answer.append(num)
    return answer

print(solution(4, 7, 14))

