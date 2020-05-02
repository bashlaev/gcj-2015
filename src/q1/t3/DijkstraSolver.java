package q1.t3;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import util.IOUtil;
import util.Solver;

public final class DijkstraSolver implements Solver<DijkstraInput> {
	private static enum Value {
		I, J, K, ONE, MINUS_I, MINUS_J, MINUS_K, MINUS_ONE;

		public static Value fromChar(int c) {
			switch (c) {
			case 'i':
				return I;
			case 'j':
				return J;
			case 'k':
				return K;
			default:
				throw new RuntimeException("unrecognized letter: " + c);
			}
		}

		public boolean isNegative() {
			return ordinal() > 3;
		}

		public Value invert() {
			return Value.values()[isNegative() ? ordinal() - 4 : ordinal() + 4];
		}
	}

	private static EnumMap<Value, EnumMap<Value, Value>> multiMap = new EnumMap<>(Value.class);

	static {
		EnumMap<Value, Value> mI = new EnumMap<>(Value.class);
		mI.put(Value.I, Value.MINUS_ONE);
		mI.put(Value.J, Value.K);
		mI.put(Value.K, Value.MINUS_J);
		mI.put(Value.ONE, Value.I);
		multiMap.put(Value.I, mI);
		EnumMap<Value, Value> mJ = new EnumMap<>(Value.class);
		mJ.put(Value.I, Value.MINUS_K);
		mJ.put(Value.J, Value.MINUS_ONE);
		mJ.put(Value.K, Value.I);
		mJ.put(Value.ONE, Value.J);
		multiMap.put(Value.J, mJ);
		EnumMap<Value, Value> mK = new EnumMap<>(Value.class);
		mK.put(Value.I, Value.J);
		mK.put(Value.J, Value.MINUS_I);
		mK.put(Value.K, Value.MINUS_ONE);
		mK.put(Value.ONE, Value.K);
		multiMap.put(Value.K, mK);
		EnumMap<Value, Value> mOne = new EnumMap<>(Value.class);
		mOne.put(Value.I, Value.I);
		mOne.put(Value.J, Value.J);
		mOne.put(Value.K, Value.K);
		mOne.put(Value.ONE, Value.ONE);
		multiMap.put(Value.ONE, mOne);
	}

	public static Value multiply(Value v1, Value v2) {
		boolean minus = false;
		if (v1.isNegative()) {
			v1 = v1.invert();
			minus = !minus;
		}
		if (v2.isNegative()) {
			v2 = v2.invert();
			minus = !minus;
		}
		Value result = multiMap.get(v1).get(v2);
		if (minus) {
			result = result.invert();
		}
		return result;
	}

	public static Value multiplyString(String s) {
		List<Value> values = s.chars().mapToObj(Value::fromChar).collect(Collectors.toList());
		Value v = Value.ONE;
		for (Value value : values) {
			v = multiply(v, value);
		}
		return v;
	}

	public static Value multiplyStrings(String s, long times) {
		Value m = multiplyString(s);
		if (times == 1) {
			return m;
		}
		Value dbl = multiply(m, m);
		if (dbl == Value.ONE) {
			return ((times % 2) == 0) ? Value.ONE : m;
		}
		if (dbl == Value.MINUS_ONE) {
			switch ((int) (times % 4)) {
			case 0:
				return Value.ONE;
			case 1:
				return m;
			case 2:
				return Value.MINUS_ONE;
			case 3:
				return m.invert();
			}
		}
		throw new RuntimeException("Should never happen!");
	}

	public static int getShortestSub(String s, Value value, boolean fromStart) {
		int i = 1;
		int length = s.length();
		while (i <= length) {
			String sub = fromStart ? s.substring(0, i) : s.substring(length - i, length);
			if (multiplyString(sub) == value) {
				return i;
			}
			i++;
		}
		return -1;
	}

	@Override
	public String solve(DijkstraInput input) {
		String s = input.getS();
		long x = input.getX();

		if (x < 1) {
			return "NO";
		}

		// first of all, you can never make it with only one letter
		if (s.length() < 2) {
			return "NO";
		}

		// then, check the total value, should be -1
		Value total = multiplyStrings(s, x);
		if (total != Value.MINUS_ONE) {
			return "NO";
		}

		// need no more than 4 iterations
		int maxIterations = (int) Math.min(4, x);
		String maxS = maxIterations == 1 ? s
				: ((maxIterations == 2) ? s + s : ((maxIterations == 3) ? s + s + s : s + s + s + s));
		int minIFromStart = getShortestSub(maxS, Value.I, true);
		if (minIFromStart < 0) {
			return "NO";
		}
		int minKFromEnd = getShortestSub(maxS, Value.K, false);
		if (minKFromEnd < 0) {
			return "NO";
		}

		if (minIFromStart + minKFromEnd < x * s.length()) {
			return "YES";
		} else {
			return "NO";
		}
	}

	public static void main(String[] args) {
		IOUtil.doIO(DijkstraInput.PARSER, new DijkstraSolver());
	}
}
