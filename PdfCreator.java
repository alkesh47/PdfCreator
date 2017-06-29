package com.skill.India.POC;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.test.annotations.WrapToTest;

import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Alkesh srivastav on 6/29/2017.
 */
@WrapToTest
public class PdfCreator {
    private static final String DEST = "PdfFiles/itext.pdf";

    public static void main(String args[]) throws IOException, SQLException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PdfCreator().createPdf(DEST);
    }

    public void createPdf(String dest)throws IOException, SQLException{
        PdfWriter pdfWriter = new PdfWriter(dest);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        //Example of an image
        Image image = new Image(ImageDataFactory.create("skill.png"));
        float width = image.getImageWidth()/50;
        float height = image.getImageHeight()/50;
        image.scaleAbsolute(width,height);
        document.add(new Paragraph().add(image));



        //Create a sample font
        PdfFont pdfFont = PdfFontFactory.createFont(FontConstants.COURIER_BOLD);
        document.add(new Paragraph("This is a sample PDF document").setFont(pdfFont));

        //Example of a list
        document.add(new Paragraph("A sample of an easy list"));
        //Create a list
        List list = new List()
                .setListSymbol("\u2022")
                .setSymbolIndent(15)
                .setFont(pdfFont);

        for (int x=0;x<10;x++)
            list.add(new ListItem("Item "+ x));

        document.add(list);
        document.close();

        //Database connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "sinestro");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM details");

        while(resultSet.next())
            System.out.println(resultSet.getString(1));

        connection.close();
    }
}
