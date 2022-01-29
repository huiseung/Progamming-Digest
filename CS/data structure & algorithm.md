# 목차
- [목차](#목차)
- [input/output](#inputoutput)
- [Bruth Forth](#bruth-forth)
  - [경우의 수 후보 생성](#경우의-수-후보-생성)
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
- [Tree](#tree)
  - [순회](#순회)
    - [이진 트리 전위, 후위, 중위 순회](#이진-트리-전위-후위-중위-순회)
    - [트리 높이 구하기(가장 긴 root-leaf 경로 길이)](#트리-높이-구하기가장-긴-root-leaf-경로-길이)
    - [가장 긴 leaf-leaf 길이](#가장-긴-leaf-leaf-길이)
  - [Binary Search Tree](#binary-search-tree)
  - [Heap](#heap)
  - [Segment Tree](#segment-tree)
  - [union find disjoint set](#union-find-disjoint-set)
  - [Trie](#trie)
- [Graph](#graph)
  - [구현](#구현)
    - [인접행렬](#인접행렬)
    - [인접리스트](#인접리스트)
  - [DFS](#dfs)
  - [BFS](#bfs)
  - [Shortest Path](#shortest-path)
  - [Minimum Spanning Tree](#minimum-spanning-tree)
  - [Maximum Flow](#maximum-flow)

# input/output

# Bruth Forth
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

### 트리 높이 구하기(가장 긴 root-leaf 경로 길이)


### 가장 긴 leaf-leaf 길이

## Binary Search Tree

## Heap

## Segment Tree

## union find disjoint set

## Trie

# Graph
## 구현
### 인접행렬

### 인접리스트
## DFS

## BFS

## Shortest Path

## Minimum Spanning Tree

## Maximum Flow 