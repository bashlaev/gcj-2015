package util;

import java.util.Scanner;

@FunctionalInterface
public interface CaseParser<T extends CaseInput> {
	public T parse(Scanner s);
}
