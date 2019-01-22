package tests;

import main.FirstLastList;
import main.IFirstLastList;

public class FirstLastListFactory {
    public static <T extends Comparable<T>> IFirstLastList<T> create() {
    	return new FirstLastList<T>();
    }
}
