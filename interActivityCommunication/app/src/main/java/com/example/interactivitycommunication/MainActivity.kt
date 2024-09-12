import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class FirstActivity : AppCompatActivity() {

    lateinit var send_button: Button
    lateinit var send_text: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_activity)

        send_button = findViewById(R.id.send_button_id)
        send_text = findViewById(R.id.send_text_id)

        send_button.setOnClickListener {
            val str = send_text.text.toString()
            val intent = Intent(applicationContext, Second_activity::class.java)
            intent.putExtra("message_key", str)
            startActivity(intent)
        }
    }
}
