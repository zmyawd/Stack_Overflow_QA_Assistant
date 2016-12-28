#!/usr/bin/python

fcname = sys.argv[1]
fc = open(fcname, 'r');
fbname = sys.argv[1]
fb = open(fbname, 'r');

ll = list()
for line in fc:
	l = line.split(',');
	x = int(l[0])
	if x < 0: 
		ll.append("/c.0/"+l[1])
	elif x == 0: 
		ll.append("/c.1/"+l[1])
	elif x < 10: 
		ll.append("/c.2/"+l[1])
	else: 
		ll.append("/c.3/"+l[1])

i = 0
for line in fb:
	print ll[i]
	i += 1
	print line.rstrip()

fc.close()
fb.close()
