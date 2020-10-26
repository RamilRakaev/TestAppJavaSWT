
public class PaymentDoc extends Document{
	public PaymentDoc(String _number, String _user, double _sum, String _date,
			String _employee) {
		super(_number, _user, _sum, _date);
		employee=_employee;
	}

	public PaymentDoc(String[] _values) 
	{
		if(_values!=null)
			if(_values.length==6) 
			{
				number=_values[1];
				user=_values[2];
				sum=Double.parseDouble(_values[3]);
				date=new Document.DateDoc(_values[4]);
				employee=_values[5];
				
			}
			else 
			{
				EstablishValues();
			}
	}
	
	//Properties
	String employee;

	//Methods
	public String getValuePay() 
	{
		return "Платёжка"+sep+String.join(sep,getValue(),employee)+end;
	}
	
	public String getShortValue() 
	{
		return "Платёжка от "+date.getDate()+" номер "+number;
	}
	
	private void EstablishValues() 
	{
		number=" ";
		user=" ";
		sum=0;
		date=new Document.DateDoc(0,0,0);
		employee="";
	}
}
