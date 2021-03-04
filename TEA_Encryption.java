import java.util.Scanner;

public class TEA_Encryption {
	
	/**
	 * Function to perform encryption using the following variables:
	 * @param X:  	Ri
	 * @param Km: 	Key value
	 * @param Kn: 	Key value
	 * @param delta: Delta 
	 * @return
	 */
	public static int F(int X, int Km, int Kn, int delta) {
		return ((X << 4) + Km) ^ ((X >>> 5) + Kn) ^ (X + delta);
	} // End F
	
	public static void main(String[] args) {
		
		final int deltaOne = 0x11111111;
		final int deltaTwo = 0x22222222;
		
		int[] K = {0,0,0,0};
		
		int[] L = {0,0x00000000,0x00000000};
		int[] R = {0,0x00000000,0x00000000};
		
		Scanner input = new Scanner(System.in);
	
	
		// Prompt and recieve hex input for all elements in array K
		for(int i = 0; i < 4; i++) {
			System.out.printf("Please input K[%s] in Hex String (without \"0x\"): ", i);
			K[i] = Integer.parseUnsignedInt(input.next(), 16);
		}// End for
		
		// Prompt and recieve hex input for L[0]
		System.out.print("Please input L[0] in Hex String (without \"0x\"): ");
		L[0] = Integer.parseUnsignedInt(input.next(), 16);
		
		// Prompt and recieve hex input for R[0]
		System.out.print("Please input R[0] in Hex String (without \"0x\"): ");
		R[0] = Integer.parseUnsignedInt(input.next(), 16);
		
		
		/*	Step 1: Find L1 and R1
		 *  L1 = R0, 
		 *  R1 = L0 + F(R0, K0, K1, D1)
		 */
		L[1] = R[0];
		R[1] = L[0] + F(R[0], K[0], K[1], deltaOne);
		
		/*
		 * Step 2: Find L2 and R2
		 * L2 = R1
		 * R2 = L1 + F(R1, K2, K3, D2) 
		 */
		L[2] = R[1];
		R[2] = L[1] + F(R[1], K[2], K[3], deltaTwo);
		
		
		// Display results for all elements of L and R
		System.out.println("Results:");
		
		for(int i = 2; i >= 0; i--) {
			System.out.printf("L[%s] = %s\t\tR[%s] = %s\n", 
					i, Integer.toHexString(L[i]), i, Integer.toHexString(R[i]));
		}// End for
	}// End main
}// End class