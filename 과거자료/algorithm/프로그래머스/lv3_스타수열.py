"""
다음을 만족하면 스타 수열이라 부른다
짝수 길이 arr
집합들 {arr[0], arr[1]}, {arr[2], arr[3]}, ... , {arr[2n-2], arr[2n-1]} 의 교집합이 공집합이 아니다
arr[0] != arr[1], ..., arr[2n-2] != arr[2n-1]


배열 x의 순서를 유지한체 원소를 뽑아낸 부분 수열중 가장 긴 스타 수열의 길이 리턴
스타수열이 없다면 0 리턴
"""

from collections import Counter

def solution(a):
    answer = 0
    lengthA = len(a)
    if lengthA == 1:
        return 0
    counter = Counter(a)
    for key, value in counter.items():
        if counter[key]*2 < answer:
            continue
        idx = 0
        length = 0
        while idx < lengthA-1:
            if a[idx] == key and a[idx+1] != key:
                length += 2
                idx += 2
            elif a[idx] != key and a[idx+1] == key:
                length += 2
                idx += 2
            else:
                idx += 1
        answer = max(answer, length)
    return answer
