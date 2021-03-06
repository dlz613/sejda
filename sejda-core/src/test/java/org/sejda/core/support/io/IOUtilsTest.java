/*
 * Created on 19/ott/2011
 * Copyright 2011 by Andrea Vacondio (andrea.vacondio@gmail.com).
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
package org.sejda.core.support.io;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collections;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.sejda.model.SejdaFileExtensions;
import org.sejda.model.exception.TaskIOException;

/**
 * @author Andrea Vacondio
 * 
 */
public class IOUtilsTest {

    @Test
    public void testCreateBuffer() throws TaskIOException {
        File tmp = IOUtils.createTemporaryBuffer();
        tmp.deleteOnExit();
        assertTrue(tmp.exists());
        assertTrue(tmp.isFile());
    }

    @Test
    public void testCreatePdfBuffer() throws TaskIOException {
        File tmp = IOUtils.createTemporaryPdfBuffer();
        tmp.deleteOnExit();
        assertTrue(tmp.exists());
        assertTrue(tmp.isFile());
        assertTrue(FilenameUtils.isExtension(tmp.getName(), Collections.singleton(SejdaFileExtensions.PDF_EXTENSION)));
    }
}
