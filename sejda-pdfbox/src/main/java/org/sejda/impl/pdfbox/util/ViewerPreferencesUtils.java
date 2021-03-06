/*
 * Created on 27/ago/2011
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
package org.sejda.impl.pdfbox.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences;
import org.apache.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences.DUPLEX;
import org.apache.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences.NON_FULL_SCREEN_PAGE_MODE;
import org.apache.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences.PRINT_SCALING;
import org.apache.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences.READING_DIRECTION;
import org.sejda.model.exception.TaskException;
import org.sejda.model.pdf.viewerpreference.PdfBooleanPreference;
import org.sejda.model.pdf.viewerpreference.PdfDirection;
import org.sejda.model.pdf.viewerpreference.PdfDuplex;
import org.sejda.model.pdf.viewerpreference.PdfNonFullScreenPageMode;
import org.sejda.model.pdf.viewerpreference.PdfPageLayout;
import org.sejda.model.pdf.viewerpreference.PdfPageMode;
import org.sejda.model.pdf.viewerpreference.PdfPrintScaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility methods related to the viewer preferences functionalities.
 * 
 * @author Andrea Vacondio
 * 
 */
public final class ViewerPreferencesUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ViewerPreferencesUtils.class);

    private ViewerPreferencesUtils() {
        // hide utility constructor
    }

    private static final Map<PdfNonFullScreenPageMode, NON_FULL_SCREEN_PAGE_MODE> NFS_MODE_CACHE;
    static {
        Map<PdfNonFullScreenPageMode, NON_FULL_SCREEN_PAGE_MODE> nfsModeCache = new HashMap<PdfNonFullScreenPageMode, NON_FULL_SCREEN_PAGE_MODE>();
        nfsModeCache.put(PdfNonFullScreenPageMode.USE_NONE, PDViewerPreferences.NON_FULL_SCREEN_PAGE_MODE.UseNone);
        nfsModeCache.put(PdfNonFullScreenPageMode.USE_OC, PDViewerPreferences.NON_FULL_SCREEN_PAGE_MODE.UseOC);
        nfsModeCache.put(PdfNonFullScreenPageMode.USE_OUTLINES,
                PDViewerPreferences.NON_FULL_SCREEN_PAGE_MODE.UseOutlines);
        nfsModeCache.put(PdfNonFullScreenPageMode.USE_THUMNS, PDViewerPreferences.NON_FULL_SCREEN_PAGE_MODE.UseThumbs);
        NFS_MODE_CACHE = Collections.unmodifiableMap(nfsModeCache);
    }

    private static final Map<PdfPageLayout, String> LAYOUT_CACHE;
    static {
        Map<PdfPageLayout, String> layoutCache = new HashMap<PdfPageLayout, String>();
        layoutCache.put(PdfPageLayout.SINGLE_PAGE, PDDocumentCatalog.PAGE_LAYOUT_SINGLE_PAGE);
        layoutCache.put(PdfPageLayout.ONE_COLUMN, PDDocumentCatalog.PAGE_LAYOUT_ONE_COLUMN);
        layoutCache.put(PdfPageLayout.TWO_COLUMN_LEFT, PDDocumentCatalog.PAGE_LAYOUT_TWO_COLUMN_LEFT);
        layoutCache.put(PdfPageLayout.TWO_COLUMN_RIGHT, PDDocumentCatalog.PAGE_LAYOUT_TWO_COLUMN_RIGHT);
        layoutCache.put(PdfPageLayout.TWO_PAGE_LEFT, PDDocumentCatalog.PAGE_LAYOUT_TWO_PAGE_LEFT);
        layoutCache.put(PdfPageLayout.TWO_PAGE_RIGHT, PDDocumentCatalog.PAGE_LAYOUT_TWO_PAGE_RIGHT);
        LAYOUT_CACHE = Collections.unmodifiableMap(layoutCache);
    }

    private static final Map<PdfPageMode, String> PAGE_MODE_CACHE;
    static {
        Map<PdfPageMode, String> pageModeCache = new HashMap<PdfPageMode, String>();
        pageModeCache.put(PdfPageMode.USE_NONE, PDDocumentCatalog.PAGE_MODE_USE_NONE);
        pageModeCache.put(PdfPageMode.USE_THUMBS, PDDocumentCatalog.PAGE_MODE_USE_THUMBS);
        pageModeCache.put(PdfPageMode.USE_OUTLINES, PDDocumentCatalog.PAGE_MODE_USE_OUTLINES);
        pageModeCache.put(PdfPageMode.FULLSCREEN, PDDocumentCatalog.PAGE_MODE_FULL_SCREEN);
        pageModeCache.put(PdfPageMode.USE_OC, PDDocumentCatalog.PAGE_MODE_USE_OPTIONAL_CONTENT);
        pageModeCache.put(PdfPageMode.USE_ATTACHMENTS, PDDocumentCatalog.PAGE_MODE_USE_ATTACHMENTS);
        PAGE_MODE_CACHE = Collections.unmodifiableMap(pageModeCache);
    }

    private static final Map<PdfDuplex, DUPLEX> DUPLEX_CACHE;
    static {
        Map<PdfDuplex, DUPLEX> duplexCache = new HashMap<PdfDuplex, DUPLEX>();
        duplexCache.put(PdfDuplex.SIMPLEX, PDViewerPreferences.DUPLEX.Simplex);
        duplexCache.put(PdfDuplex.DUPLEX_FLIP_LONG_EDGE, PDViewerPreferences.DUPLEX.DuplexFlipLongEdge);
        duplexCache.put(PdfDuplex.DUPLEX_FLIP_SHORT_EDGE, PDViewerPreferences.DUPLEX.DuplexFlipShortEdge);
        DUPLEX_CACHE = Collections.unmodifiableMap(duplexCache);
    }

    /**
     * Mapping between Sejda and PDFBox non full screen mode constants
     * 
     * @param nfsMode
     * @return the PDFBox non full screen mode constant.
     */
    public static NON_FULL_SCREEN_PAGE_MODE getNFSMode(PdfNonFullScreenPageMode nfsMode) {
        return NFS_MODE_CACHE.get(nfsMode);
    }

    /**
     * Mapping between Sejda and PDFBox page mode constants.
     * 
     * @param mode
     * @return the PDFBox page mode String constant.
     */
    public static String getPageMode(PdfPageMode mode) {
        return PAGE_MODE_CACHE.get(mode);
    }

    /**
     * Mapping between Sejda and PDFBox page layout constants.
     * 
     * @param layout
     * @return the PDFBox page layout String constant.
     */
    public static String getPageLayout(PdfPageLayout layout) {
        return LAYOUT_CACHE.get(layout);
    }

    /**
     * Mapping between Sejda and PDFBox direction constants.
     * 
     * @param direction
     * @return the PDFBox direction constant
     */
    public static READING_DIRECTION getDirection(PdfDirection direction) {
        if (PdfDirection.RIGHT_TO_LEFT.equals(direction)) {
            return PDViewerPreferences.READING_DIRECTION.R2L;
        }
        return PDViewerPreferences.READING_DIRECTION.L2R;
    }

    /**
     * Mapping between Sejda and PDFBox duplex constants
     * 
     * @param duplex
     * @return the PDFBox duplex constant
     */
    public static DUPLEX getDuplex(PdfDuplex duplex) {
        return DUPLEX_CACHE.get(duplex);
    }

    /**
     * Mapping between Sejda and PDFBox print scaling constants
     * 
     * @param scaling
     * @return the PDFBox print scaling constant
     */
    public static PRINT_SCALING getPrintScaling(PdfPrintScaling scaling) {
        if (PdfPrintScaling.NONE.equals(scaling)) {
            return PDViewerPreferences.PRINT_SCALING.None;
        }
        return PDViewerPreferences.PRINT_SCALING.AppDefault;
    }

    /**
     * Enables the given set of boolean preferences on the given preferences instance and disables the others.
     * 
     * @param preferences
     * @param enabled
     * @throws TaskException
     *             if the given preferences instance is null.
     */
    public static void setBooleanPreferences(PDViewerPreferences preferences, Set<PdfBooleanPreference> enabled)
            throws TaskException {
        if (preferences == null) {
            throw new TaskException("Unable to set preferences on a null instance.");
        }
        for (PdfBooleanPreference current : PdfBooleanPreference.values()) {
            if (enabled.contains(current)) {
                PDFBoxActivableBooleanPreference.valueFromPdfBooleanPreference(current).enable(preferences);
                LOG.trace("{} = enabled.", current);
            } else {
                PDFBoxActivableBooleanPreference.valueFromPdfBooleanPreference(current).disable(preferences);
                LOG.trace("{} = disabled.", current);
            }
        }
    }

    /**
     * enum mapping from Sejda boolean preferences to an enam capable of activating boolean preferences on a PDFBox {@link PDViewerPreferences}.
     * 
     * @author Andrea Vacondio
     * 
     */
    private static enum PDFBoxActivableBooleanPreference {
        HIDE_TOOLBAR(PdfBooleanPreference.HIDE_TOOLBAR) {
            @Override
            void enable(PDViewerPreferences preferences) {
                preferences.setHideToolbar(true);
            }

            @Override
            void disable(PDViewerPreferences preferences) {
                preferences.setHideToolbar(false);
            }
        },
        HIDE_MENUBAR(PdfBooleanPreference.HIDE_MENUBAR) {
            @Override
            void enable(PDViewerPreferences preferences) {
                preferences.setHideMenubar(true);
            }

            @Override
            void disable(PDViewerPreferences preferences) {
                preferences.setHideMenubar(false);
            }
        },
        HIDE_WINDOW_UI(PdfBooleanPreference.HIDE_WINDOW_UI) {
            @Override
            void enable(PDViewerPreferences preferences) {
                preferences.setHideWindowUI(true);
            }

            @Override
            void disable(PDViewerPreferences preferences) {
                preferences.setHideWindowUI(false);
            }
        },
        FIT_WINDOW(PdfBooleanPreference.FIT_WINDOW) {
            @Override
            void enable(PDViewerPreferences preferences) {
                preferences.setFitWindow(true);
            }

            @Override
            void disable(PDViewerPreferences preferences) {
                preferences.setFitWindow(false);
            }
        },
        CENTER_WINDOW(PdfBooleanPreference.CENTER_WINDOW) {
            @Override
            void enable(PDViewerPreferences preferences) {
                preferences.setCenterWindow(true);
            }

            @Override
            void disable(PDViewerPreferences preferences) {
                preferences.setCenterWindow(false);
            }
        },
        DISPLAY_DOC_TITLE(PdfBooleanPreference.DISPLAY_DOC_TITLE) {
            @Override
            void enable(PDViewerPreferences preferences) {
                preferences.setDisplayDocTitle(true);
            }

            @Override
            void disable(PDViewerPreferences preferences) {
                preferences.setDisplayDocTitle(false);
            }
        };

        private PdfBooleanPreference preference;

        private PDFBoxActivableBooleanPreference(PdfBooleanPreference preference) {
            this.preference = preference;
        }

        /**
         * enables the boolean preference on the given preferences object;
         * 
         * @param preferences
         */
        abstract void enable(PDViewerPreferences preferences);

        /**
         * disable the boolean preference on the given preferences object;
         * 
         * @param preferences
         */
        abstract void disable(PDViewerPreferences preferences);

        static PDFBoxActivableBooleanPreference valueFromPdfBooleanPreference(PdfBooleanPreference pref) {
            for (PDFBoxActivableBooleanPreference current : PDFBoxActivableBooleanPreference.values()) {
                if (current.preference == pref) {
                    return current;
                }
            }
            throw new IllegalArgumentException(String.format("No activable preference found for %s", pref));
        }
    }
}
