def solution(n):
    answer = ''
    numList = []
    if n < 3:
        return str(n)        
    while n >= 3:
        quotient = n //3
        remainder = n % 3
        if remainder == 0:
            quotient -= 1
            remainder = 4
        numList.append(str(remainder))            
        n = quotient
    if n != 0:
        numList.append(str(n))
    numList = numList[::-1]
    answer=  "".join(numList)
    return answer