package r1b.t1;

import util.CaseInput;
import util.CaseParser;

public class CounterInput implements CaseInput {
	public static final CaseParser<CounterInput> PARSER = scanner -> {
		return new CounterInput(scanner.nextLong());
	};

	private final long n;

	private CounterInput(long n) {
		this.n = n;
	}

	public long getN() {
		return n;
	}
}
