package com.example.khabar.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.khabar.domain.model.Article
import com.example.khabar.presentation.Dimension.ArticleImageHeight
import com.example.khabar.presentation.Dimension.mediumPadding1
import com.example.khabar.presentation.details.components.DetailsTopBar
import com.example.khabar.R
import com.example.khabar.domain.model.Source

@Composable
fun DetailsScreen(
    article: Article,
    event :(DetailsEvent)-> Unit,
    navigateUp: () ->Unit
){
    val context =   LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = { Intent(Intent.ACTION_VIEW).also {
                it.data = Uri.parse(article.url)
                if (it.resolveActivity(context.packageManager)!=null){
                    context.startActivity(it)
                }
            } },
            onShareClick = {
                           Intent(Intent.ACTION_SEND).also {
                               it.putExtra(Intent.EXTRA_TEXT,article.url)
                               it.type="text/plain"
                               if (it.resolveActivity(context.packageManager)!=null){
                                   context.startActivity(it)
                               }
                           }

            },
            onBookmarkClick = {
                event(DetailsEvent.UpsertDeleteArticle(article))
            },
            onBackClick = navigateUp

            )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = mediumPadding1,
                end = mediumPadding1,
                top = mediumPadding1
            )
        ){
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
                        .build(), contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop

                    )
                Spacer(modifier = Modifier.height(mediumPadding1))
                Text(text = article.title, style = MaterialTheme.typography.displaySmall, color = colorResource(
                    id = R.color.text_title
                ) )
                Text(text = article.content, style = MaterialTheme.typography.bodyMedium, color = colorResource(
                    id = R.color.body
                ) )

            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun DetailScreenPrieview(){
    DetailsScreen(article = Article(
        author = "Lalu",
        title = "Bhais k aage been bajaao",
        description = "Bhais is Dumb",
        content = "actually this idiom has a changed version in english its “casting pearls before swine” it gives the same meaning as “ bhains ke aage been bajana” it means that the buffaloes do not care if we play the musical instrument before them they simply don't understand or it doesn't effects them so this idiom says that if applying to a person in a situation means that the person doesn't cares at all for example - I told him to start exercising but it was as if I was casting Pearl's before swine .",
        publishedAt = "2023-06-16T22:24:365",
        source = Source(
            id = "Gadha420", name = "chaploosNews"
        ),
        url = "https://www.quora.com/Whats-the-English-meaning-of-bhains-ke-aage-been-bajana",
        urlToImage = "data:image/jpeg;base64,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"


    ),

        event = {}) {

    }

}