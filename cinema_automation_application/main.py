import sys

fileName = sys.argv[1]
dosya = open(fileName,"r")
commandsList = dosya.readlines()
outFile = open("out.txt","w")

# CREATE HALL
hallNameList = []
sizeList = []
repeatList = []

createdHalls = {}
def createHall(hallName, row_column):
    global createdHalls
    global showDictionary
    number_of_seats = int(row_column.split("x")[0])*int(row_column.split("x")[1]) #sadece print'e yazmak için bulundu.
    print("The hall '{}' having {} seats has been created.".format(hallName,number_of_seats), file=outFile)
    print("The hall '{}' having {} seats has been created.".format(hallName,number_of_seats))

    alphabet = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
              "V", "W", "X", "Y", "Z"]
    showDictionary = {}
    for i in range(int(row_column.split("x")[0])):
        showDictionary[alphabet[i]] = ["X  "*int(row_column.split("x")[1])]
    createdHalls[hallName] = showDictionary
    return showDictionary

def showHall(hallName, row_column):
    print("Printing hall layout of {}.".format(hallName),file = outFile)
    print("Printing hall layout of {}.".format(hallName))

    #outFile.write("Printing hall layout of {}.\n".format(hallName))
    for i in createdHalls[hallName]:
        print(i,str(createdHalls[hallName][i]).replace("[","").replace("]","").replace("'",""),file = outFile)
        print(i,str(createdHalls[hallName][i]).replace("[","").replace("]","").replace("'",""))

    print(" ", end = " ",file = outFile)
    print(" ", end = " ")

    #outFile.write("  "+"\n")
    for numara in range(int(row_column.split("x")[1])):
        if numara < 9:
            print(numara, end = "  ",file = outFile)
            print(numara, end = "  ")

            #outFile.write(str(numara) + "  ")
        else:
            print(numara, end = " ",file=outFile)
            print(numara, end = " ")


def sellTicket(musteriIsmi, fs,salonIsmi, koltukListesi):
    global studentNumber
    global fullNumber
    alphabet = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
              "V", "W", "X", "Y", "Z"]

    if salonIsmi not in createdHalls:
        print("There is not a hall '{}'".format(salonIsmi),file=outFile)
        print("There is not a hall '{}'".format(salonIsmi))

    else:
        for i in koltukListesi:
            if "-" not in i:
                harf = i[0]
                numara = int(i[1:])
                if harf not in alphabet:
                    print("Please enter a valid seat.",file = outFile)
                    print("Please enter a valid seat.")

                else:
                    if numara > (len(createdHalls[salonIsmi]["A"][0])/3)-1 or alphabet.index(harf) > alphabet.index(list(createdHalls[salonIsmi].keys())[-1]):
                        print("Error: The hall '{}' has less column than the specified index {}{}!".format(salonIsmi,harf,numara),file=outFile)
                        print("Error: The hall '{}' has less column than the specified index {}{}!".format(salonIsmi,harf,numara))

                    else:
                        if createdHalls[salonIsmi][harf][0][(3 * numara):(3 * numara + 1)] == "S" or \
                            createdHalls[salonIsmi][harf][0][(3 * numara):(3 * numara + 1)] == "F":
                            print("Warning: The seat {}{} cannot be sold to {} since it was already sold!".format(harf, numara,musteriIsmi),file=outFile)
                            print("Warning: The seat {}{} cannot be sold to {} since it was already sold!".format(harf, numara,musteriIsmi))

                            koltukListesi.remove(i)
                        else:
                            if fs == "student":
                                createdHalls[salonIsmi][harf][0] = createdHalls[salonIsmi][harf][0][:(3*numara)] + "S" + createdHalls[salonIsmi][harf][0][(3*numara+1):]
                                print("SUCCESS: {} has bought {} at {}.".format(musteriIsmi, i, salonIsmi),file=outFile)
                                print("SUCCESS: {} has bought {} at {}.".format(musteriIsmi, i, salonIsmi))

                            elif fs == "full":
                                createdHalls[salonIsmi][harf][0] = createdHalls[salonIsmi][harf][0][:(3*numara)] + "F" + createdHalls[salonIsmi][harf][0][(3*numara+1):]
                                print("SUCCESS: {} has bought {} at {}.".format(musteriIsmi, i, salonIsmi),file=outFile)
                                print("SUCCESS: {} has bought {} at {}.".format(musteriIsmi, i, salonIsmi))

                            else:
                                print("Please enter a valid tariff.",file=outFile)
                                print("Please enter a valid tariff.")

            else:
                harf = i[0]
                ilk = int(i.split("-")[0][1:])  # ilk sayı iki basamaklı olursa çalışmaz..
                son = int(i.split("-")[1])
                if harf not in alphabet:
                    print("Please enter a valid seat.",file=outFile)
                    print("Please enter a valid seat.")

                else:
                    if son > len(createdHalls[salonIsmi]["A"][0]) / 3 or alphabet.index(harf) > alphabet.index(
                            list(createdHalls[salonIsmi].keys())[-1]):
                        print("Error: The hall '{}' has less column than the specified index {}{}-{}!".format(salonIsmi,harf, ilk,son),file=outFile)
                        print("Error: The hall '{}' has less column than the specified index {}{}-{}!".format(salonIsmi,harf, ilk,son))

                    else:
                        numbers = []
                        for p in range(ilk, son):
                            numbers.append(p)
                        sayac = 0
                        for k in numbers:
                            numara = int(k)

                            if createdHalls[salonIsmi][harf][0][(3 * numara):(3 * numara + 1)] == "S" or \
                                    createdHalls[salonIsmi][harf][0][(3 * numara):(3 * numara + 1)] == "F":
                                print("Warning: The seats {} cannot be sold to {} due some of them have already been sold!".format(i, musteriIsmi),file=outFile)
                                print("Warning: The seats {} cannot be sold to {} due some of them have already been sold!".format(i, musteriIsmi))

                                numbers.remove(numara)
                                break
                            else:
                                if fs == "student":
                                    createdHalls[salonIsmi][harf][0] = createdHalls[salonIsmi][harf][0][:(3 * numara)] + "S" + createdHalls[salonIsmi][harf][0][(3 * numara + 1):]
                                    sayac += 1

                                elif fs == "full":
                                    createdHalls[salonIsmi][harf][0] = createdHalls[salonIsmi][harf][0][:(3 * numara)] + "F" + createdHalls[salonIsmi][harf][0][(3 * numara + 1):]
                                    sayac += 1

                                else:
                                    print("Please enter a valid tariff.",file=outFile)
                                    print("Please enter a valid tariff.")

                            if sayac == 1:
                                print("SUCCESS: {} has bought {}{}-{} at {}".format(musteriIsmi, harf, ilk, son,salonIsmi),file=outFile)
                                print("SUCCESS: {} has bought {}{}-{} at {}".format(musteriIsmi, harf, ilk, son, salonIsmi))

def cancelTicket(salonIsmi, koltukListesi):
    alphabet = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
              "V", "W", "X", "Y", "Z"]
    if salonIsmi not in createdHalls:
        print("There is not a hall {}".format(salonIsmi),file = outFile)
        print("There is not a hall {}".format(salonIsmi))

    else:
        for i in koltukListesi:
            if "-" not in i:
                harf = i[0]
                numara = int(i[1:])
                if harf not in alphabet:
                    print("Please enter a valid seat.",file = outFile)
                    print("Please enter a valid seat.")

                else:
                    if numara > int(len(createdHalls[salonIsmi][harf][0])/3) or alphabet.index(harf) > alphabet.index(list(createdHalls[salonIsmi].keys())[-1]):
                        print("Error: The hall '{}' has less column than the specified index {}{}!".format(salonIsmi, harf,numara),file=outFile)
                        print("Error: The hall '{}' has less column than the specified index {}{}!".format(salonIsmi, harf,numara))

                    else:
                        if createdHalls[salonIsmi][harf][0][(3 * numara):(3 * numara + 1)] == "X":
                            print("Error: The seat {}{} at {} has already been free! Nothing to cancel.".format(harf, numara,salonIsmi),file=outFile)
                            print("Error: The seat {}{} at {} has already been free! Nothing to cancel.".format(harf, numara,salonIsmi))

                        else:
                            createdHalls[salonIsmi][harf][0] = createdHalls[salonIsmi][harf][0][:(3*numara)] + "X" + createdHalls[salonIsmi][harf][0][(3*numara + 1):]
                            print("SUCCESS: The seat {}{} at {} has been canceled and now ready to be sold again.".format(harf,numara,salonIsmi),file=outFile)
                            print("SUCCESS: The seat {}{} at {} has been canceled and now ready to be sold again.".format(harf,numara,salonIsmi))

            else:
                harf = i[0]
                ilk = int(i.split("-")[0][1:])
                son = int(i.split("-")[1])
                if harf not in alphabet:
                    print("Please enter a valid seat.",file=outFile)
                    print("Please enter a valid seat.")

                else:
                    if son > int(len(createdHalls[salonIsmi][harf][0])/3) or alphabet.index(harf) > alphabet.index(list(createdHalls[salonIsmi].keys())[-1]):
                        print("Error: The hall '{}' has less column than the specified index {}{}-{}!".format(salonIsmi,harf,ilk,son),file=outFile)
                        print("Error: The hall '{}' has less column than the specified index {}{}-{}!".format(salonIsmi,harf,ilk,son))

                    else:
                        numbers = []
                        for p in range(ilk,son):
                            numbers.append(p)
                        sayac = 0
                        for k in numbers:
                            numara = int(k)

                            if createdHalls[salonIsmi][harf][0][(3 * numara):(3 * numara + 1)] == "x":
                                print("Warning: The seats {}{}-{} at {} cannot be cancelled due some of them have already been free.".format(harf,ilk,son,salonIsmi),file=outFile)
                                print("Warning: The seats {}{}-{} at {} cannot be cancelled due some of them have already been free.".format(harf,ilk,son,salonIsmi))

                                numbers.remove(numara)
                                break
                            else:
                                createdHalls[salonIsmi][harf][0] = createdHalls[salonIsmi][harf][0][:(3 * numara)] + "x" + createdHalls[salonIsmi][harf][0][(3 * numara + 1):]
                                sayac += 1

                            if sayac == 1 :
                                print("SUCCESS: The seat {}{}-{} at {} has been canceled and now ready to be sold again.".format(harf, ilk, son, salonIsmi),file=outFile)
                                print("SUCCESS: The seat {}{}-{} at {} has been canceled and now ready to be sold again.".format(harf, ilk, son, salonIsmi))

def balance(balanceListe):

    for salonAdi in balanceListe:
        if salonAdi not in createdHalls:
            print("There is not a hall {}".format(salonAdi),file=outFile)
            print("There is not a hall {}".format(salonAdi))

        else:
            ogrenciSayisi = 0
            yetiskinSayisi = 0
            for harf in createdHalls[salonAdi]:
                for i in createdHalls[salonAdi][harf][0]:
                    if i == "S":
                        ogrenciSayisi += 1
                    elif i == "F":
                        yetiskinSayisi += 1
            print(" Hall report of '{}'".format(salonAdi),"\n","-----------------"+"-"*len(salonAdi),"\n","Sum of students = {}, Sum of full fares = {}, Overall = {}".format(5*ogrenciSayisi,10*yetiskinSayisi,5*ogrenciSayisi+10*yetiskinSayisi),file=outFile)
            print(" Hall report of '{}'".format(salonAdi),"\n","-----------------"+"-"*len(salonAdi),"\n","Sum of students = {}, Sum of full fares = {}, Overall = {}".format(5*ogrenciSayisi,10*yetiskinSayisi,5*ogrenciSayisi+10*yetiskinSayisi))


for i in commandsList:
    if i.split()[0] == "CREATEHALL":
        # createHall fonksiyonu çalışıyor.
        if len(i.split()) < 3:
            print("Not enough parameters for creating a hall...",file=outFile)
            print("Not enough parameters for creating a hall...")

        elif len(i.split()) > 3:
            print("Too much parameters for creating a hall...",file=outFile)
            print("Too much parameters for creating a hall...")

        else:
            hallNameList.append(i.split()[1])
            sizeList.append(i.split()[2])
        a = i.split()[1]
        b = hallNameList.index(a)
        if a in repeatList:
            print("Warning: Cannot create the hall for the second time. The cinema has already {}...".format(a),file=outFile)
            print("Warning: Cannot create the hall for the second time. The cinema has already {}...".format(a))

        else:
            createHall(hallNameList[b], sizeList[b])
            repeatList.append(a)

    elif i.split()[0] == "SELLTICKET":
        musteriIsmi = i.split()[1]
        fs = i.split()[2]
        salonIsmi = i.split()[3]
        koltukListesi = []
        tumListe = i.split()
        for j in range(len(tumListe)):
            if j > 3:
                koltukListesi.append(tumListe[j])
        sellTicket(musteriIsmi,fs,salonIsmi,koltukListesi)

    elif i.split()[0] == "SHOWHALL":
        isim = i.split()[1]
        indeks = hallNameList.index(isim)

        showHall(isim,sizeList[indeks])
        print("\n",file=outFile)
        print("\n")

    elif i.split()[0] == "CANCELTICKET":
        salonIsmi = i.split()[1]
        koltuk = i.split()[2:]
        cancelTicket(salonIsmi,koltuk)

    elif i.split()[0] == "BALANCE":
        balanceListe = []
        for b in range(len(i.split())):
            if b == 0:
                continue
            else:
                balanceListe.append(i.split()[b])
        balance(balanceListe)

    else:
        print("Please enter a valid command.",file=outFile)
        print("Please enter a valid command.")

