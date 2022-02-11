import java.util.Random;
import java.util.Scanner;

public class battleShips {
	public static void main(String[] args) {
		System.out.println("**** Welcome to Battle Ships game ****");
		System.out.println("Right now, the sea is empty.");

		oceanMap(map());
		int[][] map = placeShip(map());
		oceanMap(map);

		map = computer(map);
		gameOn(map);

	}

	public static void oceanMap(int[][] map) {

		String str = "   ";
		for (int c = 0; c < map.length; c++) {
			str = str + c;
		}
		System.out.println(str);

		for (int i = 0; i < map.length; i++) {

			System.out.print(i + " |");
			for (int j = 0; j < map[i].length; j++) {

				if (map[i][j] == 3) {
					System.out.print("@");
				} else if (map[i][j] == 0 || map[i][j] == 4) {
					System.out.print(" ");
				} else if (map[i][j] == 6) {

					System.out.print("-");
				} else if (map[i][j] == 1) {
					System.out.print("!");
				} else if (map[i][j] == 2) {
					System.out.print("x");
				} else {
					System.out.print(map[i][j]);
				}
			}
			System.out.println("| " + i);
		}

		System.out.println(str);
		System.out.println();

	}

	public static int[][] placeShip(int[][] coordinates) {
		int x, y;
		int i = 0;
		Scanner in = new Scanner(System.in);

		for (; i < 5; i++) {
			System.out.print("Enter 'x' cordinate for your " + (i + 1) + " ship: ");
			x = in.nextInt();
			System.out.print("Enter 'y' cordinate for your " + (i + 1) + " ship: ");
			y = in.nextInt();
			if (x < 0 || x > 10 || y < 0 || y > 10) {
				System.out.println("Wrong Position, try again.");
				i--;
			} else if (coordinates[x][y] == 3) {
				System.out.println("Wrong Position, try again.");
				i--;
			} else {
				coordinates[x][y] = 3;
			}
		}
		System.out.println();

		return coordinates;
	}

	public static int[][] map() {

		int[][] Omap = new int[10][10];

		for (int i = 0; i < Omap.length; i++) {
			for (int j = 0; j < Omap.length; j++) {
				Omap[i][j] = 0;
			}
		}
		return Omap;

	}

	public static int[][] computer(int[][] coordinates) {
		int x, y;
		int i = 0;
		Random rand = new Random();
		System.out.println("    Deploying");
		for (; i < 5; i++) {
			x = rand.nextInt(10);
			y = rand.nextInt(10);
			if (coordinates[x][y] == 4) {
				i--;
			} else if (coordinates[x][y] == 3) {
				i--;
			} else {
				coordinates[x][y] = 4;
				System.out.println(i + 1 + ".ship deployed");
			}
		}
		System.out.println();
		return coordinates;
	}

	private static void gameOn(int[][] map) {

		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		int k = 5;
		int l = 5;
		while (k > 0 && l > 0) {
			System.out.println("YOUR TURN");

			for (int i = 0; i < 1; i++) {
				System.out.print("Gues 'x' coordinates: ");
				int x = in.nextInt();
				System.out.print("Gues 'y' coordinates: ");
				int y = in.nextInt();
				if (x < 0 || x > 10 || y < 0 || y > 10) {
					System.out.println("Try numbers from 0 to 9.");
					i--;
				} else if (map[x][y] == 3) {
					System.out.println("You sunk your own ship.");
					map[x][y] = 2;
					k--;
				} else if (map[x][y] == 4) {
					System.out.println("Good job, you sink a ship.");
					map[x][y] = 1;
					l--;
				} else if (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 6) {
					System.out.println("coordinates already in use!");
					i--;
				} else {
					map[x][y] = 6;
					System.out.println("You missed.");
				}
			}

			System.out.println();
			System.out.println("COMPUTER'S TURN");
			for (int i = 0; i < 1; i++) {
				int x = rand.nextInt(10);
				int y = rand.nextInt(10);
				if (map[x][y] == 3) {
					System.out.println("Computer sunk your ship.");
					map[x][y] = 2;
					k--;
				} else if (map[x][y] == 4) {
					System.out.println("Computer sunk its own ship.");
					map[x][y] = 1;
					l--;
				} else if (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 6) {
					i--;
				} else {
					map[x][y] = 6;
					System.out.println("Computer missed.");

				}
			}
			System.out.println();
			oceanMap(map);
			System.out.println("Your ships: " + k + " | Computer ships: " + l);
			System.out.println("--------------------------------------");

		}
		if (l == 0) {
			System.out.print("You won");
		} else if (k == 0) {
			System.out.print("Computer won");
		}
	}
}