
package com.blocker.action;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;


import com.opensymphony.xwork2.ActionSupport;
import com.blocker.dao.AdminDAO;
import com.blocker.bean.ReservationsBean;

public class ExportAction extends ActionSupport {
	private List<ReservationsBean> resList;

    @SuppressWarnings("deprecation")

    /**
	 * @throws Exception when any exceptions are caught
	 */
    public final String export() throws Exception {

try  {
String filename = "c:/data.xls";
HSSFWorkbook hwb = new HSSFWorkbook();
HSSFSheet sheet =  hwb.createSheet("new sheet");

HSSFRow rowhead =  sheet.createRow((short) 0);
rowhead.createCell((short) 0).setCellValue("ReservationId");
rowhead.createCell((short) 1).setCellValue("ResourceId");
rowhead.createCell((short) 2).setCellValue("userId");
rowhead.createCell((short) 3).setCellValue("groupID");
rowhead.createCell((short) 4).setCellValue("type");

rowhead.createCell((short) 5).setCellValue("startdate");
rowhead.createCell((short) 6).setCellValue("enddate");
rowhead.createCell((short) 7).setCellValue("starttime");
rowhead.createCell((short) 8).setCellValue("endtime");
rowhead.createCell((short) 9).setCellValue("approved");
rowhead.createCell((short) 10).setCellValue("reason");
rowhead.createCell((short) 11).setCellValue("reservationduration");
AdminDAO ad = new AdminDAO();
resList = ad.getReserve();
Iterator<ReservationsBean> itr = resList.iterator();

int i = 1;
while (itr.hasNext())  {
    ReservationsBean rbean = new ReservationsBean();
    rbean = (ReservationsBean) itr.next();



HSSFRow row =  sheet.createRow((short) i);
row.createCell((short) 0).setCellValue(Long.toString(rbean.getReservationID()));
row.createCell((short) 1).setCellValue(rbean.getResourceID());
row.createCell((short) 2).setCellValue(rbean.getUserID());
row.createCell((short) 3).setCellValue(rbean.getGroupID());
row.createCell((short) 4).setCellValue(rbean.getType());

row.createCell((short) 5).setCellValue(rbean.getStartDate());
row.createCell((short) 6).setCellValue(rbean.getEndDate());
row.createCell((short) 7).setCellValue(rbean.getStartTime());
row.createCell((short) 8).setCellValue(rbean.getEndTime());
row.createCell((short) 9).setCellValue(rbean.getApproved());
row.createCell((short) 10).setCellValue(rbean.getReason());
row.createCell((short) 11).setCellValue(rbean.getReservationDuration());
i++;
}
FileOutputStream fileOut =  new FileOutputStream(filename);
hwb.write(fileOut);
fileOut.close();
System.out.println("Your excel file has been generated!");
return "success";

} catch (Exception ex) {
    ex.printStackTrace();
    return "error";

}
    }
}
