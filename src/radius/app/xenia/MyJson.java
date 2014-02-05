package radius.app.xenia;

public class MyJson {
	public static String getString(String key, String json)
	{
		String result="#fail#";
		int end;	
		int start;
		start = json.indexOf(key)+key.length()+3;
		end = json.indexOf('\"',start);
		try{
			result = json.substring(start,end);
		}
		catch(Exception e)
		{
			
		}
		return result;
	}
	public static float getFloat(String key, String json)
	{
		int end;	
		int start;
		float result=-9998; //means unresolvable
		try
		{
			if(json.charAt(json.indexOf(key)+key.length()+2)=='\"')
			{
				start = json.indexOf(key)+key.length()+3;
				end = json.indexOf('\"',start);
				result = Float.parseFloat(json.substring(start,end));
			}
			else
			{
				start = json.indexOf(key)+key.length()+2;
				end = json.indexOf(',',start);
				result = Float.parseFloat(json.substring(start,end));
			}
		}
		catch(Exception e)
		{
			
		}
		return result;
	}
	public static int getInt(String key, String json)
	{
		return Math.round(getFloat(key,json));			
	}
}
