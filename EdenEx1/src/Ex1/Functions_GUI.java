package Ex1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Functions_GUI implements functions{
	private List<function> functionList = new ArrayList<>();

	@Override
	public int size() {
		return functionList.size();
	}

	@Override
	public boolean isEmpty() {
		return functionList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return functionList.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		// TODO Auto-generated method stub
		return functionList.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return functionList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return functionList.toArray(a);
	}

	@Override
	public boolean add(function e) {
		// TODO Auto-generated method stub
		return functionList.add(e);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return functionList.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return functionList.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		// TODO Auto-generated method stub
		return functionList.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return functionList.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return functionList.retainAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		functionList.clear();
	}

	@Override
	public void initFromFile(String file) throws IOException {
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
					
			clear();
			
			ComplexFunction cf = new ComplexFunction();
			String line = reader.readLine();
			
			while(line != null) {
				function f = cf.initFromString(line);
				add(f);
				line = reader.readLine();
			}
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
		
	}

	@Override
	public void saveToFile(String file) throws IOException {
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(new FileWriter(file));
			
			for(function f : this) {
				writer.append(f.toString());
				writer.append(System.lineSeparator());
			}
			
		} finally {
			if(writer != null) {
				writer.close();
			}
		}	
	}
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		try {
			JSONObject jsonObject = (JSONObject)JSONValue.parse(new FileReader(json_file));
			int width = ((Long)jsonObject.get("Width")).intValue();
			int height = ((Long)jsonObject.get("Height")).intValue();
			int resolution = ((Long)jsonObject.get("Resolution")).intValue();
			
			JSONArray arrayX = (JSONArray)jsonObject.get("Range_X");
			Range rangeX = new Range(getDoubleFromJSONNumber(arrayX.get(0)), getDoubleFromJSONNumber(arrayX.get(1)));
			JSONArray arrayY = (JSONArray)jsonObject.get("Range_Y");
			Range rangeY = new Range(getDoubleFromJSONNumber(arrayY.get(0)), getDoubleFromJSONNumber(arrayY.get(1)));
			
			drawFunctions(width, height, rangeX, rangeY, resolution);
			
		} catch(IOException e) {
			int Width = 1000;
			int Height = 600;
			int Resolution = 200;
			Range x = new Range(-10, 10);
			Range y = new Range(-5, 15);
			drawFunctions(Width, Height, x, y, Resolution);
		}
		
	}
	
	private static double getDoubleFromJSONNumber(Object JSONNumber) {
		return Double.parseDouble(String.valueOf(JSONNumber));
	}
	
}