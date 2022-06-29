"""
한번에 최대 2명씩, 무게제한
최소한으로 모두 이동
"""

def solution(people, limit):
    answer = 0
    sortedPeople = sorted(people)
    length = len(sortedPeople)
    start = 0
    end = length-1
    while start <= end:
        answer += 1
        if sortedPeople[start]+sortedPeople[end] <= limit:
            start += 1
        end -= 1    
    return answer