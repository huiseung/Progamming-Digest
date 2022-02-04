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
        print("!! ", vertexLeft, numIndex, vertexRight, vertex, tree[vertex])
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
