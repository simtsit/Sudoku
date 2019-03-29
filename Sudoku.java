import java.util.Scanner;

public class Sudoku{

	int[][] sudokuBoard = new int[9][9];

	public Sudoku(){
		System.out.println("\nGive \"x y n\" or \"0 0 0\"  to quit");
		System.out.println("x=column, y=row, n=number\n");

	}

	public void drawBoard(){
		for (int x=0; x<9; x++){
			for (int y=0; y<9; y++){
				
				// Tables of zeros are difficult for the eye.
				// Let's show a dot instead.
				if (sudokuBoard[x][y]==0){
					System.out.print(" . ");
				}else {
					System.out.print(" " + sudokuBoard[x][y] + " ");
				}

				if ((y+1) % 3 == 0)
				{
					System.out.print("  ");
				}
			}
			System.out.println();

			if((x+1) % 3 == 0) {
				System.out.println();
			}
		}
		System.out.println();
	}
		

	public boolean checkBlock(int givenX, int givenY, int givenNumber){

		int xMin, xMax;
		int yMin, yMax;

		// First we have the coordinates of this block...
		if(givenX<4) {
			xMin=0;
			xMax=2;
		}else if ((givenX>3) && (givenX<7)) {
			xMin=3;
			xMax=5;
		}else {
			xMin=6;
			xMax=8;
		}

		if(givenY<4) {
			yMin=0;
			yMax=2;
		}else if ((givenY>3) && (givenY<7)) {
			yMin=3;
			yMax=5;
		}else {
			yMin=6;
			yMax=8;
		}

		// And then do a check if the number already exists.
		for(int countY=yMin; countY<yMax; countY++){
			for(int countX=xMin; countX<xMax; countX++){
				if ((sudokuBoard[countX][countY] == givenNumber)&& (givenNumber!=0)){
					System.out.println("Number " + givenNumber + " is already in this block");
					return true;
				}
			}
		}
		return false;
	}

	

	public boolean checkColumn(int givenX, int givenY, int givenNumber){
		for(int countX=0; countX<9; countX++){
			if (( sudokuBoard[givenY-1][countX] == givenNumber)&& (givenNumber!=0)){
				System.out.println("Number " + givenNumber + " is already in line "+ givenY);
				return true;
			}
		}
		return false;
	}



	public boolean checkRow(int givenX, int givenY, int givenNumber){
		for(int countY=0; countY<9; countY++){
			if (( sudokuBoard[countY][givenX-1] == givenNumber) && (givenNumber!=0) ){
				System.out.println("Number " + givenNumber + " is already in column "+ givenX);
				return true;
			}
		}
		return false;
	}	


	public void addNumber(int givenX, int givenY, int givenNumber){
		sudokuBoard[givenX-1][givenY-1] = givenNumber;
	}



	public static void main(String[] args) {

		// we have to do always 3 basic checks
		// if the number already exists in row, column and the block
  		boolean isInYAxis = false;
  		boolean isInXAxis = false;
		boolean isInBlock = false; 

		System.out.println("\nWelcome to Sudoku!");		
		
		Sudoku sudoku = new Sudoku();
		sudoku.drawBoard();

		Scanner keyboard = new Scanner(System.in);
		int row = -1;
		int column = -1;
		int number = -1;
		int zeroCount = -1;

		while (!((row==0)&&(column==0)&&(number==0)) && !(zeroCount==0) ) { 

			column = keyboard.nextInt(); 	
			row = keyboard.nextInt();		
			number = keyboard.nextInt();

			if ((row==0)&&(column==0)&&(number==0)) {
				System.out.println("Thank you! Bye!");
				return;
			}

			System.out.println("Checking... " + column + " " + row + " " + number+ "\n");


			// if the number is already there...
			if((sudoku.sudokuBoard[column-1][row-1] != number) || ((number==0))){

				isInYAxis = sudoku.checkColumn(row, column, number);
				isInXAxis = sudoku.checkRow(row, column, number);
				isInBlock = sudoku.checkBlock(column, row, number);
		        
		        if ((isInYAxis == false)&&(isInXAxis == false)&&(isInBlock == false)){
		        	sudoku.addNumber(column, row, number);
		        } 

	        	sudoku.drawBoard();

				// Check if Sudoku is complete - are there any blocks with zero value?
				zeroCount = 0;
				for(int line=0; line<9; line++){
					for(int col=0; col<9; col++){
						
						if (sudoku.sudokuBoard[col][line] == 0) {
							zeroCount++;
						}

					}
				}
				if (zeroCount==0) {
					System.out.println("Congratulations! You won!");
				}
			}
		}
	}
}
