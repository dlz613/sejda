/*
 * Created on 09/ago/2011
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
package org.sejda.core.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.sejda.TestUtils;
import org.sejda.core.TestListenerFactory;
import org.sejda.core.TestListenerFactory.TestListenerFailed;
import org.sejda.core.context.DefaultSejdaContext;
import org.sejda.core.context.SejdaContext;
import org.sejda.core.notification.context.ThreadLocalNotificationContext;
import org.sejda.model.exception.TaskException;
import org.sejda.model.input.PdfStreamSource;
import org.sejda.model.parameter.SplitByGoToActionLevelParameters;
import org.sejda.model.pdf.PdfVersion;
import org.sejda.model.task.Task;

/**
 * @author Andrea Vacondio
 * 
 */
public abstract class SplitByGoToActionLevelTaskTest extends PdfOutEnabledTest implements
        TestableTask<SplitByGoToActionLevelParameters> {

    private DefaultTaskExecutionService victim = new DefaultTaskExecutionService();

    private SejdaContext context = mock(DefaultSejdaContext.class);

    @Before
    public void setUp() {
        TestUtils.setProperty(victim, "context", context);
    }

    private SplitByGoToActionLevelParameters setUpParameters(int level, String regEx) {
        SplitByGoToActionLevelParameters parameters = new SplitByGoToActionLevelParameters(level);
        parameters.setMatchingTitleRegEx(regEx);
        parameters.setCompress(true);
        parameters.setVersion(PdfVersion.VERSION_1_6);
        InputStream stream = getClass().getClassLoader().getResourceAsStream("pdf/test_outline.pdf");
        PdfStreamSource source = PdfStreamSource.newInstanceNoPassword(stream, "test_outline.pdf");
        parameters.setSource(source);
        parameters.setOverwrite(true);
        return parameters;
    }

    @Test
    public void testExecuteLevel3() throws TaskException, IOException {
        SplitByGoToActionLevelParameters parameters = setUpParameters(3, null);
        when(context.getTask(parameters)).thenReturn((Task) getTask());
        initializeNewStreamOutput(parameters);
        victim.execute(parameters);
        assertOutputContainsDocuments(2);
    }

    @Test
    public void testExecuteLevel2() throws TaskException, IOException {
        SplitByGoToActionLevelParameters parameters = setUpParameters(2, null);
        when(context.getTask(parameters)).thenReturn((Task) getTask());
        initializeNewStreamOutput(parameters);
        victim.execute(parameters);
        assertOutputContainsDocuments(3);
    }

    @Test
    public void testExecuteLevel2MatchingregEx() throws TaskException, IOException {
        SplitByGoToActionLevelParameters parameters = setUpParameters(2, ".+(page)+.+");
        when(context.getTask(parameters)).thenReturn((Task) getTask());
        initializeNewStreamOutput(parameters);
        victim.execute(parameters);
        assertOutputContainsDocuments(2);
    }

    @Test
    public void testExecuteLevel4() throws TaskException {
        SplitByGoToActionLevelParameters parameters = setUpParameters(4, null);
        when(context.getTask(parameters)).thenReturn((Task) getTask());
        initializeNewStreamOutput(parameters);
        TestListenerFailed failListener = TestListenerFactory.newFailedListener();
        ThreadLocalNotificationContext.getContext().addListener(failListener);
        victim.execute(parameters);
        assertTrue(failListener.isFailed());
    }

}
