import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Bank{
	private File file;
	private String filename;
	private BufferedWriter writer;
	private Path path = Paths.get("./output.txt");

	public Bank(String filename){
		this.file = new File(filename);
	}
	public void addPersonAndValue(String id, int ammount) throws IOException{
		this.writer = new BufferedWriter(new FileWriter(this.file, true));
		String temp = id + " " + Integer.toString(ammount);
		writer.write(temp);
		writer.newLine();
		writer.close();
	}
	public int returnValueFromFile(String id) throws IOException{
		Scanner scanner = new Scanner(file);
		String pattern = String.format("(%s)\\s{1}(\\d+)", id);
		Pattern patternObject = Pattern.compile(pattern);
		while(scanner.hasNext()){
			Matcher m = patternObject.matcher(scanner.nextLine());
			if(m.find()){
				System.out.println(m.group(1));
				return(Integer.parseInt(m.group(2)));
			}
			else{
				continue;
			}
		}
		return 0;
	}
	public void updateValueForId(String id, int value) throws IOException{
		String pattern = String.format("(%s)\\s{1}(-*\\d+)", id);
		Pattern patternObject = Pattern.compile(pattern);
		ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
		for(int i = 0; i < fileContent.size(); i++){
			Matcher m = patternObject.matcher(fileContent.get(i));
			if(m.find()){
				//System.out.println("FOUND!");
				int newValue = Integer.parseInt(m.group(2)) + value;
				String newString = String.format("%s %d",m.group(1), newValue);
				fileContent.set(i, newString);
				break;
			}
		}
		Files.write(path, fileContent, StandardCharsets.UTF_8);
	}
}

