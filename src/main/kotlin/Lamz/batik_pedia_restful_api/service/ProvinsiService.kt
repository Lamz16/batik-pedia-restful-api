package Lamz.batik_pedia_restful_api.service

import Lamz.batik_pedia_restful_api.entity.Provinsi
import Lamz.batik_pedia_restful_api.model.CreateProvinsi
import Lamz.batik_pedia_restful_api.model.ListProvinsiRequest
import Lamz.batik_pedia_restful_api.model.ProvinsiResponse
import Lamz.batik_pedia_restful_api.model.UpdateProvinsi

interface ProvinsiService
{
    fun createProvinsi(createProvinsi : CreateProvinsi) : ProvinsiResponse

    fun getProvinsi(idProvinsi : String) : ProvinsiResponse

    fun updateProvinsi(idProvinsi: String, update : UpdateProvinsi) : ProvinsiResponse

    fun delete(idProvinsi : String)

    fun listProvinsi(listProvinsiRequest: ListProvinsiRequest) : List<ProvinsiResponse>
}