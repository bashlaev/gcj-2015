package q1.t1;

import util.CaseInput;
import util.CaseParser;

public class OvationInput implements CaseInput {
	public static final CaseParser<OvationInput> PARSER = scanner -> {
		int sMax = scanner.nextInt();
		int[] audience = scanner.nextLine().trim().chars().map(i -> i - '0').toArray();
		if (audience.length != sMax + 1) {
			throw new RuntimeException("Invalid input");
		}
		return new OvationInput(audience);
	};

	private final int[] audience;

	private OvationInput(int[] audience) {
		this.audience = audience;
	}

	public int[] getAudience() {
		return audience;
	}
}
