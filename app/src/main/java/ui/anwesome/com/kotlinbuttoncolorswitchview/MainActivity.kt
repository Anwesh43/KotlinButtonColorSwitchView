package ui.anwesome.com.kotlinbuttoncolorswitchview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ui.anwesome.com.buttoncolorswitchview.ButtonColorSwitchView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ButtonColorSwitchView.create(this,arrayOf("hello","world","more","to","come","go"))
    }
}
