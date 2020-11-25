#!/usr/bin/python

#####################################################
## main scheduler program for FSO scheduling policies
## Autor: Alfons Crespo
## Version 1.0
## date 08/11/20
##
#####################################################

import sys
import os
import random
import getopt
from heapq import heappush, heappop

import System
import ReadyQueues as RQ

readyQueue = []
ioQueue = []
CPU = None
IO = None

procCPU = None
procIO = None

finishProc = {}
statProc = {}

nquanta = 0
prevProc = ""

def usage ():
    print ("Usage: python FSOscheduler.py [-c number] [-p RR|FCFS|SJF|SRTF] [-q number] [-v]\n")

def getOpts(argv):
    policy = "FCFS"
    q = 1
    verbose = False
    caso = 1

    try:
        opts, args = getopt.getopt(sys.argv[1:], "c:p:q:v", ["caso=","policy=","quantum=","verbose"])
    except getopt.GetoptError as err:
        # print help information and exit:
        print (str(err))  # will print something like "option -a not recognized"
        usage()
        sys.exit(2)
    for o, a in opts:
        if o in ("-c", "--caso"):
            caso = int(a)
        elif o in ("-p", "--policy"):
            policy = a
        elif o in ("-q", "--quantum"):
            q = int(a)
        elif o in ("-v", "--verbose"):
            verbose = True
        else:
            assert False, "unhandled option"
    # ...
    return  (caso, policy, q, verbose)

def procName(pr):
    if (pr == None):
        return "Idle"
    else:
        return pr[1][0]

def procKey(pr):
    if (pr == None):
        return "Idle"
    else:
        return pr[0]

def procExec(pr):
    if (pr == None):
        return " (-/-)"
    else:
        return " ("+str(pr[1][1])+"/"+str(pr[1][2]+1)+")"

def procQueue(pr):
    if (pr == None):
        return "Idle"
    else:
        return pr[1][5]

def procIOName(pr):
    if (pr == None):
        return "Idle"
    else:
        return pr[1][5]

def procKey(clock, qid, currentProc):
    qPolicy = RQ.rQPolicy(qid)
    if (qPolicy == "RR"):
        return clock
    elif (qPolicy == "PRIO"):
        return procKey(currentProc)
    elif (qPolicy == "FCFS"):
        return procKey(currentProc)
    elif (qPolicy == "SJF"):
        return currentProc[1][1]
    elif (qPolicy == "SRTF"):
        return (currentProc[1][1] - currentProc[1][2])

def selectProcessCPU(clk):
    global procCPU, nquanta, prevProc
    
    if (RQ.rQIsEmptyCPU()):
        return 

    if (procCPU == None):
        q = RQ.rQFirstQueueCPU()
        procCPU = RQ.rQPop(q)


def selectProcessIO():
    global procIO
    if (RQ.rQSize("IO") == 0):
        return
    if (procIO == None):
        procIO = RQ.rQPop("IO")

def traceExecution(clk, pCPU, pIO):
    txt = str(clk)
    if (pCPU != None):
        qid = procQueue(pCPU)
        t = "\t" + RQ.rQPrintAll()
        t = t.ljust(20)
        txt += t + "\tCPU: " + procName(pCPU) + str(procExec(pCPU))
    else:
        t = "\t" + RQ.rQPrintAll()
        t = t.ljust(20)
        txt += t + "\tCPU: Idle" + "    "
    if (pIO != None):
        qIO = procQueue(pIO)
        t = "\tIOQ: " + RQ.rQContent(qIO)
        t = t.ljust(10)
        txt += t  + "\tIO: " + procName(pIO) + str(procExec(pIO))
    else:
        txt += "\tIOQ: []     \tIO: Idle  "
    print (txt)

def accounting(pid,piod):

    if ((pid != None) and (pid != "Idle")):
        (cntCPU, a, b, c) = statProc[pid]
        cntCPU += 1
        statProc[pid] = (cntCPU, a, b, c)
    if ((piod != None) and (piod != "Idle")):
        (a, cntIO, b, c) = statProc[piod]
        cntIO += 1
        statProc[piod] = (a, cntIO, b, c)
    pInRQ = RQ.rQDump("Q")
    for p in System.procPids():
        if (p in pInRQ):
            (a, b, cnt, c) = statProc[p]
            cnt += 1
            statProc[p] = (a, b, cnt, c)
    pInRQ = RQ.rQDump("IO")
    for p in System.procPids():
        if (p in pInRQ):
            (a, b, c, cnt) = statProc[p]
            cnt += 1
            statProc[p] = (a, b, c, cnt)

###problem specific
def promotion(cnt):
    return "Q0"



#-------------------------#
#          MAIN           #
#-------------------------#
def main (argv):
    global procCPU, procIO, nquanta

    (caso, politica, quantum, verbose) = getOpts(argv)


    #proc pid, arrival, prio, (bursts)
    if (caso == 1):
        System.defineProc("P1", 0, 4, (4,2,2,2,1))
        System.defineProc("P2", 3, 3, (4,2,1,2,1))
        System.defineProc("P3", 4, 2, (3,1,2,1,2))
        System.defineProc("P4", 5, 1, (5,1,1))

    if (caso == 2):
        System.defineProc("P1", 0, 0, (4))
        System.defineProc("P2", 0, 0, (5))
        System.defineProc("P3", 0, 0, (4,1,4))
        System.defineProc("P4", 0, 0, (2,1,2,1,2,1,4))

    if (caso == 3):
        System.defineProc("P1", 0, 2, (1, 5, 3))
        System.defineProc("P2", 2, 3, (3, 3, 1))
        System.defineProc("P3", 3, 1, (2, 2, 1))

    if (caso == 4):
        System.defineProc("P1", 0, 2, (6, 2, 6))
        System.defineProc("P2", 0, 3, (6, 2, 6))
        System.defineProc("P3", 0, 1, (6, 2, 6))

    if (caso == 5):
        System.defineProc("P1", 0, 0, (4, 1, 4))
        System.defineProc("P2", 0, 1, (1, 1, 1))
        System.defineProc("P3", 0, 2, (8, 1, 8))
        System.defineProc("P4", 0, 3, (3, 1, 3))


    if (caso < 10):
        RQ.defineReadyQueue("Q0", 1, politica, quantum)   


    #problema examen 1 -----------------------------
    if (caso == 10):
        System.defineProc("A", 0, 0, (3,1,3,3,1))
        System.defineProc("B", 1, 1, (1,4,3,1,1))
        System.defineProc("C", 3, 3, (5))
        System.defineProc("D", 4, 3, (2))

    if (caso == 10):
        RQ.defineReadyQueue("Q0", 3, "RR", 1)
        RQ.defineReadyQueue("Q1", 2, "RR", 2)
        RQ.defineReadyQueue("Q2", 1, "FCFS", 1)

    firstQueue = "Q0"
    #problema examen-----------------------------

    # System.defineProc("A", 0, 0, (50,2,50))
    # System.defineProc("B", 0, 0, (50,2,50))
    # System.defineProc("C", 0, 0, (50,2,50))

    policyFQ = RQ.rQPolicy(firstQueue)

    RQ.defineIOQueue("IO", 10, "FCFS", None) 
    #define the queue to send processes finishing IO 
    IOQueue = "IO"

    procCPU = None
    procIO = None

    nprocs = System.procNumber()
    procCNT = {}
    for p in System.procPids():
        procCNT[p] = 0
        statProc[p] = (0,0,0,0) #cpu, io, rq,ioq

    print ("==========================================")
    print (System.procPrint(),)
    print ("==========================================")
    print ("Number of Queues: ", RQ.rQNumber())
    for q in RQ.rQids():
        print (q + "  policy: "+ RQ.rQPolicy1(q) + "  prio: "+ str(RQ.rQPriority(q)))
    print ("==========================================")
    nfinished = 0
    clock = 0

    while (True):

    ##add at arrival time process to the queue
        for pid in System.procPids():
            arrival = System.procArrival(pid)
            if (arrival == clock):
                prio  = System.procPriority(pid)
                if (policyFQ == "PRIO"):
                    key = prio
                    key2 = arrival
                elif ((policyFQ == "SJF") or (policyFQ == "SRTF")):
                    key = System.procBurst(pid, 0)
                    key2 = key
                elif (policyFQ == "FCFS"):
                    key = arrival
                    key2 = prio
                else:
                    key = arrival
                    key2 = 0
                execTime = System.procBurst(pid, 0)
                elem = ((key, key2), (pid, execTime, 0, 0, 0, firstQueue))
                RQ.rQPush(firstQueue, elem)


        if (procCPU == None):
            selectProcessCPU(clock)
        selectProcessIO()
 
        if (verbose):
            traceExecution(clock, procCPU, procIO)
        accounting(procName(procCPU), procName(procIO))

        clock += 1


        qIO = procIOName(procIO)

        if (procIO != None):
            ((key, key2), (pid, ncycles, executed, nburst, nqtm, qid)) = procIO
            executed += 1
            if (executed == ncycles):
                nburst += 1   ##increase the burst
                execTime = System.procBurst(pid, nburst)
                procCNT[pid] += 1
                destRQ = promotion(procCNT[pid])
                ##IO is always FCFS
                if (RQ.rQPolicy(destRQ) == "PRIO"):
                    key = System.procPriority(pid)
                elif ((RQ.rQPolicy(destRQ) == "SJF") or (RQ.rQPolicy(destRQ) == "SRTF")):
                    key = execTime
                else: 
                    key = clock
                elem = ((key, key2), (pid, execTime, 0, nburst, nqtm, destRQ))
                RQ.rQPush(destRQ, elem)
                procIO = None
            else:
                procIO = ((key, key2), (pid, ncycles, executed, nburst, nqtm, qid))


        #Post execution
        if (procCPU != None):
            ((key, key2), (pid, ncycles, executed, nburst, nqtm, qid)) = procCPU
            executed += 1
            #exhausted the execution time?
            if (executed == ncycles):
                nburst += 1   ##increase the burst
                execTime = System.procBurst(pid, nburst)
                if (execTime == None):  #finished process
                    nfinished += 1
                    finishProc[pid] = clock
                else:
                    elem1 = ((clock, clock), (pid, execTime, 0, nburst, 0, IOQueue))
                    RQ.rQPush(IOQueue, elem1)
                procCPU = None
            elif (RQ.rQPolicy(qid) == "RR"):
                quantum = RQ.rQParams(qid)
                nqtm += 1
                if (quantum == nqtm):
                    key = clock
                    nqtm = 0
                    elem = ((clock, clock), (pid, ncycles, executed, nburst, 0, qid))
                else:
                    elem = ((key, key2), (pid, ncycles, executed, nburst, nqtm, qid))
                RQ.rQPush(qid, elem)
                procCPU = None
            elif (RQ.rQPolicy(qid) == "SRTF"):
                elem = ((key, key2), (pid, ncycles, executed, nburst, nqtm, qid))
                RQ.rQPush(qid, elem)
                procCPU = None
            else:  ## FCFS and SJF are non - premptive
                elem = ((0, 0), (pid, ncycles, executed, nburst, nqtm, qid))
                RQ.rQPush(qid, elem)
                procCPU = None

      
        if(nfinished == System.procNumber()):
            break
    print ("==========================================")
    print ("Proc.  Finish  CPU\tIO    RQue   IOQue")
    for key in sorted(finishProc):
        print (key, "\t", finishProc[key], "\t", statProc[key][0], "\t", statProc[key][1], "\t", statProc[key][2], "\t", statProc[key][3])
    print ("==========================================")

main (sys.argv)
