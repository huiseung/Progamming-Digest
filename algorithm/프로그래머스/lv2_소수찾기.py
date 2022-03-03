"""
다른 사람 풀이: 아이디어는 같다, 구현이 더 파이썬 스럽다
from itertools import permutations
def solution(n):
    a = set()
    for i in range(len(n)):
        a |= set(map(int, map("".join, permutations(list(n), i + 1))))
    a -= set(range(0, 2))
    for i in range(2, int(max(a) ** 0.5) + 1):
        a -= set(range(i * 2, max(a) + 1, i))
    return len(a)

"""

import math
from itertools import permutations


def makeIsPrimes(n):
    #time: O(NlglgN), memory: O(N)
    isPrimes = [True for _ in range(n+1)] # 0부터 시작
    isPrimes[0] = False
    isPrimes[1] = False
    for i in range(2, int(math.sqrt(n)) + 1):
        if isPrimes[i] == True:
            j = 2
            while i*j <= n:
                isPrimes[i*j] = False
                j += 1
    return isPrimes

def solution(numbers):
    answer = 0
    idxToChar = {idx: char for idx, char in enumerate(numbers)}
    
    numberSet = set()
    maxNum = 0
    for r in range(1, len(numbers)+1):
        for cand in permutations([i for i in range(len(numbers))], r):
            string = ""
            for i in cand:
                string += idxToChar[i]
            num = int(string)
            maxNum = max(maxNum, num)
            numberSet.add(num)
    isPrimes = makeIsPrimes(maxNum)
    for number in numberSet:
        if isPrimes[number] == True:
            answer += 1
    return answer