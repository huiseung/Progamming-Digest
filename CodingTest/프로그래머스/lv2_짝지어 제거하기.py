"""

"""

def solution(s):
    answer = -1
    stack = []
    for char in s:
        if len(stack) == 0:
            stack.append(char)
        elif stack[-1] == char:
            stack.pop()
        else:
            stack.append(char)
    if len(stack) == 0:
        return 1
    return 0