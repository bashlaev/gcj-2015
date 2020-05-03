package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public final class IOUtil {
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
}
