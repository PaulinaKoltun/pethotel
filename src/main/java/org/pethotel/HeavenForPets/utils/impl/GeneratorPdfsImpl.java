package org.pethotel.HeavenForPets.utils.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.pethotel.HeavenForPets.domein.Food;
import org.pethotel.HeavenForPets.utils.GeneratorPdfs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class GeneratorPdfsImpl implements GeneratorPdfs {
    @Override
    public void generate(Object object, Document document) {
        //Document document = new Document();
        Food food = (Food) object;
        //try {
         //   PdfWriter.getInstance(document, new FileOutputStream("../PDFs/iTextHelloWorld.pdf"));
        //} catch (DocumentException e) {
        //    e.printStackTrace();
        //} catch (FileNotFoundException e) {
        //    e.printStackTrace();
        //}

        //document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
        Chunk chunk = new Chunk(food.toString(), font);
        chunk.setUnderline(0.5f, -1.5f);

        try {
            document.add(chunk);
            //document.add(chunk.NEWLINE);
            document.add(new Phrase("\n"));
            //document.add(chunk);
        } catch (DocumentException e) {
            e.printStackTrace();
        } //finally {
            //document.close();
        //}

    }

    @Override
    public void openDocument(Document document){
        try {
            PdfWriter.getInstance(document, new FileOutputStream("../PDFs/iTextHelloWorld.pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
    }
}
