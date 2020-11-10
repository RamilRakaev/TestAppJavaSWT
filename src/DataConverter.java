import java.io.File;
import java.util.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;


public class DataConverter {

	public DataConverter() {
		fs=new FileSave();
		documents=new ArrayList<>(Arrays.asList(fs.Read().split(Document.END)));
		//System.out.println(documents.size()+" init");
		if(documents.contains("")) 
		{
			documents.remove("");
		}
		
	}

	//Properties
	private FileSave fs;
	private ArrayList<String>documents;
	private String editionFile="";
	
	public void SaveChanges(ApplicationPaymentDoc doc) 
	{
		fs.Save(doc.getValueAppPay());
		documents.add(doc.getValueAppPay());
		TreeItem item =new TreeItem(TestApp.tree,SWT.NONE);
		item.setText(doc.getShortValue());
	}
	
	public void SaveChanges(PaymentDoc doc) 
	{
		fs.Save(doc.getValuePay());
		if(documents!=null)
		documents.add(doc.getValuePay());
		TreeItem item =new TreeItem(TestApp.tree,SWT.NONE);
		item.setText(doc.getShortValue());
	}

	public void SaveChanges(InvoceDoc doc) 
	{
		fs.Save(doc.getValueInvoce());
		documents.add(doc.getValueInvoce());
		TreeItem item =new TreeItem(TestApp.tree,SWT.NONE);
		item.setText(doc.getShortValue());
	}
	
	public void ExtractData() 
	{
		
		for(String offer :documents) 
		{
			if(offer!="" && offer!=" ") 
			{
			String[]values=offer.split(Document.SEP);
				switch(values[0])
				{
				case "Платёжка":
					PaymentDoc PayDoc=new PaymentDoc(values);
					newTree(PayDoc.getShortValue());
					break;
				
				case "Заявка_на_оплату":
					ApplicationPaymentDoc appPayDoc=new ApplicationPaymentDoc(values);
					newTree(appPayDoc.getShortValue());
					break;
					
				case "Накладная":
					InvoceDoc invDoc=new InvoceDoc(values);
					newTree(invDoc.getShortValue());
					break;
				}
			}

		}
	}

	private void newTree(String title) 
	{
		TreeItem item =new TreeItem(TestApp.tree,SWT.NONE);
		item.setText(title);
	}
	
	public String OpenDocument(String number) 
	{
		for(String offer:documents) 
		{
		
			if(offer.compareTo("")==0 | offer.compareTo(" ")==0) 
			{System.out.println("!!!");
				documents.remove(offer);
				ReplaceData();
				return OpenDocument(number);
			}
			else 
			{
				if(offer.split(Document.SEP)[1].compareTo(number)==0) 
				{
				editionFile=offer;
					TestApp.currentDoc=offer.split(Document.SEP);
					switch(offer.substring(0, offer.indexOf(Document.SEP))) 
					{
					case "Платёжка":
						return "PayDoc";
							
					case "Заявка_на_оплату":
						return "appPayDoc";
							
					case "Накладная":
						return "invDoc";
							
					}
				}
			}
		}
			return "";
	}
	
	public void RemoveDocument(TreeItem selItem) 
	{
		String file= selItem.getText();
		String fileNumber=file.substring(file.lastIndexOf("номер ")+6);
		for(String offer:documents) 
		{
			if(offer.indexOf(fileNumber)!=-1) 
			{
				editionFile=offer;
			}
		}
		System.out.println(documents.size());
		if(documents.contains(editionFile)) 
		{
			documents.remove(editionFile);
		}
		System.out.println(documents.size());
		editionFile="";
		ReplaceData();
		TestApp.tree.removeAll();
		ExtractData();
	}
	
	private void ReplaceData() 
	{
		String newDocuments="";
		for(String offer:documents) 
		{
			newDocuments+=offer+Document.END;
		}
		fs.Replace(newDocuments);
	}
	
	public boolean CheckUniquenessNumber(String _number) 
	{
		for(String offer:documents) 
		{
			if(offer.compareTo("")!=0)
			if(offer.split(Document.SEP)[1].compareTo(_number)==0) 
			{
				return false;
			}
				
		}
		return true;
	}
	
	public void OpenFileDocuments(Shell shell) 
	{
		DialogDoc dialog=new DialogDoc(shell);
		String path=dialog.OpenDialogFile();
		File copyFile=new File(path);
		try {
		fs.copyFile(copyFile,fs.doc);
		}
		catch(Exception err) 
		{
			System.out.println("Error: DataConverter/OpenFileDocuments");
		}
		documents=new ArrayList<>(Arrays.asList(fs.Read().split(Document.END)));
		TestApp.tree.removeAll();
		ExtractData();
	}
	
	public void SaveAs(Shell shell) 
	{
		DialogDoc dialog=new DialogDoc(shell);
		String path=dialog.OpenDialogDirectory();
		File copyFile=new File(path);
		try {
		fs.copyFile(fs.doc,copyFile);
		}
		catch(Exception err) 
		{
			System.out.println("Error: DataConverter/OpenFileDocuments");
		}
		fs=new FileSave(path);
		TestApp.tree.removeAll();
		documents=new ArrayList<>(Arrays.asList(fs.Read().split(Document.END)));
		ExtractData();
	}
}
