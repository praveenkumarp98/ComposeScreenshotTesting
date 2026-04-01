package com.example.screenshottesting

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.example.screenshottesting.components.PrimaryButton
import com.example.screenshottesting.ui.theme.AppTheme

// ═══════════════════════════════════════════════════════════════════
//  FEATURE: Multi-Preview Annotation Expansion
//
//  com.android.compose.screenshot (v0.0.1-alpha13) natively expands
//  nested @Preview annotations inside a custom annotation — exactly
//  as the IDE preview panel does.
//
//  This means a single @PreviewTest function annotated with a custom
//  multi-preview annotation generates one individually named,
//  individually diffable reference PNG per @Preview inside it.
//
//  BENEFIT — Boilerplate reduction:
//    Before: 1 function × N variants = N @PreviewTest functions
//    After:  1 function + 1 shared annotation = N reference images
//
//  Boilerplate now grows as O(components + variants) instead of
//  O(components × variants). Adding a new variant means updating
//  one annotation, not every component's test class.
//
//  CONFIRMED: Plugin produces 2 PNGs from ButtonMultiPreview below:
//    ButtonMultiPreview_Light_<hash>_0.png
//    ButtonMultiPreview_Dark_<hash>_0.png
// ═══════════════════════════════════════════════════════════════════

/**
 * Reusable multi-preview annotation.
 * Stack any @Preview variants here — every @PreviewTest function
 * that uses this annotation gets one reference image per entry.
 */
@Preview(name = "Light", showBackground = true)
@Preview(
    name = "Dark",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL
)
annotation class LightDarkPreview

class MultiPreviewExperimentScreenshots {

    /**
     * One function → two reference PNGs (Light + Dark).
     * The plugin reads the @Preview entries inside @LightDarkPreview
     * and renders each independently, same as the IDE preview panel.
     */
    @PreviewTest
    @LightDarkPreview
    @Composable
    fun ButtonMultiPreview() {
        AppTheme {
            PrimaryButton(label = "Confirm", onClick = {})
        }
    }
}
