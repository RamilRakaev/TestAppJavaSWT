
public class InvoceDoc extends Document {

	public InvoceDoc(String _number, String _user, double _sum, String _date, 
			String _currency,double _rate, String _product, double _quantity) {
		super(_number, _user, _sum, _date);
		currency=_currency;
		rate=_rate;
		product=_product;
		quantity=_quantity;
	}
	
		public InvoceDoc(String[] _values) 
		{
			if(_values!=null)
				if(_values.length==9) 
				{
					number=_values[1];
					user=_values[2];
					sum=Double.parseDouble(_values[3]);
					date=new Document.DateDoc(_values[4]);
					currency=_values[5];
					rate=Double.parseDouble(_values[6]);
					product=_values[7];
					quantity=Double.parseDouble(_values[8]);
					
				}
				else 
				{
					EstablishValues();
				}
		}
	
			//Properties
			String currency;
			double rate;
			String product;
			double quantity;
			
			//Methods
			private void EstablishValues() 
			{
				number=" ";
				user=" ";
				sum=0;
				date=new Document.DateDoc(0,0,0);
				currency=" ";
				rate=0;
				product=" ";
				quantity=0;
			}

			public String getValueInvoce() 
			{
				return "Накладная"+sep+String.join(sep,getValue(),currency,Double.toString(rate),
						product,Double.toString(quantity))+end;
			}
			
			public String getShortValue() 
			{
				return "Накладная от "+date.getDate()+" номер "+number;
			}
			
}
