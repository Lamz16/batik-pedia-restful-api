package Lamz.batik_pedia_restful_api.repository

import Lamz.batik_pedia_restful_api.entity.Provinsi
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProvinsiRepository : JpaRepository<Provinsi, String> {
    @Query(
        """
    SELECT p.idProvinsi 
    FROM Provinsi p 
    WHERE p.idProvinsi LIKE CONCAT(:prefix, '%') 
    ORDER BY p.idProvinsi DESC 
    LIMIT 1
    """
    )
    fun findLastIdOfDay(prefix: String): String?

}