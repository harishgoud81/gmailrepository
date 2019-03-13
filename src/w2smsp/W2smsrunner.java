package w2smsp;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class W2smsrunner
{
	@Test
public void method()  throws Exception
{
File f=new File("w2smsdata.xls");
Workbook rwb=Workbook.getWorkbook(f);

Sheet rsh1=rwb.getSheet(0);
int nour1=rsh1.getRows();
int nouc1=rsh1.getColumns();

Sheet rsh2=rwb.getSheet(1);
int nour2=rsh2.getRows();
int nouc2=rsh2.getColumns();

WritableWorkbook wwb=Workbook.createWorkbook(f,rwb);
WritableSheet wsh1=wwb.getSheet(0);
WritableSheet wsh2=wwb.getSheet(1);


SimpleDateFormat sf=new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
Date dt=new Date();
String cname=sf.format(dt);

Label l1=new Label(nouc1,0,cname);
wsh1.addCell(l1);

Label l2=new Label(nouc2,0,cname);
wsh2.addCell(l2);

W2smsmethods ms=new W2smsmethods();
Method m[]=ms.getClass().getMethods();

try
{
	for(int i=1;i<nour1;i++)
	{
		int flag=0;
		
		String fid=rsh1.getCell(0,i).getContents();
		String mode=rsh1.getCell(2,i).getContents();
	if(mode.equalsIgnoreCase("yes"))
	{
		for(int j=1;j<nour2;j++)
		{
			String sid=rsh2.getCell(0,j).getContents();
			if(fid.equalsIgnoreCase(sid))
			{
				String mn=rsh2.getCell(2,j).getContents();
				String l=rsh2.getCell(3,j).getContents();
				String d=rsh2.getCell(4,j).getContents();
				String c=rsh2.getCell(5,j).getContents();
			
				System.out.println(mn+" "+l+" "+c);
			
			
			for(int k=0;k<m.length;k++)
			{
				if(m[k].getName().equals(mn))
				{
					String r=(String)m[k].invoke(ms,l,d,c);
					Label lb=new Label(nouc2,j,r);
					wsh2.addCell(lb);
					if(r.contains("failed") || r.contains("failed"))
					{
						flag=1;
					}
					break;
		                }
			}
			
			}
			else
			{
				break;
			}
		
		}
	if(flag==0)
	{
		Label l=new Label(nouc1, i, "test passed");
		wsh1.addCell(l);
	}
	else
	{
		Label l=new Label(nouc1,i,"test failed"); 
		wsh1.addCell(l);
		 
	}
		}
	}
	
}
catch(Exception ex)
{
	System.out.println(ex.getMessage());
}
wwb.write();
wwb.close();
rwb.close();



}
}
