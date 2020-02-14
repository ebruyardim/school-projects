#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void mutate(int** pop_list,int pop_size,int prob_size,int mutate_number){

    int i;
    for (i=0;i<pop_size;i++){
        if (pop_list[i][mutate_number-1] == 0){
            pop_list[i][mutate_number-1] = 1;
        }
        else if (pop_list[i][mutate_number-1] == 1){
            pop_list[i][mutate_number-1] = 0;
        }
    }

}
void xover(int **pop_list,int *selections,int *xover_numbers,int prob_size){

    int es1,es2,i,j,temp;
    for (i=0;i<prob_size/2+2;i+=2){
        es1 = selections[i];
        es2 = selections[i+1];
        for (j=xover_numbers[0];j<=xover_numbers[1];j++){
            temp = pop_list[es1-1][j-1];
            pop_list[es1-1][j-1] = pop_list[es2-1][j-1];
            pop_list[es2-1][j-1] = temp;
        }
    }
}
void bestChromosome(int *best,int **array,int prob_size){
    int i;
    for (i=0;i<prob_size+1;i++){
        best[i] = array[0][i];
    }
}
void sirala(int **array,int pop_size,int prob_size){

    int i,j,k,sonElemanlar[pop_size],temp[pop_size][prob_size+1];
    for (i=0;i<pop_size;i++){
        sonElemanlar[i] = array[i][prob_size];
    }
    insertionSort(sonElemanlar,pop_size);
    for (i=0;i<pop_size;i++){
        for (j=0;j<pop_size;j++){
            if (sonElemanlar[i] == array[j][prob_size]){
                for (k=0;k<prob_size+1;k++){
                    temp[i][k] = array[j][k];
                }
            }
        }
    }
    for (i=0;i<pop_size;i++){
        for (j=0;j<prob_size+1;j++){
            array[i][j] = temp[i][j];
        }
    }
}
void insertionSort(int arr[], int n) {
    int i, key, j;
    for (i = 1; i < n; i++) {
        key = arr[i];
        j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}
int power(int base,int power){
    int result=1;
    int i;
    for (i=0;i<power;i++){
        result = result*base;
    }
    return result;
}
void ToBinary(int **array,int pop_size,int prob_size){
    int x,y;
    for (x=0;x<pop_size;x++){
        int binary = 0;
        for (y=0;y<prob_size;y++){
            binary += (array[x][y])*power(2,prob_size-y-1);
        }
        array[x][prob_size] = binary;
    }
}
void print(int **array,int pop_size,int prob_size,int gen_no){
    printf("GENERATION: %d\n",gen_no);
    int x,y;
    for (x=0;x<pop_size;x++){
        for (y=0;y<prob_size;y++){
            printf("%d ",array[x][y]);
        }
        printf("-> %d",array[x][prob_size]);
        printf("\n");
    }
}
int toInt(char c) {
    return c - '0';
}
int main(int argc, char *argv[])
{
    FILE *sel,*pop,*xo,*mut,*out;
    pop = fopen("population","r");
    sel = fopen("selection","r");
    xo = fopen("xover","r");
    mut = fopen("mutate","r");
    
	out = freopen("output.txt", "w+", stdout);

    int prob_size = atoi(argv[1]);
    int pop_size = atoi(argv[2]);
    int max_gen = atoi(argv[3]);

    char gen;
    int generation_number = 0;
    int i;
    char c;
    int **pop_list,a=0,b=0;
    pop_list = (int **) malloc(pop_size*sizeof(int *));
    for (i=0;i<pop_size;i++){
        pop_list[i] = (int *) malloc((prob_size+1)*sizeof(int ));

    }
    while (fscanf(pop,"%c",&c) != EOF){
        if (c == '\n'){
            a++;
            b=0;
        }
        else if (c != ':'){
            pop_list[a][b] = toInt(c);
            b++;
        }
    }

    int mutate_number,xover_numbers[2];
    int selections[pop_size];
    char ch[10],eb[5],ru[5],oner[500];
    int j,k,l=0;
    int bestChro[prob_size], best;

    ToBinary(pop_list,pop_size,prob_size);
    sirala(pop_list,pop_size,prob_size);
    print(pop_list,pop_size,prob_size,generation_number);
    best = pop_list[0][prob_size];
    bestChromosome(bestChro,pop_list,prob_size);
    printf("Best chromosome found so far: %d",pop_list[0][0]);
    for (i=1;i<prob_size;i++){
        printf(":%d",bestChro[i]);
    }
    printf(" -> %d\n",best);

    generation_number++;
    for (i=0;i<max_gen;i++){
        fscanf(mut,"%d",&mutate_number); // read mutate

        if( fgets(ch,10,xo) != NULL ) {  // read xover
            int len = strlen(ch);
            int nv;
            for (j=0;j<len;j++){
                if (ch[j]==':'){nv = j;break;}
                else eb[j] = ch[j];
            }
            for (k=nv+1;k<len;k++){
                ru[l] = ch[k];
                l++;
            }
            l=0;
            xover_numbers[0] = atoi(eb);
            xover_numbers[1] = atoi(ru);
        }

        if( fgets(oner,500,sel) != '\0' ) { // read selection
            char space[2] = " ";
            char *token;
            char ebru[pop_size/2][10];
            int e=0;
            token = strtok(oner, space);
            while( token != NULL ) {
                strcpy(ebru[e],token);
                token = strtok(NULL, space);
                e++;
            }
            int first,second;
            int z;
            for (z=0;z<pop_size/2;z++){
                sscanf(ebru[z],"%d:%d",&first,&second);
                selections[z*2] = first;
                selections[z*2+1] = second;
            }
        }

        xover(pop_list,selections,xover_numbers,prob_size);
        mutate(pop_list,pop_size,prob_size,mutate_number);
        ToBinary(pop_list,pop_size,prob_size);
        sirala(pop_list,pop_size,prob_size);
        print(pop_list,pop_size,prob_size,generation_number);
        if (pop_list[0][prob_size] < best){
            bestChromosome(bestChro,pop_list,prob_size);
            best = bestChro[prob_size];
        }
        printf("Best chromosome found so far: %d",bestChro[0]);
        int d;
        for (d=1;d<prob_size;d++){
            printf(":%d",bestChro[d]);
        }
        printf(" -> %d\n",best);
        generation_number++;
    }
    return 0;
}
