package ui.anwesome.com.buttoncolorswitchview

/**
 * Created by anweshmishra on 07/02/18.
 */
import android.graphics.*
import android.content.*
import android.view.*
class ButtonColorSwitchView(ctx:Context):View(ctx) {
    val paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas:Canvas) {

    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        return true
    }
}