package utils;

import java.util.HashMap;
import java.util.Map;

public class FileMap {
	
	private static Map<OptionFile, String> fileMap = new HashMap<OptionFile, String>();
	
	public enum OptionFile
	{
		CLASS,
		COURSES,
		TEACHERS;
	}
	
	public static void addFileAndPath(OptionFile FileName, String Path)
	{
		fileMap.put(FileName, Path);
		System.out.println(FileName + "    " + Path);
	}
	
	public static String getPathForOptionFile(OptionFile optionFile)
	{
		return fileMap.get(optionFile);
	}
	

}
