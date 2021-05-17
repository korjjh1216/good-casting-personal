package shop.goodcasting.api.common.csv;


import lombok.extern.java.Log;
import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.hire.domain.HireDTO;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

@Log
public class ConvertToCSV {

    public void convert2CSV(List<Hire> hireList) {
        log.info("convert2CSV");
        String filePath = "C:/Users/aa/Desktop/CSV/test.csv";

        File file = null;
        BufferedWriter bw = null;
        String NEWLINE = System.lineSeparator();

        try {
            file = new File(filePath);
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "MS949"));


            bw.write("hire_id^title^contents^regDate");
            bw.write(NEWLINE);

//            bw.write("id1,title1,contents1,regDate1");
//            bw.write(NEWLINE);
//
//            bw.write("id2,title2,contents2,regDate2");
//            bw.write(NEWLINE);
//
//            bw.write("id3,title3,contents3,regDate3");
//            bw.write(NEWLINE);
            for (Hire hire : hireList) {

                bw.write(hire.getHireId() + "^"
                        + hire.getTitle() + "^" + hire.getContents()
                        + "^" + hire.getRegDate());
                bw.write(NEWLINE);
            }

            bw.flush();
            bw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
