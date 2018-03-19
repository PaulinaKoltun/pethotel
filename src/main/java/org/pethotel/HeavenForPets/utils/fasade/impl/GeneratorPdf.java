package org.pethotel.HeavenForPets.utils.fasade.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.pethotel.HeavenForPets.domein.FoodDetails;
import org.pethotel.HeavenForPets.utils.fasade.Generator;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class GeneratorPdf implements Generator {

    private static final String filename = "iTextHelloWorld.pdf";

    @Override
    public String generate(FoodDetails foodDetails) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("src/main/webapp/WEB-INF/downloads/" + filename));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
        Chunk chunk = new Chunk(foodDetails.toString(), font);
        chunk.setUnderline(0.5f, -1.5f);

        try {
            //document.add(chunk.NEWLINE);
            document.add(new Phrase("\n"));
            document.add(chunk);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        return filename;
    }
}

