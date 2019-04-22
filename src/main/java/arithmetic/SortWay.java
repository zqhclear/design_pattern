package arithmetic;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 各种排序算法
 * @Author zhongqionghua
 * @Create 2018/10/19 14:11
 */
public class SortWay {

	public static void main(String[] args) {

		Map<String, Object> param = new HashMap<>();
		Map<String, Object> map = (Map<String, Object>) param.get("asdf");



		//冒泡排序
		int[] intArrayBubble = generateArray();
		bubbleSort(intArrayBubble);
		System.out.println();

		//选择排序
		int[] intArray = generateArray();
		selectSort(intArray);
		System.out.println();

		//插入排序
		int[] intArrayInsert = generateArray();
		insertSort(intArrayInsert);
		System.out.println();

		//二分法排序--是简单插入排序的优化
		int[] intBinaryArray = generateArray();
		binarySort(intBinaryArray);
		System.out.println();

		//希尔排序
		int[] intShellArray = generateArray();
		shellSort(intShellArray);
		System.out.println();

		//快速排序一(是对冒泡排序的优化)：
 		int[] intArrayQuick = {13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11};
		System.out.println("quickSort: " + JSONObject.toJSONString(intArrayQuick));
		long startTime = System.currentTimeMillis();
		int[] arraySorted = quickSort(intArrayQuick, 0, intArrayQuick.length - 1);
		System.out.println("TIME:" + (System.currentTimeMillis() - startTime));
		System.out.println("quickSort: " + JSONObject.toJSONString(arraySorted));
		System.out.println();

//		int[] intArrayQuick2 = generateArray();
//		int[] arraySorted2 = quickSortTwo(intArrayQuick2, 0, intArrayQuick2.length - 1);

		//堆排序
		int[] intArrayHeap = generateArray();
		heapSort(intArrayHeap);
		System.out.println();

		//归并排序：先分后治
		int[] intArrayMergeSort = generateArray();
		mergeSort(intArrayMergeSort);
		System.out.println();
	}

	/**
	 * 从第二个元素开始，和之前的已排好序的字数组的元素比较，找到合适的位置，
	 * 然后后面的元素向后移动一位，再将该元素插入到前面合适的位置。时间复杂度为（O（n^2））
	 *
	 * @param intArray
	 */
	private static void insertSort(int[] intArray) {
		System.out.println("insertSort: " + JSONObject.toJSONString(intArray));
		long startTime = System.currentTimeMillis();
		for (int i = 1; i < intArray.length; i++) {
			int key = intArray[i];
			int j = i - 1;
			while (j >= 0 && intArray[j] > key) {
				intArray[j + 1] = intArray[j];
				j--;
			}
			intArray[j + 1] = key;
		}
		System.out.println("TIME:" + (System.currentTimeMillis() - startTime));
		System.out.println("insertSort: " + JSONObject.toJSONString(intArray));
	}

	/**
	 * 选择排序
	 * 比如:从小到大,类似冒泡,每次找出最小的数,然后将最小的数放置最前边
	 * <p>
	 * 思路：类似于冒泡，每次将后面最小的元素放在前面。时间复杂度为（（O（n^2）））
	 * 和冒泡的最大的区别在于,选择排序只是记录极值的下标,之后直接替换位置,一次j的遍历只会替换一次
	 * 冒泡是便利一次比一次,一直将极值推进到对应的位置,一次j的遍历,可能会替换多次
	 *
	 * @param intArray
	 */
	private static void selectSort(int[] intArray) {
		System.out.println("selectSort: " + JSONObject.toJSONString(intArray));
		long startTime = System.currentTimeMillis();
		int temp, min;
		//保存最小的元素的下标
		for (int i = 0; i < intArray.length - 1; i++) {
			min = i;
			for (int j = i + 1; j < intArray.length; j++) {
				if (intArray[j] < intArray[min]) {
					min = j;
				}
			}
			//当最小值不是int[i]的时候,才需要调换位置
			if (min != i) {
				temp = intArray[min];
				intArray[min] = intArray[i];
				intArray[i] = temp;
			}
		}
		System.out.println("TIME:" + (System.currentTimeMillis() - startTime));
		System.out.println("selectSort: " + JSONObject.toJSONString(intArray));
	}

	/**
	 * 希尔排序
	 * 思路：类似于插入排序，只是每次所取的步长为（数组的长度 /  2 / i）。时间复杂度为（n*log n）。
	 *
	 * @param intArray
	 */
	/**
	 * 希尔排序进行分组的时候,多少元素为一组,本例子:将该十个元素分为5组,每组2个元素
	 */
	/**
	 * 每组元素个数
	 */
	static int defaultShell = 2;

	private static void shellSort(int[] intArray) {
		System.out.println("shellSort: " + JSONObject.toJSONString(intArray));
		long startTime = System.currentTimeMillis();
		for (int gap = intArray.length / defaultShell; gap > 0; gap = gap / defaultShell) {
			for (int i = gap; i < intArray.length; i++) {
				int key = intArray[i];
				int j = i - gap;
				while (j >= 0 && intArray[j] > key) {
					intArray[j + gap] = intArray[j];
					j = j - gap;
				}
				intArray[j + gap] = key;
			}
		}
		System.out.println("TIME:" + (System.currentTimeMillis() - startTime));
		System.out.println("shellSort: " + JSONObject.toJSONString(intArray));
	}

	/**
	 * 二分法排序
	 * 思路:插入排序的优化，先用二分法查找插入位置，
	 * 然后再所有插入位置（二分法查出来的）后面的元素全部后移
	 *
	 * @param values
	 */
	private static void binarySort(int[] values) {
		System.out.println("binarySort: " + JSONObject.toJSONString(values));
		long startTime = System.currentTimeMillis();
		for (int i = 1; i < values.length; i++) {
			int currentValues = values[i];
			int low = 0;
			int high = i - 1;
			while (low <= high) {
				int mid = (low + high) / 2;
				if (currentValues < values[mid]) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
			for (int j = i - 1; j > high; j--) {
				values[j + 1] = values[j];
			}
			values[high + 1] = currentValues;
			System.out.println("binarySort: " + JSONObject.toJSONString(values));
		}
		System.out.println("TIME:" + (System.currentTimeMillis() - startTime));
		System.out.println("binarySort: " + JSONObject.toJSONString(values));
	}


	/**
	 * 归并排序：分治思想，先分后治
	 * 思路：用分治的思想，将数组分至最小，再合并即可.时间复杂度为（n * log n ）是一个稳定的排序算法.
	 *
	 * @param intArray
	 */
	private static void mergeSort(int[] intArray) {
		System.out.println("mergeSort: " + JSONObject.toJSONString(intArray));
		long startTime = System.currentTimeMillis();
		int[] temp = new int[intArray.length];
		mergeSortRecursion(intArray, 0, intArray.length - 1, temp);
		System.out.println("TIME:" + (System.currentTimeMillis() - startTime));
		System.out.println("mergeSort: " + JSONObject.toJSONString(intArray));
	}

	private static void mergeSortRecursion(int[] arr, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;
			//左边归并排序，使得左子序列有序
			mergeSortRecursion(arr, left, mid, temp);
			//右边归并排序，使得右子序列有序
			mergeSortRecursion(arr, mid + 1, right, temp);
			//将两个有序子数组合并操作
			merge(arr, left, mid, right, temp);
		}
	}

	private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int i = left;
		int j = mid + 1;
		int t = 0;
		//mid左右两边的数组都是已经排序过了的,将其比较并按照从小到大加入到temp中
		//注意:下面的加入方法会出现mid左右的数组没有全部加入,此时需要通过后面的方法再添加一次
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				temp[t++] = arr[i++];
			} else {
				temp[t++] = arr[j++];
			}
		}
		//将左边剩余元素填充进temp中
		while (i <= mid) {
			temp[t++] = arr[i++];
		}
		//将右序列剩余元素填充进temp中
		while (j <= right) {
			temp[t++] = arr[j++];
		}
		t = 0;
		//将temp中的元素全部拷贝到原数组中
		while (left <= right) {
			arr[left++] = temp[t++];
		}
	}

	/**
	 * 冒泡排序（时间复杂度为O(n⑵)）
	 * 比较相邻的元素。如果第一个比第二个小，就交换他们两个。
	 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最小的数。
	 * 针对所有的元素重复以上的步骤，除了最后一个。
	 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
	 * <p>
	 * 思路：每遍历一次j,就可以将最大或最小的元素放到数组的最前.时间复杂度为（O（n^2））
	 */
	private static void bubbleSort(int[] intArray) {
		System.out.println("bubbleSort:" + JSONObject.toJSONString(intArray));
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < intArray.length; i++) {
			for (int j = i + 1; j < intArray.length; j++) {
				//倒叙
				if (intArray[i] <= intArray[j]) {
					int temp = intArray[i];
					intArray[i] = intArray[j];
					intArray[j] = temp;
				}
			}
		}
		System.out.println("TIME:" + (System.currentTimeMillis() - startTime));
		System.out.println("bubbleSort:" + JSONObject.toJSONString(intArray));
	}

	/**
	 * 快速排序一(正序) : 使用的是分治的思想，为先治后分
	 * 时间复杂度平均为：最好的情况O(nlog(n))
	 * 通过一趟排序将记录分割成独立的两部分，其中一部分的关键字均比另一部分的关键字小，
	 * 然后分别对这两部分的记录继续进行排序，以达到整个序列有序。
	 *
	 * @return int[] intArray = { 2,0,6,9,7,4,1,3,5,4 };
	 */
	public static int[] quickSort(int[] intArray, int left, int right) {
		//参数不符合要求
		if (left >= right) {
			return intArray;
		}
		int i = left;
		int j = right;
		int initialNum = intArray[i];
		while (i < j) {
			/*
			此处为什么一定是需要从后往前找，而不能从前往后找的？
			原因：在找的过程中，其实是会改变指针（i,j）的值的，如果是先找左边的话，会出现找到对应的值，
			但是这个值是不正确的，不符合规范（即i>=j），指针i的值也发生了变，后续处理也是会计算进去，错误

			右边找到第一个小于初始值的第一个位置（从后往前）
			 */
			while (i < j && intArray[j] >= initialNum) {
				j--;
			}
			//左边找到大于初始的值的第一个位置
			while (i < j && intArray[i] <= initialNum) {
				i++;
			}
			if (i < j) {
				int temp = intArray[i];
				intArray[i] = intArray[j];
				intArray[j] = temp;
				//经过了该步骤，就已经达到了intArray[i]已经是最后的一个小于初始位置的值了
			}
		}
		//到这一步,i==j已成立
		intArray[left] = intArray[j];
		intArray[j] = initialNum;
		/*
		上两步可以使得：将初始位置放置在其中间位置，
		使得左边全是小于（大于）该初始值的值，右边全是大于（小于）初始值的值
		排序初始值左边
		 */
		System.out.println(JSONObject.toJSONString(intArray));
		quickSort(intArray, left, i - 1);
		//排序初始值右边
		quickSort(intArray, i + 1, right);
		return intArray;
	}

	/**
	 * 快速排序二(正序),时间复杂度平均为：最好的情况O(nlog(n))
	 * 通过一趟排序将记录分割成独立的两部分，其中一部分的关键字均比另一部分的关键字小，
	 * 然后分别对这两部分的记录继续进行排序，以达到整个序列有序。
	 *
	 * @param intArray
	 * @param low
	 * @param high
	 * @return
	 */
//	private static int[] quickSortTwo(int[] intArray, int low, int high) {
//		if (low >= high) {
//			return intArray;
//		}
//		int i = low;
//		int j = high;
//		int index = intArray[i];
//		while (i < j) {
//			while (i < j && intArray[j] >= index) {
//				j--;
//			}
//			if (i < j) {
//				intArray[i] = intArray[j];
//				i++;
//			}
//			while (i < j && intArray[i] < index) {
//				i++;
//			}
//			// 用比基准大的记录替换高位记录
//			if (i < j) {
//				intArray[j] = intArray[i];
//			}
//		}
//		// 将基准数值替换回 a[i]
//		intArray[i] = index;
//		// 对低子表进行递归排序
//		quickSortTwo(intArray, low, i - 1);
//		// 对高子表进行递归排序
//		quickSortTwo(intArray, i + 1, high);
//		return intArray;
//	}

	/**
	 * 堆排序:先将数组变为大根(顶)堆,然后去根元素（每次取完都要重新整理成大根堆）
	 */
	private static void heapSort(int[] intArray) {
		System.out.println("heapSort:" + JSONObject.toJSONString(intArray));
		long startTime = System.currentTimeMillis();
		//堆排序,初始建堆，array[0]为第一趟值最大的元素
		buildMaxHeap(intArray);
		for (int i = intArray.length - 1; i >= 1; i--) {
			//将堆顶元素和堆底元素交换，即得到当前最大元素正确的排序位置
			int temp = intArray[0];
			intArray[0] = intArray[i];
			intArray[i] = temp;
			//整理，将剩余的元素整理成堆
			adjustDownToUp(intArray, 0, i);
		}
		System.out.println("TIME:" + (System.currentTimeMillis() - startTime));
		System.out.println("heapSort:" + JSONObject.toJSONString(intArray));
	}

	/**
	 * 构建大根堆：将array看成完全二叉树的顺序存储结构
	 *
	 * @param array
	 * @return
	 */
	private static int[] buildMaxHeap(int[] array) {
		/*
		从最后一个节点array.length-1的父节点（array.length-1-1）/2开始，直到根节点0，反复调整堆
		即：从下往上，从右往左
		 */
		for (int i = (array.length - 2) / 2; i >= 0; i--) {
			adjustDownToUp(array, i, array.length);
		}
		return array;
	}

	/**
	 * 将元素array[k]自下往上逐步调整树形结构
	 *
	 * @param array
	 * @param k
	 * @param length
	 */
	private static void adjustDownToUp(int[] array, int k, int length) {
		int temp = array[k];
		//i初始化为节点k的左孩子，沿节点较大的子节点向下调整
		for (int i = 2 * k + 1; i <= length - 1; i = 2 * i + 1) {
			//取节点较大的子节点的下标；i + 1 < length防止该根节点只有一个子节点出现数组越界
			if (i + 1 < length && array[i] < array[i + 1]) {
				//如果节点的右孩子>左孩子，则取右孩子节点的下标
				i++;
			}
			//根节点 >=左右子女中关键字较大者，不需要调整
			if (temp >= array[i]) {
				break;
				//根节点 <左右子女中关键字较大者，调整
			} else {
				//将左右子结点中较大值array[i]调整到双亲节点上
				array[k] = array[i];
				//关键:修改k值，以便继续向下调整
				k = i;
			}
		}
		//被调整的结点的值放入最终位置
		array[k] = temp;
	}

	/**
	 * 删除堆顶元素操作
	 *
	 * @param array
	 * @return
	 */
	public int[] deleteMax(int[] array) {
		//将堆的最后一个元素与堆顶元素交换，堆底元素值设为-99999
		array[0] = array[array.length - 1];
		array[array.length - 1] = -99999;
		//对此时的根节点进行向下调整
		adjustDownToUp(array, 0, array.length);
		return array;
	}

	/**
	 * 插入操作:向大根堆array中插入数据data
	 *
	 * @param array
	 * @param data
	 * @return
	 */
	public int[] insertData(int[] array, int data) {
		//将新节点放在堆的末端
		array[array.length - 1] = data;
		//需要调整的节点
		int k = array.length - 1;
		//双亲节点
		int parent = (k - 1) / 2;
		while (parent >= 0 && data > array[parent]) {
			//双亲节点下调
			array[k] = array[parent];
			k = parent;
			if (parent != 0) {
				//继续向上比较
				parent = (parent - 1) / 2;
			} else {
				//根节点已调整完毕，跳出循环
				break;
			}
		}
		//将插入的结点放到正确的位置
		array[k] = data;
		return array;
	}

	/**
	 * 初始化一个乱序数组
	 *
	 * @return
	 */
	private static int[] generateArray() {
		int[] intArray = {87, 45, 78, 32, 17, 65, 53, 9, 122, 133};
//		int[] a = new int[10000];
//		for (int i = 0; i < 10000; i++) {
//			a[i] = new Random().nextInt(5000000) + 1;
//		}
		return intArray;
	}

}
