package Lamz.batik_pedia_restful_api.model

data class WebResponse  <T> (
    val code : Int,
    val status : String,
    val data : T
)