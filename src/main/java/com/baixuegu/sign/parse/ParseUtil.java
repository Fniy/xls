package com.baixuegu.sign.parse;

import com.baixuegu.sign.beans.A;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaojunjie on 18/4/13.
 */
public class ParseUtil {
    public static List<A> readAlist(File xls) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem(xls);
        HSSFWorkbook workbook = new HSSFWorkbook(fs);
        HSSFSheet sheet = workbook.getSheetAt(0);

        List<A> aList = new ArrayList<>();

        for (int row = 1; row <= sheet.getLastRowNum(); row++) {
            HSSFRow hSSFRow = sheet.getRow(row);

            A a = new A();
            a.shop = hSSFRow.getCell(0).getStringCellValue();

            for (int col = 1; col < hSSFRow.getLastCellNum(); col++) {
                HSSFCell cell = hSSFRow.getCell(col);
                if (cell.getHyperlink() != null) {
                    A.User user = new A.User();
                    user.url = cell.getHyperlink().getAddress();
                    user.name = sheet.getRow(0).getCell(col).getStringCellValue();
                    a.users.add(user);
                }
            }

            aList.add(a);
        }
        return aList;
    }


}
