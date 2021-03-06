/*
 * Created on 22/gen/2011
 * Copyright 2010 by Andrea Vacondio (andrea.vacondio@gmail.com).
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
package org.sejda.model.pdf.label;

import org.junit.Test;
import org.sejda.TestUtils;

/**
 * @author Andrea Vacondio
 * 
 */
public class PdfPageLabelTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNullLabel() {
        PdfPageLabel.newInstanceWithLabel(null, PdfLabelNumberingStyle.ARABIC, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullStyle() {
        PdfPageLabel.newInstanceWithLabel("dsdsadsa", null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativePhysicalNumb() {
        PdfPageLabel.newInstanceWithLabel("dsdsadsa", PdfLabelNumberingStyle.ARABIC, -1);
    }

    @Test
    public void testEqualsAndHasCode() {
        PdfPageLabel victim1 = PdfPageLabel.newInstanceWithLabel("dsdsadsa", PdfLabelNumberingStyle.ARABIC, 1);
        PdfPageLabel victim2 = PdfPageLabel.newInstanceWithLabel("dsdsadsa", PdfLabelNumberingStyle.ARABIC, 1);
        PdfPageLabel victim3 = PdfPageLabel.newInstanceWithLabel("dsdsadsa", PdfLabelNumberingStyle.ARABIC, 1);
        PdfPageLabel victim4 = PdfPageLabel.newInstanceWithoutLabel(PdfLabelNumberingStyle.LOWERCASE_LETTERS, 1);
        TestUtils.testEqualsAndHashCodes(victim1, victim2, victim3, victim4);
    }
}
