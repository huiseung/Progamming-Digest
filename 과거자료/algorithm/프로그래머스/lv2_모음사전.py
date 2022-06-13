"""
A E I O U 로만 만들어진 5글자 이하에 단어 사전에서
주어진 단어가 사전순으로 몇번째인가
"""



answer = 0
cnt = 0 
height = 5
Flag = False
def search(target, now):
    global answer, cnt, height, Flag
    if target == now:
        Flag = True
        answer = cnt
        return
    if Flag:
        return
    if len(now) >= height:
        return
    for i in range(5):
        cnt += 1
        now.append(i)
        search(target, now)
        now.pop()
        

def solution(word):
    global answer
    answer = 0
    charList = ["A", "E", "I", "O", "U"]
    charDict = {charList[i]: i for i in range(len(charList))}
    target = [charDict[i] for i in word]
    search(target, [])
    return answer

print(solution("EIO"))