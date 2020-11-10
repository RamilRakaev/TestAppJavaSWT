
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Tree;


public class TestApp {

	/**
	 * Launch the application.
	 * @param args
	 */
	
	public static Tree tree;
	public static String[] currentDoc;
	public static DataConverter converter;
	
	public static void main(String[] args) {
	
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(568, 507);
		shell.setText("Документы");
		
		
		
		Invoce invoce=new Invoce(shell,shell.getStyle());
		Payment payment=new Payment(shell,shell.getStyle());
		ApplicationPayment applicationPayment=new ApplicationPayment(shell,shell.getStyle());
		
		 tree = new Tree(shell, SWT.BORDER);
		tree.setBounds(10, 10, 359, 420);
		
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				String sel= tree.getSelection()[0].getText();
				System.out.println(converter.OpenDocument(sel.substring(sel.lastIndexOf("номер ")+6)));
				switch(converter.OpenDocument(sel.substring(sel.lastIndexOf("номер ")+6))) 
				{
				case "PayDoc":
					payment.documentIsOpened=true;
					payment.open();
					break;
				case "appPayDoc":
					applicationPayment.documentIsOpened=true;
					applicationPayment.open();
					break;
					
				case "invDoc":
					invoce.documentIsOpened=true;
					invoce.open();
					break;
				}
			}
		});
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//System.out.println("!!!!!"+tree.getSelection()[0].getText());
			}
		});
		converter=new DataConverter();
		converter.ExtractData();
		
		Button invoceButton = new Button(shell, SWT.NONE);
		invoceButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				invoce.open();
			}
		});
		invoceButton.setBounds(392, 10, 131, 30);
		invoceButton.setText("\u041D\u0430\u043A\u043B\u0430\u0434\u043D\u0430\u044F");
		
		Button paymentButton = new Button(shell, SWT.NONE);
		paymentButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				payment.open();
			}
		});
		paymentButton.setBounds(392, 46, 131, 30);
		paymentButton.setText("\u041F\u043B\u0430\u0442\u0451\u0436\u043A\u0430");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
		
		
		
		
		
		
Button removeButton = new Button(shell, SWT.NONE);
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//String sel= tree.getSelection()[0].getText();
				converter.RemoveDocument(tree.getSelection()[0]);
			}
		});
		removeButton.setBounds(392, 194, 131, 30);
		removeButton.setText("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		
				
		
			
	Button openButton = new Button(shell, SWT.NONE);
	
	openButton.setBounds(392, 122, 131, 30);
	openButton.setText("\u041F\u0440\u043E\u0441\u043C\u043E\u0442\u0440");
	
		openButton.addSelectionListener(new SelectionAdapter() {
			@Override
		public void widgetSelected(SelectionEvent e) {
				String sel= tree.getSelection()[0].getText();
				//System.out.println(sel);
				//System.out.println(converter.OpenDocument(sel.substring(sel.lastIndexOf("номер ")+6)));
				
				switch(converter.OpenDocument(sel.substring(sel.lastIndexOf("номер ")+6)))
				{
				case "PayDoc":
					payment.documentIsOpened=true;
					payment.open();
					break;
				case "appPayDoc":
					applicationPayment.documentIsOpened=true;
					applicationPayment.open();
					break;
					
				case "invDoc":
					invoce.documentIsOpened=true;
					invoce.open();
					break;
				}
			}
		});
		
		
		Button downloadButton = new Button(shell, SWT.NONE);
		downloadButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				converter.OpenFileDocuments(shell);
			}
		});
		downloadButton.setBounds(392, 158, 131, 30);
		downloadButton.setText("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C");
		
			
		
		
		
		
		
		
		
		
		
		Button applicationPaymentButton = new Button(shell, SWT.NONE);
		applicationPaymentButton.setBounds(392, 82, 131, 30);
		applicationPaymentButton.setText("\u0417\u0430\u044F\u0432\u043A\u0430 \u043D\u0430 \u043E\u043F\u043B\u0430\u0442\u0443");
		
		Button saveButton = new Button(shell, SWT.NONE);
		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				converter.SaveAs(shell);
			}
		});
		saveButton.setBounds(392, 231, 131, 30);
		saveButton.setText("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C \u043A\u0430\u043A");
		applicationPaymentButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				applicationPayment.open();
			}
		});
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	}
	public void Open() 
	{
		
	}
}
