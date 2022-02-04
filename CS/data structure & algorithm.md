# 목차
- [목차](#목차)
- [자세](#자세)
- [input/outpu](#inputoutpu)
- [누적합](#누적합)
  - [배열 구간에 덧셈, 뺄셈 적용하기](#배열-구간에-덧셈-뺄셈-적용하기)
    - [2D-Matrix](#2d-matrix)
- [완전 탐색](#완전-탐색)
  - [경우의 수 후보 생성](#경우의-수-후보-생성)
  - [DFS](#dfs)
  - [Back Tracking](#back-tracking)
- [Divide and conquer](#divide-and-conquer)
- [Dynamic Programming](#dynamic-programming)
- [Greedy Algorithm](#greedy-algorithm)
- [List](#list)
  - [Linked List](#linked-list)
- [Queue](#queue)
- [Deque](#deque)
- [Stack](#stack)
- [String](#string)
- [Graph](#graph)
  - [구현](#구현)
    - [인접행렬](#인접행렬)
    - [인접리스트](#인접리스트)
  - [DFS](#dfs-1)
  - [BFS](#bfs)
  - [Shortest Path](#shortest-path)
  - [Minimum Spanning Tree](#minimum-spanning-tree)
  - [Maximum Flow](#maximum-flow)
- [Tree](#tree)
  - [순회](#순회)
    - [이진 트리 전위, 후위, 중위 순회](#이진-트리-전위-후위-중위-순회)
    - [양과 늑대 순회](#양과-늑대-순회)
    - [트리 높이 구하기(가장 긴 root-leaf 경로 길이)](#트리-높이-구하기가장-긴-root-leaf-경로-길이)
    - [가장 긴 leaf-leaf 길이](#가장-긴-leaf-leaf-길이)
  - [Binary Search Tree](#binary-search-tree)
  - [Heap](#heap)
  - [Segment Tree](#segment-tree)
    - [1D-Array Segment Tree](#1d-array-segment-tree)
    - [2D-Matrix Segment Tree](#2d-matrix-segment-tree)
  - [union find disjoint set](#union-find-disjoint-set)
  - [Trie](#trie)
# 자세


# input/outpu


# 누적합
## 배열 구간에 덧셈, 뺄셈 적용하기

```python
"""
[1, 2, 4, 8, 9]에 [0:4] 구간에 -2 적용
"""
opList = 
```

### 2D-Matrix
```python
"""
공격받으면, 내구도 감소, 파괴되도 감소 가능
회복되면 내구도 증가, 파괴되도 증가 가능
0이하면 파괴 상태
최종가서 파괴 되지 않은 갯수 리턴
"""

def solution(board, skill):
    answer = 0
    nRow = len(board)
    nCol = len(board[0])
    sumMatrix = [[0 for _ in range(nCol+1)] for _ in range(nRow+1)]
    for s in skill:
        cmd, r1, c1, r2, c2, degree = s
        if cmd == 1:
            degree *= -1
        sumMatrix[r1][c1] += degree
        sumMatrix[r2+1][c1] += -1*degree
        sumMatrix[r1][c2+1] += -1*degree
        sumMatrix[r2+1][c2+1] += degree
    for c in range(1, nCol):
        for r in range(nRow):
            sumMatrix[r][c] += sumMatrix[r][c-1]
    for r in range(1, nRow):
        for c in range(nCol):
            sumMatrix[r][c] += sumMatrix[r-1][c]
    for r in range(nRow):
        for c in range(nCol):
            if board[r][c] + sumMatrix[r][c] > 0:
                answer += 1
    return answer
```


# 완전 탐색
## 경우의 수 후보 생성
- combinations
  - 비복원, 순서 의미 없음
  - $\frac{n!}{(n-r)!r!}$
```python
from itertools import combinations
population = [1, 2, 3, 4]
r = len(population)
cand_list = combinations(population, r)
```

- permutaions
  - 순열: 비복원, 순서 의미 있음
  - $\frac{n!}{(n-r)!}$
```python
from itertools import permutaions
population = [1, 2, 3, 4]
r = len(population)
cand_list = permutations(population, r)
```

- combinations_with_replacement
  - 중복 조합: 복원, 순서 의미 없음
  - $\frac{(n+r-1)!}{(n-1)!r!}$
```python
from itertools import combinations_with_replacement
population = [1, 2, 3, 4]
r = len(population)
cand_list = combinations_with_replacement(population, r)
```

- product
  - 중복 순열: 복원, 순서 의미 있음
  - $n^r$
```python
from itertools import product
population = [1, 2, 3, 4]
r = len(population)
cand_list = product(population, repeat=r)
```
## DFS

## Back Tracking


# Divide and conquer

# Dynamic Programming

# Greedy Algorithm

# List
## Linked List

# Queue

# Deque

# Stack

# String

# Graph
- vertex
- edge
## 구현
### 인접행렬

### 인접리스트
## DFS

## BFS

## Shortest Path

## Minimum Spanning Tree

## Maximum Flow 

# Tree
- 자료간 상하위, 포함관계를 나타내는 자료구조
- 빠른 검색을 위해, 조건에 맞춰 자료를 추가, 삭제, 검색하는 자료구조
- vertex
- edge
- tree에 root
- tree에 leaf
- vertex에 parent
- vertex에 child
- vertex에 sibling
- vertex에 depth: root부터 해당 vertex까지 거치는 edge 수
- tree에 height: tree에서 가장 큰 depth

## 순회
### 이진 트리 전위, 후위, 중위 순회

### 양과 늑대 순회
```python
max_num_sheep = 0

def solve(info, graph, num_sheep, num_wolf, now_node, next_nodes):
    global max_num_sheep
    if info[now_node] == 0: 
        num_sheep += 1
    else:
        num_wolf += 1
    #
    max_num_sheep = max(max_num_sheep, num_sheep)
    #
    if(num_sheep <= num_wolf):
        return
    #
    copy_next_nodes = next_nodes.copy()
    copy_next_nodes.remove(now_node)
    for adj_node in graph[now_node]:
        copy_next_nodes.add(adj_node)
    for next_node in copy_next_nodes:
        solve(info, graph, num_sheep, num_wolf, next_node, copy_next_nodes)

def solution(info, edges):
    answer = 0
    global max_num_sheep
    graph = {i: [] for i in range(len(info))}
    for parent, child in edges:
        graph[parent].append(child)
    next_nodes = set()
    next_nodes.add(0)
    solve(info, graph, 0, 0, 0, next_nodes)
    return max_num_sheep
```

### 트리 높이 구하기(가장 긴 root-leaf 경로 길이)



### 가장 긴 leaf-leaf 길이

## Binary Search Tree

## Heap

## Segment Tree

![](../image/segmenttree.PNG)

- 숫자로 이루어진 배열이나 행렬에서 임의에 영역과 관련한 질의를 작은 시간복잡도로 처리하는 자료구조
- 숫자 변경도 가능하다
- 트리 형태로 leaf는 주어진 숫자들을, non-leaf는 자식들로 이루어진 질의 결과 값을 저장한다

### 1D-Array Segment Tree
- 1차원 배열로 표현한 이진 트리를 활용 
  - root는 index 1, i-vertex에 왼쪽 자식은 index 2i, 오른쪽 자식은 index 2i + 1에 vertex 값을 저장한다  
- 각 vertex마다 대응하는 영역이 있고, 영역에 질의를 처리한 결과를 저장한다

```python
import math

BREAK_NUM = 0
INIT_VAL = 0

def init(tree, numList, vertex, left, right):
    if left == right:
        tree[vertex] = numList[left]
        return tree[vertex]
    mid = (left+right)//2
    leftVal = init(tree, numList, vertex*2, left, mid)
    rightVal = init(tree, numList, vertex*2+1, mid+1, right)
    tree[vertex] = operation(leftVal, rightVal)
    return tree[vertex]


def operation(leftVal, rightVal):
    return leftVal + rightVal



# O(lgN)
def select(tree, b, c, n):
    # index b이상, c이하 범위의 값의 operation 결과
    return treeSelect(tree, b-1, c-1, 1, 0, n-1)


def treeSelect(tree, left, right, vertex, vertexLeft, vertexRight):
    if right < vertexLeft or vertexRight < left:
        return BREAK_NUM
    if left <= vertexLeft and vertexRight <= right:
        return tree[vertex]
    vertexMid = (vertexLeft+vertexRight)//2
    leftVal = treeSelect(tree, left, right, vertex*2, vertexLeft, vertexMid)
    rightVal = treeSelect(tree, left, right, vertex *
                          2+1, vertexMid+1, vertexRight)
    return operation(leftVal, rightVal)

# O(lgN)
def update(tree, b, c, n):
    # index b-1에 해당하는 숫자를 c로 바꿈
    return treeUpdate(tree, b-1, c, 1, 0, n-1)


def treeUpdate(tree, numIndex, newVal, vertex, vertexLeft, vertexRight):
    if numIndex < vertexLeft or vertexRight < numIndex:
        return tree[vertex]
    if vertexLeft == vertexRight:
        tree[vertex] = newVal
        return tree[vertex]
    vertexMid = (vertexLeft+vertexRight)//2
    leftVal = treeUpdate(tree, numIndex, newVal, vertex*2, vertexLeft, vertexMid)
    rightVal = treeUpdate(tree, numIndex, newVal, vertex*2+1, vertexMid+1, vertexRight)
    tree[vertex] = operation(leftVal, rightVal)
    return tree[vertex]

def printTree(tree):
    # 1
    # 2~3
    # 4~7

    for h in range(int(math.log2(len(tree)))):
        print(tree[2**h:2**(h+1)])


n, m, k = map(int, input().split(" "))

tree = [INIT_VAL for _ in range(4*n)]
numList = [int(input()) for _ in range(n)]
init(tree, numList, 1, 0, n-1)
printTree(tree)

for _ in range(m+k):
    a, b, c = map(int, input().split(" "))
    if a == 1:
        update(tree, b, c, n)
        printTree(tree)
    elif a == 2:
        print(select(tree, b, c, n))
```

### 2D-Matrix Segment Tree


## union find disjoint set

## Trie

