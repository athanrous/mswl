#!/usr/bin/python3

import urllib
import urllib2
import argparse


            
        
from BeautifulSoup import BeautifulSoup as bs

parser = argparse.ArgumentParser(description = "Let's craawl the web guys!")

parser.add_argument('url', nargs = 1, help = 'target url')

parser.add_argument('-n' , '--number-of-levels', type = int , default = 1 , help = 'how deep')

args = parser.parse_args()

target_url = args.url.pop()

deep = args.number_of_levels

page = urllib2.urlopen(target_url)

user_agent = " Mozilla /5.0 ( X11 ; U ; Linux x86_64 ; en -US ) AppleWebKit /534.7 ( KHTML , like Gecko ) Chrome/7.0.517.41 Safari /534.7 "

_opener = urllib2.build_opener ()

_opener.addheaders = [('User - agent' , user_agent ) ]

raw_code =_opener.open(target_url).read() # Toda la informacion de la pagina esta aqui

soup = bs(raw_code)
todos_enlances = [link ('href')
                  for link in soup.findAll('a')
                  if link.has_key('href')] # Aqui hago el "for" y el "if" en la misma manera con las trasparencias
#
#raw_code =_opener.open(target_url).read() # Toda la informacion de la pagina esta aqui


#
#for link in soup.findAll('a') :
 #   if link.has_key('href') :  
  #      a = link.get('href')
   #     print a

global asterisk
asterisk = ' * '

def Enlances (nivel,asterisko,target_url):
        for a in link : 
            print asterisk , a
            if nivel == 1 :
                    Enlances(1,asterisko,target_url)
            if nivel < deep :    
                    Enlances(nivel+1,asterisko+'*',a)
        
       
    
            
           
             
                    
        