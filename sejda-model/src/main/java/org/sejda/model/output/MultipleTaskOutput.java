/*
 * Created on 09/mar/2012
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
package org.sejda.model.output;

/**
 * Represent the output for a task generating a multiple outputs.
 * 
 * @author Andrea Vacondio
 * @param <T>
 *            generic type for the output destination
 */
public interface MultipleTaskOutput<T> extends TaskOutput<T> {
    // define further methods
}
