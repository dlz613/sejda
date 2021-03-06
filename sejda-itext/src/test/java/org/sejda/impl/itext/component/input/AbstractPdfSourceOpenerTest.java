/*
 * Created on 09/mar/2014
 * Copyright 2014 by Andrea Vacondio (andrea.vacondio@gmail.com).
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.sejda.impl.itext.component.input;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;

import org.junit.Test;
import org.sejda.core.Sejda;
import org.sejda.model.exception.TaskIOException;
import org.sejda.model.input.PdfStreamSource;

import com.lowagie.text.pdf.PdfReader;

/**
 * @author Andrea Vacondio
 * 
 */
public class AbstractPdfSourceOpenerTest {

    private PdfStreamSource getSource() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("pdf/enc_own_pwd.pdf");
        return PdfStreamSource.newInstanceNoPassword(stream, "enc_own_pwd.pdf");
    }

    @Test
    public void testUnethicalFull() throws TaskIOException {
        System.setProperty(Sejda.UNETHICAL_READ_PROPERTY_NAME, "false");
        PdfReader encReader = PdfSourceOpeners.newFullReadOpener().open(getSource());
        assertTrue(encReader.isEncrypted());
        System.setProperty(Sejda.UNETHICAL_READ_PROPERTY_NAME, "true");
        PdfReader reader = PdfSourceOpeners.newFullReadOpener().open(getSource());
        assertFalse(reader.isEncrypted());
    }

    @Test
    public void testUnethicalPartial() throws TaskIOException {
        System.setProperty(Sejda.UNETHICAL_READ_PROPERTY_NAME, "false");
        PdfReader encReader = PdfSourceOpeners.newPartialReadOpener().open(getSource());
        assertTrue(encReader.isEncrypted());
        System.setProperty(Sejda.UNETHICAL_READ_PROPERTY_NAME, "true");
        PdfReader reader = PdfSourceOpeners.newPartialReadOpener().open(getSource());
        assertFalse(reader.isEncrypted());
    }
}
