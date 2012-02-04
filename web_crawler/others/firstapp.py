#!/usr/bin/python

class Movie:
    def __init__(self,title,duration,price):
        self.title = title
        self.duration = duration
        self.price = price
    
    def showTitle(self):
        return self.title
    
    def showDuration(self):
        return self.duration
    
    def showPrice(self):
        return self.price
        
def main():
    
    
    monomaxos = Movie("Gladiator","240 mins","10 Euro")
    
    print (monomaxos.showPrice())
    print (monomaxos.showDuration())
    print (monomaxos.showTitle())
    
if __name__ == "__main__": main()


