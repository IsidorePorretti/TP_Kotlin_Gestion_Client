package fr.epf.min1.gcIsidore

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import fr.epf.min1.gcIsidore.model.Client
import fr.epf.min1.gcIsidore.model.Gender
import kotlinx.android.synthetic.main.activity_details_client.*
import kotlinx.android.synthetic.main.client_view.view.*


class DetailsClientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_client)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val client = intent.getParcelableExtra<Client>("id")

//        val client = Client.all()[id]

        client_lastname_textview.text = client?.lastname?.epf()

        client_firstname_textview.text = client?.firtsname

        client_details_imageview.setImageResource(client!!.getPicture())

        client_details_imageview.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 12334)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_client,menu)
        return super.onPrepareOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
//            android.R.id.home -> {
//                finish()
//            }
            R.id.delete_client_action -> {
                AlertDialog.Builder(this)
                    .setTitle("Supprimer client")
                    .setMessage("Etes-vous sûr ?")
                    .setPositiveButton("Oui") { _, _ ->
                        finish()
                        Toast.makeText(this, "Client supprimé", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Non"){ dialog,_ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 12334){
            val bitmap = data?.getParcelableExtra("data") as? Bitmap
            client_details_imageview.setImageBitmap(bitmap)
        }

    }
}


fun Client.getPicture() = when (this.gender) {
    Gender.Woman -> R.drawable.woman
    Gender.Man -> R.drawable.man
}

fun String.epf() = this.toUpperCase()