/*
 * Created on 03/ago/2011
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
package org.sejda.model.pdf.page;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Andrea Vacondio
 * 
 */
public class PredefinedSetOfPagesTest {

    @Test
    public void getPages() {
        assertEquals(10, PredefinedSetOfPages.ALL_PAGES.getPages(10).size());
        assertEquals(5, PredefinedSetOfPages.EVEN_PAGES.getPages(10).size());
        assertEquals(5, PredefinedSetOfPages.ODD_PAGES.getPages(10).size());
        assertEquals(0, PredefinedSetOfPages.NONE.getPages(0).size());
    }
}
