package com.example.pemdascalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.Nullable
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pemdascalculator.ui.theme.PemdasCalculatorTheme
import java.util.Stack

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var working by remember{ mutableStateOf("")}
            var answer:String by remember{mutableStateOf("")}
            Column(modifier = Modifier
                .fillMaxSize()
                .border(3.dp, Color.White,shape = RectangleShape)
            ) {

                TextField(
                    value = working,
                    onValueChange = {working = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(0.dp, 190.dp).background(Color.Unspecified),
                    readOnly = true,
                    textStyle = TextStyle(color = Color.Black,fontSize = 40.sp,textDirection = TextDirection.Ltr,fontWeight = FontWeight.Medium)
                )
                TextField(
                    value = "$answer",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(0.dp, 110.dp).background(Color.Unspecified).offset(0.dp,-(10.dp)),
                    readOnly = true,
                    textStyle = TextStyle(color = Color.Black,fontSize = 40.sp,textDirection = TextDirection.Ltr,fontWeight = FontWeight.Medium)
                )
                Column(modifier = Modifier.background(Color.White)){

                    Spacer(modifier = Modifier.size(5.dp))
                    //FirstRow(working)
                    Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly) {
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                working = ""
                                answer = ""

                        }) {
                            Text(text = "AC",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Blue)
                        }
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                if(working == ""){
                                    working = "("
                                }
                               else if(working.last() in '1'..'9'){
                                   working = working
                               }
                               else if(working.last() == '('){
                                   working = working
                                }
                                else working+="("

                        }) {
                            Text(text = "(",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Blue)
                        }
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                if(working == ""){
                                    working = ""
                                }
                                else if(working.last() == '+' || working.last() == '-' || working.last() == '*' ||
                                    working.last() == '%' || working.last() == '/' || working.last() == '^') {
                                        working = working.replace(working.last().toString(),")")
                                }
                               else if(working.last() == ')'){
                                   working+=")"
                                }
                               else working+=")"

                        }) {
                            Text(text = ")",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Blue)
                        }
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                working = working.dropLast(1)
                        }) {
                            Text(text = "DEL",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Blue)
                        }
                    }
                Spacer(modifier = Modifier.size(3.dp))
                    //SecondRow()
                    Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly) {
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                if (working == "") {
                                    working = ""
                                }
                                else if(working.last() == ')'){
                                    working = working
                                }
                                else if (working.last() == '+' || working.last() == '-' || working.last() == '*' ||
                                    working.last() == '%' || working.last() == '/' || working.last() == '^'
                                ) {
                                    val a = working.substring(working.length - 2, working.length - 1).toDouble()
                                    val c = a / 100
                                    val d = c.toString()
                                    working = working.dropLast(1)
                                    working = working.replace(working.last().toString(), d)
                                } else if (working.last() == '(') {
                                    working = working
                                }
                                //should be worked on
                                else if (working.last() == '1'||working.last() == '2' || working.last() == '3') {
                                    val a = working.substring(working.length - 1).toDouble()
                                    val c = a / 100
                                    val d = c.toString()
                                    working = working.replace(working.last().toString(), d)
                                }

                        }) {
                            Text(text = "%",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Blue)
                        }
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                if(working == ""){
                                    working = ""
                                }
                                else if(working.last() == ')' || working.last() == '+' || working.last() == '-' || working.last() == '*' ||
                                    working.last() == '%' || working.last() == '/' || working.last() == '^') {

                                    working = working.replace(working.last().toString(),"^")
                                }
                                else if(working.last() == '('){
                                    working = working
                                }
                                else working+="^"
                        }) {
                            Text(text = "^",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Blue)
                        }
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                if(working == ""){
                                    working = ""
                                }
                                else if(working.last() == ')' || working.last() == '+' || working.last() == '-' || working.last() == '*' ||
                                    working.last() == '%' || working.last() == '/' || working.last() == '^') {

                                    working = working.replace(working.last().toString(), "/")
                                }
                                else if(working.contains('(')){
                                    working = working
                                }
                                else if(working.last() == '('){
                                    working = working
                                }
                                else working+="/"
                        }) {
                            Text(text = "/",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Blue)
                        }
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                if(working == ""){
                                    working = ""
                                }
                                else if(working.last() == ')' || working.last() == '+' || working.last() == '-' || working.last() == '*' ||
                                    working.last() == '%' || working.last() == '/' || working.last() == '^') {

                                    working = working.replace(working.last().toString(),"*")
                                }
                                else if(working.last() == '('){
                                    working = working
                                }
                                else working+="*"
                        }) {
                            Text(text = "X",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Blue)
                        }
                    }
                    Spacer(modifier = Modifier.size(3.dp))
                    //ThirdRow(working)
                    Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly) {
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                working+="7"
                        }) {
                            Text(text = "7",fontWeight = FontWeight.ExtraBold,fontSize = 20.sp,color = Color.Black)
                        }
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                working+="8"
                        }) {
                            Text(text = "8",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Black)
                        }
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                working+="9"
                            }) {
                            Text(text = "9",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Black)
                        }
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                if(working == ""){
                                    working = "-"
                                }
                                else if(working.last() == ')' || working.last() == '+' || working.last() == '-' || working.last() == '*' ||
                                    working.last() == '%' || working.last() == '/' || working.last() == '^') {

                                    working = working.replace(working.last().toString(), "-")
                                }
                                else if(working.last() == '('){
                                    working = working
                                }
                                else working+="-"
                        }) {
                            Text(text = "-",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Blue)
                        }
                    }
                    Spacer(modifier = Modifier.size(3.dp))
                    //FourthRow()
                    Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly) {
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                working+="4"
                        }) {
                            Text(text = "4",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Black)
                        }
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                working+="5"
                        }) {
                            Text(text = "5",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Black)
                        }
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                working+="6"
                        }) {
                            Text(text = "6",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Black)
                        }
                        Button(modifier = Modifier.size(85.dp,65.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                if(working == ""){
                                    working = ""
                                }
                                else if(working.last() == '+' || working.last() == '-' || working.last() == '*' ||
                                    working.last() == '%' || working.last() == '/' || working.last() == '^') {

                                    working = working.replace(working.last().toString(), "+")
                                }
                                else if(working.last() == ')'){
                                    working+= ")"
                                }
                                else if(working.last() == '('){
                                    working = working
                                }
                                else working+="+"
                        }) {
                            Text(text = "+",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Blue)
                        }
                    }
                    Spacer(modifier = Modifier.size(3.dp))
                    //FifthRow(working)
                    Row(modifier = Modifier.fillMaxSize(), Arrangement.SpaceEvenly) {
                        Button(modifier = Modifier.size(85.dp, 65.dp)
                            .offset(4.dp,0.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                working+="1"
                        }) {
                            Text(text = "1",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Black)
                        }
                        Spacer(modifier = Modifier.size(3.dp))
                        Button(modifier = Modifier.size(85.dp, 65.dp)
                            .offset(4.dp,0.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                working+="2"
                        }) {
                            Text(text = "2",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Black)
                        }
                        Spacer(modifier = Modifier.size(3.dp))
                        Button(modifier = Modifier.size(85.dp, 65.dp)
                            .offset(4.dp,0.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                working+="3"
                        }) {
                            Text(text = "3",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp, color = Color.Black)
                        }
                        Spacer(modifier = Modifier.size(3.dp))
                        Button(modifier = Modifier.size(85.dp, 135.dp)
                            .offset(5.dp,0.dp),
                            colors =  ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                            onClick = {
                                if(working == ""){
                                    working = ""
                                }
                                else if(working.last() == '(' || working.last() == '+' || working.last() == '-' || working.last() == '*' ||
                                    working.last() == '%' || working.last() == '/' || working.last() == '^'){
                                    working = working
                                }
                                else
                                try{
                                    val formatter = yard(working).toString()
                                    if(formatter.last() == '0'){
                                        answer = formatter
                                        answer = answer.dropLast(2)
                                    }

                                  else  answer =   yard(working).toString()
                                }catch(e:Exception){
                                   Toast.makeText(applicationContext,"bad expression", Toast.LENGTH_SHORT).show()
                                }

                        }) {
                            Text(text = "=",fontWeight = FontWeight.ExtraBold,fontSize = 40.sp,color = Color.White)
                        }
                        Button(modifier = Modifier
                            .requiredSize(170.dp, 65.dp)
                            .offset(-(265).dp, 68.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                if(working == ""){
                                    working = "0"
                                }
                               else working+="0"
                        }) {
                            Text(text = "0",fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color.Black)
                        }
                        Spacer(modifier = Modifier.size(3.dp))
                        Button(modifier = Modifier
                            .requiredSize(85.dp, 65.dp)
                            .offset(-(138).dp, 68.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            onClick = {
                                if(working.last() == '(' || working.last() == '+' || working.last() == '-' || working.last() == '*' ||
                                    working.last() == '%' || working.last() == '/' || working.last() == '^') {
                                    working = working.dropLast(1)
                                }
                                working+="."
                        }) {
                            Text(text = ".",fontWeight = FontWeight.ExtraBold,fontSize = 35.sp,color = Color.Blue)
                        }

                    }
                    Spacer(modifier = Modifier.size(3.dp))
                }

            }

        }
    }
}
fun yard(expression:String):Double{
    val data = expression.toCharArray()
    val values = Stack<Double>()
    val ope = Stack<Char>()
    var counter = 0

    while(counter < data.size){
        // if(data[counter] == ' ')
        //    continue

        if(data[counter] in '0'..'9'){
            val builder = StringBuilder()
            while(counter < data.size && data[counter] in '0'..'9' || counter < data.size && data[counter] == '.')
                builder.append(data[counter++])
            values.push(builder.toString().toDouble())
            counter--
        }
        else if(data[counter] == '('){
            ope.push(data[counter])
        }
        else if(data[counter] == ')'){
            while(ope.peek() != '(')
                values.push(computation(ope.pop(), values.pop(), values.pop()))
            ope.pop()
        }
        else if(data[counter] == '+' || data[counter] == '-' || data[counter] == '*' || data[counter] == '/' || data[counter] == '^'){
            while(!ope.isEmpty() && orderOfOperation(data[counter], ope.peek()))
                values.push(computation(ope.pop(), values.pop(), values.pop()))
            ope.push(data[counter])
        }
        counter++
    }
    while(!ope.isEmpty())
        values.push(computation(ope.pop(), values.pop(), values.pop()))
    return values.pop()
}
fun orderOfOperation(a:Char, b:Char):Boolean{
    if(b == '(' || b == ')')
        return false
    return (a == '+' || a == '-')||(b == '^' || b == '*' || b == '/')
}

fun computation(ope:Char, a:Double, b:Double):Double{
    return when(ope){
        '+' -> b + a
        '-' -> b - a
        '*' -> b * a
        '^' -> {
            var count = a
            var result = 1.0
            while(count != 0.0){
                result*=b
                count--
            }
            result
        }
        '/' -> {
            if(a == 0.0){
                throw Exception("Can't divide by zero")
            }
            else b / a
        }
        else -> 0.0
    }

}

