/**
 * Provides a sum function on arrays.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-03-26
 */
public class ArraySum {

	/** Returns the sum of values in the given array. */
	public static int sum(int[] a, int left, int right) {
		if (a == null || left < 0 || right > a.length - 1) {
			return -1;
		}
		if (left == right) {
			return a[left];
		}
		return a[left] + sum(a, left + 1, right);
	}
}
