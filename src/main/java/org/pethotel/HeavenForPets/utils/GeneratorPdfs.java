package org.pethotel.HeavenForPets.utils;

import com.itextpdf.text.Document;

public interface GeneratorPdfs {
    Document generate(Object object);
}
