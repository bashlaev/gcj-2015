package y16.q.t2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public final class Solution implements Solver<PancakeInput> {

	@Override
	public String solve(PancakeInput input) {
		Boolean[] pancakes = input.getPancakes();
		int moves = 0;
		while (true) {
			int pluses = (int) Arrays.stream(pancakes).filter(Boolean::booleanValue).count();
			if (pluses == pancakes.length) {
				return "" + moves;
			}
			if (pluses == 0) {
				return "" + (moves + 1);
			}
			int n = IntStream.range(1, pancakes.length)
					.filter(i -> pancakes[i].booleanValue() != pancakes[0].booleanValue()).findFirst().getAsInt();
			flipInPlace(pancakes, n);
			moves++;
		}
	}

	private static void flipInPlace(Boolean[] pancakes, int n) {
		for (int i = 0; i < n / 2; i++) {
			boolean tmp = pancakes[i];
			pancakes[i] = !pancakes[n - 1 - i];
			pancakes[n - 1 - i] = !tmp;
		}
		if ((n % 2) > 0) {
			pancakes[n / 2] = !pancakes[n / 2];
		}
	}

	public static <T extends CaseInput> void doIO(CaseParser<T> parser, Solver<T> solver) {
		try (Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)))) {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			in.nextLine();
			for (int i = 1; i <= t; i++) {
				T caseInput = parser.parse(in);
				String output = solver.solve(caseInput);
				boolean startsWithNewLine = output.startsWith("\r\n");
				System.out.println("Case #" + i + ":" + (startsWithNewLine ? "" : " ") + output);
			}
		}
	}

	public static void main(String[] args) {
		doIO(PancakeInput.PARSER, new Solution());
	}

}

@FunctionalInterface
interface CaseParser<T extends CaseInput> {
	public T parse(Scanner s);
}

@FunctionalInterface
interface Solver<T extends CaseInput> {
	public String solve(T input);
}

interface CaseInput {
}

class PancakeInput implements CaseInput {
	public static final CaseParser<PancakeInput> PARSER = scanner -> {
		String s = scanner.nextLine().trim();
		Boolean[] pancakes = s.chars().mapToObj(i -> i == '+').toArray(Boolean[]::new);
		return new PancakeInput(pancakes);
	};

	private final Boolean[] pancakes;

	private PancakeInput(Boolean[] pancakes) {
		this.pancakes = pancakes;
	}

	public Boolean[] getPancakes() {
		return pancakes;
	}
}
