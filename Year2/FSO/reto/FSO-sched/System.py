#module  System:

#####################################################
## module System: 
## 
## Version 0.1
## date 08/11/20
##
#####################################################

from Process import Process
import operator

procDict = {}

def defineProc(pid, arrival, prio, actions):  # attributos: Id, arrivaliod, prioline
    if (not procDict.has_key(pid)):
        if (isinstance(actions, int)):
            lact = []
            lact.append(actions)
        else:
            lact = actions
        proc = Process(pid, arrival, prio, lact)
        procDict[pid] = proc
        return True
    else:
        return False

def procGet(pid):
    return procDict[pid].procParams()

def procAll():
    return procDict

def procPids():
    return procDict.keys()

def procList():
    tlist = []
    for t in (procDict.keys()):
        tlist.append(procDict[t].procParams())
    return tlist

def procArrival(pid):
    return procDict[pid].procArrival()

def procPriority(pid):
    return procDict[pid].procPrio()

def procBursts(pid):
    return procDict[pid].procBursts()

def procBurst(pid, n):
    return procDict[pid].procBurst(n)

def procNumber():
    return len(procDict)
	
def procShow(pid):
	return procDict[pid].show()

def procShowAll():
    st = ""
    for t in (procDict.keys()):
        st += procDict[t].show()
    return st

def procPrint():
    st = ""
    for k in sorted(procDict):
        st += procDict[k].show() + "\n"
    return st


