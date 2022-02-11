import java.util.Random;
import java.util.Scanner;

public class battleShips {
	public static void main(String[] args) {
		
		System.out.println("**** Welcome to Battle Ships game ****");
		System.out.println("Right now, the sea is empty.");
		
		// prints the empty sea map		
		oceanMap(map());
		
		// stores the coordinates
		int[][] map = userPlaceShip(map());
		
		// prints filled map with users coordinates
		oceanMap(map);

		// prints filled map with computers coordinates
		map = computerPlaceShip(map);
		
		// starts the game
		gameOn(map);

	}
	// set the sea map
	public static void oceanMap(int[][] map) {
		
		String str = "   ";
		// draw map with empty spaces
		for (int i = 0; i < map.length; i++) {
			str = str + i;
		}
		System.out.println(str);

		
		for (int i = 0; i < map.length; i++) {
			
			// left borders
			System.out.print(i + " |");
			
			// set symbols
			for (int j = 0; j < map[i].length; j++) {
				
				// representing user's ships with '@'
				if (map[i][j] == 3) {
					System.out.print("@");
				
				// representing computer's ships with empty space (hidden)
				} else if (map[i][j] == 0 || map[i][j] == 4) {
					System.out.print(" ");
				
				// representing missed tries with '-'
				} else if (map[i][j] == 6) {
					System.out.print("-");

				// representing the user's sunk ships
				} else if (map[i][j] == 1) {
					System.out.print("!");
				
				// representing computer's sunk ships
				} else if (map[i][j] == 2) {
					System.out.print("x");
				
				// error msg
				} else {
					System.out.print("error" + map[i][j]);
				}
			}
			// right borders
			System.out.println("| " + i);
		}
		
		System.out.println(str);
		System.out.println();

	}

	// 	returns an array of placed ships
	public static int[][] userPlaceShip(int[][] coordinates) {
		
		int x, y;
		int i = 0;
		
		Scanner in = new Scanner(System.in);
		
		// Asking the user to insert coordinates 
		for (; i < 5; i++) {
			System.out.print("Enter 'x' coordinate for your " + (i + 1) + " ship: ");
			x = in.nextInt();
			System.out.print("Enter 'y' coordinate for your " + (i + 1) + " ship: ");
			y = in.nextInt();
			
			// check if coordinates aren't bigger than the map
			if (x < 0 || x > 10 || y < 0 || y > 10) {
				System.out.println("Wrong Position, try again.");
				i--;
			
			// check if position is taken from another ship you placed
			} else if (coordinates[x][y] == 3) {
				System.out.println("Wrong Position, try again.");
				i--;
				
			// by representing the placed ships with the number 3
			} else {
				coordinates[x][y] = 3;
			}
		}
		System.out.println();
		
		return coordinates;
	}

	// we fill all the positions of the map with 0
	public static int[][] map() {

		int[][] Omap = new int[10][10];

		for (int i = 0; i < Omap.length; i++) {
			for (int j = 0; j < Omap.length; j++) {
				Omap[i][j] = 0;
			}
		}
		return Omap;
	}

	// Computer's ships coordinates that takes the same array of coordinates
	public static int[][] computerPlaceShip(int[][] coordinates) {
		int x, y;
		int i = 0;
		Random rand = new Random();
		System.out.println("    Deploying");
		
		for (; i < 5; i++) {
			x = rand.nextInt(10);
			y = rand.nextInt(10);
			
			
			// checking if the position of the map is taken by another ship
			// by representing every placed ship with the number 4
			if (coordinates[x][y] == 4) {
				i--;
				
			// checking if position is taken by the user's ships
			} else if (coordinates[x][y] == 3) {
				i--;
				
			// giving the value 4 to all corrected ships placed
			} else {
				coordinates[x][y] = 4;
				System.out.println(i + 1 + ".ship deployed");
			}
		}
		System.out.println();
		return coordinates;
	}
	
	// start game
	private static void gameOn(int[][] map) {

		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		
		// SCORE
		int userShipsLeft = 5;
		int computerShipsLeft = 5;
		
		
		while (userShipsLeft > 0 && computerShipsLeft > 0) {
			System.out.println("YOUR TURN");
		
			// User's guess coordinates 
			for (int i = 0; i < 1; i++) {
				System.out.print("Gues 'x' coordinates: ");
				int x = in.nextInt();
				System.out.print("Gues 'y' coordinates: ");
				int y = in.nextInt();
				
				// checks if the inserted values are bigger than the map
				if (x < 0 || x > 10 || y < 0 || y > 10) {
					System.out.println("Try numbers from 0 to 9.");
					i--;
				
				// check if computer sunk your ship
				} else if (map[x][y] == 3) {
					System.out.println("You sunk your own ship.");
					map[x][y] = 2;
					userShipsLeft--;
					
				// check if computer sunk its own ship
				} else if (map[x][y] == 4) {
					System.out.println("Good job, you sink a ship.");
					map[x][y] = 1;
					computerShipsLeft--;
					
				// if the coordinates are already passed
				} else if (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 6) {
					System.out.println("coordinates already tried!");
					i--;
					
				// print '-' if missed
				} else {
					map[x][y] = 6;
					System.out.println("You missed.");
				}
			}

			System.out.println();
			System.out.println("COMPUTER'S TURN");
			
		
			// Computers random guess
			for (int i = 0; i < 1; i++) {
				
				// longitude and latitude
				int x = rand.nextInt(10);
				int y = rand.nextInt(10);
				
				// check if computer sunk your ship
				if (map[x][y] == 3) {
					System.out.println("Computer sunk your ship.");
					map[x][y] = 2;
					userShipsLeft--;
				// check if computer sunk its own ship
				} else if (map[x][y] == 4) {
					System.out.println("Computer sunk its own ship.");
					map[x][y] = 1;
					computerShipsLeft--;
				// if the coordinates are already passed
				} else if (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 6) {
					i--;
				// print '-' if missed
				} else {
					map[x][y] = 6;
					System.out.println("Computer missed.");

				}
			}
			
			// Print SCORE
			System.out.println();
			oceanMap(map);
			System.out.println("Your ships: " + userShipsLeft + " | Computer ships: " + computerShipsLeft);
			System.out.println("--------------------------------------");

		}
		// winning message
		if (computerShipsLeft == 0) {
			System.out.print("You won");
		} else if (userShipsLeft == 0) {
			System.out.print("Computer won");
		}
	}
}