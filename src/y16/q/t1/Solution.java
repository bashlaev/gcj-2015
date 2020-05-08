package y16.q.t1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Solution implements Solver<SheepInput> {

	@Override
	public String solve(SheepInput input) {
		long n = input.getN();
		if (n == 0) {
			return "INSOMNIA";
		}
		Set<Integer> digitsNotMet = IntStream.range(0, 10).boxed().collect(Collectors.toSet());
		long value = n;
		while (true) {
			// "say" value
			Long.toString(value).chars().map(i -> i - '0').forEach(digitsNotMet::remove);
			if (digitsNotMet.isEmpty()) {
				return Long.toString(value);
			}
			value += n;
		}
	}

	public static <T extends CaseInput> void doIO(CaseParser<T> parser, Solver<T> solver) {
		try (Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)))) {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; i++) {
				T caseInput = parser.parse(in);
				String output = solver.solve(caseInput);
				boolean startsWithNewLine = output.startsWith("\r\n");
				System.out.println("Case #" + i + ":" + (startsWithNewLine ? "" : " ") + output);
			}
		}
	}

	public static void main(String[] args) {
		doIO(SheepInput.PARSER, new Solution());
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

class SheepInput implements CaseInput {
	public static final CaseParser<SheepInput> PARSER = scanner -> {
		return new SheepInput(scanner.nextLong());
	};

	private final long n;

	private SheepInput(long n) {
		this.n = n;
	}

	public long getN() {
		return n;
	}
}
