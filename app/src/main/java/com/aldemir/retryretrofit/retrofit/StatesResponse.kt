package com.aldemir.retryretrofit.retrofit

import com.google.gson.annotations.SerializedName

data class StatesResponse (
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("sigla")
    val sigla: String? = null,

    @SerializedName("nome")
    val nome: String? = null,

    @SerializedName("regiao")
    val regiao: Region? = null
) {
    data class Region(
        @SerializedName("id")
        val id: Int? = null,

        @SerializedName("sigla")
        val sigla: String? = null,

        @SerializedName("nome")
        val nome: String? = null
    )
}