package r1b.t2;

import util.IOUtil;
import util.Solver;

public final class NoisySolver implements Solver<NoisyInput> {
	@Override
	public String solve(NoisyInput input) {
		// r <= c
		int r = input.getR();
		int c = input.getC();
		int n = input.getN();

		// shortcuts
		if (n < 2) {
			return "0";
		}

		if (r == 1) {
			int maxHappyTenants = c / 2 + (c % 2);
			if (n <= maxHappyTenants) {
				return "0";
			}
			return Long.toString((n - maxHappyTenants) * 2);
		}

		int maxHappyTenants = (r / 2) * (c / 2) * 2;
		if ((r % 2) == 1) {
			maxHappyTenants += (c / 2);
		}
		if ((c % 2) == 1) {
			maxHappyTenants += (r / 2);
		}
		if (((r % 2) == 1) && ((c % 2) == 1)) {
			maxHappyTenants++;
		}

		if (n <= maxHappyTenants) {
			return "0";
		}

		// TODO Auto-generated method stub
		return r + " " + c + " " + n;
	}

	public static void main(String[] args) {
		IOUtil.doIO(NoisyInput.PARSER, new NoisySolver());
	}

}
