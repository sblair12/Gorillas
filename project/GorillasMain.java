package project;

public class GorillasMain {

	public static void main(String[] args) {
		Initializer i = new Initializer(args);
		int loop = 1;
		while (loop == 1) {
			//Player 1
			loop = i.startThrow(true);

			//Player 2
			if (loop == 1) {
				loop = i.startThrow(false);
			}
		}
		i.endScreen();
	}
}