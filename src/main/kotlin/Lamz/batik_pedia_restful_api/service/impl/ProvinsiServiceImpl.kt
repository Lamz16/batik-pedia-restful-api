package Lamz.batik_pedia_restful_api.service.impl

import Lamz.batik_pedia_restful_api.entity.Provinsi
import Lamz.batik_pedia_restful_api.error.NotFoundException
import Lamz.batik_pedia_restful_api.model.CreateProvinsi
import Lamz.batik_pedia_restful_api.model.ListProvinsiRequest
import Lamz.batik_pedia_restful_api.model.ProvinsiResponse
import Lamz.batik_pedia_restful_api.model.UpdateProvinsi
import Lamz.batik_pedia_restful_api.repository.ProvinsiRepository
import Lamz.batik_pedia_restful_api.service.ProvinsiService
import Lamz.batik_pedia_restful_api.utils.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.Date
import java.util.stream.Collectors

@Service
class ProvinsiServiceImpl(
    val provinsiRepository: ProvinsiRepository,
    val validationUtil : ValidationUtil) : ProvinsiService {
    override fun createProvinsi(createProvinsi: CreateProvinsi): ProvinsiResponse {
        validationUtil.validate(createProvinsi)

        val generatedId = generateProvinsiId()

        val provinsi = Provinsi(
            idProvinsi = generatedId,
            namaProvinsi = createProvinsi.namaProvinsi,
            imgProvinsi = createProvinsi.imgProvinsi,
            detailProvinsi = createProvinsi.detailProvinsi,
            createdAt = Date(),
            updatedAt = Date(),
        )

        provinsiRepository.save(provinsi)

        return convertToProvinsiResponse(provinsi)
    }

    override fun getProvinsi(idProvinsi: String): ProvinsiResponse {
        val provinsi = provinsiRepository.findByIdOrNull(idProvinsi)

        if (provinsi == null) {
            throw NotFoundException()
        }else{
            return convertToProvinsiResponse(provinsi)
        }
    }

    override fun updateProvinsi(
        idProvinsi: String,
        update: UpdateProvinsi
    ): ProvinsiResponse {
        val provinsi = findProvinsiById(idProvinsi)
        validationUtil.validate(update)

        provinsi.apply {
            namaProvinsi = update.namaProvinsi
            detailProvinsi = update.detailProvinsi
            imgProvinsi = update.imgProvinsi
            updatedAt = Date()
        }

        provinsiRepository.save(provinsi)

        return convertToProvinsiResponse(provinsi)
    }

    override fun delete(idProvinsi: String){
        val provinsi = findProvinsiById(idProvinsi)
        provinsiRepository.delete(provinsi)

    }

    override fun listProvinsi(listProvinsiRequest: ListProvinsiRequest): List<ProvinsiResponse> {
        val page = provinsiRepository.findAll(PageRequest.of(listProvinsiRequest.page,listProvinsiRequest.size))
        val provinsi : List<Provinsi> = page.get().collect(Collectors.toList())
        return provinsi.map { convertToProvinsiResponse(it) }
    }

    private fun findProvinsiById(idProvinsi: String): Provinsi {
        val provinsi = provinsiRepository.findByIdOrNull(idProvinsi)

        if (provinsi == null) {
            throw NotFoundException()
        }else{
            return provinsi
        }
    }

    private fun convertToProvinsiResponse(provinsi : Provinsi) : ProvinsiResponse {
        return ProvinsiResponse(
            idProvinsi = provinsi.idProvinsi,
            namaProvinsi = provinsi.namaProvinsi,
            imgProvinsi = provinsi.imgProvinsi,
            detailProvinsi = provinsi.detailProvinsi,
            createdAt = provinsi.createdAt,
            updatedAt = provinsi.updatedAt,
        )
    }

    private fun generateProvinsiId(): String {
        val today = java.time.LocalDate.now()
        val dateStr = today.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"))

        val prefix = "PROV$dateStr"

        val lastId = provinsiRepository.findLastIdOfDay(prefix)

        val nextNumber = if (lastId == null) {
            1
        } else {
            lastId.substringAfter("-").toInt() + 1
        }

        val formattedNum = String.format("%03d", nextNumber)

        return "$prefix-$formattedNum"
    }


}