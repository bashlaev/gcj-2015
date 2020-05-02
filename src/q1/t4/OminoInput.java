package q1.t4;

import util.CaseInput;
import util.CaseParser;

public class OminoInput implements CaseInput {
	public static final CaseParser<OminoInput> PARSER = scanner -> {
		int x = scanner.nextInt();
		int r = scanner.nextInt();
		int c = scanner.nextInt();
		return new OminoInput(x, r, c);
	};

	private final int x;
	private final int r;
	private final int c;

	private OminoInput(int x, int r, int c) {
		this.x = x;
		this.r = r;
		this.c = c;
	}

	public int getX() {
		return x;
	}

	public int getR() {
		return r;
	}

	public int getC() {
		return c;
	}
}
