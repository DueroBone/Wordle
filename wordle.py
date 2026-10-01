from sys import argv
import re

maxPrintable = 0


wordlist = open("wordleList.txt").read().lower().splitlines()
if len(argv) == 1:
	knownLetters = input("What are all the letters known to be in it?")
	greyLetters = input("What are the grey letters?")
	yellowLetters = [word.replace(",", "") for word in re.findall(r"[a-z]*,", (input("What are the yellow letters? (list format)")))]
	greenLetters = input("What are the green letters?")
elif len(argv) == 5:
	knownLetters = argv[1]
	greyLetters = argv[2]
	yellowLetters = [word.replace(",", "") for word in re.findall(r"[a-z]*,", argv[3])]
	greenLetters = argv[4]
else:
	print("Arguments used improperly!!")
	exit()

# input(yellowLetters)

def prepareWordlist(listIn):
	listOut = []
	for word in listIn:
		if len(word) == 5:
			listOut.append(word)
	return tuple(listOut)

def firstPass(WORDS):
	"""Yellow letters"""
	possWords = []
	for word in WORDS:
		possible = True
		for letter in knownLetters:
			if letter not in word:
				possible = False
		if possible:
			possWords.append(word)
	return possWords

def secondPass(WORDS):
	"""Grey letters"""
	possWords = []
	for word in WORDS:
		possible = True
		for letter in greyLetters:
			if letter in word:
				possible = False
		if possible:
			possWords.append(word)
	return possWords

def thirdPass(WORDS):
	"""Yellow letters"""
	possWords = []
	for word in WORDS:
		possible = True
		i = 0
		for yellowLets in yellowLetters:
			for yellowLet in yellowLets:
				if yellowLet == word[i]:
					possible = False
			i += 1
		if possible:
			possWords.append(word)
	return possWords

def fourthPass(WORDS):
	"""Green letters"""
	possWords =[]
	for word in WORDS:
		possible = True
		for i in range(5):
			if word[i] != greenLetters[i] and greenLetters[i] != " ":
				possible = False
		if possible:
			possWords.append(word)
	return possWords

def myPrint(myVar):
	if len(myVar) < 11:
		myVar2 = myVar
	else:
		myVar2 = ""


myVar = secondPass(firstPass(prepareWordlist(wordlist)))
try:
	myVar = thirdPass(myVar)
except IndexError:
	print("There was an error, skipping 3...")
try:
	myVar = fourthPass(myVar)
except IndexError:
	print("There was an error, skipping 4...")

for word in myVar:
	print(word)
	a = 1
