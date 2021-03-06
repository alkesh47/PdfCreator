package com.skill.India.POC;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alkesh srivastav on 7/6/2017.
 */
public class JasperFill {
    @SuppressWarnings("unchecked")
    public static void main(String args[]){
        String sourceFileName = "D://Jasper/lantern.jasper";
        DataBeanList DataBeanList = new DataBeanList();
        ArrayList<DataBean> dataList = DataBeanList.getDataBeanList();

        String printFileName = null;
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
        Map parameters = new HashMap();

        String rndm = String.valueOf(Math.random()).substring(4,8);
        String destFileName = "D://Jasper/" + rndm + ".pdf";
        try{
            printFileName = JasperFillManager.fillReportToFile(sourceFileName,parameters,beanCollectionDataSource);

            if(printFileName!=null)
                JasperExportManager.exportReportToPdfFile(printFileName,destFileName);

            System.out.println("Pdf generated successfully....!!!");
        } catch (JRException e) {

        }
    }
}
