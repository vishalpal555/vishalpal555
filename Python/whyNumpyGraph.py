#graph showing why numpy is so good



import os
import time

from matplotlib import markers
try:
    import numpy
    from matplotlib import pyplot
except ModuleNotFoundError:
    print("if you dont download the package, programme might not work")

    value = input("do you want to continue.......(yes|no):").lower()
    
    if value == 'yes' or value == 'y':
        print("Downloading Package....")
        os.system("pip install numpy && pip install matplotlib")
    
    else:
        print("Exiting....!! ;(")
        exit()


def mySum(listArgs):
    sum = 0
    for value in listArgs:
        sum += value


numpyTime = []
mySumTime = []
pythonSumTime = []
x_graph = []

for x in range(8):
    x_graph.append(10**x)

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



pyplot.subplot(3,1,1)
pyplot.plot(x_graph, numpyTime, '-o', color='g')
pyplot.ylabel("Milliseconds")
pyplot.legend(["Numpy"])

pyplot.subplot(3,1,2)
pyplot.plot(x_graph, pythonSumTime, '-o', color='y')
pyplot.ylabel("Milliseconds")
pyplot.legend(["Python Sum"])

pyplot.subplot(3,1,3)
pyplot.plot(x_graph, mySumTime, '-o', color='r')
pyplot.xlabel(r'$\sum_{i=0}^{x} N_i$')
pyplot.ylabel("Milliseconds")
pyplot.legend(["My code for Sum"])



pyplot.show()
