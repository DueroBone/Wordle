inputText = open("words.txt").read().splitlines()
textOut = ""
for word in inputText:
    if len(word) == 5:
        textOut = textOut + word + "\n"
open("wordleList.txt", "x").write(str(textOut))