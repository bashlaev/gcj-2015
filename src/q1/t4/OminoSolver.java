package q1.t4;

import util.IOUtil;
import util.Solver;

public final class OminoSolver implements Solver<OminoInput> {
	@Override
	public String solve(OminoInput input) {
		int x = input.getX();
		int r = input.getR();
		int c = input.getC();

		// if not divisible - RICHARD wins
		if ((r * c % x) != 0) {
			return "RICHARD";
		}

		// if x is 1 or 2 - GABRIEL wins automatically
		if (x <= 2) {
			return "GABRIEL";
		}

		// if x >= 7, RICHARD wins (he can choose an omino with a hole)
		if (x >= 7) {
			return "RICHARD";
		}

		// otherwise RICHARD can win if he can choose an omino that won't fit
		int minDimension = (x + 1) / 2;
		if (minDimension > Math.min(r, c)) {
			return "RICHARD";
		}
		if (x > Math.max(r, c)) {
			return "RICHARD";
		}

		// now the only case RICHARD can win is if he can force a bad split
		if (minDimension == Math.min(r, c)) {
			// no way for 3-mino
			if (x == 3) {
				return "GABRIEL";
			}
			// always for 4-mino
			if (x == 4) {
				return "RICHARD";
			}
			// for 5-mino
			if (x == 5) {
				// if bigger dimension is at least 10, then Gabriel, else Richard (by choosing a
				// shape below)
				// OO
				// OO
				// O
				return Math.max(r, c) >= 10 ? "GABRIEL" : "RICHARD";
			}
			if (x == 6) {
				// RICHARD should always win by choosing the shape below
				// O O
				// OOO
				// O
				return "RICHARD";
			}
		}

		return "GABRIEL";
	}

	public static void main(String[] args) {
		IOUtil.doIO(OminoInput.PARSER, new OminoSolver());
	}

}
