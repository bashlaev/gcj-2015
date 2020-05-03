package r1b.t1;

import java.util.stream.LongStream;

import org.apache.commons.lang3.tuple.Pair;

import util.IOUtil;
import util.Solver;

public final class CounterSolver implements Solver<CounterInput> {

	@Override
	public String solve(CounterInput input) {
		long n = input.getN();
		Pair<Long, Long> value = Pair.of(0L, n);
		while (value.getRight() > 0) {
			value = stepDown(value);
		}
		return "" + value.getLeft();
	}

	// (moves spent, final value)
	private Pair<Long, Long> stepDown(Pair<Long, Long> pair) {
		long n = pair.getRight();
		long moves = pair.getLeft();
		if (n < 10) {
			return Pair.of(moves + n, 0L);
		}
		String nStr = Long.toString(n);
		int order = nStr.length();
		int halfOrder = order / 2;
		long halfOrderBase = decPow(halfOrder - 1);
		long halfOrderValue = Long.parseLong(nStr.substring(0, halfOrder));
		if (halfOrderBase == halfOrderValue) {
			// already at base
			long finalValue = decPow(order - 1);
			return Pair.of(moves + 1 + n - finalValue, finalValue - 1);
		}

		long secondHalfOrder = decPow(halfOrder);
		long secondHalfBase = n % secondHalfOrder;

		if (secondHalfBase == 1) {
			return Pair.of(moves + 1, Long.parseLong(new StringBuilder().append(nStr).reverse().toString()));
		}

		// lower to 1
		long lowerBy = (secondHalfBase == 0) ? secondHalfOrder - 1 : secondHalfBase - 1;
		return Pair.of(moves + lowerBy, n - lowerBy);
	}

	private static long decPow(int power) {
		if (power == 0) {
			return 1;
		}
		return LongStream.range(0, power).reduce(1L, (i, j) -> 10L * i);
	}

	public static void main(String[] args) {
		IOUtil.doIO(CounterInput.PARSER, new CounterSolver());
	}

}
