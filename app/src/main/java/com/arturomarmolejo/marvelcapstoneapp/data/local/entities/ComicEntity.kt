package com.arturomarmolejo.marvelcapstoneapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.arturomarmolejo.marvelcapstoneapp.model.comic.*
import com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.comics.*

@TypeConverters(
        CharactersTypeConverter::class,
        ComicsTypeConverter::class,
        CreatorTypeConverter::class,
        CollectedIssuesTypeConverter::class,
        CollectedIssuesListTypeConverter::class,
        DateTypeConverter::class,
        DateListTypeConverter::class,
        EventsTypeConverter::class,
        ImageTypeConverter::class,
        ImageListTypeConverter::class,
        ItemTypeConverter::class,
        ItemListTypeConverter::class,
        PriceTypeConverter::class,
        PriceListTypeConverter::class,
        TextObjectTypeConverter::class,
        TextObjectListTypeConverter::class,
        SeriesTypeConverter::class,
        StoriesTypeConverter::class,
        ThumbnailTypeConverter::class,
        UrlTypeConverter::class,
        UrlListTypeConverter::class,
        VariantTypeConverter::class,
        VariantListTypeConverter::class,
)
@Entity(tableName = "comics")
data class ComicEntity (
        @PrimaryKey val id: Int,
        val characters: Characters, //*
        val collectedIssues: List<CollectedIssue>, //*
        val creators: Creators, //*
        val dates: List<Date>, //*
        val description: String?,
        val diamondCode: String,
        val digitalId: Int,
        val ean: String,
        val events: Events, //*
        val format: String,
        val images: List<Image>, //*
        val isbn: String,
        val issn: String,
        val issueNumber: Int,
        val modified: String,
        val pageCount: Int,
        val prices: List<Price>, //*
        val resourceURI: String,
        val series: Series, //*
        val stories: Stories, //*
        val textObjects: List<TextObject>, //*
        val thumbnail: Thumbnail, //*
        val title: String,
        val upc: String,
        val urls: List<Url>, //*
        val variantDescription: String,
        val variants: List<Variant>,

        val isFavorite: Boolean = false
)

fun List<ComicsResult>?.mapToComicEntity(): List<ComicEntity> {
        return this?.map {
                ComicEntity(
                        id = it.id,
                        characters = it.characters,
                        collectedIssues = it.collectedIssues.map { issue ->
                                CollectedIssue(
                                        name = issue.name,
                                        resourceURI = issue.resourceURI
                                )
                        },
                        creators = it.creators,
                        dates = it.dates.map { date ->
                                Date(
                                        date = date.date,
                                        type = date.type,
                                )
                        },
                        description = it.description,
                        diamondCode = it.diamondCode,
                        digitalId = it.digitalId,
                        ean = it.ean,
                        events = it.events,
                        format = it.format,
                        images = it.images.map{ image ->
                                Image(
                                     extension = image.extension,
                                     path = image.path
                                )
                        },
                        isbn = it.isbn,
                        issn = it.issn,
                        issueNumber = it.issueNumber,
                        modified = it.modified,
                        pageCount = it.pageCount,
                        prices = it.prices.map { price ->
                                Price(
                                        price = price.price,
                                        type = price.type
                                )
                        },
                        resourceURI = it.resourceURI,
                        series = it.series,
                        stories = it.stories,
                        textObjects = it.textObjects.map { textObject ->
                                TextObject(
                                        language = textObject.language,
                                        text = textObject.text,
                                        type = textObject.type
                                )
                        },
                        thumbnail = it.thumbnail,
                        title = it.title,
                        upc = it.upc,
                        urls = it.urls.map { url ->
                                Url(
                                        type = url.type,
                                        url = url.url
                                )
                        },
                        variantDescription = it.variantDescription,
                        variants = it.variants.map {variant ->
                                Variant(
                                    name = variant.name,
                                    resourceURI = variant.resourceURI
                                )
                        }
                )
        } ?: emptyList()
}