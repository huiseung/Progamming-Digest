import re


def operation(dict1, dict2):
    inter = {}
    union = {key: value for key, value in dict1.items()}
    for key, value in dict2.items():
        if key in dict1:
            inter[key] = min(dict1[key], value)
            union[key] = max(dict1[key], value)
        else:
            union[key] = value    
    return inter, union

def J(inter, union):
    numerator = 0
    denominator = 0
    for value in union.values():
        denominator += value
    if denominator == 0:
        return 1
    for value in inter.values():
        numerator += value
    return numerator/denominator

def postProcessing(answer):
    BING_INT = 65536
    return int(answer*BING_INT)

def makeDict(string):
    ret = {}
    p = re.compile("[a-z]")
    for char1, char2 in zip(string[:-1], string[1:]):
        if p.match(char1) and p.match(char2):
            key = char1+char2
            if key not in ret:
                ret[key] = 0
            ret[key] += 1
    return ret
     
def solution(str1, str2):
    answer = 0
    str1 = str1.lower()
    str2 = str2.lower()
    #print("lower: ", str1, " ", str2)
    dict1 = makeDict(str1)
    dict2 = makeDict(str2)
    #print("dict1: ", dict1)
    #print("dict2: ", dict2)
    inter, union = operation(dict1, dict2)
    #print("inter: ", inter)
    #print("union: ", union)
    answer = postProcessing(J(inter, union))               
    return answer