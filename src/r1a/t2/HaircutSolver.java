package r1a.t2;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import util.IOUtil;
import util.Solver;

public final class HaircutSolver implements Solver<HaircutInput> {
	@Override
	public String solve(HaircutInput input) {
		long n = input.getN();
		int[] barbers = input.getBarbers();

		long nLeft = n;
		int[] barbersWait = IntStream.range(0, barbers.length).map(i -> 0).toArray();

		if (n > barbers.length + 100) {
			// calculate threshold x
			BigDecimal mult = BigDecimal.ONE;
			for (int barber : barbers) {
				mult = mult.multiply(BigDecimal.valueOf(barber));
			}
			BigDecimal sum = BigDecimal.ZERO;
			for (int barber : barbers) {
				sum = sum.add(mult.divide(BigDecimal.valueOf(barber)));
			}
			long x = mult.multiply(BigDecimal.valueOf(n - barbers.length)).divide(sum, BigDecimal.ROUND_DOWN)
					.longValueExact();

			for (int i = 0; i < barbers.length; i++) {
				long clientsServed = x / (barbers[i]);
				nLeft -= clientsServed;
				barbersWait[i] = (int) (x - clientsServed * barbers[i]);
				if (barbersWait[i] > 0) {
					// currently serving client
					nLeft -= 1;
					barbersWait[i] = barbers[i] - barbersWait[i];
				}
			}

			if (nLeft <= 0) {
				throw new RuntimeException("Should never happen!");
			}
		}

		while (true) {
			// assign customers
			for (int i = 0; i < barbers.length; i++) {
				if (barbersWait[i] == 0) {
					barbersWait[i] = barbers[i];
					nLeft--;
					if (nLeft == 0) {
						return "" + (i + 1);
					}
				}
			}
			// pass a minute
			for (int i = 0; i < barbers.length; i++) {
				barbersWait[i] -= 1;
			}
		}
	}

	public static void main(String[] args) {
		IOUtil.doIO(HaircutInput.PARSER, new HaircutSolver());
	}

}
