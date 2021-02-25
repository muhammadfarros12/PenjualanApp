package com.farroos.penjualanapp.data.model

import com.google.gson.annotations.SerializedName

data class ResponseDetailTransaction(

	@field:SerializedName("data")
	val data: List<DataItem>? = null
)

data class DataItem(

	@field:SerializedName("harga_rupiah")
	val hargaRupiah: String? = null,

	@field:SerializedName("nama_produk")
	val namaProduk: String? = null,

	@field:SerializedName("jumlah")
	val jumlah: String? = null,

	@field:SerializedName("harga")
	val harga: String? = null,

	@field:SerializedName("kd_transaksi_detail")
	val kdTransaksiDetail: Int? = null,

	@field:SerializedName("kd_produk")
	val kdProduk: String? = null,

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("gambar_produk")
	val gambarProduk: String? = null,

	@field:SerializedName("no_faktur")
	val noFaktur: String? = null
)
