"""
뒤에 기능은 앞 기능이 완성될때 같이 배포
progresses: 현재 진척도, 100되면 배포가능
speeds: 하루 진척도
return: 배포시 묶어 배포할 수 있는 기능 수들을 담아 리턴
"""

from collections import deque

def solution(progresses, speeds):
    answer = []
    progressesQueue = deque(progresses)
    speedsQueue = deque(speeds)
    count = 0
    day = 1
    while progressesQueue:
        if progressesQueue[0] + day*speedsQueue[0] >= 100:
            progressesQueue.popleft()
            speedsQueue.popleft()
            count += 1
        else:
            if count > 0:
                answer.append(count)
                count = 0
            day += 1
    answer.append(count)
    return answer