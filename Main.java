import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import neuralNetwork.NeuralNetwork;
import neuralNetwork.RandomWeightGenerator;
import processing.core.*;

public class Main extends PApplet
{
	NeuralNetwork nn;
	
	boolean mouseWasDown;
	ArrayList<Button> buttons;
	PGraphics canvas;
	PImage actualImage;
	String results;
		
	public void settings() { size(800,600); }

	public void createAndTrainNN()
	{
		nn = createNN();

		// learn from every image in the images folder
		String[] filenames = getImageFileNamesInDirectory(new File("images"));
		for(int i = 0; i < filenames.length; i++)
		{
			int digit = identifyImageDigit(filenames[i]);
			double[] inputs = extractPixelsFromImage(loadImage("images/" + filenames[i]));
			double[] outputs = setOutputsAccordingToDigit(digit);
			nn.train(inputs, outputs);
			if(i % 500 == 0) {
				System.out.println("PROGRESS: " + i + "/" + filenames.length);
			}
		}
	}
	
	private NeuralNetwork createNN()
	{
		int[] layerSizes = {400, 300, 10};
		NeuralNetwork nn = new NeuralNetwork(layerSizes, new RandomWeightGenerator());
		return nn;
	}
	
	private String[] getImageFileNamesInDirectory(File directory)
	{
		if(!directory.isDirectory() || !directory.exists())
		{
			throw new IllegalArgumentException("Invalid image directory.");
		}
		// Get all the names of files in the directory
		List<String> filenames = Arrays.asList(directory.list());
		// Ignore any files that are not images
		List<String> imageFiles = new ArrayList<String>(filenames.size());
		for(String file : filenames)
		{
			if(validImageFile(file))
			{
				imageFiles.add(file);
			}
		}
		return imageFiles.toArray(new String[imageFiles.size()]);
	}
	
	private boolean validImageFile(String filename)
	{
		return filename.matches("digit_\\d+_\\d.png");
	}

	private int identifyImageDigit(String filename)
	{
		String digitString = filename.split("_")[2].split("\\.")[0];
		return Integer.parseInt(digitString);
	}
	
	private double[] extractPixelsFromImage(PImage img)
	{
		img.updatePixels();
		double[] pixels = new double[img.pixels.length];

		// Set input as the brightness of each pixel
		for(int i = 0; i < img.pixels.length; i++)
		{
			pixels[i] = (brightness(img.pixels[i]) / 255.0);
		}
		return pixels;
	}
	
	private double[] setOutputsAccordingToDigit(int digit)
	{
		double[] outputs = new double[10];
		for(int i = 0; i < 10; i++)
		{
			outputs[i] = (digit == i) ? 1.0 : 0.0;
		}
		return outputs;
	}
	
	public void testNN(PImage image)
	{
		// when image is null, pick a random image from images folder
		if(image == null)
		{
			image = pickRandomImageFromDirectory(new File("images/"));
			actualImage = image;
		}
		
		// Extract pixels from image
		double[] inputs = extractPixelsFromImage(image);
		
		// get prediction from neural network
		double[] outputs = nn.networkOutputsFor(inputs);
		
		// convert prediction into results string
		convertOutputsToResult(outputs);
	}
	
	private PImage pickRandomImageFromDirectory(File directory)
	{
		String[] filenames = directory.list();
		String filename = "";
		while(!validImageFile(filename))
		{
			int randomIndex = (int) (Math.random() * filenames.length);
			filename = filenames[randomIndex];
		}
		PImage image = loadImage("images/" + filename);
		return image;
	}
	
	private void convertOutputsToResult(double[] outputs)
	{
		int prediction = 0;
		results = "RESULTS:\n";
		for(int i=1;i<outputs.length;i++)
		{
			results += i + ": " + outputs[i] + "\n";
			if(outputs[i] > outputs[prediction])
			{
				prediction = i;
			}
		}
		results += " \nPREDICTION: " + prediction;
	}
	
	public void setup()
	{				
		createAndTrainNN();

		// set processing draw modes
		textSize(24);
		textAlign(CENTER,CENTER);
		imageMode(CENTER);
		rectMode(CENTER);

		mouseWasDown = true;
		
		buttons = new ArrayList<Button>();
		buttons.add(new Button("CLEAR",600,64));
		buttons.add(new Button("CUSTOM",600,112));
		buttons.add(new Button("RANDOM",600,160));
		
		// canvas is what the user draws on
		canvas = createGraphics(400,400);		
		canvas.beginDraw();
		canvas.stroke(255);
		canvas.strokeWeight(24);
		canvas.background(0);
		canvas.endDraw();
		// actual image contains data set to test neural network
		actualImage = createImage(20,20,RGB);
		results = "";
	}
	
	public void draw()
	{
		boolean mouseClick = mousePressed && !mouseWasDown;
		mouseWasDown = mousePressed;
		
		// handle user input
		canvas.beginDraw();
		boolean mouseOverButton = false;
		if(mouseClick)
			for(Button b : buttons)
				if(b.mouseOverButton)
				{
					mouseOverButton = true;
					switch(b.label)
					{
					case "CLEAR":
						canvas.background(0);
						break;
					case "CUSTOM":					
						testNN(actualImage);
						break;
					case "RANDOM":
						testNN(null);
						break;					
					}
				}
		if(mousePressed && !mouseOverButton)
		{
			canvas.line(mouseX-100,mouseY-100,pmouseX-100,pmouseY-100);
			actualImage = canvas.copy();
			actualImage = cropAndResize(actualImage);
		}
		canvas.endDraw();
		
		// clear background
		background(127);
		// draw prompt
		textSize(24); fill(255); stroke(0);
		text("Draw a number here:",width/2-100,64);
		// draw buttons
		for(Button b : buttons) b.update();
		// draw canvas and actual image
		image(canvas,width/2-100,height/2);
		image(actualImage, 600, 210);
		fill(255);
		textSize(12);
		text(results,600,350);
	}
		
	public class Button {
		public String label;
		public int buttonX, buttonY, buttonW, buttonH;
		public boolean mouseOverButton;
		public Button(String label, int buttonX, int buttonY) 
		{
			this.label = label;
			this.buttonX = buttonX;
			this.buttonY = buttonY;
			buttonW = (int)textWidth(label)+16;
			buttonH = 32;
			mouseOverButton = false;
		}
		public void update()
		{
			mouseOverButton = Math.abs(mouseX-buttonX) < buttonW/2 && Math.abs(mouseY-buttonY) < buttonH/2;
			if(mouseOverButton) { fill(160); stroke(0); }
			else { fill(0); stroke(255); }			
			rect(buttonX,buttonY,buttonW,buttonH,8);
			if(mouseOverButton) { fill(0); stroke(255); }
			else { fill(255); stroke(0); }			
			text(label,buttonX,buttonY);		
		}
	}

	public static final int threshold = 64;
	public PImage cropAndResize(PImage image)
	{
		// copy image data in int[][] data
		image.resize(28, 28);
		image.loadPixels();
		int[][] data = new int[image.height][image.width];
		for(int j=0;j<image.height;j++)
			for(int i=0;i<image.width;i++)
				data[j][i] = image.pixels[j*data[0].length+i];		
		
		// find edges
		int top = 0;
		for(int j=0;j<data.length;j++)
			for(int i=0;i<data[0].length;i++)
				if(brightness(data[j][i]) > threshold)
				{
					top = j;
					i = data[0].length;
					j = data.length;						
				}
		int bottom = data.length-1;
		for(int j=data.length-1;j>=0;j--)
			for(int i=0;i<data[0].length;i++)
				if(brightness(data[j][i]) > threshold)
				{
					bottom = j;
					i = data[0].length;
					j = -1;						
				}
		int left = 0;
		for(int i=0;i<data[0].length;i++)
			for(int j=0;j<data.length;j++)
				if(brightness(data[j][i]) > threshold)
				{
					left = i;
					i = data[0].length;
					j = data.length;						
				}
		int right = data[0].length-1;
		for(int i=data[0].length-1;i>=0;i--)
			for(int j=0;j<data.length;j++)
				if(brightness(data[j][i]) > threshold)
				{
					right = i;
					i = -1;
					j = data.length;						
				}

		// preserve aspect ratio
		int size = Math.max(right-left,bottom-top);
		if(right-left < size)
		{
			left -= (size - right + left)/2;
			right = left + size;
		}
		if(bottom-top < size)
		{
			top -= (size - bottom + top)/2;
			bottom = top + size;
		}

	    // crop to new data
		int[][] newData = new int[size][size];
		for(int j=0;j<size;j++)
			for(int i=0;i<size;i++)
				if(j+top < 0 || j+top >= data.length || i+left < 0 || i+left >= data[0].length)
					newData[j][i] = color(0,0,0);
				else
					newData[j][i] = data[j+top][i+left];

		// return new 20x20 image containing newData
		image = createImage(newData.length,newData[0].length,RGB);
		image.loadPixels();
		for(int j=0;j<image.height;j++)
			for(int i=0;i<image.width;i++)
				image.pixels[j*newData[0].length+i] = newData[j][i];
		image.updatePixels();
		image.resize(20, 20);
		return image;
	}
	
	public static void main(String[] args)
	{
		PApplet.main("Main");
	}
}
