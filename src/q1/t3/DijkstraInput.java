package q1.t3;

import util.CaseInput;
import util.CaseParser;

public class DijkstraInput implements CaseInput {
	public static final CaseParser<DijkstraInput> PARSER = scanner -> {
		int l = scanner.nextInt();
		long x = scanner.nextLong();
		scanner.nextLine();
		String s = scanner.nextLine().trim();
		if (s.length() != l) {
			throw new RuntimeException("Error reading input");
		}
		return new DijkstraInput(x, s);
	};

	private final long x;
	private final String s;

	private DijkstraInput(long x, String s) {
		this.x = x;
		this.s = s;
	}

	public long getX() {
		return x;
	}

	public String getS() {
		return s;
	}
}
