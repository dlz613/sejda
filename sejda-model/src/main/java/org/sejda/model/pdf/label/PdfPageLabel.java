/*
 * Created on 02/gen/2011
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
package org.sejda.model.pdf.label;

import java.security.InvalidParameterException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Model for a page label. <br>
 * Pdf reference 1.7, Chap. 8.3.1
 * 
 * @author Andrea Vacondio
 * 
 */
public final class PdfPageLabel {

    @NotNull
    private String labelPrefix;
    @NotNull
    private PdfLabelNumberingStyle numberingStyle;
    @Min(value = 1)
    private int logicalPageNumber;

    private PdfPageLabel(String labelPrefix, PdfLabelNumberingStyle numberingStyle, int logicalPageNumber) {
        this.labelPrefix = labelPrefix;
        this.numberingStyle = numberingStyle;
        this.logicalPageNumber = logicalPageNumber;
    }

    public String getLabelPrefix() {
        return labelPrefix;
    }

    public PdfLabelNumberingStyle getNumberingStyle() {
        return numberingStyle;
    }

    public int getLogicalPageNumber() {
        return logicalPageNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(labelPrefix).append(numberingStyle)
                .append("logicalPageNumber", logicalPageNumber).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(labelPrefix).append(numberingStyle).append(logicalPageNumber).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PdfPageLabel)) {
            return false;
        }
        PdfPageLabel pageLabel = (PdfPageLabel) other;
        return new EqualsBuilder().append(labelPrefix, pageLabel.getLabelPrefix())
                .append(numberingStyle, pageLabel.getNumberingStyle())
                .append(logicalPageNumber, pageLabel.getLogicalPageNumber()).isEquals();
    }

    /**
     * Creates an empty label with the given style for the given logical page number.
     * 
     * @param numberingStyle
     * @param logicalPageNumber
     * @return the newly created instance
     * @throws InvalidParameterException
     *             if the input logical page number is not positive. if the input numbering style is null.
     */
    public static PdfPageLabel newInstanceWithoutLabel(PdfLabelNumberingStyle numberingStyle, int logicalPageNumber) {
        return PdfPageLabel.newInstanceWithLabel("", numberingStyle, logicalPageNumber);
    }

    /**
     * Creates a label with given label and given style for the given logical page number.
     * 
     * @param label
     * @param numberingStyle
     * @param logicalPageNumber
     * @return the newly created instance
     * @throws InvalidParameterException
     *             if the input logical page number is not positive. if the input label or numbering style are null.
     */
    public static PdfPageLabel newInstanceWithLabel(String label, PdfLabelNumberingStyle numberingStyle,
            int logicalPageNumber) {
        if (logicalPageNumber < 1) {
            throw new InvalidParameterException("Input page number must be positive.");
        }
        if (label == null) {
            throw new InvalidParameterException("Input label cannot be null.");
        }
        if (numberingStyle == null) {
            throw new InvalidParameterException("Input numbering style cannot be null.");
        }
        return new PdfPageLabel(label, numberingStyle, logicalPageNumber);
    }

}
