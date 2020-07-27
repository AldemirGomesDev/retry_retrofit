package com.aldemir.retryretrofit

import com.google.gson.annotations.SerializedName

data class States (
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("sigla")
    var sigla: String? = null,

    @SerializedName("nome")
    var nome: String? = null,

    @SerializedName("regiao")
    var regiao: String? = null
)