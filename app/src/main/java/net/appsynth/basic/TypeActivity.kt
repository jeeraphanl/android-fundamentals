package net.appsynth.basic

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_type.*

class TypeActivity : AppCompatActivity() {

    companion object {
        private const val ANIMATION_DURATION = 2500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type)

        scale()
        translation()
        rotation()
        alpha()
    }

    private fun scale() {
        val scaleX = ObjectAnimator.ofFloat(scaleView, "scaleX", 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(scaleView, "scaleY", 0f, 1f)

        AnimatorSet().apply {
            play(scaleX).with(scaleY)
            duration = ANIMATION_DURATION
            start()
        }.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                scale()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
    }

    private fun translation() {
        ObjectAnimator.ofFloat(translationView, "translationX", 0f, 200f).apply {
            duration = ANIMATION_DURATION
            repeatMode = ObjectAnimator.REVERSE
            start()
        }.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                translation()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
    }

    private fun rotation() {
        ObjectAnimator.ofFloat(rotationView, "rotation", 0f, 360f).apply {
            duration = ANIMATION_DURATION
            start()
        }.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                rotation()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
    }

    private fun alpha() {
        ObjectAnimator.ofFloat(alphaView, "alpha", 0f, 0.1f).apply {
            duration = ANIMATION_DURATION
            start()
        }.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                alpha()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })

        var helpBob: HelpBob? = null

        // let and run
        helpBob?.let {
            print(it)
        } ?: run {
            print(this)
        }

        // apply and also
        helpBob = HelpBob().apply {
            name = "name"
            age = 1
        }.also {
            print(this)
        }


        // with
        // only nonnull, it will be crash
        with(helpBob){
            print(this.name)
            print(this.age)
        }
    }
}

class HelpBob {
    var name: String = ""
    var age: Int = 0
}