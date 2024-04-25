package com.mygdx.imageeditor;

public class Util {
	public static int bytesToInt(byte[] bytes) {
		unsignBytes(bytes);
		int result = 0;
		for(int i = 0; i < bytes.length; i++) {
		result += (int) bytes[i] << (8 * i);
		}
		return result;
	}
	public static int[] unsignBytes(byte[] bytes) {
		int[] ints = new int[bytes.length];
		int dist;
		int correct;
		for(int i = 0; i < bytes.length; i++) {
			if (bytes[i] >= 0) {
				ints[i] = bytes[i];
			}
			if (bytes[i] < 0) {
				dist = -129 + bytes[i];
				correct = dist + 127;
				ints[i] = correct;
			}
		}
		return ints;
	}
	public static byte[] intToSignedBytes(int value) {
	    byte[] result = new byte[4];
	    result[0] = (byte)(value >> 24); 	// Isolate the first byte
	    result[1] = (byte)(value >> 16); 	// Isolate the second byte
	    result[2] = (byte)(value >> 8); 	// Isolate the third byte
	    result[3] = (byte)value; 	// Isolate the fourth byte
	    
	    
	    
	    
	    
	    
	    return result;
	}

	
	public static void testIntToSignedBytes() {
		byte[] testResults = intToSignedBytes(543152314);
		int[] expectedResults = {32, 95, -40, -70};
		for(int i = 0; i < testResults.length; i++) {
		if((int) testResults[i] != expectedResults[i])
		System.out.println("TEST FAILED! INDEX " + i + " IS "
		+ testResults[i] + " EXPECTED: " + expectedResults[i]);
		}
	}
	
}
