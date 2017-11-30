#!/usr/bin/python3

import json
from pprint import pprint

departments = ["ME", "AG", "FSN", "PEW", "NR", "AGB", "RPTA", "CSC", "PSY", "SOC", "PHYS", "AGC", "ERSC", "TH", "MATE", "POLS", "GEOG", "SPAN", "JPNS", "SCM", "COMS", "EE", "BMED", "PSC", "ITP", "CD", "BRAE", "ARCE", "DATA", "AERO", "AGED", "AEPS", "WLC", "ES", "MSCI", "KINE", "BIO", "ANT", "MATH", "LA", "ENGL", "PEM", "CHIN", "RELS", "GRC", "JOUR", "LAES", "ENGR", "HNRS", "GSB", "SS", "MU", "ASCI", "IME", "GEOL", "EDUC", "ENVE", "CRP", "ARCH", "CM", "ECON", "HNRC", "FR", "BUS", "LS", "EDES", "WGS", "STAT", "HIST", "CPE", "CE", "ISLA", "SOCS", "BOT", "DANC", "ITAL", "GER", "MSL", "PHIL", "WVIT", "CHEM", "DSCI", "ASTR", "ART", "MCRO"]

course_numbers = range(100, 700)

for dept in departments:
	for num in course_numbers:
		print("\"" + " ".join([dept, str(num)]) + "\",")
