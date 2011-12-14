#!/usr/bin/python3

import urllib
import urllib2
import argparse

from BeautifulSoup import BeautifulSoup as bs

def Enlances (nivel,asterisko,enlance):
    raw_code =_opener.open(enlance).read()
    soup = bs(raw_code)
    todos_enlances = [link ('href')
                  for link in soup.findAll('a')
                  if link.has_key('href')] # Aqui hago el "for" y el "if" en la misma manera con las trasparencias

    for a in todos_enlances : 
            print asterisko , a
            if nivel == 1 :
                    print "Something went wrong,very low level,please retry"
            if nivel < deep :    
                    Enlances(nivel+1,asterisko+'*',a) 

parser = argparse.ArgumentParser(description = "Let's craawl the web guys!")

parser.add_argument('url', nargs = 1, help = 'target url')

parser.add_argument('-n' , '--number-of-levels', type = int , default = 1 , help = 'how deep')

args = parser.parse_args()

target_url = args.url.pop()

deep = args.number_of_levels



global asterisko # Pongo global la variable porque se utilizara dentro la function
asterisko = ' * '

     

user_agent = " Mozilla /5.0 ( X11 ; U ; Linux x86_64 ; en -US ) AppleWebKit /534.7 ( KHTML , like Gecko ) Chrome/7.0.517.41 Safari /534.7 "

_opener = urllib2.build_opener ()

_opener.addheaders = [('User - agent' , user_agent ) ]

# # Toda la informacion de la pagina esta aqui

Enlances(1,asterisko,target_url) # We call the Enlances function with the level 1 because is the default level of the web pages craawled


