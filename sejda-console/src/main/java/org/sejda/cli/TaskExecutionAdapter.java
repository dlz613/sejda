/*
 * Created on Jul 4, 2011
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

import org.sejda.model.parameter.base.TaskParameters;

/**
 * Sejda cli adapter for the {@link org.sejda.core.service.TaskExecutionService}
 * 
 * @author Eduard Weissmann
 * 
 */
public interface TaskExecutionAdapter {

    /**
     * Executes {@link org.sejda.model.task.Task} providing the taskParameters as input
     * 
     * @param taskParameters
     */
    void execute(TaskParameters taskParameters);
}
