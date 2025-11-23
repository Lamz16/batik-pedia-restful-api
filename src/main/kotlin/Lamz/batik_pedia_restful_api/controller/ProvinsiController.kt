package Lamz.batik_pedia_restful_api.controller

import Lamz.batik_pedia_restful_api.model.CreateProvinsi
import Lamz.batik_pedia_restful_api.model.ListProvinsiRequest
import Lamz.batik_pedia_restful_api.model.ProvinsiResponse
import Lamz.batik_pedia_restful_api.model.UpdateProvinsi
import Lamz.batik_pedia_restful_api.model.WebResponse
import Lamz.batik_pedia_restful_api.service.ProvinsiService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProvinsiController(val provinsiService: ProvinsiService) {

    @PostMapping(
        value = ["/api/provinsi"],
        produces = ["application/json"],
        consumes = ["application/json"],
    )
    fun createProvinsi(@RequestBody createProvinsi: CreateProvinsi): WebResponse<ProvinsiResponse> {
        val provinsiResponse = provinsiService.createProvinsi(createProvinsi)

        return convertToWebResponse(provinsiResponse)
    }

    @GetMapping(
        value = ["/api/provinsi/{idProvinsi}"],
        produces = ["application/json"],
    )
    fun getProvinsi(@PathVariable("idProvinsi") idProvinsi: String): WebResponse<ProvinsiResponse> {
        val provinsiResponse = provinsiService.getProvinsi(idProvinsi)

        return convertToWebResponse(provinsiResponse)
    }


    @PutMapping(
        value = ["/api/provinsi/{idProvinsi}"],
        produces = ["application/json"],
        consumes = ["application/json"],

        )

    fun updateProvinsi(
        @PathVariable("idProvinsi") idProvinsi: String,
        @RequestBody updateProvinsi: UpdateProvinsi
    ): WebResponse<ProvinsiResponse> {
        val provinsiResponse = provinsiService.updateProvinsi(idProvinsi, updateProvinsi)

        return convertToWebResponse(provinsiResponse)
    }

    @DeleteMapping(
        value = ["/api/provinsi/{idProvinsi}"],
        produces = ["application/json"],
    )
    fun deleteProvinsi(@PathVariable("idProvinsi") idProvinsi: String): WebResponse<String> {
        provinsiService.delete(idProvinsi)
        return convertToWebResponse(data = idProvinsi, code = 201)
    }


    @GetMapping(
        value = ["/api/provinsi"],
        produces = ["application/json"],
    )
    fun getProvinsiList(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<ProvinsiResponse>> {
        val request = ListProvinsiRequest(page = page, size = size)
        val responses = provinsiService.listProvinsi(request)

        return convertToWebResponse(responses)

    }

    private fun <T> convertToWebResponse(data: T, code: Int = 200, status: String = "success"): WebResponse<T> {
        return WebResponse(
            code = code,
            status = status,
            data = data
        )
    }
}