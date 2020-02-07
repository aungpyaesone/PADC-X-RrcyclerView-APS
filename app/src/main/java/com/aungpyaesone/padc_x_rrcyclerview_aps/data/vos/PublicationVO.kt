package com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos

import com.google.gson.annotations.SerializedName

data class PublicationVO(
    @SerializedName("publication_name")val publicationName: String ="",
    @SerializedName("posted_date")val postedDate: String ="",
    @SerializedName("posted_time")val postedTime: String =""
)