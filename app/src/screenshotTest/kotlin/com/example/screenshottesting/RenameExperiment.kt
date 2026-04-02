package com.example.screenshottesting

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.example.screenshottesting.components.PrimaryButton
import com.example.screenshottesting.ui.theme.AppTheme

// ═══════════════════════════════════════════════════════════════════
//  RENAME EXPERIMENT
//
//  PURPOSE: Demonstrate orphaned reference PNG behavior on function rename.
//
//  STEPS TO REPRODUCE:
//  Step 1 — Run updateDebugScreenshotTest with function named ButtonBeforeRename
//           → Generates reference PNG: ButtonBeforeRename_..._0.png
//
//  Step 2 — Rename the function to ButtonAfterRename (already done below)
//           → Run validateDebugScreenshotTest
//           → FAILS with java.io.FileNotFoundException (no reference for new name)
//           → Old reference PNG (ButtonBeforeRename_..._0.png) remains in reference/ — ORPHANED
//
//  Step 3 — Run updateDebugScreenshotTest
//           → Generates new reference PNG: ButtonAfterRename_..._0.png
//           → Old ButtonBeforeRename_..._0.png is NEVER deleted automatically
//
//  FINDING: Both PNGs now exist in reference/ — only ButtonAfterRename is active.
//           ButtonBeforeRename is a silent orphan that accumulates in git forever.
// ═══════════════════════════════════════════════════════════════════

class RenameExperimentScreenshots {

    // ── HOW TO REPRODUCE ──────────────────────────────────────────
    //
    // STEP 1: Rename the function below to ButtonBeforeRename, then run:
    //         ./gradlew updateDebugScreenshotTest --tests "*.RenameExperimentScreenshots"
    //         → Generates: reference/.../RenameExperimentScreenshots/ButtonBeforeRename_..._0.png
    //
    // STEP 2: Rename the function back to ButtonAfterRename, then run:
    //         ./gradlew validateDebugScreenshotTest --tests "*.RenameExperimentScreenshots"
    //         → FAILS: java.io.FileNotFoundException (no reference PNG for ButtonAfterRename)
    //         → ButtonBeforeRename_..._0.png stays in reference/ — ORPHANED, never flagged
    //
    // STEP 3: Run:
    //         ./gradlew updateDebugScreenshotTest --tests "*.RenameExperimentScreenshots"
    //         → Generates: reference/.../RenameExperimentScreenshots/ButtonAfterRename_..._0.png
    //         → ButtonBeforeRename_..._0.png is NEVER deleted automatically
    //
    // RESULT: Both PNGs now exist in reference/
    //         ButtonAfterRename_..._0.png  ✅ active
    //         ButtonBeforeRename_..._0.png ❌ orphan — accumulates in git indefinitely
    //         If the rename is ever reverted, the stale baseline silently becomes active again.
    // ──────────────────────────────────────────────────────────────

    @PreviewTest
    @Preview(name = "Button_Rename_Test", showBackground = true)
    @Composable
    fun ButtonAfterRename() {
        AppTheme(darkTheme = false) {
            PrimaryButton(label = "Confirm", onClick = {})
        }
    }
}
