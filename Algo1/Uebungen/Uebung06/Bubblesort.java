static class Bubblesort1{


    int tmp = 0;
    for(int i = 0; i < a.length - 1; i++){
        for(j = i + 1; j < a.length; j++){
            if (a[i] > a[j]){
                tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
    }
}