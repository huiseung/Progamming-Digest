"""

"""

pattern = "110"

def change(string):
    retString = ""
    idxList=  []
    nPattern = 0
    for i in range(len(string)):
        if string[i:i+3] == pattern:
            nPattern += 1
            idxList.append(i)
    if nPattern == 0:
        return string, nPattern
    retString += string[:idxList[0]]
    if nPattern >= 2:
        for start, end in zip(idxList[:-1], idxList[1:]):
            retString += string[start+3:end]
    hasZero = False
    for c in retString:
        if c == "0":
            hasZero = True
            break
    if hasZero:
        for _ in range(nPattern):
            retString = retString + pattern
    else:
        for _ in range(nPattern):
            retString = pattern + retString
    return retString, nPattern

def solve(string):
    pastString = string
    string, nPattern = change(string)
    if pastString == string:
        return string
    while True:
        pastString = string
        string, nPattern = change(string)
        if pastString == string:
            break
    return string

def solution(arr):
    answer = []
    for string in arr:
        answer.append(solve(string))
    return answer

print(solution(["1110","100111100","0111111010"]))