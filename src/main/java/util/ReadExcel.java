package util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import util.ExcelCell;
import util.ImgBean;
import util.PrintToPdfUtil;
import util.QRCodeGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取excel表格内容，根据表格内容生成二维码并生成pdf文件
 */
public class ReadExcel {
    public static void excel() throws Exception {
        //用流的方式先读取到你想要的excel的文件
        /*FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"/src/excel.xls"));*/
        FileInputStream fis=new FileInputStream(new File("D:\\twocode\\tmd.xls"));
        //解析excel
        POIFSFileSystem pSystem=new POIFSFileSystem(fis);
        //获取整个excel
        HSSFWorkbook hb=new HSSFWorkbook(pSystem);
        System.out.println(hb.getNumCellStyles());
        //获取第一个表单sheet
        HSSFSheet sheet=hb.getSheetAt(0);
        //获取第一行
        int firstrow=    sheet.getFirstRowNum();
        //获取最后一行
        int lastrow=    sheet.getLastRowNum();
        //创建一个集合来存放每次读取的25行数据
        List<ExcelCell> excelCellList = new ArrayList<>();
        //将所有的数据存放到一个集合中，每25行数据为一个小集合
        List<List> listList = new ArrayList<>();
        //循环行数依次获取列数
        for (int i = firstrow+1; i < lastrow+1; i++) {
            //获取哪一行i
            Row row=sheet.getRow(i);
            if (row!=null) {
                //获取这一行的第一列
                int firstcell=    row.getFirstCellNum();
                //获取这一行的最后一列
                int lastcell=    row.getLastCellNum();
                //创建一个集合，用处将每一行的每一列数据都存入集合中
                List<String> list=new ArrayList<>();
                for (int j = firstcell; j <lastcell; j++) {
                    //获取第j列
                    Cell cell=row.getCell(j);

                    if (cell!=null) {
                     /*   System.out.print(cell+"\t");*/
                        list.add(cell.toString());
                    }
                }
                ExcelCell ecell = new ExcelCell();
                if (list.size()>0) {
                    ecell.setNumber(list.get(0));
                    ecell.setCode(list.get(1));
                }
                excelCellList.add(ecell);
                if(i % 25==0){
                    excelCellList.add(ecell);
                    System.out.println(i);
                    listList.add(excelCellList);
                    excelCellList = new ArrayList<ExcelCell>();
                }
            }
        }

        int j= 1;

        //编号后缀
        int suffix = 20045000;

        for(List<ExcelCell> a : listList){
            System.out.println(a);

            //创建文件夹
            File file=new File("D:\\GBF"+j);

            //如果文件夹不存在
            if(!file.exists()){
                //创建文件夹
                file.mkdir();
            }
                //根据集合生成二维码
                QRCodeGenerator generateQRCodeImage =new QRCodeGenerator();
            //编号前缀
            int prefix = 200180;

                for(int i = 0;i < a.size()-1;i++){
                    ExcelCell ecell = a.get(i);
                   // generateQRCodeImage.generateQRCodeImage(ecell.getCode(), 350, 350, "D:/twocode2/"+ecell.getNumber()+".png");
                    generateQRCodeImage.generateQRCodeImage(ecell.getCode(), 350, 350, "D:/GBF"+j+"/"+ecell.getNumber()+".png");
                    //将二维码与文字合并
                    //ImgBean.ImgYin(ecell.getNumber(),"D:/GBF"+j+"/"+ecell.getNumber()+".png");
                    /**/
                    suffix = suffix+1;
                    ImgBean.ImgYin("HN"+prefix+j+"-"+"YS"+suffix,"D:/GBF"+j+"/"+ecell.getNumber()+".png");
                    /**/
                }
                j++;

        }
       /* for(ExcelCell x : excelCellList){
            System.out.println(x);
        }*/
        fis.close();
        //创建文件夹,用来存放pdf文件
        File file=new File("D:\\PDFTotal");

        //如果文件夹不存在
        if(!file.exists()){
            //创建文件夹
            file.mkdir();
        }
        //将文件夹中合成的二维码生成pdf
        for(int i = 1;i < listList.size()+1;i++){
            PrintToPdfUtil.toPdf("D:/GBF"+i+"/","D:/PDFTotal/GBF"+i+".pdf");
        }

    }

    public static void main(String[] args) {
        try {
            excel();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
