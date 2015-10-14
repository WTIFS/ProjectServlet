package filecounter.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileDao {
	public int getCount(){
		int count = 0;
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;
		try{
			File f = new File("FileCounter.txt");
			if (!f.exists()){
				f.createNewFile();
				printWriter = new PrintWriter(new FileWriter(f));
				printWriter.println(0);
				printWriter.close();
			}
			
			fileReader = new FileReader(f);
			bufferedReader = new BufferedReader(fileReader);
			String initial = bufferedReader.readLine();
			count = Integer.parseInt(initial);
		}
		catch (Exception exception){
			exception.printStackTrace();
			if (printWriter!=null) printWriter.close();
		}
		if (bufferedReader!=null){
			try{
				bufferedReader.close();
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public void save(int count) throws Exception{
		FileWriter fileWriter = new FileWriter("FileCounter.txt");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		printWriter.print(count);
		
		if (printWriter!=null) printWriter.close();
	}
}

//Ref
//http://www.vogella.com/tutorials/EclipseWTP/article.html#overview