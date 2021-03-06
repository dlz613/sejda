/*
 * Created on Aug 29, 2011
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

import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Abstract base class for tests for task traits
 * 
 * @author Eduard Weissmann
 * 
 */
@RunWith(Parameterized.class)
public abstract class AbstractTaskTraitTest extends AbstractTaskTest {

    public AbstractTaskTraitTest(TestableTask testableTask) {
        super(testableTask);
    }

    public static <T> Collection<Object[]> asParameterizedTestData(T[] items) {
        Collection<Object[]> result = new ArrayList<Object[]>();
        for (T eachItem : items) {
            result.add(new Object[] { eachItem });
        }

        return result;
    }
}
