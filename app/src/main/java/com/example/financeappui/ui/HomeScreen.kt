package com.example.financeappui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.financeappui.BankCard
import com.example.financeappui.R
import com.example.financeappui.Transaction
import com.example.financeappui.ui.theme.*

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        UserCards()
        CardSection(
            BankCard(
                number = "4562 1122 4595 7852",
                ownerName = "Ghulam",
                expiryDate = "24/2021",
                company = "Mastercard"
            )
        )
        TransactionsSection(
            listOf(
                Transaction(
                    title = "KFC",
                    date = "June 26",
                    payment = "+$2,010",
                    iconId = R.drawable.ic_outline_lunch_dining_24
                ),
                Transaction(
                    title = "Paypal",
                    date = "June 28",
                    payment = "+$12,010",
                    iconId = R.drawable.ic_baseline_local_parking_24
                ),
                Transaction(
                    title = "Car Repair",
                    date = "Aug 12",
                    payment = "+$232,010",
                    iconId = R.drawable.ic_outline_settings_24
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun UserCards(activeCardsCount: Int = 3) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "Your Cards",
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "You have $activeCardsCount Active Cards",
                style = MaterialTheme.typography.body1
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Yellow)
                .padding(5.dp)
                .clickable {
                    //Do something
                }
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_input_add),
                contentDescription = "Add Card"
            )
        }
    }
}

@Composable
fun CardSection(card: BankCard) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 15.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(Purple500)
            .aspectRatio(1.9f)
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //todo icon

            Text(
                text = card.number,
                style = MaterialTheme.typography.h1
            )

            Text(
                text = card.ownerName,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                ),
            )

            Column {
                Text(
                    text = "Expiry Date", style = MaterialTheme.typography.body1.copy(
                        fontSize = 12.sp
                    )
                )
                Text(
                    text = buildAnnotatedString {
                        val (month, year) = card.expiryDate.split("/")
                        append(month)

                        withStyle(
                            SpanStyle(fontWeight = FontWeight.ExtraLight, color = AquaBlue)
                        ) { append("/") }

                        append(year)
                    },
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                )
            }
        }
        CardCompany(card)
    }
}

@Composable
fun CardCompany(card: BankCard) {
    val constraints = ConstraintSet {
        val iconBox = createRefFor("iconBox")
        val cardCompany = createRefFor("cardCompany")

        constrain(cardCompany) {
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.preferredWrapContent
            height = Dimension.wrapContent
        }

        constrain(iconBox) {
            start.linkTo(cardCompany.start)
            end.linkTo(cardCompany.end)
            bottom.linkTo(cardCompany.top)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
    }

    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .layoutId("iconBox")
        ) {
            Box(
                modifier = Modifier
                    .offset(x = -10.dp)
                    .size(35.dp)
                    .clip(CircleShape)
                    .alpha(0.9f)
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .offset(x = 10.dp)
                    .size(35.dp)
                    .clip(CircleShape)
                    .alpha(0.9f)
                    .background(Orange)
            )
        }
        Text(
            text = card.company, style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Bold, color = TextWhite
            ),
            modifier = Modifier.layoutId("cardCompany")
        )
    }
}

@Composable
fun TransactionsSection(
    transactions: List<Transaction>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(text = "Recent Transactions", style = MaterialTheme.typography.h2)

        LazyColumn {
            items(transactions.size) {
                TransactionItem(transactions[it])
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    //15 dp horizontal padding, top = 15.dp
}