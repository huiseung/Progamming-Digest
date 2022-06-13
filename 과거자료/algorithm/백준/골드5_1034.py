
"""
직사각형
열 단위로 전구가 켜지고 꺼짐
k번의 스위치 작동
켜진 행을 최대값 리턴

N = nROw <= 50
M = nCol <= 50
k <= 1000
1: 켜짐, 0: 꺼짐

"""

nRow = 0
nCol = 0
k = 0

from itertools import product

def print_matrix(matrix):
    for row in matrix:
        print(row)
    print()

def search(matrix, step):
    global nRow, nCol, k
    pass

def solution(matrix):
    global nRow, nCol, k
    

nRow, nCol = map(int, input().split())
matrix = []
for _ in range(nRow):
    row = list(map(int, list(input())))
    matrix.append(row)
k = int(input())
