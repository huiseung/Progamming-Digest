a = [1, 5, 1, 3]
b = "123"
string = ""
_dict = {}
for i, num in enumerate(sorted(list(set(a)))):
    _dict[num] = i
for val in a:
    string += b[_dict[val]]
print(string)
