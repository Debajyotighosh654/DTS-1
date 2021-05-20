import java.util.Scanner;

public class Practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name = "Sabita";
		char[] no = {'a', 'd', 'v'} ;
		int[] in = {1,3,4,5,6};
		int i = name.length();
		System.out.println(name);
		System.out.println(i);
		System.out.println(no.length);
		System.out.println(in.length);
		
		System.out.println(name.isEmpty());
		
		char dog = name.charAt(5);
		System.out.println(dog);
		System.out.println(name.indexOf('a'));
		System.out.println(name.lastIndexOf('a'));
		
		System.out.println("Rohit" + 50 + 10);
		System.out.println(50 + 10 + "Rohit");
		System.out.println( 50 + 'c' + "rohit");
		System.out.println("rohit" + 'c' + 50);
		System.out.println('c' + "rohit" + 50);
		System.out.println('c' + "r" +50);
		System.out.println(true + "sabita");
		
		
		String na1 = "billi";
		String naya = name.concat("hsshs");
		System.out.println(naya);
		
		
		Scanner input = new Scanner (System.in);
//		String name1 =  input.next();
//		System.out.println(name1);
//		String name2 = input.nextLine();
//		System.out.println(name2);
//		int name3 = input.nextInt();
//		System.out.println(name3);
		
	}

}
