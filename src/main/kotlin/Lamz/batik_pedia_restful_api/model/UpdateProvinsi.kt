package Lamz.batik_pedia_restful_api.model

import jakarta.validation.constraints.NotBlank

data class UpdateProvinsi(

    @field:NotBlank
    val namaProvinsi : String,

    @field:NotBlank
    val imgProvinsi : String,

    @field:NotBlank
    val detailProvinsi : String,

)