package r1a.t3;

import java.util.stream.IntStream;

import util.CaseInput;
import util.CaseParser;

public class LoggingInput implements CaseInput {
	public static class Point {
		private final long x;
		private final long y;

		private Point(long x, long y) {
			this.x = x;
			this.y = y;
		}

		public long x() {
			return x;
		}

		public long y() {
			return y;
		}

		@Override
		public String toString() {
			return String.format("(%d,%d)", x, y);
		}
	}

	public static final CaseParser<LoggingInput> PARSER = scanner -> {
		int count = scanner.nextInt();
		Point[] trees = IntStream.range(0, count).mapToObj(i -> new Point(scanner.nextLong(), scanner.nextLong()))
				.toArray(Point[]::new);
		return new LoggingInput(trees);
	};

	private final Point[] trees;

	private LoggingInput(Point[] trees) {
		this.trees = trees;
	}

	public Point[] getTrees() {
		return trees;
	}
}
