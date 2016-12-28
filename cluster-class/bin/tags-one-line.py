#!/usr/bin/python
import sys

fname = sys.argv[1]
f = open(fname, 'r');

curr = -1
first = 1
ll = list()
for line in f:
	l = line.split(',');
	if not l[0].isdigit():
		continue

	id = int(l[0])
	if id != curr:
		curr = id
		if first == 1:
			first = 0
			continue
		print " ".join(ll)
		ll = list()

	ll.append(l[1].rstrip())
print " ".join(ll)

f.close()
