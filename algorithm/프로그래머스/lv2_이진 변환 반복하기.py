"""
0과 1로 이루어진 x를 변환
- x의 모든 0 제거후, x의 길이를 c, x를 c에 이진수로 대체
x가 1이 되기까지 변환 횟수와 제거된 0에 수 리턴 
"""

def eraser(string):
    nZero = 0
    ret = ""
    for s in string:
        if s == "1":
            ret += "1"
        elif s == "0":
            nZero += 1
    return ret, nZero

def change(string):
    return bin(len(string))[2:]

def solution(s):
    nChange = 0
    nZero = 0
    string = s
    while string != "1":
        string, plus = eraser(string)
        string = change(string)
        nZero += plus
        nChange += 1
    return [nChange, nZero]