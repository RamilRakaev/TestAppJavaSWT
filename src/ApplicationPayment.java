import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class ApplicationPayment extends Dialog{

	public boolean documentIsOpened=false;
	protected Object result;
	protected Shell shell;
	private Text commissionText;
	private Text counterpartyText;
	private Text rateText;
	private Text currencyText;
	private Text sumText;
	private Text userText;
	private Text dateText;
	private Text numberText;
	private Label errorLabel;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	
			
	public ApplicationPayment(Shell parent, int style) {
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
		shell.setSize(434, 421);
		shell.setText("\u0417\u0430\u044F\u0432\u043A\u0430 \u043D\u0430 \u043E\u043F\u043B\u0430\u0442\u0443");
		
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
		
		Label sumLabel = new Label(shell, SWT.CENTER);
		sumLabel.setText("\u0421\u0443\u043C\u043C\u0430");
		sumLabel.setAlignment(SWT.LEFT);
		sumLabel.setBounds(10, 154, 140, 33);
		
		Label currencyLabel = new Label(shell, SWT.CENTER);
		currencyLabel.setText("\u0412\u0430\u043B\u044E\u0442\u0430");
		currencyLabel.setAlignment(SWT.LEFT);
		currencyLabel.setBounds(10, 193, 140, 27);
		
		Label rateLabel = new Label(shell, SWT.CENTER);
		rateLabel.setText("\u041A\u0443\u0440\u0441 \u0432\u0430\u043B\u044E\u0442\u044B");
		rateLabel.setAlignment(SWT.LEFT);
		rateLabel.setBounds(10, 229, 140, 27);
		
		Label counterpartyLabel = new Label(shell, SWT.CENTER);
		counterpartyLabel.setText("\u041A\u043E\u043D\u0442\u0440\u0430\u0433\u0435\u043D\u0442");
		counterpartyLabel.setAlignment(SWT.LEFT);
		counterpartyLabel.setBounds(10, 118, 140, 30);
		
		Label commissionLabel = new Label(shell, SWT.CENTER);
		commissionLabel.setText("\u041A\u043E\u043C\u0438\u0441\u0441\u0438\u044F");
		commissionLabel.setAlignment(SWT.LEFT);
		commissionLabel.setBounds(10, 265, 140, 30);
		
		commissionText = new Text(shell, SWT.BORDER);
		commissionText.setBounds(156, 262, 248, 30);
		
		counterpartyText = new Text(shell, SWT.BORDER);
		counterpartyText.setBounds(156, 118, 248, 30);
		
		rateText = new Text(shell, SWT.BORDER);
		rateText.setBounds(156, 226, 248, 30);
		
		currencyText = new Text(shell, SWT.BORDER);
		currencyText.setBounds(156, 190, 248, 30);
		
		sumText = new Text(shell, SWT.BORDER);
		sumText.setBounds(156, 154, 248, 30);
		
		userText = new Text(shell, SWT.BORDER);
		userText.setBounds(156, 82, 248, 30);
		
		dateText = new Text(shell, SWT.BORDER);
		dateText.setBounds(156, 46, 248, 30);
		
		numberText = new Text(shell, SWT.BORDER);
		numberText.setBounds(156, 10, 248, 30);
		
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
		okButton.setBounds(272, 316, 90, 30);
		okButton.setText("\u041F\u0440\u0438\u043D\u044F\u0442\u044C");
		
		errorLabel = new Label(shell, SWT.NONE);
		errorLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		errorLabel.setBounds(10, 321, 222, 20);
		EstablishTextValues();
	}
	private void EstablishTextValues() 
	{
		if(documentIsOpened)
		if(TestApp.currentDoc!=null) 
		{
			if(TestApp.currentDoc.length==9) 
			{
				numberText.setText(TestApp.currentDoc[1]);
				userText.setText(TestApp.currentDoc[2]);
				sumText.setText(TestApp.currentDoc[3]);
				dateText.setText(TestApp.currentDoc[4]);
				counterpartyText.setText(TestApp.currentDoc[5]);
				currencyText.setText(TestApp.currentDoc[6]);
				rateText.setText(TestApp.currentDoc[7]);
				commissionText.setText(TestApp.currentDoc[8]);
				
			}
			TestApp.currentDoc=null;
			//documentIsOpened=false;
		}
	}
	private void Ok() 
	{
		ApplicationPaymentDoc doc=new ApplicationPaymentDoc(numberText.getText(),userText.getText(),
				Double.parseDouble(sumText.getText()),dateText.getText(),
				 counterpartyText.getText(),currencyText.getText(),
				Double.parseDouble(rateText.getText()),
				Double.parseDouble(commissionText.getText()));
		
		//DataConverter converter=new DataConverter();
		if(documentIsOpened) 
		{
			TestApp.converter.RemoveDocument(TestApp.tree.getSelection()[0]);
			documentIsOpened=false;
		}
		TestApp.converter.SaveChanges(doc);
		shell.setVisible(false);
	}
}
