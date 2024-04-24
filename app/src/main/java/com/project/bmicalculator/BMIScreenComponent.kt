package com.project.bmicalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.bmicalculator.ui.theme.CustomBlue
import com.project.bmicalculator.ui.theme.CustomGray
import com.project.bmicalculator.ui.theme.CustomGreen
import com.project.bmicalculator.ui.theme.CustomOrange
import com.project.bmicalculator.ui.theme.CustomRed
import com.project.bmicalculator.ui.theme.Pink80
import java.lang.reflect.Modifier
import java.nio.file.WatchEvent

@Composable
fun UnitItem(
    text: String,
    textColor: Color = Color.Black,
    onClick: () -> Unit
    
){
    Row(
        modifier = androidx.compose.ui.Modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, fontSize = 22.sp, color = textColor)
        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Select Unit" )
    }
}



@Composable
fun InputUnitValue(
    inputValue: String,
    inputUnit: String,
    inputNoColor: Color,
){
    Column(
        horizontalAlignment = Alignment.End

    ) {
        Text(text = inputValue, fontSize = 40.sp, color = inputNoColor)
        Text(text= inputUnit, fontSize = 12.sp)

    }
}

@Composable
fun NumberKeyboard(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
    onNumberClick: (String) -> Unit
){
    Column(modifier = modifier ) {
        val numberButtonList = listOf(
            "7","8","9","4","5","6",
            "1","2","3","","0","."
        )
        LazyVerticalGrid(columns = GridCells.Fixed(3)
        ) {
            items(numberButtonList){item ->
                NumberButton(
                    modifier = androidx.compose.ui.Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    number = item,
                    onClick = onNumberClick
                )
            }
        }
    }
}


@Composable
fun NumberButton(
    modifier: androidx.compose.ui.Modifier= androidx.compose.ui.Modifier,
    number:String,
    onClick: (String) -> Unit
){
    if (number != ""){
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .padding(10.dp)
                .clip(CircleShape)
                .clickable { onClick(number) }

        ) {
            Text(text = number, fontSize = 40.sp)
        }
    }
}

@Composable
fun ColumnScope.SymbolButton(
    symbol:String,
    onClick: () -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = androidx.compose.ui.Modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(CustomGray)
            .clickable { onClick() }
            .padding(15.dp)
            .weight(1f)
            .aspectRatio(1f)

    ) {
        Text(text = symbol, fontSize = 26.sp)
    }
}

@Composable
fun ColumnScope.SymbolButtonWithIcon(
    onClick: () -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = androidx.compose.ui.Modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(CustomGray)
            .clickable { onClick() }
            .padding(15.dp)
            .weight(1f)
            .aspectRatio(1f)

    ) {
        Icon(painter = painterResource(R.drawable.ic_backspace),
            contentDescription = "Backspace Icon",
            tint = Pink80
        )
    }
}

@Composable
fun BMIResultCard(
    bmi:Double,
    bmiStage: String = "Normal",
    bmiStageColor: Color = CustomGreen

)
{
    Column(
        modifier = androidx.compose.ui.Modifier
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
     ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "$bmi", fontSize = 70.sp, color = Pink80)
            Spacer(modifier = androidx.compose.ui.Modifier.width(15.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "BMI", fontSize = 40.sp, color = Color.Gray)
                Text(text = "$bmiStage", fontSize = 18.sp, color = bmiStageColor)
            }
        }
        Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))
        Divider(
            modifier= androidx.compose.ui.Modifier
                .background(Color.DarkGray)
                .shadow(elevation = 5.dp),
            thickness = 5.dp, DarkGray
        )
        Text(
            modifier = androidx.compose.ui.Modifier.padding(vertical = 25.dp),
            text = "Information",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 20.sp
        )
        Row(
            modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = "Underweight", color = CustomBlue)
            Text(text = "Normal", color = CustomGreen)
            Text(text = "Overweight", color = CustomRed)
        }
        Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))
        Row {
            Divider(
                modifier = androidx.compose.ui.Modifier
                    .width(100.dp),
                thickness = 5.dp,
                color = CustomBlue
            )
            Divider(
                modifier = androidx.compose.ui.Modifier
                    .width(100.dp),
                thickness = 5.dp,
                color = CustomGreen
            )
            Divider(
                modifier = androidx.compose.ui.Modifier
                    .width(100.dp),
                thickness = 5.dp,
                color = CustomRed
            )
        }
        Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))
        Row(
            modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "18.0", fontSize = 18.sp, color = Color.DarkGray)
            Text(text = "18.5 ", fontSize = 18.sp, color = Color.DarkGray)
            Text(text = "24.9", fontSize = 18.sp, color = Color.DarkGray)
            Text(text = "40.0", fontSize = 18.sp, color = Color.DarkGray)

        }
    }
}

@Composable
fun BottomSheetContent(
    sheetTitle: String,
    sheetItemsList: List<String>,
    onItemClicked: (String) -> Unit,
    onCancelClicked: () -> Unit
) {
    Text(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .padding(10.dp),
        text = sheetTitle,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
    sheetItemsList.forEach { item ->
        Box(
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .clickable { onItemClicked(item) }
        ) {
            Text(
                text = item,
                modifier = androidx.compose.ui.Modifier.padding(15.dp)
            )
        }
    }
    Button(
        modifier = androidx.compose.ui.Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .background(CustomGray),
        onClick = onCancelClicked,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        )
    ) {
        Text(
            text = "Cancel"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Prev(){

}