from bs4 import BeautifulSoup
import requests
import re, sys
titulo = sys.argv[:2]
url = "https://scholar.google.com/scholar_lookup?title="+str(titulo[1])
requisicao = requests.get(url)
website = requisicao.text
website_soup = BeautifulSoup(website,"html.parser")
citation = website_soup.find("div",{"class":"gs_r"})
citacao = str(citation)
regex = re.compile(r"Cited by([^\<]+)")
regex1 = re.search(regex, citacao).groups(0)
citacao = str(regex1).replace("(","").replace(",)","")
print(citacao)
