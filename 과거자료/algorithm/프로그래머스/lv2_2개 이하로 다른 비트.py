"""
f(x) = x 초과이면서 x와 비트가 1,2개 다른 수중 가장 작은 수
if x 짝수, x+1
if x 홀수, 
 
"""



def f(x):
    num = 0
    if x % 2 == 0:
        num = x+1
    else:
        binNum = bin(x)
        zeroIdx = 0
        for i in range(len(binNum)-1, -1, -1):
            if binNum[i] == "0":
                zeroIdx = i
                break
        if zeroIdx == 0:
            binNum =  "0b10" + binNum[3:]
        else:
            binNum = binNum[:zeroIdx] + "10" + binNum[zeroIdx+2:]
        num = int(binNum, 2)        
    return num

def solution(numbers):
    answer = []
    for num in numbers:
        aNum = f(num)
        answer.append(aNum)
    return answer

print(solution([2, 7, 9]))