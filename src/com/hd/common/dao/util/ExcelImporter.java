package com.hd.common.dao.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.mapping.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 * excel导入util
 * @author Administrator
 *
 */
public class ExcelImporter {

	public List readExcel(MultipartFile filePath, int cellNum){
	
		List<List> list = new ArrayList<List>();
		try {
//			File file = new File(filePath);
			InputStream is = filePath.getInputStream();;
			
			Workbook workBook = null;

			String filename = filePath.getOriginalFilename();
			if(filename.endsWith(".xls")){//Excel 2003
				workBook = new HSSFWorkbook(is);
			}else if(filename.endsWith(".xlsx")){//Excel 2007
				workBook = new XSSFWorkbook(is);
			}
			
			
			int sheetNum = workBook.getNumberOfSheets();
			Sheet sheet = null;
			for(int i=0; i<sheetNum; i++){
				sheet = workBook.getSheetAt(i);
				Row row = null;
				Iterator<Row> iterator = sheet.iterator();
				
				List list_row = null;
				while(iterator.hasNext()){
					row = iterator.next();
					list_row = new ArrayList();
					Cell cell = null;
					for( int j = 0;j<cellNum;j++){//此处4表示excel模板里有4列数据
						cell=row.getCell(j);
						if(cell != null){
							cell.setCellType(Cell.CELL_TYPE_STRING);
							list_row.add(cell.getStringCellValue());
						}else{
							list_row.add("");
						}
					}
					list.add(list_row);
				}
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List readExcel2(File filePath, int cellNum,HttpServletRequest request){
		System.out.println("ppppppppppppppppppppppp"+filePath);
		List<List> list = new ArrayList<List>();
		try {
//			File file = new File(filePath);
			InputStream is =  new FileInputStream(filePath);
			
			Workbook workBook = null;

			String filename = filePath.getName();
			System.out.println("filenamefilenamefilenamefilenamefilename"+filename);
			if(filename.endsWith(".xls")){//Excel 2003
				workBook = new HSSFWorkbook(is);
			}else if(filename.endsWith(".xlsx")){//Excel 2007
				workBook = new XSSFWorkbook(is);
			}
			
			
			int sheetNum = workBook.getNumberOfSheets();
			Sheet sheet = null;
			for(int i=0; i<sheetNum; i++){
				sheet = workBook.getSheetAt(i);
				Row row = null;
				Iterator<Row> iterator = sheet.iterator();
				
				List list_row = null;
				while(iterator.hasNext()){
					row = iterator.next();
					list_row = new ArrayList();
					Cell cell = null;
					for( int j = 0;j<cellNum;j++){//此处4表示excel模板里有4列数据
						cell=row.getCell(j);
						if(cell != null){
							cell.setCellType(Cell.CELL_TYPE_STRING);
							list_row.add(cell.getStringCellValue());
						}else{
							list_row.add("");
						}
					}
					list.add(list_row);
				}
			}
			List list_row =  list.get(1);
			String code = (String)list_row.get(0);
			
			int point = filename.indexOf(".");
			 String loadpath = request.getSession().getServletContext()
						.getRealPath("/")
						+ "WEB-INF"
						+ File.separator
						+ "upload"
						+ File.separator + "ceshi" + File.separator;
			filename = (new Date()).getTime()
					+ filename.substring(point, filename.length());
		    filePath.renameTo(new File(loadpath+code+"_"+filename));//改名     
			System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+filePath.getName());
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkaaa"+list.get(1));
		
		return list;
	}
	
}
