package r1a.t3;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import r1a.t3.LoggingInput.Point;
import util.IOUtil;
import util.Solver;

public final class LoggingSolver implements Solver<LoggingInput> {
	@Override
	public String solve(LoggingInput input) {
		Point[] trees = input.getTrees();

		if (trees.length == 1) {
			return "\r\n0";
		}

		ConcurrentMap<Integer, Integer> map = new ConcurrentHashMap<>();

		Stream<Pair<Integer, Integer>> keyStream = IntStream.range(0, trees.length - 1).boxed()
				.flatMap(i -> IntStream.range(i + 1, trees.length).boxed().map(j -> Pair.of(i, j)));

		keyStream.parallel().forEach(pair -> {
			int div = divideTrees(trees, pair.getLeft(), pair.getRight());
			map.merge(pair.getLeft(), div, (v1, v2) -> Math.min(v1, v2));
			map.merge(pair.getRight(), div, (v1, v2) -> Math.min(v1, v2));
		});

		List<Integer> result = IntStream.range(0, trees.length).mapToObj(map::get).collect(Collectors.toList());
		String resultStr = result.stream().map(i -> "\r\n" + i).collect(Collectors.joining());

		return resultStr;
	}

	private static int divideTrees(Point[] trees, int t1, int t2) {
		long x1 = trees[t1].x();
		long y1 = trees[t1].y();
		long x2 = trees[t2].x();
		long y2 = trees[t2].y();
		int countBelowLine = 0;
		int countOnLine = 0;
		int countAboveLine = 0;
		for (Point tree : trees) {
			// special case - vertical line
			if (x1 == x2) {
				if (tree.x() > x1) {
					countAboveLine++;
				} else if (tree.x() == x1) {
					countOnLine++;
				} else {
					countBelowLine++;
				}
			} else {
				long lhs = tree.y() * (x2 - x1);
				long rhs = tree.x() * (y2 - y1) + y1 * x2 - y2 * x1;
				if (lhs < rhs) {
					countBelowLine++;
				} else if (lhs == rhs) {
					countOnLine++;
				} else {
					countAboveLine++;
				}
			}
		}

		return Math.min(countAboveLine, countBelowLine);
	}

	public static void main(String[] args) {
		IOUtil.doIO(LoggingInput.PARSER, new LoggingSolver());
	}

}
