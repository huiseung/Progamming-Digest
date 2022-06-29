"""

n <= 1,-,-
k < n
cmd <= 200,-

U X: 현재 행에서 x칸 위 행 선택
D X: 현재 행에서 x칸 아래 행 선택
전체 사이즈를 넘어가는 이동은 없다
X <= 300,-

C: 현재 행 삭제 후 아래 행 선택(마지막 행을 삭제한경우 바로 위 행 선택)
Z: 최근 삭제 행위 되돌리기, 선택 행은 유지

cmd 수행 후 원본 중 삭제된 행에 x표시, 남아있는 행에 o표시 
"""


def solution(n, k, cmd):
    answer = ''
    linkedList = {i: {"prev": i-1, "next": i+1, "data": i} for i in range(n)}
    linkedList[0]["prev"] = n-1
    linkedList[n-1]["next"] = 0
    eraserStack = []
    
    for c in cmd:
        if c[0] == "U":
            x = int(c[2:])
            for _ in range(x):
                k = linkedList[k]["prev"]
        elif c[0] == "D":
            x = int(c[2:])
            for _ in range(x):
                k = linkedList[k]["next"]
        elif c[0] == "C":
            #
            node = linkedList[k]
            prev = node["prev"]
            next = node["next"]
            data = node["data"]
            linkedList[prev]["next"] = next
            linkedList[next]["prev"] = prev
            del linkedList[k]
            #
            eraserStack.append((k, prev, next, data))
            #
            if next == 0:
                k = prev
            else:
                k = next
        elif c[0] == "Z":
            now, prev, next, data = eraserStack.pop()
            linkedList[now] = {"prev": prev, "next": next, "data": data}
            linkedList[prev]["next"] = now
            linkedList[next]["prev"] = now        
    for i in range(n):
        if i in linkedList:
            answer += "O"
        else:
            answer += "X"
    return answer

print(solution(8, 2, ["D 2","C","U 3","C","D 4","C","U 2","Z","Z"]))