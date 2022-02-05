from itertools import combinations, permutations, product, combinations_with_replacement



population = [1, 2, 3, 4]
r = len(population)
cand_list = product(population, repeat=r)
for cand in cand_list:
    print(cand)