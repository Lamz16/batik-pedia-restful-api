package Lamz.batik_pedia_restful_api.entity

import jakarta.persistence.*
import java.util.*


@Entity
@Table(   name = "provinsis",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["nama_provinsi"])
    ])
data class Provinsi(
    @Id
    val idProvinsi : String,

    @Column(name = "nama_provinsi", unique = true)
    var namaProvinsi : String,

    @Column(name = "img_provinsi")
    var imgProvinsi : String,

    @Column(name = "detail_provinsi")
    var detailProvinsi : String,

    @Column(name = "created_at")
    val createdAt : Date,

    @Column(name = "updated_at")
    var updatedAt : Date

)
