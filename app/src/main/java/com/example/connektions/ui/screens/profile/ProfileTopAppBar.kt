package com.example.connektions.ui.screens.profile

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults.iconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import com.example.connektions.R
import com.example.connektions.data.model.User

@OptIn(ExperimentalMotionApi::class)
@Composable
fun ProfileTopAppBar(
    lazyScrollState: LazyListState,
    user: User
) {
    val context = LocalContext.current
    val motionSceneContent = remember {
        context.resources.openRawResource(R.raw.wonderful_motion_scene).readBytes().decodeToString()
    }


    val scroll = remember { derivedStateOf { lazyScrollState.firstVisibleItemIndex } }.value
    val progress by animateFloatAsState(
        targetValue = if (scroll in 0..0) 0f else 1f, label = "",
    )
    val motionHeight by animateDpAsState(
        targetValue = if (scroll in 0..0) 159.dp else 58.dp, label = "",
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        MotionLayout(
            motionScene = MotionScene(content = motionSceneContent),
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.Transparent
                )
                .height(motionHeight)
        ) {
            Image(painterResource(id = R.drawable.placeholder_profile_topbar_image),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(
                                Brush.verticalGradient(
                                    0.5f to Color.Transparent, 1f to Color.Black
                                )
                            )
                        }
                    }
                    .layoutId("itmo_image"))
            Image(painterResource(id = R.drawable.placeholder_profile_image),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                        }
                    }
                    .clip(RoundedCornerShape(60.dp))
                    .border(3.dp, Color.White, RoundedCornerShape(60.dp))
                    .size(77.dp)
                    .layoutId("user_image"))
            IconButton(
                onClick = { /*TODO*/ },
                colors = iconButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                ),
                modifier = Modifier
                    .layoutId("back_button"),
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Button"
                )
            }

            IconButton(
                onClick = { /*TODO*/ },
                colors = iconButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                ),
                modifier = Modifier
                    .layoutId("settings_button"),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.profile_settings),
                    contentDescription = "Profile settings button"
                )
            }

            IconButton(
                onClick = { /*TODO*/ },
                colors = iconButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                ),
                modifier = Modifier
                    .layoutId("rewrite_button"),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.profile_rewrite),
                    contentDescription = "Profile edit button"
                )
            }

            Text(
                text = user.name,
                color = Color.White,
                modifier = Modifier
                    .layoutId("username"),
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = stringResource(R.string.user_city_name, user.city),
                color = Color(0xFFE1E2EC),
                modifier = Modifier
                    .layoutId("user_city"),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}
