#!/usr/bin/python

import urllib2
import argparse


from BeautifulSoup import BeautifulSoup as Sp


def obtainLink(nivel,simbolo,enlance):
    try:
        page = urllib2.urlopen(target_url)
        
        soupa = Sp ( page )
        links = [ link ('href') for link
            in soupa.findAll('a')
            if link.has_key('href')]
    except urllib2.URLError :
        if nivel == 1:
            print "Something went wrong,please retry"
        return
    for link in links :
        print simbolo+"-"+link
    
    if nivel < deep :
            obtainLink(nivel+1,simbolo+'-',link)
           


parser = argparse.ArgumentParser(description = "Let's craawl the web guys!")

parser.add_argument('url', nargs = 1, help = 'target url')

parser.add_argument('-n' , '--number-of-levels', type = int , default = 1 , help = 'how deep')

args = parser.parse_args()

target_url = args.url.pop()

deep = args.number_of_levels


user_agent = " Mozilla /5.0 ( X11 ; U ; Linux x86_64 ; en -US ) AppleWebKit /534.7 ( KHTML , like Gecko ) Chrome/7.0.517.41 Safari /534.7 "
_opener = urllib2.build_opener()
_opener.addheaders = [('User - agent',user_agent ) ]
obtainLink(1,"-",target_url) 



