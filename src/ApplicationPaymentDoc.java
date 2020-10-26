
public class ApplicationPaymentDoc extends Document{
	
	public ApplicationPaymentDoc(String _number, String _user, double _sum, String _date,
			String _counterparty, String _currency,double _rate, double _commission) {
		super(_number, _user, _sum, _date);
		counterparty=_counterparty;
		currency=_currency;
		rate=_rate;
		commission=_commission;
	}
	public ApplicationPaymentDoc(String[] _values) 
	{
		if(_values!=null)
			if(_values.length==9) 
			{
				number=_values[1];
				user=_values[2];
				sum=Double.parseDouble(_values[3]);
				date=new Document.DateDoc(_values[4]);
				counterparty=_values[5];
				currency=_values[6];
				rate=Double.parseDouble(_values[7]);
				commission=Double.parseDouble(_values[8]);
				
			}
			else 
			{
				EstablishValues();
			}
	}
	
	//Properties
	String counterparty;
	String currency;
	double rate;
	double commission;
	
	//Methods
	private void EstablishValues() 
	{
		number=" ";
		user=" ";
		sum=0;
		date=new Document.DateDoc(0,0,0);
		counterparty="";
		currency=" ";
		rate=0;
		commission=0;
	}
	
	public String getValueAppPay() 
	{
		return "Заявка_на_оплату"+sep+String.join(sep,getValue(),counterparty, 
				currency,Double.toString(rate),Double.toString(commission))+end;
	}
	
	public String getShortValue() 
	{
		return "Заявка на оплату от "+date.getDate()+" номер "+number;
	}
}
