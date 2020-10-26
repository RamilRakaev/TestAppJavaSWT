
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.wb.swt.SWTResourceManager;



public class Payment extends Dialog  {

	static public boolean documentIsOpened=false;
	protected Object result;
	protected Shell shell;
	private Text sumText;
	private Text employeeText;
	private Text userText;
	private Text dateText;
	private Text numberText;
	private Label errorLabel;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */

	
	public Payment(Shell parent, int style) {
		super(parent, style); 
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				documentIsOpened=false;
				errorLabel.setText("");
			}
		});
		shell.setSize(451, 329);
		shell.setText("\u041F\u043B\u0430\u0442\u0451\u0436\u043A\u0430");
		
		Button okButton = new Button(shell, SWT.NONE);
		okButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try 
				{
					if(TestApp.converter.CheckUniquenessNumber(numberText.getText())) 
					{
						Ok();
					}
					else 
					{
						errorLabel.setText("Такой номер уже существует!");
					}
				}
				catch(Exception ex) 
				{
					errorLabel.setText("Неверный формат данных!");
				}
			}
		});
		okButton.setBounds(280, 215, 90, 30);
		okButton.setText("\u041F\u0440\u0438\u043D\u044F\u0442\u044C");
		
		Label numberLabel = new Label(shell, SWT.CENTER);
		numberLabel.setText("\u041D\u043E\u043C\u0435\u0440");
		numberLabel.setAlignment(SWT.LEFT);
		numberLabel.setBounds(10, 10, 140, 30);
		
		Label DateLabel = new Label(shell, SWT.CENTER);
		DateLabel.setText("\u0414\u0430\u0442\u0430");
		DateLabel.setAlignment(SWT.LEFT);
		DateLabel.setBounds(10, 49, 140, 30);
		
		Label userLabel = new Label(shell, SWT.CENTER);
		userLabel.setText("\u041F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044C");
		userLabel.setAlignment(SWT.LEFT);
		userLabel.setBounds(10, 85, 140, 27);
		
		Label employeeLabel = new Label(shell, SWT.CENTER);
		employeeLabel.setText("\u0421\u043E\u0442\u0440\u0443\u0434\u043D\u0438\u043A");
		employeeLabel.setAlignment(SWT.LEFT);
		employeeLabel.setBounds(10, 153, 140, 30);
		
		Label sumLabel = new Label(shell, SWT.CENTER);
		sumLabel.setText("\u0421\u0443\u043C\u043C\u0430");
		sumLabel.setAlignment(SWT.LEFT);
		sumLabel.setBounds(10, 118, 140, 33);
		
		sumText = new Text(shell, SWT.BORDER);
		sumText.setBounds(156, 118, 248, 30);
		
		employeeText = new Text(shell, SWT.BORDER);
		employeeText.setBounds(156, 153, 248, 30);
		
		userText = new Text(shell, SWT.BORDER);
		userText.setBounds(156, 82, 248, 30);
		
		dateText = new Text(shell, SWT.BORDER);
		dateText.setBounds(156, 46, 248, 30);
		
		numberText = new Text(shell, SWT.BORDER);
		numberText.setBounds(156, 10, 248, 30);
		
		errorLabel = new Label(shell, SWT.NONE);
		errorLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		errorLabel.setBounds(10, 220, 241, 20);
		EstablishTextValues();
	}
	private void EstablishTextValues() 
	{
		if(documentIsOpened)
		if(TestApp.currentDoc!=null) 
		{
			if(TestApp.currentDoc.length==6) 
			{
				numberText.setText(TestApp.currentDoc[1]);
				userText.setText(TestApp.currentDoc[2]);
				sumText.setText(TestApp.currentDoc[3]);
				dateText.setText(TestApp.currentDoc[4]);
				employeeText.setText(TestApp.currentDoc[5]);
			}
			TestApp.currentDoc=null;
			//documentIsOpened=false;
		}
	}
	private void Ok() 
	{
		PaymentDoc doc=new PaymentDoc(numberText.getText(),userText.getText(),
				Double.parseDouble(sumText.getText()),
				dateText.getText(),employeeText.getText());
		if(documentIsOpened) 
		{
			TestApp.converter.RemoveDocument(TestApp.tree.getSelection()[0]);
			documentIsOpened=false;
		}
		TestApp.converter.SaveChanges(doc);
		shell.setVisible(false);
	}
}
