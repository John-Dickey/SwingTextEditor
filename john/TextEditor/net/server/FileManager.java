package john.TextEditor.net.server;

import java.rmi.server.UID;
import java.util.ArrayList;

import john.TextEditor.net.packet.File;

public class FileManager {

	private static FileManager instance = new FileManager();
	
	private ArrayList<File> files = new ArrayList<File>();
	
	private FileManager() {
	}
	
	public void addFile(File f) {
		files.add(f);
	}
	
	public void removeFile(File f) {
		files.remove(f);
	}
	
	public ArrayList<File> getFiles() {
		return files;
	}
	
	public static FileManager getInstance() {
		return instance;
	}
	public File getFile(UID u)
	{
		for(File f : files)//TODO working on this
		{
			if(f.getUid().equals(u))
				return f;
			p(u.toString() + ":" + f.getUid().toString());
		}
		throw new IllegalArgumentException("Could not find");
	}
	public boolean exists(File fil)
	{
		for(File f : files)
		{
			if(f.equals(fil))
				return true;
		}
		return false;
	}
	private void p(String s)
	{
		System.out.println(s);
	}
}
