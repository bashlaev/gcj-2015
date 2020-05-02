package q1.t2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import util.IOUtil;
import util.Solver;

public final class PancakesSolver implements Solver<PancakesInput> {
	@Override
	public String solve(PancakesInput input) {
		return solve1(input);
	}

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

	public String solve1(PancakesInput input) {
		List<Integer> pancakes = Arrays.stream(input.getPancakes()).boxed().sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
//		System.out.println(pancakes);
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

//			System.out.println("lowering to " + bestLowerTo);
			final int bestLowerToF = bestLowerTo;
			pancakes = pancakes.stream().flatMap(num -> lowerTo(num, bestLowerToF)).sorted(Comparator.reverseOrder())
					.collect(Collectors.toList());
//			System.out.println(pancakes);
			specialMinutes += bestTotalCost;
		}
		return "" + (pancakes.get(0) + specialMinutes);
	}

	public String solve2(PancakesInput input) {
		List<Integer> pancakes = Arrays.stream(input.getPancakes()).boxed().sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
//		System.out.println(pancakes);
		int specialMinutes = 0;
		while (true) {
			int max = pancakes.get(0);
			List<Integer> rest = pancakes.subList(1, pancakes.size());
			int bestSaved = 0;
			int bestDiv = 0;
			for (int div = 2; div < max - 1; div++) {
				// calculate what we save
				int maxDivved = max / div + ((max % div) > 0 ? 1 : 0);
				int maxOfTheRest = rest.stream().max(Comparator.naturalOrder()).orElse(0);
				// lower max to max of the rest
				int saved1 = max - Math.max(maxDivved, maxOfTheRest); // - (int) rest.stream().filter(i -> i >
																		// maxDivved).count();
				// lower max to maxDiv by lowering all the rest to maxDiv
				int saved2 = max - maxDivved - rest.stream().filter(i -> i > maxDivved)
						.mapToInt(i -> i / maxDivved + ((i % maxDivved) > 0 ? 1 : 0) - 1).sum();
				int saved = Math.max(saved1, saved2) - (div - 1);
				if (saved > bestSaved) {
					bestSaved = saved;
					bestDiv = div;
				}
			}
			if (bestDiv == 0) {
				break;
			}
			pancakes.remove(0);
			int res = max / bestDiv;
			int rem = max % bestDiv;
			IntStream.range(0, rem).forEach(i -> pancakes.add(res + 1));
			IntStream.range(0, bestDiv - rem).forEach(i -> pancakes.add(res));
			Collections.sort(pancakes, Comparator.reverseOrder());
//			System.out.println(pancakes);
			specialMinutes += (bestDiv - 1);
		}
		return "" + (pancakes.get(0) + specialMinutes);
	}

	public static void main(String[] args) {
		IOUtil.doIO(PancakesInput.PARSER, new PancakesSolver());
	}
}
