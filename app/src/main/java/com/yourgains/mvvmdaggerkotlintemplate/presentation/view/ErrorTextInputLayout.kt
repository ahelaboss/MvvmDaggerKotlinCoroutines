package com.yourgains.mvvmdaggerkotlintemplate.presentation.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import com.yourgains.mvvmdaggerkotlintemplate.R

class ErrorTextInputLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {

    private var errorBackground: Drawable? = null
    private var normalBackground: Drawable? = null

    private var errorDrawableStart: Drawable? = null
    private var normalDrawableLeft: Drawable? = null

    private var errorTextCopy: CharSequence? = null

    init {
        initParams(attrs)
    }

    private fun initParams(attrs: AttributeSet?) {
        if (attrs != null) {
            val typedArray =
                context?.obtainStyledAttributes(attrs, R.styleable.ErrorTextInputLayout, 0, 0)
            typedArray?.let {
                try {
                    errorBackground =
                        typedArray.getDrawable(R.styleable.ErrorTextInputLayout_error_background)
                    errorDrawableStart =
                        typedArray.getDrawable(R.styleable.ErrorTextInputLayout_error_drawable_start)
                } finally {
                    typedArray.recycle()
                }
            }
        }
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        changeBackground()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        changeDrawableLeft()
    }

    override fun setError(error: CharSequence?) {
        errorTextCopy = error
        super.setError(error)
        changeDrawableLeft()
    }

    private fun changeBackground() {
        editText?.let { et ->
            errorBackground?.let {
                if (normalBackground == null) {
                    normalBackground = et.background
                    normalBackground?.clearColorFilter()
                }
                if (TextUtils.isEmpty(errorTextCopy)) {
                    et.background = normalBackground
                } else {
                    et.background = it
                }
                et.background?.clearColorFilter()
            }
        }
    }

    private fun changeDrawableLeft() {
        editText?.let { et ->
            errorDrawableStart?.let {
                if (normalDrawableLeft == null) normalDrawableLeft = et.compoundDrawables[0]
                if (TextUtils.isEmpty(errorTextCopy)) {
                    et.setCompoundDrawablesWithIntrinsicBounds(normalDrawableLeft, null, null, null)
                } else {
                    et.setCompoundDrawablesWithIntrinsicBounds(it, null, null, null)
                }
            }
        }
    }
}