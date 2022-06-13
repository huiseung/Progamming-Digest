"""
올바른 괄호 문자열 정의
(), [], {} 은 올바른 괄호 문자열
A가 올바른 괄호 문자열일때 , (A), [A], {A}는 올바른 괄호 문자열
A, B가 올바른 괄호 문자열일때, AB는 올바른 괄호 문자열

주어진 문자열 s를 셔플해서 나올 수 있는 문자열중 올바른 괄호 문자열인 갯수 리턴
"""



def shuffle(s):
    string = s[1:] + s[0]
    return string

def check(s):
    stack = []
    for c in s:
        if len(stack) > 0:
            if c == ")" and stack[-1] == "(":
                stack.pop()
            elif c == "]" and stack[-1] == "[":
                stack.pop()
            elif c == "}" and stack[-1] == "{":
                stack.pop()
            else:
                stack.append(c)
        else:
            stack.append(c)
    if len(stack) == 0:
        return True
    else:
        return False
        

def solution(s):
    answer = 0
    n = len(s)
    if n % 2 == 1:
        return 0
    for _ in range(n):
        s = shuffle(s)
        if check(s):
            answer += 1
    return answer