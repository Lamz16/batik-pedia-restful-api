package Lamz.batik_pedia_restful_api.error

class NotFoundException(message: String = "Data tidak ditemukan") : RuntimeException(message)
