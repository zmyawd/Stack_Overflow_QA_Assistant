#!/usr/bin/python

COMMAS = 5

finame = sys.argv[1]
fi = open(finame, 'r');
fcname = sys.argv[2]
fc = open(fcname, 'r');

ll = list()
for line in fi:
	l = line.split(',');
	x = int(l[0])
	ll.append(x)
ll.append(0)

i = 0
j = 0
k = ll[i]
flag = 0
for line in fc:
	j += 1
	if j == k:
		i += 1
		k = ll[i]
		print ""

		# we only need data after COMMAS commas
		c = 0
		cc = 0
		while 1:
			if line[c] == ',':
				cc += 1
				if cc == COMMAS:
					break
			c += 1
		c += 1
		pl = line[c:]
		print pl.rstrip(),
		continue
	print line.rstrip(),
print ""

fi.close()
fc.close()
