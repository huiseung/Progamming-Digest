"""
피로도: 음이아닌 정수

dungeons(던전수는 8개 이하)
최소 피로도: 입장조건
소모피로도: 입장시 사용량

k: 현재 피로도
같은 던전은 한번씩만 입장가능
최대한 많은 던전 입장하기

==========
중복을 허락하지 않고 순서 신경쓰며 뽑기 = permutation 
"""
from itertools import permutations
 
def solution(k, dungeons):
    answer = 0
    idxList=  [i for i in range(len(dungeons))]
    flag = False
    for r in range(len(dungeons), -1, -1):
        if flag:
            break
        for cand in permutations(idxList, r):
            now = k
            count = 0
            for i in cand:
                minFatigue, consumeFatigue = dungeons[i]
                if now >= minFatigue:
                    now-=consumeFatigue
                    count += 1
                else:
                    break  
            answer = max(answer, count)
            if answer == r:
                flag = True
                break
    return answer

print(solution(80, [[80, 20], [50, 40], [30, 10]]))