
"""
변경 전
data = [
    0, [1, 'a'],
    1, [2, 'b'],
    2, [3, 'b'],
    3, [1, 'c'],
    4, [7, 'c'],
    5, [8, 'c'],
    6, [9, 'c'],
    7, [9, 'd']
]

변경 후
[
    0, [1, 'a'],
    1, [2, 'b'],
    2, [3, 'b'],
    3, [1, 'a'],
    4, [7, 'a'],
    5, [8, 'a'],
    6, [9, 'a'],
    7, [9, 'a']
]   

규칙
설명에 편의상 data에 자료구조를 데이터 프레임으로 보자
첫번째 열 값이 같으면 두번째 열값을 같게 한다. 
이때 같게 하는 값을 A라 하자(사전순상 앞에 오는 것).
두번째 열값이 같은 애들도 따라서 A로 바꿔준다 
"""
data = [
    [1, 'a'],
    [2, 'b'],
    [3, 'b'],
    [1, 'c'],
    [7, 'c'],
    [8, 'c'],
    [9, 'c'],
    [9, 'd']
]

def solution(data):
    cache = {}
    changeDict = {}
    ret = []
    for key, value in data: 
        inputVal = value
        if key in cache:
            changeValue = cache[key]
            while changeValue in changeDict:
                changeValue = changeDict[changeValue]
            changeDict[value] = changeValue
        if value in changeDict:
            inputVal = changeDict[value]
        if key not in cache:
            cache[key] = value
        ret.append([key, inputVal])
    return ret

print(solution(data))