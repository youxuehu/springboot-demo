#!/usr/local/bin/python
import sys

for line in sys.stdin:
    # wordcount map
    ss = line.strip().split(" ")
    for s in ss:
        if s.strip() != "":
            print "%s\t%s" % (s, 1)
