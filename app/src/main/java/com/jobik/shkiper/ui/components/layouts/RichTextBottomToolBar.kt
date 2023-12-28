package com.jobik.shkiper.ui.components.layouts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jobik.shkiper.R
import com.jobik.shkiper.ui.components.buttons.RichTextStyleButton
import com.jobik.shkiper.ui.theme.CustomTheme
import com.mohamedrejeb.richeditor.model.RichTextState

@Composable
fun RichTextBottomToolBar(
    modifier: Modifier = Modifier,
    state: RichTextState,
    onClose: () -> Unit
) {
    val MainHeaderSize = 22.sp
    val SecondaryHeaderSize = 19.sp
    val PlainTextSize = 16.sp

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .height(56.dp),
        horizontalArrangement = Arrangement.spacedBy(
            space = 12.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RichTextStyleButton(
            isActive = state.currentSpanStyle.fontSize == MainHeaderSize,
            onClick = { state.toggleSpanStyle(SpanStyle(fontSize = if (state.currentSpanStyle.fontSize == MainHeaderSize) PlainTextSize else MainHeaderSize)) },
            icon = R.drawable.format_h1_fill0_wght400_grad0_opsz24,
            contentDescription = ""
        )
        RichTextStyleButton(
            isActive = state.currentSpanStyle.fontSize == SecondaryHeaderSize,
            onClick = { state.toggleSpanStyle(SpanStyle(fontSize = if (state.currentSpanStyle.fontSize == SecondaryHeaderSize) PlainTextSize else SecondaryHeaderSize)) },
            icon = R.drawable.format_h2_fill0_wght400_grad0_opsz24,
            contentDescription = ""
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = 6.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RichTextStyleButton(
                isActive = state.currentSpanStyle.fontSize == PlainTextSize || state.currentSpanStyle.fontSize == TextUnit.Unspecified,
                onClick = { state.toggleSpanStyle(SpanStyle(fontSize = PlainTextSize)) },
                icon = R.drawable.match_case_fill0_wght400_grad0_opsz24,
                contentDescription = ""
            )
            Divider(
                modifier = Modifier
                    .heightIn(30.dp)
                    .width(1.dp),
                color = CustomTheme.colors.textSecondary.copy(alpha = .2f)
            )
            RichTextStyleButton(
                isActive = state.currentSpanStyle.fontWeight == FontWeight.Bold,
                onClick = { state.toggleSpanStyle(SpanStyle(fontWeight = FontWeight.Bold)) },
                icon = R.drawable.format_bold_fill0_wght400_grad0_opsz24,
                contentDescription = ""
            )
        }
        RichTextStyleButton(
            isActive = state.currentSpanStyle.fontStyle == FontStyle.Italic,
            onClick = { state.toggleSpanStyle(SpanStyle(fontStyle = FontStyle.Italic)) },
            icon = R.drawable.format_italic_fill0_wght400_grad0_opsz24,
            contentDescription = ""
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = 6.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RichTextStyleButton(
                isActive = state.currentSpanStyle.textDecoration == TextDecoration.Underline,
                onClick = { state.toggleSpanStyle(SpanStyle(textDecoration = TextDecoration.Underline)) },
                icon = R.drawable.format_underlined_fill0_wght400_grad0_opsz24,
                contentDescription = ""
            )
            Divider(
                modifier = Modifier
                    .heightIn(30.dp)
                    .width(1.dp),
                color = CustomTheme.colors.textSecondary.copy(alpha = .2f)
            )
            RichTextStyleButton(
                isActive = false,
                onClick = {
                    state.removeParagraphStyle(state.currentParagraphStyle)
                    state.removeSpanStyle(state.currentSpanStyle)
                    state.removeCode()
                },
                icon = R.drawable.format_clear_fill0_wght400_grad0_opsz24,
                contentDescription = ""
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            RichTextStyleButton(
                isActive = false,
                onClick = onClose,
                icon = R.drawable.close_fill0_wght400_grad0_opsz24,
                contentDescription = ""
            )
        }

    }
}
