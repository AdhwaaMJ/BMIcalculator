package com.project.bmicalculator

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.cast.tv.cac.UserAction
import com.project.bmicalculator.ui.theme.CustomBlue
import com.project.bmicalculator.ui.theme.CustomGreen
import com.project.bmicalculator.ui.theme.CustomRed
import kotlinx.coroutines.launch
import com.project.bmicalculator.ui.theme.GrayBackground
import com.project.bmicalculator.ui.theme.Pink80


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMIScreen(
    viewModel:  BMIViewModel
) {

    val state = viewModel.state
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheet = rememberModalBottomSheetState()
    ModalBottomSheet(
        sheetState = modalBottomSheet,
        content = {
            BottomSheetContent(sheetTitle = state.sheetTitle, sheetItemsList = state.sheetItemsList,
                onItemClicked = {
                    coroutineScope.launch {
                        modalBottomSheet.show()
                    }
                    viewModel.onAction(userAction = com.project.bmicalculator.UserAction.OnSheetItemClicked(it))  //OnSheetItemClicked
                },
                onCancelClicked = {
                    coroutineScope.launch { modalBottomSheet.hide() }

                })
        },
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        onDismissRequest = {},
//            BMIScreenContent(
//                state = state,
//                onWeightUnitClicked = {
//                    coroutineScope.launch { modalBottomSheet.show() }
//                    viewModel.onAction(UserAction.OnWeightTextClicked)
//                },
//                onHeightUnitClicked = {
//                    coroutineScope.launch { modalBottomSheet.show() }
//                    viewModel.onAction(UserAction.OnHeightTextClicked)
//                },
//                onHeightValueClicked = {
//                    viewModel.onAction(UserAction.OnHeightValueClicked)
//                },
//                onWeightValueClicked = {
//                    viewModel.onAction(UserAction.OnWeightValueClicked)
//                },
//                onOKButtonClicked = {
//                    viewModel.onAction(UserAction.OnOKButtonClicked(context = context))
//                },
//                onNumberClicked = {
//                    viewModel.onAction(UserAction.OnNumberClicked(number = it))
//                },
//                onACButtonClicked = {
//                    viewModel.onAction(UserAction.OnAllClearButtonClicked)
//                },
//                onDeleteButtonClicked = {
//                    viewModel.onAction(UserAction.OnDeleteButtonClicked)
//                }
//
//            )
      //  }

    )
                BMIScreenContent(
                state = state,
                onWeightUnitClicked = {
                    coroutineScope.launch { modalBottomSheet.show() }
                    viewModel.onAction(com.project.bmicalculator.UserAction.OnWeightTextClicked)
                },
                onHeightUnitClicked = {
                    coroutineScope.launch { modalBottomSheet.show() }
                    viewModel.onAction(com.project.bmicalculator.UserAction.OnHeightTextClicked)
                },
                onHeightValueClicked = {
                    viewModel.onAction(com.project.bmicalculator.UserAction.OnHeightValueClicked)
                },
                onWeightValueClicked = {
                    viewModel.onAction(com.project.bmicalculator.UserAction.OnWeightValueClicked)
                },
                onOKButtonClicked = {
                    viewModel.onAction(com.project.bmicalculator.UserAction.OnOKButtonClicked(context = context))
                },
                onNumberClicked = {
                    viewModel.onAction(com.project.bmicalculator.UserAction.OnNumberClicked(number = it))
                },
                onACButtonClicked = {
                    viewModel.onAction(com.project.bmicalculator.UserAction.OnAllClearButtonClicked)
                },
                onDeleteButtonClicked = {
                    viewModel.onAction(com.project.bmicalculator.UserAction.OnDeleteButtonClicked)
                }

            )



}

@Composable
fun BMIScreenContent(
    state:BMIScreenState,
    onWeightUnitClicked: () -> Unit,
    onHeightUnitClicked: () -> Unit,
    onHeightValueClicked: () -> Unit,
    onWeightValueClicked: () -> Unit,
    onOKButtonClicked: () -> Unit,
    onACButtonClicked: () -> Unit,
    onDeleteButtonClicked: () -> Unit,
    onNumberClicked: (String) -> Unit
)
{ Column(modifier = Modifier
    .fillMaxSize()
    .background(GrayBackground)
    .padding(15.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "BMI Calculator",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                UnitItem(text = "Weight", onClick = {onWeightUnitClicked})
                InputUnitValue(
                    inputValue = state.weightValue,
                    inputUnit = state.weightUnit,
                    if (state.weightValueStage != WeightValueStage.INACTIVE) {
                        Pink80
                    } else Color.Black,
//                    = onWeightValueClicked   // onUnitValueClicked
                )

            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                UnitItem(text = "Height", onClick = {onHeightUnitClicked})
                InputUnitValue(
                    inputValue = state.heightValue,
                    inputUnit = state.heightUnit,
                    inputNoColor = if (state.heightValueStage != HeightValueStage.INACTIVE) {
                        Pink80
                    } else Color.Black,
//                    onUnitValueClicked = onHeightValueClicked
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            Crossfade(targetState = state.shouldBMICardShow) {
                if (it) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        BMIResultCard(
                            bmi = state.bmi,
                            bmiStage = state.bmiStage,
                            bmiStageColor = when(state.bmiStage) {
                                "Underweight" -> CustomBlue
                                "Normal" -> CustomGreen
                                else -> CustomRed
                            }
                        )
                    }
                } else {
                    Divider()
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        NumberKeyboard(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(7f),
                            onNumberClick = onNumberClicked
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(3f)
                        ) {
                            SymbolButton(symbol = "AC", onClick = onACButtonClicked)
                            SymbolButtonWithIcon(onClick = onDeleteButtonClicked)
                            SymbolButton(symbol = "OK", onClick = onOKButtonClicked)
                        }
                    }
                }
            }


        }
    }
}


@Preview
@Composable
fun BMIScreenPrv(){
}











