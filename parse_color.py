#!/usr/bin/python
import csv

def parse_file(floor, file_path) :
  f = open(file_path)

  data = []
  for line in f:
      data_line = line.rstrip().split(';')
      data.append(data_line)
  for data_splitted in data:
    print "color = Color.parseColor(\"" + data_splitted[1] + "\");"
    print "roomDao.insert(new Room(\"" + data_splitted[0] +"\", " + floor + ", Color.red(color), Color.green(color), Color.blue(color)));"

parse_file("2", "2sale-kolor.csv");