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
        private const val ANIMATION_SHORT_DURATION = 1000L
        private const val ANIMATION_DURATION = 2500L
    }

    private var screenHeight = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        containerFrameLayout.setOnClickListener {
            startValueAnimatorRotate()
        }
    }

    override fun onResume() {
        super.onResume()
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenHeight = displayMetrics.heightPixels.toFloat()
    }

    /**
     * ValueAnimator
     */
    private fun startValueAnimatorRotate() {

        ValueAnimator.ofFloat(0f, 360f).apply {
            addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Float
                falconImageView.rotation = value
            }

            repeatCount = 3
            duration = ANIMATION_SHORT_DURATION
            start()
        }.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                startValueAnimatorRotate()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
    }

    private fun startValueAnimatorTranslationYLinearInterpolator() {
        ValueAnimator.ofFloat(0f, -screenHeight).apply {
            addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Float
                falconImageView.translationY = value
            }

            interpolator = LinearInterpolator()
            duration = ANIMATION_DURATION
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            start()
        }
    }

    private fun startValueAnimatorTranslationYAccelerateInterpolator() {

        ValueAnimator.ofFloat(0f, -screenHeight).apply {
            addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Float
                falconImageView.translationY = value
            }

            interpolator = AccelerateInterpolator(2.7f)
            duration = ANIMATION_DURATION
            start()
        }
    }

    /**
     * ObjectAnimator
     */
    private fun startObjectAnimatorTranslationY() {
        ObjectAnimator.ofFloat(falconImageView, "translationY", 0f, -screenHeight).apply {
            duration = ANIMATION_DURATION
            start()
        }
    }

    private fun startObjectAnimatorBackgroundColor() {
        ObjectAnimator.ofObject(
                containerFrameLayout,
                "backgroundColor",
                ArgbEvaluator(),
                ContextCompat.getColor(this, R.color.colorNoon),
                ContextCompat.getColor(this, R.color.colorNight)
        ).apply {
            repeatCount = 1
            duration = ANIMATION_DURATION
            start()
        }
    }

    /**
     * Final animator
     */
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

        AnimatorSet().apply {
            play(transitionYAnimation).with(backgroundAnimation)
            duration = ANIMATION_DURATION
            start()
        }
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

    /**
     * Android API 27
     */
    private fun startViewPropertyAnimatorTranslationY() {
        falconImageView.animate()
                .translationY(-screenHeight)
                .setDuration(ANIMATION_DURATION)
                .setInterpolator(BounceInterpolator())
                .start()
    }

    /**
     * Animator XML
     */
    private fun statAnimatorWithXml() {
        val animatorSet = AnimatorInflater.loadAnimator(this, R.animator.falcon_animator) as AnimatorSet
        animatorSet.setTarget(falconImageView)
        animatorSet.start()
    }
}

/**
 * apha
 * scaleX
 * scaleY
 * translationX
 * translationY
 * rotation
 * rotationX
 * rotationY
 * backgroundColor
 */