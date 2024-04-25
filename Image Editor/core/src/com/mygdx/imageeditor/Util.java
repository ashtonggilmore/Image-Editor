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
	
}
