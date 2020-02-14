#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

int random_number(int r)
{
    int l = 1,count = 1;
    int i;
    for (i = 0; i < count; i++) {
        int rand_num = (rand() % (r - l + 1)) + l;

    return rand_num;
    }
}
struct non_terminal{
    int id;
    char *name;
    int no_rules;
    int *rule_childs;  // keep number of child for each rule
};
typedef struct non_terminal non_terminal;
struct ucCocuklu{
    int data;
    int id;
    int rule_id;
    int isTerminal;
    void* first;
    void* second;
    void* third;
};
typedef struct ucCocuklu ucCocuklu;
struct ikiCocuklu{
    int data;
    int id;
    int rule_id;
    int isTerminal;
    void* first;
    void* second;
};
typedef struct ikiCocuklu ikiCocuklu;
struct birCocuklu{
    int data;
    int id;
    int rule_id;
    int isTerminal;
    void* first;
};
typedef struct birCocuklu birCocuklu;
struct terminal{ // cocuksuz
    char* name;
    char** content;
    int no_symbol;
};
typedef struct terminal terminal;

void readTree(ucCocuklu *head,terminal *terminalArray){

    int i;
    if (head == NULL)
        return;

    if (head->id == 0){  // cond
        printf("( ");
        readTree(head->first,terminalArray);
        printf(") ");
        readTree(head->second,terminalArray);
        printf("( ");
        readTree(head->third,terminalArray);
        printf(") ");
    }
    else if (head->id == 1){ // expr
        if (head->rule_id == 1){ // uc cocuklu expr
            printf("( ");
            readTree(head->first,terminalArray);
            printf(") ");
            readTree(head->second,terminalArray);
            printf("( ");
            readTree(head->third,terminalArray);
            printf(")");
        }
        else if (head->rule_id == 2){ // 2 cocuklu expr
            readTree(head->first,terminalArray);
            printf("(");
            readTree(head->second,terminalArray);
            printf(")");
        }
        else if (head->rule_id == 3){ // 1 cocuklu expr
            readTree(head->first,terminalArray);
        }
    }
    else if (head->id == 2){  // op
        int x;
        for (i=0;i<5;i++){
            if (strcmp("op",terminalArray[i].name)==0){
                x = random_number(terminalArray[i].no_symbol);
                printf("%s ",terminalArray[i].content[x-1]);
            }
        }
        readTree(head->first,terminalArray);
    }
    else if (head->id == 3){ // pre-op
        int x;
        for (i=0;i<5;i++){
            if (strcmp("pre_op",terminalArray[i].name)==0){
                x = random_number(terminalArray[i].no_symbol);
                printf("%s ",terminalArray[i].content[x-1]);
            }
        }
        readTree(head->first,terminalArray);
    }
    else if (head->id == 4){ // rel-op
        int x;
        for (i=0;i<5;i++){
            if (strcmp("rel_op",terminalArray[i].name)==0){
                x = random_number(terminalArray[i].no_symbol);
                printf("%s ",terminalArray[i].content[x-1]);
            }
        }
        readTree(head->first,terminalArray);
    }
    else if (head->id == 5){ // set-op
        int x;
        for (i=0;i<5;i++){
            if (strcmp("set_op",terminalArray[i].name)==0){
                x = random_number(terminalArray[i].no_symbol);
                printf("%s ",terminalArray[i].content[x-1]);
            }
        }
        readTree(head->first,terminalArray);
    }
    else if (head->id == 6){ // var
        int x;
        for (i=0;i<5;i++){
            if (strcmp("var",terminalArray[i].name)==0){
                x = random_number(terminalArray[i].no_symbol);
                printf("%s ",terminalArray[i].content[x-1]);
            }
        }
        readTree(head->first,terminalArray);
    }


}

void createOp(birCocuklu **op){
    birCocuklu *temp = malloc(sizeof(birCocuklu));
    temp->id = 2;
    temp->first = NULL;
    *op = temp;
}

void createPreOp(birCocuklu **preop){
    birCocuklu *temp = malloc(sizeof(birCocuklu));
    temp->id = 3;
    temp->first = NULL;

    *preop = temp;
}

void createRelOp(birCocuklu **relop){
    birCocuklu *temp = malloc(sizeof(birCocuklu));
    temp->id = 4;
    temp->first = NULL;

    *relop = temp;
}

void createSetOp(birCocuklu** setop){
    birCocuklu *temp = malloc(sizeof(birCocuklu));
    temp->id = 5;
    temp->first = NULL;

    *setop = temp;
}

void createVar(birCocuklu **var){
    birCocuklu *temp = malloc(sizeof(birCocuklu));
    temp->id = 6;
    temp->first = NULL;

    *var = temp;
}

void oneChildExpr(birCocuklu **expr){

    birCocuklu *temp = malloc(sizeof(birCocuklu));
    temp->id = 1;
    temp->rule_id = 3;
    birCocuklu *var = malloc(sizeof(birCocuklu));
    temp->first = var;
    (*expr) = temp;
    createVar(&(*expr)->first);
}

void twoChildrenExpr(ikiCocuklu **expr){

    ikiCocuklu *temp = malloc(sizeof(ikiCocuklu));
    temp->id = 1;
    temp->rule_id = 2;

    *expr = temp;
    int childRule = random_number(3);
    birCocuklu *temp1  = malloc(sizeof(ucCocuklu));  // pre-op

    ucCocuklu *tempuc2 = malloc(sizeof(ucCocuklu));
    ikiCocuklu *tempiki2 = malloc(sizeof(ikiCocuklu));
    birCocuklu *tempbir2 = malloc(sizeof(birCocuklu));

    (*expr)->first = temp1;
    createPreOp(&(*expr)->first);

    if (childRule == 1){
        (*expr)->second = tempuc2;
        threeChildrenExpr(&(*expr)->second);
    }
    else if (childRule == 2){
        (*expr)->second = tempiki2;
        twoChildrenExpr(&(*expr)->second);
    }
    else if (childRule == 3){
        (*expr)->second = tempbir2;
        oneChildExpr(&(*expr)->second);
    }
}

void threeChildrenExpr(ucCocuklu **expr){

    int firstChildRule = random_number(3);
    int thirdChildRule = random_number(3);

    ucCocuklu *temp = malloc(sizeof(ucCocuklu));
    temp->id = 1;
    temp->rule_id = 1;

    (*expr) = temp;

    ucCocuklu *tempuc1  = malloc(sizeof(ucCocuklu));
    ikiCocuklu *tempiki1  = malloc(sizeof(ikiCocuklu));
    birCocuklu *tempbir1  = malloc(sizeof(birCocuklu));

    birCocuklu *temp2 = malloc(sizeof(birCocuklu));

    ucCocuklu *tempuc3 = malloc(sizeof(ucCocuklu));
    ikiCocuklu *tempiki3 = malloc(sizeof(ikiCocuklu));
    birCocuklu *tempbir3 = malloc(sizeof(birCocuklu));

    if (firstChildRule == 1 && thirdChildRule == 1){
        (*expr)->first = tempuc1;
        (*expr)->second = temp2;
        (*expr)->third = tempuc3;

        threeChildrenExpr(&(*expr)->first);
        createOp(&(*expr)->second);
        threeChildrenExpr(&(*expr)->third);
    }
    else if (firstChildRule == 2 && thirdChildRule == 2){
        (*expr)->first = tempiki1;
        (*expr)->second = temp2;
        (*expr)->third = tempiki3;

        twoChildrenExpr(&(*expr)->first);
        createRelOp(&(*expr)->second);
        twoChildrenExpr(&(*expr)->third);
    }
    else if (firstChildRule == 3 && thirdChildRule == 3){
        (*expr)->first = tempbir1;
        (*expr)->second = temp2;
        (*expr)->third = tempbir3;

        oneChildExpr(&(*expr)->first);
        createRelOp(&(*expr)->second);
        oneChildExpr(&(*expr)->third);
    }
    else if (firstChildRule == 1 && thirdChildRule == 2){
        (*expr)->first = tempuc1;
        (*expr)->second = temp2;
        (*expr)->third = tempiki3;

        threeChildrenExpr(&(*expr)->first);
        createRelOp(&(*expr)->second);
        twoChildrenExpr(&(*expr)->third);
    }
    else if (firstChildRule == 1 && thirdChildRule == 3){
        (*expr)->first = tempuc1;
        (*expr)->second = temp2;
        (*expr)->third = tempbir3;

        threeChildrenExpr(&(*expr)->first);
        createRelOp(&(*expr)->second);
        oneChildExpr(&(*expr)->third);
    }
    else if (firstChildRule == 2 && thirdChildRule == 1){
        (*expr)->first = tempiki1;
        (*expr)->second = temp2;
        (*expr)->third = tempuc3;

        twoChildrenExpr(&(*expr)->first);
        createRelOp(&(*expr)->second);
        threeChildrenExpr(&(*expr)->third);
    }
    else if (firstChildRule == 3 && thirdChildRule == 1){
        (*expr)->first = tempbir1;
        (*expr)->second = temp2;
        (*expr)->third = tempuc3;

        oneChildExpr(&(*expr)->first);
        createRelOp(&(*expr)->second);
        threeChildrenExpr(&(*expr)->third);
    }
    else if (firstChildRule == 2 && thirdChildRule == 3){
        (*expr)->first = tempiki1;
        (*expr)->second = temp2;
        (*expr)->third = tempbir3;

        twoChildrenExpr(&(*expr)->first);
        createRelOp(&(*expr)->second);
        oneChildExpr(&(*expr)->third);
    }
    else if (firstChildRule == 3 && thirdChildRule == 2){
        (*expr)->first = tempbir1;
        (*expr)->second = temp2;
        (*expr)->third = tempiki3;

        threeChildrenExpr(&(*expr)->first);
        createRelOp(&(*expr)->second);
        twoChildrenExpr(&(*expr)->third);
    }

}

void createCond(ucCocuklu** cond){

    ucCocuklu *temp  = malloc(sizeof(ucCocuklu));
    temp->id= 0;
    temp->rule_id = 1;

    *cond = temp;
    int rule = random_number(2); // number of rules in cond are 2

    ucCocuklu *tempuc1  = malloc(sizeof(ucCocuklu));
    ikiCocuklu *tempiki1  = malloc(sizeof(ikiCocuklu));
    birCocuklu *tempbir1  = malloc(sizeof(birCocuklu));

    birCocuklu *temp2 = malloc(sizeof(birCocuklu));

    ucCocuklu *tempuc3 = malloc(sizeof(ucCocuklu));
    ikiCocuklu *tempiki3 = malloc(sizeof(ikiCocuklu));
    birCocuklu *tempbir3 = malloc(sizeof(birCocuklu));

    if (rule == 1){         // cond set-op cond
        (*cond)->first = tempuc1;
        (*cond)->second = temp2;
        (*cond)->third = tempuc3;

        createCond(&(*cond)->first);
        createSetOp(&(*cond)->second);
        createCond(&(*cond)->third);
    }
    else if (rule == 2){     // expr rel-op expr
        int firstChildRule = random_number(3);  // expr fonksiyonunda type casting yapamadigim icin cocuk sayisina burda karar veriyorum (typelarini belirlemek icin)
        int thirdChildRule = random_number(3);

        if (firstChildRule == 1 && thirdChildRule == 1){
            (*cond)->first = tempuc1;
            (*cond)->second = temp2;
            (*cond)->third = tempuc3;

            threeChildrenExpr(&(*cond)->first);
            createRelOp(&(*cond)->second);
            threeChildrenExpr(&(*cond)->third);
        }
        else if (firstChildRule == 2 && thirdChildRule == 2){
            (*cond)->first = tempiki1;
            (*cond)->second = temp2;
            (*cond)->third = tempiki3;

            twoChildrenExpr(&(*cond)->first);
            createRelOp(&(*cond)->second);
            twoChildrenExpr(&(*cond)->third);
        }
        else if (firstChildRule == 3 && thirdChildRule == 3){
            (*cond)->first = tempbir1;
            (*cond)->second = temp2;
            (*cond)->third = tempbir3;

            oneChildExpr(&(*cond)->first);
            createRelOp(&(*cond)->second);
            oneChildExpr(&(*cond)->third);
        }
        else if (firstChildRule == 1 && thirdChildRule == 2){
            (*cond)->first = tempuc1;
            (*cond)->second = temp2;
            (*cond)->third = tempiki3;

            threeChildrenExpr(&(*cond)->first);
            createRelOp(&(*cond)->second);
            twoChildrenExpr(&(*cond)->third);
        }
        else if (firstChildRule == 1 && thirdChildRule == 3){
            (*cond)->first = tempuc1;
            (*cond)->second = temp2;
            (*cond)->third = tempbir3;

            threeChildrenExpr(&(*cond)->first);
            createRelOp(&(*cond)->second);
            oneChildExpr(&(*cond)->third);
        }
        else if (firstChildRule == 2 && thirdChildRule == 1){
            (*cond)->first = tempiki1;
            (*cond)->second = temp2;
            (*cond)->third = tempuc3;

            twoChildrenExpr(&(*cond)->first);
            createRelOp(&(*cond)->second);
            threeChildrenExpr(&(*cond)->third);
        }
        else if (firstChildRule == 3 && thirdChildRule == 1){
            (*cond)->first = tempbir1;
            (*cond)->second = temp2;
            (*cond)->third = tempuc3;

            oneChildExpr(&(*cond)->first);
            createRelOp(&(*cond)->second);
            threeChildrenExpr(&(*cond)->third);
        }
        else if (firstChildRule == 2 && thirdChildRule == 3){
            (*cond)->first = tempiki1;
            (*cond)->second = temp2;
            (*cond)->third = tempbir3;

            twoChildrenExpr(&(*cond)->first);
            createRelOp(&(*cond)->second);
            oneChildExpr(&(*cond)->third);
        }
        else if (firstChildRule == 3 && thirdChildRule == 2){
            (*cond)->first = tempbir1;
            (*cond)->second = temp2;
            (*cond)->third = tempiki3;

            threeChildrenExpr(&(*cond)->first);
            createRelOp(&(*cond)->second);
            twoChildrenExpr(&(*cond)->third);
        }
    }
}

int main(){
srand(time(NULL));
    int i,e,total_file=5;
    FILE *fp;
    char fileNames[total_file][7];

    strcpy(fileNames[0],"op"); fileNames[0][2] = '\0';
    strcpy(fileNames[1],"pre_op"); fileNames[1][6] = '\0';
    strcpy(fileNames[2],"rel_op"); fileNames[2][6] = '\0';
    strcpy(fileNames[3],"set_op"); fileNames[3][6] = '\0';
    strcpy(fileNames[4],"var"); fileNames[0][3] = '\0';

    terminal *terminal_items = (terminal*) malloc(total_file*sizeof(terminal));
    int file_counter = 0;
    for  (e=0;e<total_file;e++){
        //Dosyalari tek tek geziyor
        fp = fopen(fileNames[e],"r");
        char c;
        int no_symbol = 0;  // number of symbol in current file
        for (c = getc(fp); c != EOF; c = getc(fp))
            if (c == '\n')
                no_symbol = no_symbol + 1;
        rewind(fp);
        fclose(fp);
        // ----------------

        fp = fopen(fileNames[e],"r");

        terminal_items[file_counter].no_symbol = no_symbol;  // symbol
        terminal_items[file_counter].name = (char *) malloc((strlen(fileNames[e]))*sizeof(char));   // name
        strcpy(terminal_items[file_counter].name,fileNames[e]);
        terminal_items[file_counter].content = (char **) malloc((no_symbol)*sizeof(char *));   // content
        for (i=0;i<no_symbol;i++){
            terminal_items[file_counter].content[i] = (char *) malloc(50*sizeof(char));
        }

        int j=0;
        while (fscanf(fp,"%s",terminal_items[file_counter].content[j])==1){
            int len = strlen(terminal_items[file_counter].content[j]);
            terminal_items[file_counter].content[j][len] = '\0';
            j++;
        }
        file_counter++;
    }

    // ----------------------------------------------------------------------------------------------

    ucCocuklu *head = NULL;

    createCond(&head);
    printf("if ( ");
    readTree(head,terminal_items);
    printf(") { }\n");





/*
int l,y;
for (l=0;l<total_file;l++){
    printf("%s: ",terminal_items[l].name);
    for (y=0;y<terminal_items[l].no_symbol;y++){
        printf("%s  ",terminal_items[l].content[y]);
    }
    printf("\n");
}
*/


    return 0;
}
