package com.example.countryinfoapp.classes.utilities

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ViewTarget
import com.example.countryinfoapp.CountryDetailsQuery
import com.example.countryinfoapp.CountryListQuery
import com.example.countryinfoapp.constants.Constants

fun glideSetup(
    imageView: ImageView,
    list: CountryListQuery.Country
) : ViewTarget<ImageView, Drawable> {
    return Glide.with(imageView)
        .load(String.format(Constants.FLAGS_LIST_LINK, list.code.lowercase()))
        .into(imageView)
}

fun glideSetup(
    imageView: ImageView,
    cDetails: CountryDetailsQuery.Country
) : ViewTarget<ImageView, Drawable>{
    return Glide.with(imageView)
        .load(String.format(Constants.FLAGS_DETAILS_LINK, cDetails.code.lowercase()))
        .into(imageView)
}