import java.util.Scanner;

public class TEA_Decryption {

	public static void main(String[] args) {
		
		final int deltaOne = 0x11111111;
		final int deltaTwo = 0x22222222;
		
		int[] L = {0x00000000,0x00000000,0};
		int[] R = {0x00000000,0x00000000,0};
		
		int[] K = {0,0,0,0};

		Scanner input = new Scanner(System.in);
		
		// Prompt and recieve hex input for all elements in array K
		for(int i = 0; i < 4; i++) {
			System.out.printf("Please input K[%s] in Hex String (without \"0x\"): ", i);
			K[i] = Integer.parseUnsignedInt(input.next(), 16);
		}// End for

		// Prompt and recieve hex input for L[2]
		System.out.print("Please input L[2] in Hex String (without \"0x\"): ");
		L[2] = Integer.parseUnsignedInt(input.next(), 16);
		
		// Prompt and recieve hex input for R[2]
		System.out.print("Please input R[2] in Hex String (without \"0x\"): ");
		R[2] = Integer.parseUnsignedInt(input.next(), 16);
		

		// Calculate R0
		R[0] = R[2] - (((L[2] << 4) + K[2]) ^ (L[2] + deltaTwo) ^ ((L[2] >>> 5) + K[3]));
		
		//Calculate L0
		L[0] = L[2] - (((R[0] << 4) + K[0]) ^ (R[0] + deltaOne) ^ ((R[0] >>> 5) + K[1]));
		
		// Display results for all elements of L and R
		System.out.println("Results:");
		
		for(int i = 2; i >= 0; i--) {
			System.out.printf("L[%s] = %s\t\tR[%s] = %s\n", 
					i, Integer.toHexString(L[i]), i, Integer.toHexString(R[i]));
		}// End for
	} // End main
}// End class