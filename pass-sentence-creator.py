#!/usr/bin/python3

strs = []

synonyms = ["offered", "open", "available", "provided", "given"]

q_formats = ["Is {Course}", "Is {Course} going to be", "Will {Course} be", "What classes are", "What classes will be", "What classes are being", "Tell me about classes that are", "What is"]

c_formats = ["Tell me about {Course}", "Find information about {Course}", "{Course}", "Classes"]

suffix = "next quarter"

for synonym in synonyms:
	for q_format in q_formats:
		base_string = " ".join([q_format, synonym])
		base_with_suffix = " ".join([base_string, suffix])

		strs.append(base_string)
		strs.append(base_with_suffix)

for c_format in c_formats:
	strs.append(c_format)
	strs.append(" ".join([c_format, suffix]))

for test_str in strs:
	temp = "\"" + test_str + "\","
	print(temp)
