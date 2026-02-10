package ru.laba

import android.app.LocaleConfig
import android.content.pm.ConfigurationInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.laba.ui.theme.LabaTheme

class MainActivity : ComponentActivity() {
    var isTablet : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            isTablet = LocalContext.current.resources.getBoolean(R.bool.isTablet)
            InitLayout(
                orientation=resources.configuration.orientation,
                isTablet=isTablet
            )
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setContent {
            InitLayout(
                orientation=resources.configuration.orientation,
                isTablet=isTablet
            )
        }
    }
}

@Composable
fun InitLayout(orientation: Int, isTablet : Boolean){
    LabaTheme{
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background
        ){
            innerPadding -> run {
                when (orientation){
                    Configuration.ORIENTATION_PORTRAIT -> {
                        if (isTablet){
                            PortraitTabletLayout(innerPadding)
                        }else{
                            PortraitPhoneLayout(innerPadding)
                        }
                    }

                    Configuration.ORIENTATION_LANDSCAPE -> {
                        if (isTablet){
                            LandscapeTabletLayout(innerPadding)
                        }else{
                            LandscapePhoneLayout(innerPadding)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LandscapePhoneLayout(innerPadding : PaddingValues){
    Row(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(Modifier.weight(1f))
        IconFrame(Modifier.weight(4f))
        Spacer(Modifier.weight(1f))
        ContactsFrame(Modifier.weight(3f))
    }
}

@Composable
fun PortraitPhoneLayout(innerPadding : PaddingValues){
    Column(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.weight(2f))
        IconFrame(Modifier.weight(4f))
        Spacer(Modifier.weight(1f))
        ContactsFrame(Modifier.weight(3f))
    }
}

@Composable
fun LandscapeTabletLayout(innerPadding : PaddingValues){
    Row(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(Modifier.weight(1f))
        IconFrame(Modifier.weight(4f))
        Spacer(Modifier.weight(1f))
        ContactsFrame(Modifier.weight(3f))
    }
}

@Composable
fun PortraitTabletLayout(innerPadding : PaddingValues){
    Column(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.weight(1f))
        IconFrame(Modifier.weight(4f))
        Spacer(Modifier.weight(1f))
        ContactsFrame(Modifier.weight(3f))
    }
}

@Preview(showBackground = true)
@Composable
fun IconFramePreview(){
    LabaTheme {
        IconFrame()
    }
}

@Composable
fun IconFrame(modifier : Modifier = Modifier){
    Column(modifier = modifier) {
        Image(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            painter = painterResource(R.drawable.app_logo),
            contentDescription = ""
        )
        Spacer(
            Modifier.height(dimensionResource(R.dimen.icon_incol_space))
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.icon_dev_name),
            fontSize = dimensionResource(R.dimen.icon_dev_name_fontsz).value.sp,
            color = MaterialTheme.colorScheme.primary,
        )
        Spacer(
            Modifier.height(dimensionResource(R.dimen.icon_incol_space))
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.icon_group_num),
            fontSize = dimensionResource(R.dimen.icon_group_num_fontsz).value.sp,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactsFramePreview(){
    LabaTheme{
        ContactsFrame()
    }
}

@Composable
fun ContactsFrame(modifier : Modifier = Modifier){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        ContactRow(
            imgRes = R.drawable.phone_icon,
            textRes = R.string.contact_phone
        )
        Spacer(
            Modifier.height(dimensionResource(R.dimen.contacts_incol_space))
        )
        ContactRow(
            imgRes = R.drawable.tg_icon,
            textRes = R.string.contact_tg
        )
        Spacer(
            Modifier.height(dimensionResource(R.dimen.contacts_incol_space))
        )
        ContactRow(
            imgRes = R.drawable.email_icon,
            textRes = R.string.contact_email
        )
    }
}

@Composable
fun ContactRow(@DrawableRes imgRes : Int = R.drawable.app_logo, @StringRes textRes : Int = R.string.default_str){
    Row{
        Image(
            modifier = Modifier.width(dimensionResource(R.dimen.contacts_icon_width)).align(Alignment.CenterVertically),
            painter = painterResource(imgRes),
            contentDescription = ""
        )
        Spacer(
            Modifier.width(dimensionResource(R.dimen.contacts_inrow_space))
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = stringResource(textRes),
            fontSize = dimensionResource(R.dimen.contacts_fontsz).value.sp,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}