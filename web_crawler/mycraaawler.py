#!/usr/bin/python3


import urllib2
import argparse

from BeautifulSoup import BeautifulSoup as bs

def Enlances (nivel,asterisko,url): # This function 
    try:
        raw_code =_opener.open(url).read()
        soup = bs( raw_code )
        todos_enlances = [ link ('href') for link
            in soup.findAll('a')
            if link.has_key('href')] # In this part of code i assign the variable todos_enlances in order to receive all the url's from the page , as shown in the presentation 
    except urllib2.URLError : # I add this exception in order to avoid and refuse syntaxis url errors
        if nivel == 1:
            print "Something went wrong,very low level,please retry"
        return    
    for link in todos_enlances :
        print todos_enlances
        if nivel < deep :
            Enlances(nivel+1,asterisko+'*',link)
           
parser = argparse.ArgumentParser(description = "Let's craawl the web guys!")

parser.add_argument('url', nargs = 1, help = 'target url')

parser.add_argument('-n' , '--number-of-levels', type = int , default = 1 , help = 'how deep')

args = parser.parse_args()

target_url = args.url.pop()

deep = args.number_of_levels

user_agent = " Mozilla /5.0 ( X11 ; U ; Linux x86_64 ; en -US ) AppleWebKit /534.7 ( KHTML , like Gecko ) Chrome/7.0.517.41 Safari /534.7 "

_opener = urllib2.build_opener()
        
_opener.addheaders = [('User - agent',user_agent ) ] # In the block of code above i add the parser , the args ,user_agent,deep,target_url,  variables as shown in the presantation during the classes so as to parse the arguments and connect the variables with the command line arguments

        

Enlances(1,"*",target_url) # I call the fucnction Enlances as  a default call of the function