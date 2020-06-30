package com.bambulabs.fdingredients

import android.content.Context
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout

class FDIngredients {

    companion object {

        /**
         * Additive list for ingredients
         */

        private val additiveList = arrayOf(
            "colorant",
            "preservative",
            "antioxidant",
            "flavor_enhancer",
            "blackened",
            "waxed",
            "sweetener",
            "phenylalanine",
            "laxative_if_used_excessively",
            "milk_protein",
            "phosphate",
            "quinine",
            "caffeine",
            "taurine",
            "irradiated",
            "treated",
            "sulphuretted"
        )

        /**
         * Get ingredient names with underscore string resources
         *
         * @param ingredient
         * @return
         */

        fun getNameWithUnderscore(ingredient : String) : String {

            var renamed = ingredient

            if (ingredient.contains("-"))
                renamed = ingredient.replace("-", "_")

            return renamed
        }

        /**
         * Get ingredient names with prefix for drawable resources
         *
         * @param ingredient
         * @return
         */

        fun getNameForDrawable(ingredient : String) : String {

            var renamed = ingredient

            renamed = "ico_".plus(renamed)

            return renamed
        }

        /**
         * Check ingredient is additive
         *
         * @param ingredient
         * @return
         */

        fun isAdditive(ingredient : String) : Boolean {

            return additiveList.contains(ingredient)
        }

        /**
         * Get ingredient view with custom parameters
         *
         * @param context
         * @param ingredient
         * @param pair
         * @param biggerTypeOption
         * @param color
         * @return
         */

        fun getIngredientView(
            context: Context,
            ingredient: String,
            pair: Pair<Int, Int>,
            biggerTypeOption: Boolean,
            color: Int
        ): ImageView? {

            val resID = getIngredientResID(ingredient)

            if (resID != -1) {

                val imageView = ImageView(context)
                imageView.setImageResource(resID)

                val params = if (biggerTypeOption) {
                    LinearLayout.LayoutParams(
                        pair.first + pair.second * 2,
                        pair.first + pair.second * 2
                    )
                } else {
                    LinearLayout.LayoutParams(
                        pair.first + pair.second,
                        pair.first + pair.second
                    )

                }
                params.setMargins(4, 4, 4, 4)
                params.gravity = Gravity.CENTER
                imageView.layoutParams = params

                imageView.setColorFilter(color)

                return imageView

            } else
                return null
        }

        /**
         * Get ingredient res ID
         *
         * @param ingredient
         * @return
         */

        private fun getIngredientResID(ingredient: String): Int {
            return getResId(
                getNameWithUnderscore(getNameForDrawable(ingredient)),
                R.drawable::class.java
            )
        }

        fun getResId(resName: String, c: Class<*>): Int {
            val idField = c.getDeclaredField(resName)
            return idField.getInt(idField)
        }

    }
}