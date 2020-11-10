import java.io.*;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileSave {

	
	public FileSave() {
		// TODO Auto-generated constructor stub
		doc=new File(file);
		if(!doc.exists()) 
		{
			try {
			boolean create=doc.createNewFile();
			}
			catch(IOException e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}
	public FileSave(String _file) {
		// TODO Auto-generated constructor stub
		file=_file;
		doc=new File(file);
		if(!doc.exists()) 
		{
			try {
			boolean create=doc.createNewFile();
			}
			catch(IOException e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}
	
	public File doc;
	private String file="documents.txt";
	
	public void Save(String _text) 
	{

		try(FileWriter wr=new FileWriter(file,true)) 
		{
			wr.write(_text);
			wr.flush();
		}
		catch(IOException e) 
		{
			System.out.println(e.getMessage());
		}
		
	}
	public String Read() 
	{
		String text="";
		try(FileReader rd=new FileReader(file))
		{
			int c;
			while((c=rd.read())!=-1) 
			{
				text+=(char)c;
			}
		}
		catch(IOException e) 
		{
			System.out.println(e.getMessage());
		}
		return text;
	}
	
	public void Replace(String _newRecord) 
	{
		try(FileWriter wr=new FileWriter(file,false)) 
		{
			wr.write(_newRecord);
			wr.flush();
		}
		catch(IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void copyFile(File sourceFile, File destFile) throws IOException {
	    if(!destFile.exists()) {
	        destFile.createNewFile();
	    }

	    FileChannel source = null;
	    FileChannel destination = null;

	    try {
	        source = new FileInputStream(sourceFile).getChannel();
	        destination = new FileOutputStream(destFile).getChannel();
	        destination.transferFrom(source, 0, source.size());
	    }
	    finally {
	        if(source != null) {
	            source.close();
	        }
	        if(destination != null) {
	            destination.close();
	        }
	    }
	}
}
