#!/usr/bin/python3

strs = []

synonyms = ["available", "open", "accessible", "free", "unoccupied"]

q_formats = ["Are computers {}", "Are there any {} computers", "How many computers are {}", "Are there computers {}"]

floor_formats = ["on the {fl_string} floor", "on floor {fl_number}"]

suffix = "in the library"

for synonym in synonyms:
	for q_format in q_formats:
		base_string = q_format.format(synonym)
		base_with_suffix = " ".join([base_string, suffix])

		strs.append(base_string)
		strs.append(base_with_suffix)

		for floor_format in floor_formats:
			floor_format_string = " ".join([base_string, floor_format])
			floor_with_suffix = " ".join([floor_format_string, suffix])

			strs.append(floor_format_string)
			strs.append(floor_with_suffix)

for test_str in strs:
	temp = "\"" + test_str + "\","
	print(temp)
