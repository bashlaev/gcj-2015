package r1a.t2;

import java.util.stream.IntStream;

import util.CaseInput;
import util.CaseParser;

public class HaircutInput implements CaseInput {
	public static final CaseParser<HaircutInput> PARSER = scanner -> {
		int b = scanner.nextInt();
		long n = scanner.nextLong();
		scanner.nextLine();
		int[] barbers = IntStream.range(0, b).map(i -> scanner.nextInt()).toArray();
		return new HaircutInput(n, barbers);
	};

	private final long n;
	private final int[] barbers;

	private HaircutInput(long n, int[] barbers) {
		this.n = n;
		this.barbers = barbers;
	}

	public long getN() {
		return n;
	}

	public int[] getBarbers() {
		return barbers;
	}
}
