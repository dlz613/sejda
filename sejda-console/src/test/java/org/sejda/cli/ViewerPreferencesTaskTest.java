/*
 * Created on Jul 1, 2011
 * Copyright 2011 by Eduard Weissmann (edi.weissmann@gmail.com).
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
package org.sejda.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.EnumSet;

import org.junit.Test;
import org.sejda.model.parameter.ViewerPreferencesParameters;
import org.sejda.model.pdf.viewerpreference.PdfBooleanPreference;
import org.sejda.model.pdf.viewerpreference.PdfDirection;
import org.sejda.model.pdf.viewerpreference.PdfDuplex;
import org.sejda.model.pdf.viewerpreference.PdfNonFullScreenPageMode;
import org.sejda.model.pdf.viewerpreference.PdfPageLayout;
import org.sejda.model.pdf.viewerpreference.PdfPageMode;
import org.sejda.model.pdf.viewerpreference.PdfPrintScaling;

/**
 * Tests for the ViewerPreferences command line interface
 * 
 * @author Eduard Weissmann
 * 
 */
public class ViewerPreferencesTaskTest extends AbstractTaskTest {

    public ViewerPreferencesTaskTest() {
        super(TestableTask.SET_VIEWER_PREFERENCES);
    }

    @Test
    public void onFlagOptions() {
        ViewerPreferencesParameters parameters = defaultCommandLine().withFlag("--centerWindow")
                .withFlag("--displayDocTitle").withFlag("--hideMenu").withFlag("--fitWindow")
                .withFlag("--hideWindowUI").withFlag("--hideToolbar").invokeSejdaConsole();

        assertContainsAll(EnumSet.allOf(PdfBooleanPreference.class), parameters.getEnabledPreferences());
    }

    @Test
    public void offFlagOptions() {
        ViewerPreferencesParameters parameters = defaultCommandLine().invokeSejdaConsole();

        assertTrue(parameters.getEnabledPreferences().isEmpty());
    }

    @Test
    public void specifiedValues() {
        ViewerPreferencesParameters parameters = defaultCommandLine().with("--printScaling", "app_default")
                .with("--direction", "r2l").with("--duplex", "duplex_flip_short_edge").with("--nfsMode", "nfsthumbs")
                .with("--layout", "twopagel").with("--mode", "attachments").invokeSejdaConsole();

        assertEquals(PdfPrintScaling.APP_DEFAULT, parameters.getPrintScaling());
        assertEquals(PdfDirection.RIGHT_TO_LEFT, parameters.getDirection());
        assertEquals(PdfDuplex.DUPLEX_FLIP_SHORT_EDGE, parameters.getDuplex());
        assertEquals(PdfNonFullScreenPageMode.USE_THUMNS, parameters.getNfsMode());
        assertEquals(PdfPageLayout.TWO_PAGE_LEFT, parameters.getPageLayout());
        assertEquals(PdfPageMode.USE_ATTACHMENTS, parameters.getPageMode());
    }

    @Test
    public void defaultValues() {
        ViewerPreferencesParameters parameters = defaultCommandLine().invokeSejdaConsole();
        assertNull(parameters.getPrintScaling());
        assertNull(parameters.getDirection());
        assertNull(parameters.getDuplex());
        assertEquals(PdfNonFullScreenPageMode.USE_NONE, parameters.getNfsMode());
        assertEquals(PdfPageLayout.SINGLE_PAGE, parameters.getPageLayout());
        assertEquals(PdfPageMode.USE_NONE, parameters.getPageMode());
    }
}
