import static org.junit.jupiter.api.Assertions.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

class DataConverterTest {

	DataConverter data;
	TestApp app;
	Shell shell;
	FileSave fileSave;
	ApplicationPaymentDoc applicationPaymentDoc;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
		data=new DataConverter();
		fileSave=new FileSave();
		app=new TestApp();
		shell = new Shell();
		app.tree=new Tree(shell, SWT.BORDER);
		applicationPaymentDoc=new ApplicationPaymentDoc
				("123nub","newUser",200,"10.10.2020","counterparty1","currency",200,100);
	}

	@AfterEach
	void tearDown() throws Exception {
		shell.close();
		
	}
	@Test
	void test_NO_NULL() 
	{
		assertNotNull(data.OpenDocument("123nub"));
	}
	
	@Test
	void SaveChangesTest() 
	{
		int initial=app.tree.getItems().length;
		data.SaveChanges(applicationPaymentDoc);
		assertEquals(initial+1, app.tree.getItems().length);
		assertEquals(data.OpenDocument("123nub"), "appPayDoc");
		data.RemoveDocument(app.tree.getItem(0));
		assertEquals(initial, app.tree.getItems().length);
	}

	@Test
	void CheckUniquenessNumberTest() {
		data.SaveChanges(applicationPaymentDoc);
		assertFalse(data.CheckUniquenessNumber("123nub"));
		data.RemoveDocument(app.tree.getItem(0));
	}
	
	@Test
	void OpenFileDocumentsTest() {
		data.OpenFileDocuments(shell);
		assertEquals(1, app.tree.getItems().length);
		data.RemoveDocument(app.tree.getItem(0));
	}
	
	@Test
	void SaveAsTest() 
	{
		data.SaveAs(shell);
		data.SaveChanges(applicationPaymentDoc);
		assertEquals(fileSave.Read(),"");
		data.RemoveDocument(app.tree.getItem(0));
	}

	@Test
	void ExtractDataTest() 
	{
		fileSave.Save("Заявка_на_оплату&123nub&newUser&200.0&10.10.2020&counterparty1&currency&200.0&100.0&&");
		data=new DataConverter();
		data.ExtractData();
		assertEquals(1, app.tree.getItems().length);
		fileSave.Replace("");
	}
}
