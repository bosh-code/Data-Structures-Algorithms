package binSort;

import java.security.SecureRandom;

public final class BinSort {
	
	public static final int ARRAY_LENGTH = 25;
	
	/**
	 * Unused empty default
	 */
	private BinSort() {}
	
	public static void main(String... args) {
		SecureRandom rand = new SecureRandom();
		int[] list = new int[ARRAY_LENGTH];
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			list[i] = rand.nextInt(ARRAY_LENGTH << 2);
		}
		final int maxValue = 100;
		int[] flags = new int[maxValue + 1];
		
		for (int value : list) {
			flags[value]++;
		}
		
		int flagNo = 0;
		for (int i = 0; i < list.length; i++) {
			while (flagNo < flags.length && !(flags[flagNo] >= 1)) {
				flagNo++;
			}
			while (flags[flagNo] > 1) {
				list[i] = flagNo;
				++i;
				flags[flagNo]--;
			}
			list[i] = flagNo;
			flagNo++;
		}
		
		for (int i = 0; i < list.length; i++) {
			if (i > 0) {
				System.out.print(", " + list[i]);
			} else {
				System.out.print(list[i]);
			}
		}
	}
}
