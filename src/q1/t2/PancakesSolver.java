package q1.t2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import util.IOUtil;
import util.Solver;

public final class PancakesSolver implements Solver<PancakesInput> {
	private int lowerToCost(int num, int lowerTo) {
		if (num <= lowerTo) {
			return 0;
		}
		return (num / lowerTo) + ((num % lowerTo) > 0 ? 1 : 0) - 1;
	}

	private Stream<Integer> lowerTo(int num, int lowerTo) {
		if (num <= lowerTo) {
			return Stream.of(num);
		}
		int div = lowerToCost(num, lowerTo) + 1;
		int res = num / div;
		int rem = num % div;
		Stream.Builder<Integer> b = Stream.builder();
		IntStream.range(0, rem).forEach(i -> b.add(res + 1));
		IntStream.range(0, div - rem).forEach(i -> b.add(res));
		return b.build();
	}

	@Override
	public String solve(PancakesInput input) {
		List<Integer> pancakes = Arrays.stream(input.getPancakes()).boxed().sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		int specialMinutes = 0;
		while (true) {
			int max = pancakes.get(0);
			int bestSaved = 0;
			int bestLowerTo = 0;
			int bestTotalCost = 0;
			for (int lowerTo = 1; lowerTo < max - 1; lowerTo++) {
				// calculate total cost of lowering to this value
				final int lowerToF = lowerTo;
				int totalCost = pancakes.stream().filter(i -> i > lowerToF).mapToInt(i -> lowerToCost(i, lowerToF))
						.sum();
				int saved = max - lowerTo - totalCost;
				if (saved > bestSaved) {
					bestSaved = saved;
					bestLowerTo = lowerTo;
					bestTotalCost = totalCost;
				}
			}
			if (bestSaved == 0) {
				break;
			}

			final int bestLowerToF = bestLowerTo;
			pancakes = pancakes.stream().flatMap(num -> lowerTo(num, bestLowerToF)).sorted(Comparator.reverseOrder())
					.collect(Collectors.toList());
			specialMinutes += bestTotalCost;
		}
		return "" + (pancakes.get(0) + specialMinutes);
	}

	public static void main(String[] args) {
		IOUtil.doIO(PancakesInput.PARSER, new PancakesSolver());
	}
}
