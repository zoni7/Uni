from heapq import heappush, heappop


#####################################################
## class Queue: 
## Autor: Alfons Crespo
## Version 1.0
## date 08/11/20
##
#####################################################

class Queue:

# Queue: models a queue
  #    qid: queue identifier del proceso
  #    prio: queue priority
  #    policy: queue policy (RR, FCFS, PRIO, SJF, SRTF)
  #    param: quantum in RR (optional)
  # -------------------------------------------------------------------------

#Constructor
    def __init__(self, qid, prio, policy, param):  
        self.qid = qid
        self.prio = prio
        self.policy = policy
        self.quantum = param
        self.queue = []

#Observers
    def qParams(self):
        return (self.qid, self.prio, self.policy, self.quantum)

    def qId(self):
        return self.pid

    def qPolicy(self):
        return self.policy  

    def qPrio(self):
      return self.prio

    def qParams(self):
        return self.quantum

    def qShow(self):
        return "Q(" +self.qid+ ", " +str(self.policy)+ ", " +str(self.prio)+ ", " +str(self.quantum)+ ")"
    
    def qPush(self, item):
        heappush(self.queue, item)

    def qPop(self):
        return heappop(self.queue)

    def qSize(self):
        return len(self.queue) 

    def qList(self):
        txt = "["
        for i in range(len(self.queue)):
            txt = txt + self.queue[i][1][0] + ";"
        return txt + "]"

    def qDump(self):
        t = ""
        for i in range(len(self.queue)):
            t += self.queue[i][1][0]+" "
        return t


