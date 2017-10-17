package servlet;

public class common {
	
	public static int checkUandP(String user, String pass)
	{
		String arr[] = {"M", "123", "", ""};
		
		for (int i = 0; i < arr.length/2; i+=2) {
			if(user.equals(arr[i]))
			{
				if(pass.equals(arr[i+1])) return 1;
			}
		}
		return 0;
	}
	
	public static void DownloadFile(String fileName)
	{
		
	}
	
	public static void Wait()
	{
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
