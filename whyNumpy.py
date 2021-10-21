import os
import time
try:
    import numpy
except ModuleNotFoundError:
    print("if you dont download the package, programme might not work")

    value = input("do you want to continue.......(yes|no):").lower()
    
    if value == 'yes' or value == 'y':
        print("Downloading Package....")
        os.system("pip install numpy")
    
    else:
        print("Exiting....!! ;(")
        exit


def mySum(listArgs):
    sum = 0
    for value in listArgs:
        sum += value


numpyTime = []
mySumTime = []
pythonSumTime = []

for x in range(9):
    array_list = numpy.arange(10**x)
    begin = time.time_ns()
    numpy.sum(array_list)
    end = time.time_ns()
    numpyTime.append((end-begin)/1e6)

    begin = time.time_ns()
    sum(array_list)
    end = time.time_ns()
    pythonSumTime.append((end-begin)/1e6)

    begin = time.time_ns()
    mySum(array_list)
    end = time.time_ns()
    mySumTime.append((end-begin)/1e6)



print("in milliseconds")
print("Numpy   sum = ",numpyTime)
print("My code sum = ",mySumTime)
print("Python  sum = ",pythonSumTime)

