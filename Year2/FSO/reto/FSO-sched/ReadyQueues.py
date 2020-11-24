#module  ReadyQueues:

#####################################################
## module ReadyQueue: 
## 
## Version 0.1
## date 08/11/20
##
#####################################################


from Queue import Queue

rQDict = {}
rQList = []

def defineReadyQueue(qid, priority, policy, param): 
    if (not rQDict.has_key(qid)):
        q = Queue(qid, priority, policy, param)
        rQDict[qid] = q
        if (len(rQList) > 0):
            inserted = False
            for i in range(len(rQList)):
                if (priority < rQDict[rQList[i]].qPrio()):
                    rQList.insert(i, qid)
                    inserted = True
                    break
        if ((len(rQList) == 0) or (not inserted)):
            rQList.append(qid)
        return True
    else:
        return False

def defineIOQueue(qid, priority, policy, param): 
    return defineReadyQueue(qid, priority, policy, param)

def rQPrioList():
    return rQList

def rQGet(qid):
    return rQDict[qid].procParams()

def rQAll():
    return rQDict

def rQids():
    return rQDict.keys()

def rQIsEmptyCPU():
    for q in rQList:
        if ("Q" in q):
            if (rQDict[q].qSize() > 0):
                return False
    return True

def rQFirstQueueCPU():
    for q in rQList:
        if ("Q" in q):
            if (rQDict[q].qSize() > 0):
                return q
    return None

def rQAsList():
    tlist = []
    for t in (rQDict.keys()):
        tlist.append(rQDict[t].qParams())
    return tlist

def rQPolicy(qid):
    return rQDict[qid].qPolicy()

def rQPolicy1(qid):
    if ("RR" in rQDict[qid].qPolicy()):
        return rQDict[qid].qPolicy() + " ("+str(rQParams(qid))+")"
    return rQDict[qid].qPolicy()

def rQPriority(qid):
    return rQDict[qid].qPrio()

def rQParams(qid):
    return rQDict[qid].qParams()

def rQNumber():
    return len(rQDict)
	
def rQShow(qid):
	return rQDict[qid].qShow()

def rQShowAll():
    st = ""
    for t in (rQDict.keys()):
        st += rQDict[t].qShow()
    return st

def rQPush(qid, item):
    rQDict[qid].qPush(item)

def rQPop(qid):
    return rQDict[qid].qPop()

def rQSize(qid):
    return rQDict[qid].qSize()

def rQContent(qid):
    if (qid != "Idle"):
        return rQDict[qid].qList()
    else:
        return "Idle"

def rQDump(pat):
    txt = ""
    for t in (rQDict.keys()):
        if (pat in t):
            txt += rQDict[t].qDump()
    return txt

def rQPrintAll():
    txt = ""
    for t in (rQList):
        if (rQDict[t].qPrio() < 6):
            txt += t+":"+rQDict[t].qList()
    return txt