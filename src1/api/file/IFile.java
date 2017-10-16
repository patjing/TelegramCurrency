package api.file;

public interface IFile {
	public String readFile();
	public void writeFile(String message);
	public void changeFile(String message) throws Exception;

}
