package org.pethotel.HeavenForPets.utils;

import com.itextpdf.text.Document;

public interface GeneratorPdfs {
    void generate(Object object, Document document);
    void openDocument(Document document);
}
