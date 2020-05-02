package util;

@FunctionalInterface
public interface Solver<T extends CaseInput> {
	public String solve(T input);
}
