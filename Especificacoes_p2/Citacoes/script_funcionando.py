import sys, subprocess, re
argv = sys.argv[:2]
author = argv[1]
exe = ["/home/kevin/miniconda3/bin/python scholar.py -a "+str(author)+" -c 1"]
p = subprocess.Popen(exe, stdout = subprocess.PIPE, shell=True)
out,err = p.communicate()
resultado = str(out)
regex = re.compile(r"Citations ([0-9]+[^\n])")
try:
	regex1 = re.search(regex, resultado).groups(0)
	citacao = str(regex1).replace("(","").replace(",)","").replace("\\","")
	print(citacao)
except:
	print("0")