package ui.anwesome.com.buttoncolorswitchview

/**
 * Created by anweshmishra on 07/02/18.
 */
import android.graphics.*
import android.content.*
import android.view.*


fun getColorWithScale(target:Array<Int>,source:Array<Int>,scale:Float):Int {
    val getColorPartAtIndex:(Int)->Int = {
       source[it] + (scale*(target[it] - source[it])).toInt()
    }
    return Color.rgb(getColorPartAtIndex(0), getColorPartAtIndex(1), getColorPartAtIndex(2))
}

class ButtonColorSwitchView(ctx:Context):View(ctx) {
    val paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas:Canvas) {

    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
    data class Animator(var view:View,var animated:Boolean = false) {
        fun animate(updatecb:()->Unit) {
            if(animated) {
                updatecb()
                try {
                    Thread.sleep(50)
                    view.invalidate()
                }
                catch(ex:Exception) {

                }
            }
        }
        fun start() {
            if(!animated) {
                animated = true
                view.postInvalidate()
            }
        }
        fun stop() {
            if(animated) {
                animated = false
            }
        }
    }
    data class ButtonColorSwitch(var i:Int, var text:String, var x:Float, var y:Float, var w:Float, var h:Float) {
        val state = ButtonColorSwitchState()
        fun draw(canvas:Canvas,paint:Paint) {
            var darkColorRGB:Array<Int> = arrayOf(38,50,56)
            var whiteColorRGB:Array<Int> = arrayOf(255,255,255)
            val blueColorRGB:Array<Int> = arrayOf(48,63,159)
            val r = Math.max(w,h)/8
            canvas.save()
            canvas.translate(x,y)
            paint.style = Paint.Style.FILL
            paint.color = getColorWithScale(darkColorRGB, whiteColorRGB, state.scale)
            canvas.drawRoundRect(RectF(-w/2,-h/2,w/2,h/2),r,r,paint)
            paint.color = getColorWithScale(whiteColorRGB, darkColorRGB, state.scale)
            paint.textSize = h/4
            canvas.drawText(text,-paint.measureText(text)/2,h/8,paint)
            paint.strokeWidth = Math.min(w,h)/30
            paint.style = Paint.Style.STROKE
            paint.color = getColorWithScale(blueColorRGB, darkColorRGB, state.scale)
            canvas.drawRoundRect(RectF(-w/2,-h/2,w/2,h/2),r,r,paint)
            canvas.restore()
        }
        fun update(stopcb:(Int)->Unit) {
            state.update {
                stopcb(i)
            }
        }
        fun startUpdating(startcb:()->Unit) {
            state.startUpdating {
                startcb()
            }
        }
    }
    data class ButtonColorSwitchState(var scale:Float = 0f,var dir:Float = 0f,var prevScale:Float = 0f) {
        fun update(stopcb: ()->Unit) {
            scale += 0.1f*dir
            if(Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                if(prevScale == 1f) {
                    stopcb()
                }
            }
        }
        fun startUpdating(startcb: ()->Unit) {
            if(dir == 0f) {
                dir = 1f - 2*scale
                if(prevScale == 0f) {
                    startcb()
                }
            }
        }
    }
}