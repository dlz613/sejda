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
package org.sejda.cli.model;

import uk.co.flamingpenguin.jewel.cli.Option;

/**
 * Trait for cli tasks that output image files
 * 
 * @author Eduard Weissmann
 * 
 */
public interface CliArgumentsWithImageOutput extends TaskCliArguments {

    @Option(shortName = "r", description = "resolution in dpi. Default is 72 (optional)")
    int getResolution();

    boolean isResolution();

    @Option(shortName = "z", description = "zoom factor for the generated images (EX. 1.5 generates images with a 150% zoom factor). (optional)")
    float getUserZoom();

    boolean isUserZoom();
}
