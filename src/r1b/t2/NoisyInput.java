package r1b.t2;

import util.CaseInput;
import util.CaseParser;

public class NoisyInput implements CaseInput {
	public static final CaseParser<NoisyInput> PARSER = scanner -> {
		return new NoisyInput(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
	};

	private final int r;
	private final int c;
	private final int n;

	private NoisyInput(int r, int c, int n) {
		this.r = Math.min(r, c);
		this.c = Math.max(r, c);
		this.n = n;
	}

	public int getR() {
		return r;
	}

	public int getC() {
		return c;
	}

	public int getN() {
		return n;
	}
}
