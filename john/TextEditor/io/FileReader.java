package john.TextEditor.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileReader 
{
	public static String constructString(File f)
	{
		BufferedReader reader;
		try 
		{
			reader = Files.newBufferedReader(f.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		try 
		{
			while(reader.ready())
			{
				stringBuilder.append(reader.readLine() + '\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return stringBuilder.toString();
	}
}
