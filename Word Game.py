import sys

try :
    first = sys.argv[1]
    second = sys.argv[2]
    third = sys.argv[3]
except IndexError:
    print("Please run with 3 arguments!")
    sys.exit()

a = len(first)
b = len(second)
c = len(third)

if a == b or a == c or b == c:
    print("Length of vocabularies should not be the same.")
    sys.exit()

liste = list()
alphabet = ["a","b","c","ç","d","e","f","g","ğ","h","ı","i","j","k","l","m","n","o","ö","p","r","s","ş","t","u","ü","v","y","z","x","w","q"]

for i in first:
    liste.append(i)
    if i not in alphabet:
        print("Argument",first,"is not a word. All arguments should be a word.")
        sys.exit()
for j in second:
    liste.append(j)
    if j not in alphabet:
        print("Argument",second,"is not a word. All arguments should be a word.")
        sys.exit()
for k in third:
    liste.append(k)
    if k not in alphabet:
        print("Argument",third,"is not a word. All arguments should be a word.")
        sys.exit()

liste.sort()
print(liste)
print("""Please try to derive the longest word as using above letters,
You have one change find a correct word...""")
guess = input("Guess:")

if guess != first and guess != second and guess != third:
    print("The word you guessed is not in the list.")
else:
    if len(guess) == max(a,b,c):
        print("You won 50 points...")
    elif len(guess) == min(a,b,c):
        print("You won 10 points...")
    else:
        print("You won 30 points...")