import sys

a = sys.argv[1]
dosya = open(a, "r")
liste = [i for i in dosya.read().split("\n")]
chart = [i.split(" ") for i in liste]

number_of_deleted = 0

def fibonacci(n):
    if n < 2:
        return n
    else:
        return fibonacci(n - 2) + fibonacci(n - 1)

def deleteUp(row, column):
    global number_of_deleted
    chart[row - 1][column] = " "
    chart[row][column] = " "
    number_of_deleted += 1

def deleteDown(row, column):
    global number_of_deleted
    chart[row + 1][column] = " "
    chart[row][column] = " "
    number_of_deleted += 1

def deleteLeft(row, column):
    global number_of_deleted
    chart[row][column - 1] = " "
    chart[row][column] = " "
    number_of_deleted += 1

def deleteRight(row, column):
    global number_of_deleted
    chart[row][column + 1] = " "
    chart[row][column] = " "
    number_of_deleted += 1

def delete_recursively(row, column):
    if row -1 < 0:
        pass
    elif chart[row - 1][column] == number:  # ÜST
        deleteUp(row, column)
        delete_recursively(row - 1, column)

    if column + 2 > len(chart[row]):
        pass
    elif chart[row][column + 1] == number:  # SAĞ
        deleteRight(row, column)
        delete_recursively(row, column + 1)

    if column - 1 < 0:
        pass
    elif chart[row][column - 1] == number:  # SOL
        deleteLeft(row, column)
        delete_recursively(row, column - 1)

    if row + 2 > len(chart):
        pass
    elif chart[row + 1][column] == number:  # ALT
        deleteDown(row, column)
        delete_recursively(row + 1, column)

def slideDown():
    number_of_line = len(chart)
    for satir in range(0, number_of_line):
        for eleman in chart[satir]:
            if eleman == " ":
                a = chart[satir].index(eleman)
                chart[satir][a] = chart[satir - 1][a]
                chart[satir - 1][a] = " "
    return chart

def slideDown2():
    number_of_line = len(chart)
    for satir in range(1, number_of_line):
        a = 0
        for eleman in chart[satir]:
            if eleman == " ":
                chart[satir][a] = chart[satir - 1][a]
                chart[satir - 1][a] = " "
            a += 1

def slideLeft2(sutun):
    for i in range(len(chart)):
        chart[i][sutun] = chart[i][sutun+1]
        chart[i].remove(chart[i][sutun+1])

def slideLeft():  # TRY EXCEPT
    try:
        donen = []  # Sütun Sayısı
        for i in chart:
            donen = [j for j in range(len(i))]
        for j in donen:
            sayac = 0
            for k in range(len(chart)):
                if chart[k][j] == " ":
                    sayac += 1
            if sayac == len(chart):
                slideLeft2(j)
    except IndexError:
        pass

def deleteRow():
    sayar = 0
    for satir in chart:  # Boş satır silme
        for eleman in satir:
            if eleman == " ":
                sayar += 1
        if sayar == len(satir):
            chart.remove(satir)
        sayar = 0

score = 0
while True:
    for i in chart:  # SHOW
        print(" ".join(i))

    entry = input("Please enter a row and column number(There should be a space between the numbers):")
    row = int(entry.split(" ")[0]) - 1
    column = int(entry.split(" ")[1]) -1

    if row+1 > len(chart) or column+1 > len(chart[row]):
        print("Please enter a correct size!")
    else:
        number = chart[row][column]
        if number == " ":
            print("Please enter a correct size!")
        else:
            delete_recursively(row, column)
            slideDown()
            slideDown2()
            slideDown2()
            slideDown2()
            slideDown2()
            slideDown2()
            slideLeft()

            if number_of_deleted != 0:  # SCORE
                score += int(number)*int((fibonacci(number_of_deleted+1)))
            else:
                score += 0

            number_of_deleted = 0
            print("Your score is: {}".format(score))

            for i in chart:  # Boş satırları siler.
                deleteRow()

    bonus = 0
    for satir in range(len(chart)):
        for eleman in chart[satir]:
            a = chart[satir].index(eleman)
            if satir - 1 < 0:
                pass
            elif eleman == chart[satir-1][a] and eleman != " ":
                bonus += 1
            if satir+2 > len(chart):
                pass
            elif eleman == chart[satir+1][a] and eleman != " ":
                bonus += 1
            if a - 1 < 0:
                pass
            elif eleman == chart[satir][a-1] and eleman != " ":
                bonus += 1
            if a+2 > len(chart[satir]):
                pass
            elif eleman == chart[satir][a+1] and eleman != " ":
                bonus += 1
    if bonus == 0:
        print("Game Over")
        for i in chart:
            print(" ".join(i))
        sys.exit()
