package com.example.screenshottesting

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.example.screenshottesting.components.AppTopBar
import com.example.screenshottesting.components.ContentCard
import com.example.screenshottesting.components.PrimaryButton
import com.example.screenshottesting.components.SecondaryButton
import com.example.screenshottesting.ui.theme.AppTheme

// ─────────────────────────────────────────────────────────────────
//  HOW TO RUN
//  Module level:   ./gradlew validateDebugScreenshotTest
//  Class level:    ./gradlew validateDebugScreenshotTest --tests "*.ButtonPreviewScreenshots"
//  Function level: ./gradlew validateDebugScreenshotTest --tests "*.ButtonPreviewScreenshots.ButtonPrimaryLightMode"
//
//  Update references: ./gradlew updateDebugScreenshotTest
//  HTML report:       app/build/reports/screenshotTest/preview/debug/index.html
//
//  DO NOT rename any @PreviewTest function — renames orphan the reference PNG.
// ─────────────────────────────────────────────────────────────────

// ═══════════════════════════════════════════════════════════════════
//  ButtonPreviewScreenshots — 13 tests
//
//  Standard:  Primary light/dark, Secondary light/dark, font scales
//  Edge cases:
//    • Disabled state     — button renders muted/grayed in both themes
//    • Long label         — verifies text doesn't overflow the button bounds
//    • Secondary at 1.5×  — outlined stroke + label reflow under large font
//  Inspired by NiA (Roborazzi → translated to @PreviewTest):
//    • Leading icon       — icon+text layout inside filled button (light + dark)
//    • Huge font (2.0×)   — NiA tests this specifically; more extreme than 1.5×
//    • RTL locale (ar)    — text direction flips; icon stays on leading (visual) side
// ═══════════════════════════════════════════════════════════════════
class ButtonPreviewScreenshots {

    // ── Standard variations ───────────────────────────────────────

    @PreviewTest
    @Preview(name = "Button_Primary_LightMode", showBackground = true)
    @Composable
    fun ButtonPrimaryLightMode() {
        AppTheme(darkTheme = false) {
            PrimaryButton(label = "Confirm", onClick = {})
        }
    }

    @PreviewTest
    @Preview(
        name = "Button_Primary_DarkMode",
        showBackground = true,
        uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL
    )
    @Composable
    fun ButtonPrimaryDarkMode() {
        AppTheme(darkTheme = true) {
            PrimaryButton(label = "Confirm", onClick = {})
        }
    }

    @PreviewTest
    @Preview(name = "Button_Secondary_LightMode", showBackground = true)
    @Composable
    fun ButtonSecondaryLightMode() {
        AppTheme(darkTheme = false) {
            SecondaryButton(label = "Cancel", onClick = {})
        }
    }

    @PreviewTest
    @Preview(
        name = "Button_Secondary_DarkMode",
        showBackground = true,
        uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL
    )
    @Composable
    fun ButtonSecondaryDarkMode() {
        AppTheme(darkTheme = true) {
            SecondaryButton(label = "Cancel", onClick = {})
        }
    }

    @PreviewTest
    @Preview(name = "Button_Primary_LargeFontScale", showBackground = true, fontScale = 1.5f)
    @Composable
    fun ButtonPrimaryLargeFontScale() {
        AppTheme(darkTheme = false) {
            PrimaryButton(label = "Confirm", onClick = {})
        }
    }

    @PreviewTest
    @Preview(name = "Button_Primary_SmallFontScale", showBackground = true, fontScale = 0.85f)
    @Composable
    fun ButtonPrimarySmallFontScale() {
        AppTheme(darkTheme = false) {
            PrimaryButton(label = "Confirm", onClick = {})
        }
    }

    // ── Edge cases ────────────────────────────────────────────────

    // Disabled — primary fill becomes surface-variant; text becomes on-surface/38%
    @PreviewTest
    @Preview(name = "Button_Primary_Disabled_LightMode", showBackground = true)
    @Composable
    fun ButtonPrimaryDisabledLightMode() {
        AppTheme(darkTheme = false) {
            PrimaryButton(label = "Confirm", onClick = {}, enabled = false)
        }
    }

    // Disabled in dark mode — verifies muted colour is visible against dark surface
    @PreviewTest
    @Preview(
        name = "Button_Primary_Disabled_DarkMode",
        showBackground = true,
        uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL
    )
    @Composable
    fun ButtonPrimaryDisabledDarkMode() {
        AppTheme(darkTheme = true) {
            PrimaryButton(label = "Confirm", onClick = {}, enabled = false)
        }
    }

    // Long label — catches unintended text clipping or button width explosion
    @PreviewTest
    @Preview(name = "Button_Primary_LongLabel_LightMode", showBackground = true)
    @Composable
    fun ButtonPrimaryLongLabelLightMode() {
        AppTheme(darkTheme = false) {
            PrimaryButton(label = "Submit Application Form", onClick = {})
        }
    }

    // Secondary at large font — outlined stroke must remain intact; label may reflow
    @PreviewTest
    @Preview(name = "Button_Secondary_LargeFontScale", showBackground = true, fontScale = 1.5f)
    @Composable
    fun ButtonSecondaryLargeFontScale() {
        AppTheme(darkTheme = false) {
            SecondaryButton(label = "Cancel", onClick = {})
        }
    }

    // ── NiA-inspired ──────────────────────────────────────────────

    // Leading icon light — icon and label must be horizontally aligned with correct spacing
    @PreviewTest
    @Preview(name = "Button_Primary_WithIcon_LightMode", showBackground = true)
    @Composable
    fun ButtonPrimaryWithIconLightMode() {
        AppTheme(darkTheme = false) {
            PrimaryButton(label = "Add Item", onClick = {}, leadingIcon = Icons.Filled.Add)
        }
    }

    // Leading icon dark — icon tint must follow onPrimary colour in dark theme
    @PreviewTest
    @Preview(
        name = "Button_Primary_WithIcon_DarkMode",
        showBackground = true,
        uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL
    )
    @Composable
    fun ButtonPrimaryWithIconDarkMode() {
        AppTheme(darkTheme = true) {
            PrimaryButton(label = "Add Item", onClick = {}, leadingIcon = Icons.Filled.Add)
        }
    }

    // Huge font 2.0× (NiA pattern) — more extreme than 1.5×; label must not clip
    @PreviewTest
    @Preview(name = "Button_Primary_HugeFontScale", showBackground = true, fontScale = 2.0f)
    @Composable
    fun ButtonPrimaryHugeFontScale() {
        AppTheme(darkTheme = false) {
            PrimaryButton(label = "Confirm", onClick = {})
        }
    }

    // RTL locale (NiA uses DeviceConfigurationOverride; @Preview locale param achieves same)
    // Text draws right-to-left; icon stays on the visual leading side
    @PreviewTest
    @Preview(name = "Button_Primary_RtlLocale", showBackground = true, locale = "ar")
    @Composable
    fun ButtonPrimaryRtlLocale() {
        AppTheme(darkTheme = false) {
            PrimaryButton(label = "تأكيد", onClick = {}, leadingIcon = Icons.Filled.Add)
        }
    }
}

// ═══════════════════════════════════════════════════════════════════
//  CardPreviewScreenshots — 10 tests
//
//  Standard:  Content light/dark, Loading light/dark, Content 1.5× font
//  Edge cases:
//    • No icon            — layout should left-align without the icon spacer
//    • Long text          — title/subtitle wrapping, card height expansion
//    • Loading at 1.5×    — skeleton boxes are dp-sized, font scale is irrelevant;
//                           confirms skeleton doesn't accidentally scale with font
//  Inspired by NiA:
//    • Narrow width       — card on 280dp screen; text must wrap not clip
//    • RTL locale (ar)    — icon flips to right; text reads right-to-left
// ═══════════════════════════════════════════════════════════════════
class CardPreviewScreenshots {

    // ── Standard variations ───────────────────────────────────────

    @PreviewTest
    @Preview(name = "Card_Content_LightMode", showBackground = true)
    @Composable
    fun CardContentLightMode() {
        AppTheme(darkTheme = false) {
            ContentCard(
                title = "Feature Title",
                subtitle = "A brief description of this feature card",
                icon = Icons.Filled.Star,
                isLoading = false
            )
        }
    }

    @PreviewTest
    @Preview(
        name = "Card_Content_DarkMode",
        showBackground = true,
        uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL
    )
    @Composable
    fun CardContentDarkMode() {
        AppTheme(darkTheme = true) {
            ContentCard(
                title = "Feature Title",
                subtitle = "A brief description of this feature card",
                icon = Icons.Filled.Star,
                isLoading = false
            )
        }
    }

    @PreviewTest
    @Preview(name = "Card_Loading_LightMode", showBackground = true)
    @Composable
    fun CardLoadingLightMode() {
        AppTheme(darkTheme = false) {
            ContentCard(title = "", subtitle = "", icon = null, isLoading = true)
        }
    }

    @PreviewTest
    @Preview(
        name = "Card_Loading_DarkMode",
        showBackground = true,
        uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL
    )
    @Composable
    fun CardLoadingDarkMode() {
        AppTheme(darkTheme = true) {
            ContentCard(title = "", subtitle = "", icon = null, isLoading = true)
        }
    }

    @PreviewTest
    @Preview(name = "Card_Content_LargeFontScale", showBackground = true, fontScale = 1.5f)
    @Composable
    fun CardContentLargeFontScale() {
        AppTheme(darkTheme = false) {
            ContentCard(
                title = "Feature Title",
                subtitle = "A brief description of this feature card",
                icon = Icons.Filled.Star,
                isLoading = false
            )
        }
    }

    // ── Edge cases ────────────────────────────────────────────────

    // No icon — Row should not leave an orphaned Spacer on the left
    @PreviewTest
    @Preview(name = "Card_Content_NoIcon_LightMode", showBackground = true)
    @Composable
    fun CardContentNoIconLightMode() {
        AppTheme(darkTheme = false) {
            ContentCard(
                title = "No Icon Card",
                subtitle = "Layout should stay left-aligned without extra leading space",
                icon = null,
                isLoading = false
            )
        }
    }

    // Long text — verifies title/subtitle wrap correctly; card must expand vertically
    @PreviewTest
    @Preview(name = "Card_Content_LongText_LightMode", showBackground = true)
    @Composable
    fun CardContentLongTextLightMode() {
        AppTheme(darkTheme = false) {
            ContentCard(
                title = "This Is A Considerably Long Card Title That May Wrap",
                subtitle = "This subtitle is intentionally verbose to verify that the card body expands rather than clips the overflowing text content",
                icon = Icons.Filled.Star,
                isLoading = false
            )
        }
    }

    // Loading at 1.5× font — skeleton uses dp sizes, NOT sp; dimensions must stay fixed
    @PreviewTest
    @Preview(name = "Card_Loading_LargeFontScale", showBackground = true, fontScale = 1.5f)
    @Composable
    fun CardLoadingLargeFontScale() {
        AppTheme(darkTheme = false) {
            ContentCard(title = "", subtitle = "", icon = null, isLoading = true)
        }
    }

    // ── NiA-inspired ──────────────────────────────────────────────

    // Narrow screen (280dp) — NiA tests compact/medium/expanded widths;
    // card's fillMaxWidth should fill the constrained canvas; text must wrap
    @PreviewTest
    @Preview(name = "Card_Content_NarrowWidth", showBackground = true, widthDp = 280)
    @Composable
    fun CardContentNarrowWidth() {
        AppTheme(darkTheme = false) {
            ContentCard(
                title = "Feature Title",
                subtitle = "Description text on a narrow screen",
                icon = Icons.Filled.Star,
                isLoading = false
            )
        }
    }

    // RTL locale — icon must appear on the right; subtitle aligns right-to-left
    @PreviewTest
    @Preview(name = "Card_Content_RtlLocale", showBackground = true, locale = "ar")
    @Composable
    fun CardContentRtlLocale() {
        AppTheme(darkTheme = false) {
            ContentCard(
                title = "عنوان البطاقة",
                subtitle = "وصف موجز لمحتوى هذه البطاقة",
                icon = Icons.Filled.Star,
                isLoading = false
            )
        }
    }
}

// ═══════════════════════════════════════════════════════════════════
//  TopBarPreviewScreenshots — 7 tests
//
//  Standard:  Light, Dark, Large font
//  Edge cases:
//    • No back arrow      — navigation icon area must collapse cleanly
//    • Long title         — title should truncate with ellipsis, not push other elements
//  Inspired by NiA:
//    • Huge font (2.0×)   — NiA has a dedicated topAppBar_hugeFont test
//    • RTL locale (ar)    — back arrow flips to point right; title aligns right
// ═══════════════════════════════════════════════════════════════════
class TopBarPreviewScreenshots {

    // ── Standard variations ───────────────────────────────────────

    @PreviewTest
    @Preview(name = "TopBar_LightMode", showBackground = true)
    @Composable
    fun TopBarLightMode() {
        AppTheme(darkTheme = false) {
            AppTopBar(title = "Screen Title", onNavigateBack = {})
        }
    }

    @PreviewTest
    @Preview(
        name = "TopBar_DarkMode",
        showBackground = true,
        uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL
    )
    @Composable
    fun TopBarDarkMode() {
        AppTheme(darkTheme = true) {
            AppTopBar(title = "Screen Title", onNavigateBack = {})
        }
    }

    @PreviewTest
    @Preview(name = "TopBar_LargeFontScale", showBackground = true, fontScale = 1.5f)
    @Composable
    fun TopBarLargeFontScale() {
        AppTheme(darkTheme = false) {
            AppTopBar(title = "Screen Title", onNavigateBack = {})
        }
    }

    // ── Edge cases ────────────────────────────────────────────────

    // No back arrow — title should shift to the far left; no empty icon slot
    @PreviewTest
    @Preview(name = "TopBar_NoBackArrow_LightMode", showBackground = true)
    @Composable
    fun TopBarNoBackArrowLightMode() {
        AppTheme(darkTheme = false) {
            AppTopBar(title = "Home", onNavigateBack = null)
        }
    }

    // Long title — Material3 TopAppBar truncates with ellipsis; back arrow must stay visible
    @PreviewTest
    @Preview(name = "TopBar_LongTitle_LightMode", showBackground = true)
    @Composable
    fun TopBarLongTitleLightMode() {
        AppTheme(darkTheme = false) {
            AppTopBar(
                title = "This Is A Very Long Screen Title That Should Truncate",
                onNavigateBack = {}
            )
        }
    }

    // ── NiA-inspired ──────────────────────────────────────────────

    // Huge font 2.0× — NiA's topAppBar_hugeFont test uses fontScale=2; title must not
    // overlap the back arrow or be cut off vertically
    @PreviewTest
    @Preview(name = "TopBar_HugeFontScale", showBackground = true, fontScale = 2.0f)
    @Composable
    fun TopBarHugeFontScale() {
        AppTheme(darkTheme = false) {
            AppTopBar(title = "Screen Title", onNavigateBack = {})
        }
    }

    // RTL locale — back arrow (AutoMirrored) must flip to point right (→);
    // title text aligns to the right edge
    @PreviewTest
    @Preview(name = "TopBar_RtlLocale", showBackground = true, locale = "ar")
    @Composable
    fun TopBarRtlLocale() {
        AppTheme(darkTheme = false) {
            AppTopBar(title = "عنوان الشاشة", onNavigateBack = {})
        }
    }
}
