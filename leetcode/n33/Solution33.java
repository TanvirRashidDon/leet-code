package leetcode.n33;

public class Solution33 {
    public int search() {
        int[] nums = new int[]{3, 1};
        int target = 0;

        int left = 0;
        int right = nums.length - 1;

        if (target < nums[left] && target > nums[right]) { // not in the list
            return -1;
        }

        if (isRotated(nums)) {
            int pivot = findPivot(nums, left, right);
            System.out.println(pivot);

            if (target < nums[left]) {
                left = pivot;
            } else if (right != 0){ // array length is not 1
                right = pivot - 1;
            }
        }

        System.out.println("left: " + left + ", right: " + right);
        return bSearch(nums, target, left, right);
    }
    private boolean isRotated(int[] nums) {
        // first element is greater than last element
        return nums[0] > nums[nums.length - 1];
    }

    private int findPivot(int[] nums, int left, int right) {
        if (nums.length == 2) { // second index is pivot if array size is 2
            return 1;
        }

        int mid = (left + right) / 2;
        System.out.println("left: " + nums[left] + ", mid: " + nums[mid] + ", right: " + nums[right]);

        if (isPivot(nums, mid)) {
            return mid;
        }

        if (isPivotInLeftSide(nums, mid, right)) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }

        return findPivot(nums, left, right);
    }

    private boolean isPivot(int[] nums, int currentIndex) {
        int previousIndex = currentIndex - 1;
        int nextIndex = currentIndex + 1;

        return nums[previousIndex] > nums[currentIndex] && nums[currentIndex] < nums[nextIndex];
    }

    private boolean isPivotInLeftSide(int[] nums, int mid, int right) {
        return nums[mid] < nums[right];
    }

    private int bSearch(int[] array, int target, int left, int right) {
        int mid = (left + right) / 2;
//        System.out.println("left: " + array[left] + ", mid: " + array[mid] + ", right: " + array[right]);

        if (array[mid] == target) { // found
            return mid;
        }

        if (left >= right) { // not present
            return -1;
        }

        // target prest in right side
        if (target > array[mid]) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }

        return bSearch(array, target, left, right);
    }
}
