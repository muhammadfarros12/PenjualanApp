package com.farroos.penjualanapp.ui.agent.update

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.farroos.penjualanapp.R
import com.farroos.penjualanapp.data.model.agen.ResponseAgentDetail
import com.farroos.penjualanapp.data.model.agen.ResponseAgentUpdate
import com.farroos.penjualanapp.ui.agent.AgentMapsActivity
import com.farroos.penjualanapp.utils.GlideHelper
import com.lazday.poslaravel.util.GalleryHelper
import kotlinx.android.synthetic.main.activity_agent_create.*

class AgentUpdateActivity : AppCompatActivity(), AgentUpdateContract.View {

    private var uriImage: Uri? = null
    private var pickImage = 1
    lateinit var presenter: AgentUpdatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_create)
        presenter = AgentUpdatePresenter(this)
        presenter.getDetail(com.farroos.penjualanapp.data.Constant.AGENT_ID)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImage && resultCode == Activity.RESULT_OK) {
            uriImage = data!!.data
            img_image.setImageURI(uriImage)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        if (com.farroos.penjualanapp.data.Constant.LATITUDE.isNotEmpty()) {
            edt_location.setText("${com.farroos.penjualanapp.data.Constant.LATITUDE}, ${com.farroos.penjualanapp.data.Constant.LONGITUDE}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        com.farroos.penjualanapp.data.Constant.LONGITUDE = ""
        com.farroos.penjualanapp.data.Constant.LATITUDE = ""
    }

    override fun initActivity() {
        supportActionBar!!.title = "Agent Edit"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        edt_location.setOnClickListener {
        startActivity(Intent(this, AgentMapsActivity::class.java))
    }

        img_image.setOnClickListener {
            if (GalleryHelper.permissionGallery(this, this, pickImage)) {
                GalleryHelper.openGallery(this)
            }
        }

        btn_simpan.setOnClickListener {
            val nameStore = edt_name_store.text
            val nameOwner = edt_owner_store.text
            val address = edt_address.text
            val location = edt_location.text

            if (nameStore.isNullOrEmpty() || nameOwner.isNullOrEmpty() || address.isNullOrEmpty() || location.isNullOrEmpty() ) {
                showMessage("Lengkapi data dengan benar")
            } else {
                presenter.updateAgent(
                    com.farroos.penjualanapp.data.Constant.AGENT_ID,
                    nameStore.toString(),
                    nameOwner.toString(),
                    address.toString(),
                    com.farroos.penjualanapp.data.Constant.LATITUDE,
                    com.farroos.penjualanapp.data.Constant.LONGITUDE,
                    com.farroos.penjualanapp.utils.FileUtils.getFile(this, uriImage)
                )
            }
        }

    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> {
                progress.visibility = View.VISIBLE
                btn_simpan.visibility = View.GONE
            }
            false -> {
                progress.visibility = View.GONE
                btn_simpan.visibility = View.VISIBLE
            }
        }
    }

    override fun onResultDetail(responseAgentDetail: ResponseAgentDetail) {
        val agent = responseAgentDetail.dataAgent

        edt_name_store.setText(agent.nama_toko!!)
        edt_name_store.setText(agent.nama_pemilik!!)
        edt_address.setText(agent.alamat!!)
        edt_location.setText("${agent.latitude}, ${agent.latitude}")

        com.farroos.penjualanapp.data.Constant.LATITUDE = agent.latitude!!
        com.farroos.penjualanapp.data.Constant.LONGITUDE = agent.longitude!!

        GlideHelper.setImage(this, agent.gambar_toko!!, img_image)
    }

    override fun onResultUpdate(responseAgentUpdate: ResponseAgentUpdate) {
        showMessage(responseAgentUpdate.msg)
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}