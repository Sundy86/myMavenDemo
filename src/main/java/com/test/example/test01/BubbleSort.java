package com.test.example.test01;

public class BubbleSort {
    public static void main(String[] args) {
        int [] a={2,9,1,8,5,4};
        for(int A:a){
            System.out.print(A+" ");
        }
        System.out.println("冒泡排序： ");
        int temp;
        //每进行一趟排序，就会少比较一次，因为每进行一趟排序都会找出一个较大值。
        // 第一趟比较之后，排在最后的一个数一定是最大的一个数，
        // 第二趟排序的时候，只需要比较除了最后一个数以外的其他的数，同样也能找出一个最大的数排在参与第二趟比较的数后面，
        // 第三趟比较的时候，只需要比较除了最后两个数以外的其他的数，以此类推……也就是说，没进行一趟比较，每一趟少比较一次
        //N个数字要排序完成，总共进行N-1趟排序，每i趟的排序次数为(N-i)次，所以可以用双重循环语句，
        // 外层控制循环多少趟，内层控制每一趟的循环次数
        // 因为数组下标从0开始，所以内层循环用 趟数-
        for(int i=0;i<a.length-1;i++){
            for(int j=0;j<a.length-1-i;j++){
                if(a[j]>a[j+1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        for(int A:a){
            System.out.print(A+" ");
        }
    }
}
