package q1.t1;

import util.IOUtil;
import util.Solver;

public final class OvationSolver implements Solver<OvationInput> {

	public static void main(String[] args) {
		IOUtil.doIO(OvationInput.PARSER, new OvationSolver());
	}

	@Override
	public String solve(OvationInput input) {
		int[] audience = input.getAudience();
		int toAdd = 0;
		int rollingSum = 0;
		for (int i = 0; i < audience.length; i++) {
			if (rollingSum < i) {
				int addThisLevel = i - rollingSum;
				toAdd += addThisLevel;
				rollingSum += addThisLevel;
			}
			rollingSum += audience[i];
		}
		return "" + toAdd;
	}

}
