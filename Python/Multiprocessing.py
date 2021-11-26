#use multithreads for I/O based ops
#use multiprocessing for crunching numbers ie cpu intensives

import numpy
import concurrent.futures
import time 

power = 7    #how tough u wanna make the cpu run
l = []
for x in range(9):
    l.append(numpy.arange(10**power))


normal_start = time.time()

counter = 0
for values in l:
    counter += 1
    sum(values)
    print(f"done {counter}")

normal_end = time.time()


threading_start = time.time()
counter = 0
with concurrent.futures.ThreadPoolExecutor() as executer:
    results = executer.map(sum, l)

    for element in results:
        counter += 1
        print(f"done {counter}")

threading_end = time.time()

process_start = time.time()
counter = 0
with concurrent.futures.ProcessPoolExecutor() as executer:
    results = executer.map(sum, l)

    for element in results:
        counter += 1
        print(f"done {counter}")
    
process_end = time.time()


print(f"Time taken normally: {normal_end - normal_start} s")
print(f"Time taken Multithreading: {threading_end - threading_start} s")
print(f"Time taken MultiProcessing: {process_end - process_start} s")
