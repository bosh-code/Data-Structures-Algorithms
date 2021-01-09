package binSort;

/**
 * Class which demonstrates how bin sort can be used to
 * sort distinct integer numbers between 0 and MAX_VALUE
 */

public class BinSort {
	public static void main(String[] args) {
		int[] list = {17, 2, 23, 7, 41, 29, 19, 43, 31, 5, 11, 47, 13, 3, 37}; // distinct integer values between 0 and MAX_VALUE
		final int MAX_VALUE = 50;
		boolean[] flags = new boolean[MAX_VALUE + 1]; //initially all false
		// determine which bins should be set to true
		for (int i : list)
			flags[i] = true;
		// use the flags to put the numbers back in the list sorted
		int flagNo = 0;
		for (int j = 0; j < list.length; j++) {  // find the next flag that is true
			while (flagNo < flags.length && !flags[flagNo])
				flagNo++;
			list[j] = flagNo++;
		}
		// output the results
		for (int k = 0; k < list.length; k++)
			System.out.print((k > 0 ? ", " : "") + list[k]);
		System.out.println();
	}
}
