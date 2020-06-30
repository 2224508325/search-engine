package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FileDocSource extends DocSource{
	protected ArrayList<File> _files;
	public FileDocSource(String src) {
		_files = FileFinder.GetAllFiles(src);
	}
	@Override
	public int getNumDocs() {
		// TODO Auto-generated method stub
		return _files.size();
	}

	@Override
	public String getDoc(int id) {
		// TODO Auto-generated method stub
		String oneFile = "";
		try {
			BufferedReader cin = new BufferedReader(new FileReader(_files.get(id)));
			String line =cin.readLine();
			while(line != null) {
				oneFile +=  line + "";
				line =cin.readLine();
			}
			return oneFile;
		}
		catch(Exception e){
			System.out.println("No file exist");
		}
		return null;
	}
	
	public static void main(String args[]) {
		String source = "C:\\Users\\fyx56\\OneDrive\\Documents\\MIE258\\project5-2224508325\\files\\Part1\\awards_1994\\awd_1994_00";
		FileDocSource text = new FileDocSource(source);
		System.out.print(text.getDoc(1));
	}
	
}
