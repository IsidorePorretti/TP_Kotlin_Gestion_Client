package fr.epf.min1.gcIsidore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add_client.*
import fr.epf.min1.gcIsidore.model.Client

private const val TAG = "AddClientActivity"

class AddClientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_client)
        add_client_button.setOnClickListener{
            val last_name : String = client_last_name_edit_text.text.toString()
            Log.d(TAG, "Nom : ${last_name.toUpperCase()}")

            val selectedItem = client_level_spinner.selectedItem as String
            Log.d(TAG, "Niveau: ${selectedItem.toUpperCase()}")
            finish()


            val clients : List<Client> = Client.all()

         }
    }
}