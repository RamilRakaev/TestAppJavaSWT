import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;

import java.io.*;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class Invoce extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	
	  	public boolean documentIsOpened=false;
		private Text numberText;
		private Button okButton;
		private Text dateText;
		private Text userText;
		private Text sumText;
		private Text currencyText;
		private Text rateText;
		private Text productText;
		private Text quantityText;
		private Label errorLabel;

		
		
	public Invoce(Shell parent, int style) {
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
		shell.setSize(437, 413);
		shell.setText("\u041D\u0430\u043A\u043B\u0430\u0434\u043D\u0430\u044F");
		
		numberText = new Text(shell, SWT.BORDER);
		numberText.setBounds(156, 13, 248, 30);
		
		okButton = new Button(shell, SWT.NONE);
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
		okButton.setBounds(267, 315, 90, 30);
		okButton.setText("\u041F\u0440\u0438\u043D\u044F\u0442\u044C");
		
		Label numberLabel = new Label(shell, SWT.CENTER);
		numberLabel.setAlignment(SWT.LEFT);
		numberLabel.setBounds(10, 13, 140, 30);
		numberLabel.setText("\u041D\u043E\u043C\u0435\u0440");
		
		Label dateLabel = new Label(shell, SWT.CENTER);
		dateLabel.setAlignment(SWT.LEFT);
		dateLabel.setBounds(10, 52, 140, 30);
		dateLabel.setText("\u0414\u0430\u0442\u0430");
		
		Label userLabel = new Label(shell, SWT.CENTER);
		userLabel.setAlignment(SWT.LEFT);
		userLabel.setText("\u041F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044C");
		userLabel.setBounds(10, 88, 140, 27);
		
		Label sumLabel = new Label(shell, SWT.CENTER);
		sumLabel.setAlignment(SWT.LEFT);
		sumLabel.setText("\u0421\u0443\u043C\u043C\u0430");
		sumLabel.setBounds(10, 121, 140, 33);
		
		Label currencyLabel = new Label(shell, SWT.CENTER);
		currencyLabel.setAlignment(SWT.LEFT);
		currencyLabel.setText("\u0412\u0430\u043B\u044E\u0442\u0430");
		currencyLabel.setBounds(10, 160, 140, 27);
		
		Label rateLabel = new Label(shell, SWT.CENTER);
		rateLabel.setAlignment(SWT.LEFT);
		rateLabel.setText("\u041A\u0443\u0440\u0441 \u0432\u0430\u043B\u044E\u0442\u044B");
		rateLabel.setBounds(10, 196, 140, 27);
		
		Label productLabel = new Label(shell, SWT.CENTER);
		productLabel.setAlignment(SWT.LEFT);
		productLabel.setText("\u0422\u043E\u0432\u0430\u0440");
		productLabel.setBounds(10, 229, 140, 30);
		
		Label quantityLabel = new Label(shell, SWT.CENTER);
		quantityLabel.setAlignment(SWT.LEFT);
		quantityLabel.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E");
		quantityLabel.setBounds(10, 265, 140, 30);
		
		dateText = new Text(shell, SWT.BORDER);
		dateText.setBounds(156, 49, 248, 30);
		
		userText = new Text(shell, SWT.BORDER);
		userText.setBounds(156, 85, 248, 30);
		
		sumText = new Text(shell, SWT.BORDER);
		sumText.setBounds(156, 121, 248, 30);
		
		currencyText = new Text(shell, SWT.BORDER);
		currencyText.setBounds(156, 157, 248, 30);
		
		rateText = new Text(shell, SWT.BORDER);
		rateText.setBounds(156, 193, 248, 30);
		
		productText = new Text(shell, SWT.BORDER);
		productText.setBounds(156, 229, 248, 30);
		
		quantityText = new Text(shell, SWT.BORDER);
		quantityText.setBounds(156, 265, 248, 30);
		
		errorLabel = new Label(shell, SWT.NONE);
		errorLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		errorLabel.setBounds(10, 320, 225, 20);
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
				currencyText.setText(TestApp.currentDoc[5]);
				rateText.setText(TestApp.currentDoc[6]);
				productText.setText(TestApp.currentDoc[7]);
				quantityText.setText(TestApp.currentDoc[8]);
			}
			TestApp.currentDoc=null;
			//documentIsOpened=false;
		}
	}
	
	private void Ok() 
	{
		InvoceDoc doc=new InvoceDoc(numberText.getText(),userText.getText(),
			Double.parseDouble(sumText.getText()),dateText.getText(),
			currencyText.getText(),Double.parseDouble(rateText.getText()),
			productText.getText(),Double.parseDouble(quantityText.getText()));
		
		if(documentIsOpened) 
		{
			TestApp.converter.RemoveDocument(TestApp.tree.getSelection()[0]);
			documentIsOpened=false;
		}
		TestApp.converter.SaveChanges(doc);
		
		shell.setVisible(false);
	}
}
