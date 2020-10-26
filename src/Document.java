

public class Document {
	
	Document()
	{
		
	}
	public Document(String _number,String _user,double _sum,String _date) {
		// TODO Auto-generated constructor stub
		number=_number;
		user=_user;
		sum=_sum;
		date=new DateDoc(_date);
	}
	//Properties
	public static String sep="&";
	protected static String end="&&";
	String number;
	String user;
	double sum;
	DateDoc date;
	
	
	protected String getValue() 
	{
		return String.join(sep, number,user,Double.toString(sum),date.getDate());
	}
	
	class DateDoc{
		public DateDoc(int _day,int _month, int _year) 
		{
			day= _day;
			month=_month;
			year=_year;
		}

		public DateDoc(String _dateDoc) 
		{
			String[] num=_dateDoc.split("\\.");
			if(num.length==3) 
			{
				day=Integer.parseInt(num[0]);
				month=Integer.parseInt(num[1]);
				year=Integer.parseInt(num[2]);
			}
			else 
			{
				day=0;
				month=0;
				year=0;
			}
		}
		
		private int day;
		private int month;
		private int year;
		
		public String getDate() 
		{
			return Integer.toString(day)+"."+Integer.toString(month)+"."+Integer.toString(year);
		}
		
		public void setDate(int _day,int _month, int _year) 
		{
			day= _day;
			month=_month;
			year=_year;
		}

		public void setDay(int _day) 
		{
			day= _day;
		}

		public void setMonth(int _month) 
		{
			month=_month;
		}

		public void setYear(int _year) 
		{
			year=_year;
		}
	}
}

