package com.mygdx.imageeditor;
import java.io.FileOutputStream;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;

public class ImageInputOutput {
	private byte[] _fileHeader;
	private Pixmap _pixels;
	public static ImageInputOutput Instance;
	public ImageInputOutput() {
	Instance = this;
	
	}
	public Pixmap loadImage(String filePath) {
		
		
		System.out.println("I'm going to load " + filePath);
		byte[] fileBytes = Gdx.files.internal(filePath).readBytes();
		int[] fileIntData = Util.unsignBytes(fileBytes);
		if(fileBytes[0] != 'B' || fileBytes[1] != 'M') {
			System.out.println(filePath + " is NOT a bitmap image");
		}
		byte[] fileSize = {fileBytes[2], fileBytes[3], fileBytes[4], fileBytes[5]};
		byte[] start = {fileBytes[10], fileBytes[11], fileBytes[12], fileBytes[13]};
		byte[] widthBytes = {fileBytes[18], fileBytes[19], fileBytes[20], fileBytes[21]};
		byte[] heightBytes = {fileBytes[22], fileBytes[23], fileBytes[24], fileBytes[25]};
		byte[] bitsPerPixel = {fileBytes[28], fileBytes[29]};
		int startPoint = Util.bytesToInt(start);
		int width = Util.bytesToInt(widthBytes);
		int height = Util.bytesToInt(heightBytes);
		int bytesPerPixel = Util.bytesToInt(bitsPerPixel) / 8;
		if(bytesPerPixel != 3) {System.out.println (
			"Unsupported image pixel format. Incorrect bits per pixel");}
		
		_fileHeader = new byte[startPoint];
		for (int i = 0; i < startPoint && i < fileBytes.length; i++) {
            _fileHeader[i] = fileBytes[i];}
		
		Pixmap pixels = new Pixmap(width, height, Format.RGBA8888);
		int b,g,r;
		int x = 0;
		int y = height;
		
		_pixels = pixels;
		
		for(int i = startPoint; i < fileIntData.length - 3; i += 3) {
			b = fileIntData[i];
			g = fileIntData[i+1];
			r = fileIntData[i+2];
			
			if(x >= width) {
				x = 0;
				y -= 1;
			}
			
			pixels.setColor(r/256f, g/256f, b/256f, 1);
			
			pixels.drawPixel(x, y);
			x++;
				
		}
		
		
		
		return pixels;
		
	}
	public void saveImage(String filePath) throws IOException {
		FileOutputStream output = new FileOutputStream(filePath);
		byte[] color;
		byte[] colorData = new byte[_pixels.getWidth() * _pixels.getHeight() * 3];
		int colorIndex = 0;
		for(int y = _pixels.getHeight() - 1; y >= 0; y--) {
			for(int x = 0; x < _pixels.getWidth(); x++) {
				color = Util.intToSignedBytes(_pixels.getPixel(x, y));
				colorData[colorIndex] = color[2];
				colorData[colorIndex + 1] = color[1];
				colorData[colorIndex + 2] = color[0];
				colorIndex += 3;
				//int tempColor = _pixels.getPixel(x, y);
		}	
			
		}
		output.write(_fileHeader);
		output.write(colorData);
		output.close();
		
		
	}
	
}
