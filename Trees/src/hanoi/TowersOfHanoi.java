package hanoi;

import java.util.Scanner;

public class TowersOfHanoi {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		towersOfHanoi(num);
		scan.close();
	}

	public static void towersOfHanoi(int n) {
		move(n, "peg 1", "peg 2", "peg 3");
	}

	public static void move(int n, String start, String middle, String dest) {
		if (n == 1)
			System.out.println(start + " to " + dest);
		else {
			move(n - 1, start, dest, middle);
			System.out.println(start + " to " + dest);
			move(n - 1, middle, start, dest);
		}
	}

}
