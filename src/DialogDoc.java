import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;



public class DialogDoc {

	public DialogDoc(Shell shell) {
		dialogFile=new FileDialog(shell,SWT.OPEN);
		setFilters();
		dialogDirectory=new DirectoryDialog(shell,SWT.OPEN);
	}

	private FileDialog dialogFile;
	private DirectoryDialog dialogDirectory;
	private final String[][]filters= 
		{
			{"Текстовые файлы (*.txt)","*.txt"}
		};
	private void setFilters() 
	{
		String[]names=new String[filters.length];
		String[]exts=new String[filters.length];
		
		for(int i=0;i<filters.length;i++) {
			names[i]=filters[i][0];
			exts[i]=filters[i][1];
		}
		dialogFile.setFilterNames(names);
		dialogFile.setFilterExtensions(exts);
	}
	
	public String OpenDialogFile() 
	{
		return dialogFile.open();
	}
	
	public String OpenDialogDirectory() 
	{
		return dialogDirectory.open()+"\\documents.txt";
	}
}
