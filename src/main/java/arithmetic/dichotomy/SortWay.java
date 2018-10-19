package arithmetic.dichotomy;

public class SortWay {
	
	public static void main(String[] args){
		System.out.print("直接排序之后的值为：");
		for(int i : directSequence()){
			System.out.print(i + ",");
		}
		System.out.println();
		
		System.out.print("初始数据：2,0,6,9,7,4,1,3,5,4,快速排序之后的结果为：");
		int[] arry = { 2,0,6,9,7,4,1,3,5,4 };
		for(int i : quickSortTwo(arry, 0, arry.length - 1)){
			System.out.print(i + ",");
		}
		System.out.println();
		
		System.out.print("堆排序之后的结果为：");
		for(int i : directSequence()){
			System.out.print(i + ",");
		}
		System.out.println();
		
		System.out.print("归并排序之后的结果为：");
		for(int i : directSequence()){
			System.out.print(i + ",");
		}
		System.out.println();
		
	}
	
	/**
	 * 直接排序(正序),时间复杂度为O(n⑵)
	 * @param intArr
	 */
	private static int[] directSequence(){
		int[] arry = { 2,0,6,9,7,4,1,3,5,4 };
		for(int i = 1; i < arry.length; i++){
			if(arry[i] < arry[i - 1]){
				int position = i; //记录空缺的位置，初始为节点位置
				int temp = arry[i];
				for(int j = i - 1; j >= 0 && temp < arry[j]; j--){
					arry[j + 1] = arry[j];
					position = j;	//记录每一次迭代的空缺位置，一边后续填值
				}
				arry[position] = temp;	//将最后的值填写到空缺的的位置
			}
		}
		return arry;
	}
	
	/**
	 * 快速排序一(正序),时间复杂度平均为：最好的情况O(nlog(n))
	 * 通过一趟排序将记录分割成独立的两部分，其中一部分的关键字均比另一部分的关键字小，
	 * 然后分别对这两部分的记录继续进行排序，以达到整个序列有序。
	 * @return
	 * int[] arry = { 2,0,6,9,7,4,1,3,5,4 };
	 */
	private static int[] quicksort(int[] arry, int left, int right){
		if(left >= right)return arry;
		int i = left;
		int j = right;
		int initialNum = arry[i];
		while(i < j){
			//此处为什么一定是需要从后往前找，而不能从前往后找的？
			//原因：在找的过程中，其实是会改变指针（i,j）的值的，如果是先找左边的话，会出现找到对应的值，
			//		但是这个值是不正确的，不符合规范（即i>=j），指针i的值也发生了变，后续处理也是会计算进去，错误
			while(i < j && arry[j] >= initialNum){	//右边找到第一个小于初始值的第一个位置（从后往前）
				j--;
			}
			while(i < j && arry[i] <= initialNum){	//左边找到大于初始的值的第一个位置
				i++;
			}	
			if(i < j){
				int temp = arry[i];
				arry[i] = arry[j];
				arry[j] = temp;
				//经过了该步骤，就已经达到了arry[i]已经是最后的一个小于初始位置的值了
			}
		}
		arry[left] = arry[i];
		arry[i] = initialNum;
		//上两步可以使得：将初始位置放置在其中间位置，
		//使得左边全是小于（大于）该初始值的值，右边全是大于（小于）初始值的值
		//排序初始值左边
		quicksort(arry, left, i - 1);
		//排序初始值右边
		quicksort(arry, i + 1, right);
		return arry;
	}
	/**
	 * 快速排序二(正序),时间复杂度平均为：最好的情况O(nlog(n))
	 * 通过一趟排序将记录分割成独立的两部分，其中一部分的关键字均比另一部分的关键字小，
	 * 然后分别对这两部分的记录继续进行排序，以达到整个序列有序。
	 * @return
	 * int[] arry = { 2,0,6,9,7,4,1,3,5,4 };
	 */
	private static int[] quickSortTwo(int[] arry, int low, int hight){
		if(low >= hight)return arry;
        int i = low;
        int j = hight;
        int index = arry[i]; // 用子表的第一个记录做基准
        while (i < j) { // 从表的两端交替向中间扫描
            while (i < j && arry[j] >= index)
                j--;
            if (i < j)
            {//arry[i++] = arry[j];// 用比基准小的记录替换低位记录,类比于下：
        		
            	arry[i] = arry[j];
            i++;}

            while (i < j && arry[i] < index)
                i++;
            if (i < j) // 用比基准大的记录替换高位记录
            	arry[j--] = arry[i];
//            	arry[j] = arry[i];
//            	j--;
            for(int m : arry){
    			System.out.print(m + ",");
    		}
            System.out.println("----i,j,index"+i+j+index);
        }
        arry[i] = index;// 将基准数值替换回 a[i]
        for(int m : arry){
			System.out.print(m + ",");
		}
        System.out.println();
        quickSortTwo(arry, low, i - 1); // 对低子表进行递归排序
        quickSortTwo(arry, i + 1, hight); // 对高子表进行递归排序
        return arry;
	}
	
	private static void replaceArry(int left, int right, int[] arry){
		int temp = arry[left];
		arry[left] = arry[right];
		arry[right] = temp;
	}

}
