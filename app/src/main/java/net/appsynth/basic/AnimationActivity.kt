package net.appsynth.basic

import android.animation.*
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationActivity : AppCompatActivity() {

    companion object {
        private const val ANIMATION_DURATION = 2500L
    }

    private var screenHeight = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        containerFrameLayout.setOnClickListener {
            startAnimationSetTransitionYAndBackground()
        }
    }

    override fun onResume() {
        super.onResume()
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenHeight = displayMetrics.heightPixels.toFloat()
    }

    private fun startViewPropertyAnimatorTranslationY() {
        falconImageView.animate()
                .translationY(-screenHeight)
                .setDuration(ANIMATION_DURATION)
                .setInterpolator(BounceInterpolator())
                .start()
    }

    /**
     * ValueAnimator
     */
    private fun startValueAnimatorRotate() {
        val valueAnimator = ValueAnimator.ofFloat(0f, 360f)

        valueAnimator.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            falconImageView.rotation = value
        }

        valueAnimator.repeatCount = 3
        valueAnimator.duration = ANIMATION_DURATION
        valueAnimator.start()
    }

    private fun startValueAnimatorTranslationYLinearInterpolator() {
        val valueAnimator = ValueAnimator.ofFloat(0f, -screenHeight)

        valueAnimator.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            falconImageView.translationY = value
        }

        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = ANIMATION_DURATION
        valueAnimator.repeatCount = 1
        valueAnimator.repeatMode = ValueAnimator.REVERSE
        valueAnimator.start()
    }

    private fun startValueAnimatorTranslationYAccelerateInterpolator() {
        val valueAnimator = ValueAnimator.ofFloat(0f, -screenHeight)

        valueAnimator.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            falconImageView.translationY = value
        }

        valueAnimator.interpolator = AccelerateInterpolator(2.7f)
        valueAnimator.duration = ANIMATION_DURATION
        valueAnimator.start()
    }

    /**
     * ObjectAnimator
     */
    private fun startObjectAnimatorTranslationY() {
        val objectAnimator = ObjectAnimator.ofFloat(falconImageView, "translationY", 0f, -screenHeight)
        objectAnimator.duration = ANIMATION_DURATION
        objectAnimator.start()
    }

    private fun startObjectAnimatorBackgroundColor() {
        val objectAnimator = ObjectAnimator.ofObject(
                containerFrameLayout,
                "backgroundColor",
                ArgbEvaluator(),
                ContextCompat.getColor(this, R.color.colorNoon),
                ContextCompat.getColor(this, R.color.colorNight)
        )
        objectAnimator.repeatCount = 1
        objectAnimator.duration = ANIMATION_DURATION
        objectAnimator.start()
    }

    private fun startAnimationSetTransitionYAndBackground() {
        val transitionYAnimation = ObjectAnimator.ofFloat(falconImageView, "translationY", 0f, -screenHeight)
        transitionYAnimation.repeatCount = 1
        transitionYAnimation.repeatMode = ValueAnimator.REVERSE

        val backgroundAnimation = ObjectAnimator.ofObject(
                containerFrameLayout,
                "backgroundColor",
                ArgbEvaluator(),
                ContextCompat.getColor(this, R.color.colorNoon),
                ContextCompat.getColor(this, R.color.colorNight)
        )
        backgroundAnimation.repeatCount = 1
        backgroundAnimation.repeatMode = ValueAnimator.REVERSE

        val animatorSet = AnimatorSet()
        animatorSet.play(transitionYAnimation).with(backgroundAnimation)
        animatorSet.duration = ANIMATION_DURATION
        animatorSet.start()
    }

    private fun startAnimationSetTransitionYAndBackgroundReverse() {
        val transitionYAnimation = ObjectAnimator.ofFloat(falconImageView, "translationY", 0f, -screenHeight)
        val transitionYAnimationBack = ObjectAnimator.ofFloat(falconImageView, "translationY", -screenHeight, 0f)
        val backgroundAnimation = ObjectAnimator.ofObject(
                containerFrameLayout,
                "backgroundColor",
                ArgbEvaluator(),
                ContextCompat.getColor(this, R.color.colorNoon),
                ContextCompat.getColor(this, R.color.colorNight)
        )

        val backgroundAnimationBack = ObjectAnimator.ofObject(
                containerFrameLayout,
                "backgroundColor",
                ArgbEvaluator(),
                ContextCompat.getColor(this, R.color.colorNight),
                ContextCompat.getColor(this, R.color.colorNoon)
        )

        val animatorSet = AnimatorSet()
        animatorSet.play(transitionYAnimation).with(backgroundAnimation)
        animatorSet.duration = ANIMATION_DURATION

        animatorSet.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                val animatorSet = AnimatorSet()
                animatorSet.play(transitionYAnimationBack).with(backgroundAnimationBack)
                animatorSet.duration = ANIMATION_DURATION
                animatorSet.start()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })

        animatorSet.start()
    }

    private fun statAnimatorWithXml() {
        val animatorSet = AnimatorInflater.loadAnimator(this, R.animator.falcon_animator) as AnimatorSet
        animatorSet.setTarget(falconImageView)
        animatorSet.start()
    }
}

/**
 * scaleX
 * scaleY
 * translationX
 * translationY
 * rotation
 * rotationX
 * rotationY
 * backgroundColor
 */