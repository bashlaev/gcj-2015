package q1.t2;

import java.util.stream.IntStream;

import util.CaseInput;
import util.CaseParser;

public class PancakesInput implements CaseInput {
	public static final CaseParser<PancakesInput> PARSER = scanner -> {
		int size = scanner.nextInt();
		int[] pancakes = IntStream.range(0, size).map(i -> scanner.nextInt()).toArray();
		return new PancakesInput(pancakes);
	};

	private final int[] pancakes;

	private PancakesInput(int[] pancakes) {
		this.pancakes = pancakes;
	}

	public int[] getPancakes() {
		return pancakes;
	}
}
