/*
 * Created on Oct 2, 2011
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
package org.sejda.cli.transformer;

import org.sejda.cli.model.PdfToSingleTiffTaskCliArguments;
import org.sejda.core.manipulation.model.parameter.image.PdfToSingleTiffParameters;

/**
 * {@link CommandCliArgumentsTransformer} for the PdfToSingleTiff task command line interface
 * 
 * @author Eduard Weissmann
 * 
 */
public class PdfToSingleTiffCliArgumentsTransformer extends BaseCliArgumentsTransformer implements
        CommandCliArgumentsTransformer<PdfToSingleTiffTaskCliArguments, PdfToSingleTiffParameters> {

    /**
     * Transforms {@link PdfToSingleTiffParameters} to {@link PdfToSingleTiffParameters}
     * 
     * @param taskCliArguments
     * @return populated task parameters
     */
    public PdfToSingleTiffParameters toTaskParameters(PdfToSingleTiffTaskCliArguments taskCliArguments) {
        PdfToSingleTiffParameters parameters = new PdfToSingleTiffParameters(taskCliArguments.getColorType()
                .getEnumValue());

        parameters.setCompressionType(taskCliArguments.getCompressionType().getEnumValue());

        populateSourceParameters(parameters, taskCliArguments);
        populateAbstractParameters(parameters, taskCliArguments);

        return parameters;
    }
}