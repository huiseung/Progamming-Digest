"""
직사각형을 대각선으로 그을때
멀쩡한 1x1 정사각형 갯수

직사각형 가로 세로는 1억이하의 자연수
"""


import math

def solution(w,h):
    answer = w*h - (w+h-math.gcd(w, h))
    return answer

print(solution(8, 12))