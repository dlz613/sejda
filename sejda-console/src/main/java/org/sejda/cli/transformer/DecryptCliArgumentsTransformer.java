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
package org.sejda.cli.transformer;

import org.sejda.cli.model.DecryptTaskCliArguments;
import org.sejda.model.parameter.DecryptParameters;

/**
 * {@link CommandCliArgumentsTransformer} for the Decrypt task command line interface
 * 
 * @author Eduard Weissmann
 * 
 */
public class DecryptCliArgumentsTransformer extends BaseCliArgumentsTransformer implements
        CommandCliArgumentsTransformer<DecryptTaskCliArguments, DecryptParameters> {

    /**
     * Transforms {@link DecryptTaskCliArguments} to {@link DecryptParameters}
     * 
     * @param taskCliArguments
     * @return populated task parameters
     */
    public DecryptParameters toTaskParameters(DecryptTaskCliArguments taskCliArguments) {
        DecryptParameters parameters = new DecryptParameters();
        populateAbstractParameters(parameters, taskCliArguments);
        populateSourceParameters(parameters, taskCliArguments);
        populateOutputTaskParameters(parameters, taskCliArguments);
        populateOutputPrefix(parameters, taskCliArguments);
        return parameters;
    }

}
