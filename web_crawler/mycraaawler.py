#!/usr/bin/python3


import urllib2
import argparse


from BeautifulSoup import BeautifulSoup as bs


def Enlances (nivel,asterisko,url):
    try:
        user_agent = " Mozilla /5.0 ( X11 ; U ; Linux x86_64 ; en -US ) AppleWebKit /534.7 ( KHTML , like Gecko ) Chrome/7.0.517.41 Safari /534.7 "

        _opener = urllib2.build_opener()
        
        urllib2.install_opener(url)


        _opener.addheaders = [('User - agent',user_agent ) ]
        
        
        
        raw_code =_opener.open(url).read()
        soup = bs( raw_code )
        todos_enlances = [ link ('href') for link
            in soup.findAll('a')
            if link.has_key('href')] 
    except urllib2.URLError :
        if nivel == 1:
            print "Something went wrong,very low level,please retry"
        return   
    
    for link in todos_enlances : 
        #print link  
        if nivel < deep :   
           Enlances(nivel+1,asterisko+'*',link)  
           print "Ante gamhsou"
          
        

parser = argparse.ArgumentParser(description = "Let's craawl the web guys!")

parser.add_argument('url', nargs = 1, help = 'target url')

parser.add_argument('-n' , '--number-of-levels', type = int , default = 1 , help = 'how deep')

args = parser.parse_args()

target_url = args.url.pop()

deep = args.number_of_levels

Enlances(1,"*",target_url) 

    



