package arithmetic;

public class Dichotomy {
	
	public static void main(String[] args) {
		int[] intArr = new int[]{1,2,3,4,5,6,7,8,9,9,9,10};
		
		int position = calculatePosition(intArr, 6);
		System.out.println(position == -1 ? "sorry，未找到该元素" : "该key位于:" + (position + 1));
		
		int lessPosition = findLastNumLessthanKey(intArr, 7);
		System.out.println("小于key的最后的位置是：" + (lessPosition + 1));
		
		int morePosition = findFirstNumMoreThanKet(intArr, 7);
		System.out.println("大于等于key的第一个位置是：" + (morePosition + 1));
		
		int p3 = findLastNumLessThanKey(intArr, 7);
		System.out.println("小于key的最后位置是：" + (p3 + 1));
		
	}
	
	/**
	 * 查找最后一个小于key的元素位置
	 * int[] intArr = new int[]{1,2,3,4,5,6,7,8,9,9,9,10};
	 * @param intArr
	 * @param key
	 * @return
	 */
	private static int findLastNumLessThanKey(int[] intArr, int key){
		if(isEmpty(intArr)){
			return -1;
		}
		int left = 0;
		int right = intArr.length - 1;
		int mid;
		while(left <= right){
			mid = (left + right) / 2;
			System.out.println("[" + left + "," + right + "," + mid +"]");
			if(intArr[mid] < key){
				left = mid + 1;
			}else {
				right = mid - 1;
			}
			
		}
		return right;
	}
	
	/**
	 * 查找大于key的第一个元素位置
	 * int[] intArr = new int[]{1,2,3,4,5,6,7,8,9,9,9,10};
	 * @param intArr
	 * @param key
	 * @return
	 */
	private static int findFirstNumMoreThanKet(int[] intArr, int key){
		if(isEmpty(intArr)){
			return -1;
		}
		int left = 0;
		int right = intArr.length - 1;
		int mid;
		while(left <= right){
			mid = (left + right) / 2;
			if(intArr[mid] < key){
				left = mid + 1;
			}else if(intArr[mid] >= key){
				right = mid - 1;
			}
			System.out.println("[" + left + "," + right +"]");
		}
		return left;
	}
	/**
	 * 找到最后一个小于key的元素位置
	 * @param intArr
	 * @param key
	 * @return
	 * int[] intArr = new int[]{1,2,3,4,5,6,7,8,9,9,9,10};
	 */
	private static int findLastNumLessthanKey(int[] intArr, int key){
		if(isEmpty(intArr)){
			return -1;
		}
		int left = 0;
		int right = intArr.length - 1;
		int mid;
		while(left <= right){
			mid = (left + right) / 2;
			if(intArr[mid] < key){
				left = mid + 1;
			}else {
				right = mid - 1;
			}
			System.out.println("[" + left + "," + right +"]");
		}
		return right;
	}
	
	/**
	 * 使用二分法查找值的位置
	 * @param intArr
	 * @param key
	 * @return
	 */
	private static int calculatePosition(int[] intArr, int key){
		if(intArr == null || intArr.length == 0){
			return -1;
		}
		int left = 0;
		int right = intArr.length - 1;
		int mid;
		while(left <= right){
			mid = (left + right) / 2;
			if(intArr[mid] < key){
				left = mid + 1;
			}else if(intArr[mid] > key){
				right = mid -1;
			}else {
				return mid;
			}
			System.out.println("[" + left + "," + right +"]");
		}
		return -1;
	}
	
	private static boolean isEmpty(int[] intArr){
		return intArr == null || intArr.length == 0;
	}
}

