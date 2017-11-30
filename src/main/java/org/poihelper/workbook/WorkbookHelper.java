package org.poihelper.workbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.poihelper.sheet.SheetDescriptor;
import org.poihelper.sheet.SheetProcessor;

public class WorkbookHelper {
    public static final String MS_EXCEL_TYPE = "application/vnd.ms-excel";

    @Inject
    private SheetProcessor sheetProcessor;

    public OutputStream create(List<SheetDescriptor> sheetDescriptors) throws IOException {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (Workbook wb = new XSSFWorkbook()) {
            sheetDescriptors.stream().forEach(sd -> sheetProcessor.process(wb, sd));

            wb.write(byteArrayOutputStream);
        }

        return byteArrayOutputStream;
    }

}