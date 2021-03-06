"""
1행1열부터 i행i열 까지의 행열에 빈칸을 i로 채우기, 1채우고 2채우고 3채우고...
행 단위로 이어붙여 1차원 배열 arr로 만들기
arr[left:right+1]만 남기기

#
1 2 3 4
2 2 3 4
3 3 3 4
4 4 4 4
#
(0, 0) (0, 1) (0, 2) (0, 3) 
(1, 0) (1, 1) (1, 2) (1, 3)
(2, 0) (2, 1) (2, 2) (2, 3)
(3, 0) (3, 1) (3, 2) (3, 3) 
"""

def solution(n, left, right):
    answer = []
    for i in range(left, right+1):
        answer.append(max(i//n,i%n)+1)
    return answer

print(solution(4, 7, 14))

