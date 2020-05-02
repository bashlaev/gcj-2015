package r1a.t1;

import util.IOUtil;
import util.Solver;

public class MushroomSolver implements Solver<MushroomInput> {

	@Override
	public String solve(MushroomInput input) {
		int[] mushrooms = input.getMushrooms();
//		System.out.println(Arrays.toString(mushrooms));
		int res1 = 0;
		int maxDiff = 0;
		for (int i = 1; i < mushrooms.length; i++) {
			int diff = mushrooms[i - 1] - mushrooms[i];
			if (diff > 0) {
				res1 += diff;
				maxDiff = Math.max(maxDiff, diff);
			}
		}
		int res2 = 0;
		for (int i = 0; i < mushrooms.length - 1; i++) {
			res2 += Math.min(mushrooms[i], maxDiff);
		}
		return res1 + " " + res2;
	}

	public static void main(String[] args) {
		IOUtil.doIO(MushroomInput.PARSER, new MushroomSolver());
	}

}
