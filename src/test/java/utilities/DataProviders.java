package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider
	
	@DataProvider(name ="LoginData")
	public String [][] getData() throws Exception {
		String path = "D:/eclipse/eclipse/eclipse-workspace/selenium/openCart/testData/DataExcel.xlsx";
		
		ExcelUtils xlutil = new ExcelUtils(path);
		
		int totalRowCount = xlutil.getRowCount("Sheet1");
		//System.out.println("row count = "+totalRowCount);
		int totalColCount = xlutil.getCellCount("Sheet1", totalRowCount);
		//System.out.println("Col Count = "+totalColCount);
		
		String loginData[][] = new String[totalRowCount][totalColCount];
		
		for(int r=1;r<=totalRowCount;r++)
		{
			for(int c=0;c<totalColCount;c++)
			{
				loginData[r-1][c] = xlutil.getCellData("Sheet1", r, c);
			}
		}
		
		return loginData;
		
	}
}
