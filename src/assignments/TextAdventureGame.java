package assignments;

import java.util.Random;
import java.util.Scanner;
// 1 . main() method,       2 . generateForest() method, 
// 3 . displayForest() method  4 . movePlayer(), 
public class TextAdventureGame {
public static void main(String[] args) {
	int rows = 12;
	int cols = 12;
	char[][] forestArray = generateForest(rows, cols);
	displayForest(forestArray);
	Scanner scanner = new Scanner(System.in);
	while (true) {
		System.out.print("Enter direction (W,A,S,D to move, Q to quit): ");
		char direction = scanner.nextLine().toUpperCase().charAt(0);
		if (direction == 'Q') {
			System.out.print("***GAME OVER***** PLAY/TRY AGAIN  ");
			break;
		}
			movePlayer(forestArray, direction);
			displayForest(forestArray);
	}
	scanner.close();
}
	
public static char[][] generateForest(int rows, int cols) {
	char[][] forest = new char[rows][cols];
	Random random = new Random();
// Populate the forest with trees 'T' and open spaces '.'
	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < cols; j++) {
			forest[i][j] = random.nextDouble() < 0.8 ? '.' : 'T';
		}
	}
// Place the player 'P' at a random empty location
	//while (true) {
		int playerRow = random.nextInt(rows);
		int playerCol = random.nextInt(cols);
		if (forest[playerRow][playerCol] == '.') {
			forest[playerRow][playerCol] = 'P';
		//break;
	//	}
	}
	return forest;
}

public static void displayForest(char[][] forest) {
	for (char[] row : forest) {
		for (char cell : row) {
				System.out.print(cell + " ");
		}
		System.out.println();
	}
}
public static void movePlayer(char[][] forest, char direction) {
	int[] playerPos = findPlayer(forest);
	int playerRow = playerPos[0];
	int playerCol = playerPos[1];
	int newRow = playerRow;
	int newCol = playerCol;
	switch (direction) {
		case 'W':
			newRow = playerRow - 1;
			break;
		case 'S':
			newRow = playerRow + 1;
			break;
		case 'A':
			newCol = playerCol - 1;
			break;
		case 'D':
			newCol = playerCol + 1;
			break;
		default:
			System.out.println("****Invalid direction! Use W, A, S, D to move.");
		return;
	}
	if (isMoveValid(forest, newRow, newCol)) {
		forest[playerRow][playerCol] = '.';
		forest[newRow][newCol] = 'P';
	} 
	else {
		System.out.println("***Invalid move! You can't move outside the forest or \n **** into a tree.");
	}
}
private static int[] findPlayer(char[][] forest) {
	for (int i = 0; i < forest.length; i++) {
		for (int j = 0; j < forest[i].length; j++) {
			if (forest[i][j] == 'P') {
				return new int[]{i, j};
			}
		}
	} 
	return null;
}
private static boolean isMoveValid(char[][] forest, int newRow, int newCol) {
	return newRow >= 0 && newRow < forest.length && newCol >= 0 && newCol <
			forest[0].length && forest[newRow][newCol] == '.';
}
}