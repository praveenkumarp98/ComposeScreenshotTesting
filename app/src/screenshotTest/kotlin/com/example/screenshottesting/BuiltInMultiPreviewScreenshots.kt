package com.example.screenshottesting

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.android.tools.screenshot.PreviewTest
import com.example.screenshottesting.components.AppTopBar
import com.example.screenshottesting.components.ContentCard
import com.example.screenshottesting.components.PrimaryButton
import com.example.screenshottesting.components.SecondaryButton
import com.example.screenshottesting.ui.theme.AppTheme

// ═══════════════════════════════════════════════════════════════════
//  BUILT-IN MULTI-PREVIEW ANNOTATIONS
//
//  Demonstrates all 4 Compose built-in multi-preview annotations
//  used with @PreviewTest. Each annotation expands into N reference
//  images from a single function — same as custom annotations.
//
//  @PreviewLightDark   → 2  PNGs  (Light, Dark)
//  @PreviewFontScale   → 7  PNGs  (85%, 100%, 115%, 130%, 150%, 180%, 200%)
//  @PreviewScreenSizes → 5  PNGs  (Phone, Phone Landscape, Foldable, Tablet, Desktop)
//  @PreviewDynamicColors → 4 PNGs (Red, Blue, Green, Yellow wallpaper seeds)
//
//  NOTE — @PreviewDynamicColors:
//    AppTheme uses static lightColorScheme / darkColorScheme.
//    Dynamic color requires runtime wallpaper access (unavailable in
//    Layoutlib). All 4 wallpaper variants render identically using
//    the static Purple palette — by design, not a bug.
//    To make dynamic color work: pass isDynamicColor=true to AppTheme
//    and call dynamicColorScheme(context) at runtime only.
//
//  NOTE — Stacking multiple annotations (Section 2):
//    Stacking is ADDITIVE, not combinatorial.
//    @PreviewLightDark + @PreviewFontScale = 2+7 = 9 PNGs, NOT 2×7=14.
//    @PreviewLightDark + @PreviewFontScale + @PreviewScreenSizes + @PreviewDynamicColors
//    = 2 + 7 + 5 + 4 = 18 PNGs per function
//    Each annotation renders its own variants independently.
//
//  Update baselines:  ./gradlew updateDebugScreenshotTest
//  Validate:          ./gradlew validateDebugScreenshotTest
//  HTML report:       app/build/reports/screenshotTest/preview/debug/index.html
// ═══════════════════════════════════════════════════════════════════

// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
//  SECTION 1 — Each annotation demonstrated individually
// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

// ── @PreviewLightDark (2 PNGs per function) ───────────────────────
// Generates: Light + Dark.
// AppTheme reads isSystemInDarkTheme() so uiMode from @Preview is
// picked up automatically — no need to pass darkTheme manually.
class PreviewLightDarkScreenshots {

    @PreviewTest
    @PreviewLightDark
    @Composable
    fun PrimaryButtonLightDark() {
        AppTheme {
            PrimaryButton(label = "Confirm", onClick = {})
        }
    }

    @PreviewTest
    @PreviewLightDark
    @Composable
    fun SecondaryButtonLightDark() {
        AppTheme {
            SecondaryButton(label = "Cancel", onClick = {})
        }
    }

    @PreviewTest
    @PreviewLightDark
    @Composable
    fun ContentCardLightDark() {
        AppTheme {
            ContentCard(
                title = "Feature Title",
                subtitle = "A brief description of this feature card",
                icon = Icons.Filled.Star,
                isLoading = false
            )
        }
    }

    @PreviewTest
    @PreviewLightDark
    @Composable
    fun AppTopBarLightDark() {
        AppTheme {
            AppTopBar(title = "Screen Title", onNavigateBack = {})
        }
    }
}

// ── @PreviewFontScale (7 PNGs per function) ──────────────────────
// Generates: 85%, 100%, 115%, 130%, 150%, 180%, 200% font scales.
// Best demonstrated on text-heavy components to catch label reflow
// and overflow at extreme scales.
class PreviewFontScaleScreenshots {

    @PreviewTest
    @PreviewFontScale
    @Composable
    fun PrimaryButtonFontScales() {
        AppTheme {
            PrimaryButton(label = "Confirm", onClick = {})
        }
    }

    @PreviewTest
    @PreviewFontScale
    @Composable
    fun PrimaryButtonWithIconFontScales() {
        AppTheme {
            PrimaryButton(label = "Add Item", onClick = {}, leadingIcon = Icons.Filled.Add)
        }
    }

    @PreviewTest
    @PreviewFontScale
    @Composable
    fun ContentCardFontScales() {
        AppTheme {
            ContentCard(
                title = "Feature Title",
                subtitle = "A brief description of this feature card",
                icon = Icons.Filled.Star,
                isLoading = false
            )
        }
    }

    @PreviewTest
    @PreviewFontScale
    @Composable
    fun AppTopBarFontScales() {
        AppTheme {
            AppTopBar(title = "Screen Title", onNavigateBack = {})
        }
    }
}

// ── @PreviewScreenSizes (5 PNGs per function) ─────────────────────
// Generates: Phone, Phone Landscape, Foldable, Tablet, Desktop.
// Most impactful on components that use fillMaxWidth — ContentCard
// stretches visibly across screen widths. Buttons wrap in place.
class PreviewScreenSizesScreenshots {

    @PreviewTest
    @PreviewScreenSizes
    @Composable
    fun PrimaryButtonScreenSizes() {
        AppTheme {
            PrimaryButton(label = "Confirm", onClick = {})
        }
    }

    @PreviewTest
    @PreviewScreenSizes
    @Composable
    fun ContentCardScreenSizes() {
        AppTheme {
            ContentCard(
                title = "Feature Title",
                subtitle = "A brief description of this feature card",
                icon = Icons.Filled.Star,
                isLoading = false
            )
        }
    }

    @PreviewTest
    @PreviewScreenSizes
    @Composable
    fun ContentCardLoadingScreenSizes() {
        AppTheme {
            ContentCard(title = "", subtitle = "", icon = null, isLoading = true)
        }
    }

    @PreviewTest
    @PreviewScreenSizes
    @Composable
    fun AppTopBarScreenSizes() {
        AppTheme {
            AppTopBar(title = "Screen Title", onNavigateBack = {})
        }
    }
}

// ── @PreviewDynamicColors (4 PNGs per function) ───────────────────
// Generates: Red / Blue / Green / Yellow wallpaper-seeded color schemes.
// LIMITATION: AppTheme uses static lightColorScheme/darkColorScheme.
// All 4 variants render identically (same Purple40 primary color).
// To observe real dynamic color differences, AppTheme would need to
// call dynamicColorScheme(LocalContext.current) — unavailable in Layoutlib.
class PreviewDynamicColorsScreenshots {

    @PreviewTest
    @PreviewDynamicColors
    @Composable
    fun PrimaryButtonDynamicColors() {
        AppTheme {
            PrimaryButton(label = "Confirm", onClick = {})
        }
    }

    @PreviewTest
    @PreviewDynamicColors
    @Composable
    fun ContentCardDynamicColors() {
        AppTheme {
            ContentCard(
                title = "Feature Title",
                subtitle = "A brief description of this feature card",
                icon = Icons.Filled.Star,
                isLoading = false
            )
        }
    }

    @PreviewTest
    @PreviewDynamicColors
    @Composable
    fun AppTopBarDynamicColors() {
        AppTheme {
            AppTopBar(title = "Screen Title", onNavigateBack = {})
        }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
//  SECTION 2 — All 4 annotations stacked (additive, not combinatorial)
//
//  Total PNGs per function = 2 + 7 + 5 + 4 = 18
//  NOT 2 × 7 × 5 × 4 = 280 (that would be combinatorial)
// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
class AllBuiltInAnnotationsScreenshots {

    @PreviewTest
    @PreviewLightDark
    @PreviewFontScale
    @PreviewScreenSizes
    @PreviewDynamicColors
    @Composable
    fun PrimaryButtonAllAnnotations() {
        AppTheme {
            PrimaryButton(label = "Confirm", onClick = {})
        }
    }

    @PreviewTest
    @PreviewLightDark
    @PreviewFontScale
    @PreviewScreenSizes
    @PreviewDynamicColors
    @Composable
    fun ContentCardAllAnnotations() {
        AppTheme {
            ContentCard(
                title = "Feature Title",
                subtitle = "A brief description of this feature card",
                icon = Icons.Filled.Star,
                isLoading = false
            )
        }
    }
}
