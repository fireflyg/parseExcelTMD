package test;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试读取excel内容
 */
public class Excel {
    public static void excel() throws Exception {
        //用流的方式先读取到你想要的excel的文件
        /*FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"/src/excel.xls"));*/
        FileInputStream fis = new FileInputStream(new File("D:\\twocode\\tmd2.xls"));
        //解析excel
        POIFSFileSystem pSystem = new POIFSFileSystem(fis);
        //获取整个excel
        HSSFWorkbook hb = new HSSFWorkbook(pSystem);
        System.out.println(hb.getNumCellStyles());
        //获取第一个表单sheet
        HSSFSheet sheet = hb.getSheetAt(0);
        //获取第一行
        int firstrow = sheet.getFirstRowNum();
        //获取最后一行
        int lastrow = sheet.getLastRowNum();
        //创建一个集合来存放每次读取的25行数据
        List<TestExcelCell> excelCellList = new ArrayList<>();
        //将所有的数据存放到一个集合中，每25行数据为一个小集合
        List<List> listList = new ArrayList<>();
        //循环行数依次获取列数
        for (int i = firstrow + 1; i < lastrow + 1; i++) {
            //获取哪一行i
            Row row = sheet.getRow(i);
            if (row != null) {
                //获取这一行的第一列
                int firstcell = row.getFirstCellNum();
                //获取这一行的最后一列
                int lastcell = row.getLastCellNum();
                //创建一个集合，用处将每一行的每一列数据都存入集合中
                List<String> list = new ArrayList<>();
                for (int j = firstcell; j < lastcell; j++) {
                    //获取第j列
                    Cell cell = row.getCell(j);

                    if (cell != null) {
                        /*   System.out.print(cell+"\t");*/
                        list.add(cell.toString());
                    }
                }

                TestExcelCell ecell = new TestExcelCell();
                if (list.size() > 0) {
                    ecell.setNumber(list.get(0));
                    ecell.setCode(list.get(1));
                }
                excelCellList.add(ecell);

            }
        }
        for(TestExcelCell x : excelCellList){
            System.out.println(x);
        }
        fis.close();
    }

    public static void main(String[] args) throws Exception {
        excel();
    }

}