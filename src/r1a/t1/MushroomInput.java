package r1a.t1;

import java.util.stream.IntStream;

import util.CaseInput;
import util.CaseParser;

public class MushroomInput implements CaseInput {
	public static final CaseParser<MushroomInput> PARSER = scanner -> {
		int count = scanner.nextInt();
		int[] mushrooms = IntStream.range(0, count).map(i -> scanner.nextInt()).toArray();
		return new MushroomInput(mushrooms);
	};

	private final int[] mushrooms;

	private MushroomInput(int[] mushrooms) {
		this.mushrooms = mushrooms;
	}

	public int[] getMushrooms() {
		return mushrooms;
	}
}
