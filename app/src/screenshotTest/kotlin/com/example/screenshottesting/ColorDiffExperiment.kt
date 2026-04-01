package com.example.screenshottesting

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.tools.screenshot.PreviewTest
import com.example.screenshottesting.components.PrimaryButton
import com.example.screenshottesting.ui.theme.AppTheme

// ═══════════════════════════════════════════════════════════════════
//  COLOR & SIZE DIFF EXPERIMENT — FINDINGS
//
//  ┌─────────────────────────────────────────────────────────────────┐
//  │  DIFF IMAGE PRODUCED (test fails + diff PNG generated)          │
//  ├─────────────────────────────────────────────────────────────────┤
//  │  A — Obvious color change (Red → Blue)                          │
//  │  B — Subtle color/hue shift (0xFFE040FB → 0xFF7C4DFF)           │
//  │  C — Single RGB channel step (+1) on theme primary color        │
//  │  F — Color change in dark-mode context                          │
//  └─────────────────────────────────────────────────────────────────┘
//
//  ┌─────────────────────────────────────────────────────────────────┐
//  │  FAIL BUT NO DIFF IMAGE (image dimensions changed)              │
//  ├─────────────────────────────────────────────────────────────────┤
//  │  D — Button width shrinks via padding reduction                 │
//  │      ref=426×210  rendered=352×210  (different width)           │
//  │  E — Button width shrinks via explicit .width() modifier        │
//  │      ref=609×210  rendered=504×210  (different width)           │
//  │                                                                 │
//  │  WHY: When image dimensions differ, the engine cannot do a      │
//  │  pixel-level overlay diff. It throws an ImageComparison error   │
//  │  but skips diff PNG generation entirely.                        │
//  └─────────────────────────────────────────────────────────────────┘
//
//  ┌─────────────────────────────────────────────────────────────────┐
//  │  NO DIFF IMAGE, TEST PASSES (blind spots)                       │
//  ├─────────────────────────────────────────────────────────────────┤
//  │  G — No change at all (trivial baseline match)                  │
//  │  H — Color change on fully-transparent composable (alpha=0)     │
//  │      Both Red and Blue at alpha=0 render as identical pixels    │
//  │  J — Color change on disabled button                            │
//  │      Material3 ignores containerColor when enabled=false        │
//  │      Both Red and Blue render with the same disabledContainerColor│
//  │  K — Color.Red vs Color(0xFFFF0000) — identical pixel values    │
//  │  L — showBackground=false, composable itself unchanged          │
//  └─────────────────────────────────────────────────────────────────┘
//
//  Run update to reset baselines:  ./gradlew updateDebugScreenshotTest
//  Run validate to test mutations: ./gradlew validateDebugScreenshotTest
//                                    --tests "*.ColorDiffExperimentScreenshots"
//  Diff images: app/build/outputs/screenshotTest-results/preview/debug/diffs/
// ═══════════════════════════════════════════════════════════════════

class ColorDiffExperimentScreenshots {

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    //  SECTION 1 — DIFF IMAGE PRODUCED on color change
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    // [A] Obvious: mutate Color.Red → Color.Blue
    @PreviewTest
    @Preview(name = "A_Color_Obvious", showBackground = true)
    @Composable
    fun ScenarioA_ObviousColorChange() {
        AppTheme {
            Button(
                onClick = {},
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) { Text("Confirm") }
        }
    }

    // [B] Subtle hue shift: mutate Color(0xFFE040FB) → Color(0xFF7C4DFF)
    @PreviewTest
    @Preview(name = "B_Color_Subtle", showBackground = true)
    @Composable
    fun ScenarioB_SubtleColorChange() {
        AppTheme {
            Button(
                onClick = {},
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE040FB))
            ) { Text("Confirm") }
        }
    }

    // [C] Single RGB channel +1: mutate Color.kt Purple40 0xFF6650A4 → 0xFF6651A4
    @PreviewTest
    @Preview(name = "C_Color_SingleChannelStep", showBackground = true)
    @Composable
    fun ScenarioC_SingleChannelStep() {
        AppTheme {
            PrimaryButton(label = "Confirm", onClick = {})
        }
    }

    // [F] Dark mode color change: mutate Color(0xFFE040FB) → Color(0xFF7C4DFF)
    @PreviewTest
    @Preview(
        name = "F_Color_DarkModeOverride",
        showBackground = true,
        uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL
    )
    @Composable
    fun ScenarioF_DarkModeColorChange() {
        AppTheme(darkTheme = true) {
            Button(
                onClick = {},
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE040FB))
            ) { Text("Confirm") }
        }
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    //  SECTION 2 — FAIL BUT NO DIFF IMAGE (dimension mismatch)
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    // [D] Padding reduction: mutate horizontal = 16.dp → 2.dp
    // ref=426×210  rendered=352×210 after mutation → dimensions differ → no diff PNG
    @PreviewTest
    @Preview(name = "D_Size_PaddingChange", showBackground = true)
    @Composable
    fun ScenarioD_PaddingChange() {
        AppTheme {
            PrimaryButton(
                label = "Confirm",
                onClick = {},
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }

    // [E] Explicit width: mutate .width(200.dp) → .width(160.dp)
    // ref=609×210  rendered=504×210 after mutation → dimensions differ → no diff PNG
    @PreviewTest
    @Preview(name = "E_Size_ExplicitWidth", showBackground = true)
    @Composable
    fun ScenarioE_ExplicitWidthChange() {
        AppTheme {
            Button(
                onClick = {},
                modifier = Modifier
                    .padding(16.dp)
                    .width(200.dp)
            ) { Text("Confirm") }
        }
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    //  SECTION 3 — NO DIFF IMAGE, TEST PASSES (blind spots)
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    // [G] No change → trivially passes
    @PreviewTest
    @Preview(name = "G_Color_NoChange", showBackground = true)
    @Composable
    fun ScenarioG_NoChange() {
        AppTheme {
            Button(
                onClick = {},
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009688))
            ) { Text("Confirm") }
        }
    }

    // [H] Fully transparent composable — color change is invisible
    // Mutate: Color.Red.copy(alpha=0f) → Color.Blue.copy(alpha=0f) → no visible pixels change
    @PreviewTest
    @Preview(name = "H_Color_FullyTransparent", showBackground = true)
    @Composable
    fun ScenarioH_TransparentComposable() {
        AppTheme {
            Button(
                onClick = {},
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red.copy(alpha = 0f)
                )
            ) { Text("") }
        }
    }

    // [J] Disabled button — Material3 overrides containerColor with disabledContainerColor
    // Mutate: containerColor = Color.Red → Color.Blue (both ignored when enabled=false)
    @PreviewTest
    @Preview(name = "J_Color_DisabledOverride", showBackground = true)
    @Composable
    fun ScenarioJ_DisabledColorOverride() {
        AppTheme {
            Button(
                onClick = {},
                enabled = false,
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) { Text("Confirm") }
        }
    }

    // [K] Equivalent color representations — same pixel value, different syntax
    // Mutate: Color.Red → Color(0xFFFF0000) → identical render output
    @PreviewTest
    @Preview(name = "K_Color_EquivalentRepresentation", showBackground = true)
    @Composable
    fun ScenarioK_EquivalentColor() {
        AppTheme {
            Button(
                onClick = {},
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) { Text("Confirm") }
        }
    }

    // [L] showBackground=false — background not included in PNG
    // Composable unchanged; only canvas background would differ if showBackground toggled
    @PreviewTest
    @Preview(name = "L_Color_NoBackground", showBackground = false)
    @Composable
    fun ScenarioL_NoBackground() {
        AppTheme {
            Button(
                onClick = {},
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009688))
            ) { Text("Confirm") }
        }
    }
}
