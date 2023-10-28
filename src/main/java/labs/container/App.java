package labs.container;

import java.util.function.Predicate;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		LinkedList<Integer> testList = new LinkedList<>();
		System.out.println("Empty list is empty: " + testList.isEmpty());

		testList.add(5);
		testList.add(-2);
		testList.add(0);
		System.out.println("Not empty list is empty: " + testList.isEmpty());
		System.out.println("Not empty list: " + testList.toString());

		LinkedList<Integer> copyTest = new LinkedList<>(testList);
		System.out.println("Copy list : " + copyTest.toString());

		testList.clear();
		System.out.println("Cleared list is empty: " + testList.isEmpty());
		System.out.println("Cleared list: " + testList.toString());

		copyTest.addFirst(7);
		System.out.println("Add at the top to the not empty list " + copyTest.toString());

		copyTest.removeIf(new Predicate<Integer>() {
			@Override
			public boolean test(Integer number) {
				return number == -2;
			}
		});
		System.out.println("Removing -2: " + copyTest.toString());

		copyTest.removeIf(new Predicate<Integer>() {
			@Override
			public boolean test(Integer number) {
				return number == 0;
			}
		});
		System.out.println("Removing 0: " + copyTest.toString());
		copyTest.add(4);
		System.out.println("Adding 4 at the end of the list " + copyTest.toString());
	}
}
