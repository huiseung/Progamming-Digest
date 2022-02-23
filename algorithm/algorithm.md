# 목차
- [목차](#목차)
- [숫자칸 채우기, 돌리기, 뒤집기](#숫자칸-채우기-돌리기-뒤집기)
  - [문제 파악](#문제-파악)
  - [풀이](#풀이)
  - [예제](#예제)
- [완전탐색](#완전탐색)
  - [문제 파악](#문제-파악-1)
  - [풀이](#풀이-1)
  - [예제](#예제-1)
- [BackTracking](#backtracking)
  - [문제 파악](#문제-파악-2)
  - [풀이](#풀이-2)
  - [예제](#예제-2)
- [Greedy Algorithm](#greedy-algorithm)
  - [문제 파악](#문제-파악-3)
  - [풀이](#풀이-3)
  - [예제](#예제-3)
- [Dynamic Programming](#dynamic-programming)
  - [문제 파악](#문제-파악-4)
  - [풀이](#풀이-4)
  - [예제](#예제-4)
- [Parameter Search](#parameter-search)
  - [문제 파악](#문제-파악-5)
  - [풀이](#풀이-5)
  - [예제](#예제-5)
- [BFS 2차원 최단거리](#bfs-2차원-최단거리)
  - [문제 파악](#문제-파악-6)
  - [풀이](#풀이-6)
  - [예제](#예제-6)
- [상태공간트리](#상태공간트리)
  - [문제 파악](#문제-파악-7)
  - [풀이](#풀이-7)
  - [예제](#예제-7)
- [누적합](#누적합)
  - [문제 파악](#문제-파악-8)
  - [풀이](#풀이-8)
  - [예제](#예제-8)

------

# 숫자칸 채우기, 돌리기, 뒤집기
## 문제 파악
- 달팽이 채우기
- 정삼각형 채우기
- 직사각형 태두리 돌리기

## 풀이

## 예제


------

# 완전탐색
## 문제 파악

## 풀이


## 예제
- 프로그래머스 lv2 피로도
  - permutations

------
# BackTracking
## 문제 파악
- 최적화 문제

## 풀이
![](./image/3branch-dfs.PNG)

## 예제


------
# Greedy Algorithm
## 문제 파악
- 최적화 문제
- 최적 부분 구조
  - 작게 쪼갠 부분 문제에 최적해를 합쳐 큰 문제에 최적해를 만들 수 있다
- 탐욕 선택
  - 부분 문제에 최적해 구성은 추후 해 구성에 영향을 주지 않는다

## 풀이

## 예제

------

# Dynamic Programming
## 문제 파악

## 풀이

## 예제

------

# Parameter Search
## 문제 파악
- 최적화 문제
- 최적화 문제를 결정 문제(현재 해가 조건에 맞습니까)로 바꾸기
- 이분 탐색으로 최적해를 찾다
- 시간복잡도 lgN

- 조건을 만족하는 최소 해 찾기
  - 해 x는 음이 아닌 정수
  - 해 x에 상한, 하한이 있다
  - 해 x가 조건 충족시, x보다 큰 모든 음이 아닌 정수대해서도 조건을 만족
  - 해 x가 조건을 만족하지 않을때, x보다 작은 모든 음이 아닌 정수에 대해서도 조건을 만족하지 않는다

- 조건을 만족하는 최대 해 찾기
  - 해 x는 음이 아닌 정수
  - 해 x에 상한, 하한이 있다
  - 해 x가 조건 충족시, x보다 작은 음이 아닌 정수대해서도 조건을 만족
  - 해 x가 조건을 만족하지 않을때, x보다 큰 모든 음이 아닌 정수에 대해서도 조건을 만족하지 않는다

## 풀이
- start=하한, end=상한, mid = (start+end)//2 로 둔다
- start <= end 일 동안 다음을 반복
- mid가 조건에 맞지 않을때
  - 최소해 찾기는, start = mid로 두고 다시 시도(mid보다 작은 해도 조건을 만족하지 않으므로) 
  - 최대해 찾기는, end = mid로 두고 다시 시도(mid보다 큰 해도 조건을 만족하지 않으므로)
- 반복 결과 계산된 mid가 최소/최대 해이다

## 예제

```python
# 금과 은 운반하기
"""

"""

```


------

# BFS 2차원 최단거리
## 문제 파악

## 풀이

## 예제


------

# 상태공간트리
## 문제 파악

## 풀이

## 예제
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


------

# 누적합
## 문제 파악
- 연속된 구간에 같은 값을 더하거나 빼는 쿼리를 여러번 동작

## 풀이

![](./image/cumulSum1D.PNG)

![](./image/cumulSum2D-1.PNG)

![](./image/cumulSum2D-2.PNG)

## 예제
- 프로그래머스 lv3 파괴되지 않은 건물
