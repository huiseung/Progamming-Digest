"""
문자열에 110을 옮겨 사전상 가장 앞에 오게 옮기기, 이동후 110이 또 생기면 이동할 수 있다 
"""
from collections import deque

def solution(s):
    answer = []
    for string in s:
        stack = []
        nPattern = 0
        for char in string:
            if char == "0":
                if stack[-2:] == ["1", "1"]:
                    nPattern += 1
                    stack.pop()
                    stack.pop()
                else:
                    stack.append(char)
            elif char == "1":
                stack.append(char)
        
        if nPattern == 0:
            answer.append(string)
        else:
            q = deque()
            while stack:
                if stack[-1] == "1":
                    q.append(stack.pop())
                elif stack[-1] == "0":
                    break
            while nPattern > 0:
                q.appendleft("0")
                q.appendleft("1")
                q.appendleft("1")
            while stack:
                q.appendleft(stack.pop())
            answer.append("".join(q))
    return answer
                    