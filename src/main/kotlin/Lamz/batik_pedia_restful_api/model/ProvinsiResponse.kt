package Lamz.batik_pedia_restful_api.model

import java.util.Date

data class ProvinsiResponse(
    val idProvinsi : String,
    val namaProvinsi : String,
    val imgProvinsi : String,
    val detailProvinsi : String,
    val createdAt : Date,
    val updatedAt : Date,
)
