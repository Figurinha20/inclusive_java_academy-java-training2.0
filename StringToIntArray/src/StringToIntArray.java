 import java.util.Scanner;

public class StringToIntArray {
	public static void main(String[] args) {
		StringToIntArray stringToIntArray = new StringToIntArray();
		stringToIntArray.run();
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		String[] strArrayString = str.split(" ");
	    for (String s : strArrayString) {
	    	System.out.println(s);
	    }
	    scanner.close();
	}
}
