/*
 * Created on Aug 25, 2011
 * Copyright 2010 by Eduard Weissmann (edi.weissmann@gmail.com).
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

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.sejda.model.exception.TaskException;
import org.sejda.model.parameter.base.TaskParameters;

/**
 * For tasks that support single file as output, test various scenarios related to this trait
 * 
 * @author Eduard Weissmann
 * 
 */
public class FileOutputTraitTest extends AbstractTaskTraitTest {

    @Parameters
    public static Collection<Object[]> data() {
        return asParameterizedTestData(TestableTask.allTasksExceptFor(TestableTask.getTasksWithFolderOutput()));
    }

    public FileOutputTraitTest(TestableTask testableTask) {
        super(testableTask);
    }

    @Test
    public void positive() throws TaskException {
        assertFalse(new File("./outputs/fileOutput.pdf").exists());

        TaskParameters result = defaultCommandLine().with("-o", "./outputs/fileOutput.pdf").invokeSejdaConsole();
        assertOutputFile(result.getOutput(), new File("./outputs/fileOutput.pdf"));
    }
}
