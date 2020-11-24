class Process:

#####################################################
## class Process: 
## 
## Version 0.1
## date 08/11/20
##
#####################################################

# Process model
  #    pid: process identifier
  #    arrival: arrival time
  #    prio: process priority
  #    bursts: sequence of cpu, io bursts
  # -------------------------------------------------------------------------

#Constructora
    def __init__(self, pid, tarr, prio, bursts):  
        self.pid = pid
        self.arrival = tarr
        self.prio = prio
        self.actions = bursts

#Observadora
    def procParams(self):
        return (self.pid, self.arrival, self.prio, self.actions)

    def procId(self):
        return self.pid

    def procArrival(self):
        return self.arrival  

    def procPrio(self):
      return self.prio

    def procBursts(self):
        return self.actions

    def procBurst(self, n):
        if (n < len(self.actions)):
            return self.actions[n]
        else:
            return None

    def show(self):
        return "P(" +self.pid+ ", " +str(self.arrival)+ ", " +str(self.prio)+ ", " +str(self.actions)+ ")"
    

