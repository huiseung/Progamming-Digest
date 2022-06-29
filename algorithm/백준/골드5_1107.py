"""
채널은 0 ~ 무한대 있다
+ 누르면 채널 1 증가
- 누르면 채널 1 감소, 0에선 감소 하지 않는다
0 ~ 9 숫자로도 채널 이동 가능
현재 채널 100 에서 목표 채널 N = target 까지 이동하기 위해 필요한 최소 버튼 클릭 수
N <= 500,000
M <= 10, 고장난 숫자 버튼 수
"""

target = int(input())
nDown = int(input())
if nDown:
    downSet = set(input().split())
else:
    downSet = set()
answer = 0


def solution(downSet, target):
    global answer
    nowNum = 100
    answer = abs(nowNum - target)
    for num in range(1111111):
        stringNum = str(num)
        flag = True
        for n in stringNum:
            if n in downSet:
                flag = False
                break
        if flag:
            answer = min(answer, len(stringNum) + abs(num - target))
    return answer
print(solution(downSet, target))

