package util;

import java.io.*;
import java.util.Scanner;

public final class IOUtil {
	public static <T extends CaseInput> void doIO(CaseParser<T> parser, Solver<T> solver, int linesPerCase) {
		try (Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)))) {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; i++) {
				T caseInput = parser.parse(in);
				String output = solver.solve(caseInput);
				System.out.println("Case #" + i + ": " + output);
			}
		}
	}
}
