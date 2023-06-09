package ru.netology.nmedia


import android.R.attr.x
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import kotlin.math.floor
import kotlin.math.round
import kotlin.math.roundToInt
import ru.netology.nmedia.dto.WallService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val post = Post(
            0,
            "Нетология, Университет-профессий будущего",
            "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать бастрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netolo.gy/fyb",
            "21 мая в 18:36",
            false
        )

        with(binding) {
            authorTextView.text = post.author
            postDateTextView.text = post.published
            contentTextView.text = post.content

            if (post.likeByMe) {
                like.setImageResource(R.drawable.baseline_favorite_24)
            }

            likesAmount.text = WallService.countAmountFormat(post.likes)
            shareAmount.text = WallService.countAmountFormat(post.shares)
            viewsAmount.text = WallService.countAmountFormat(post.views)

            like.setOnClickListener {
                post.likeByMe = !post.likeByMe
                post.likes = if (post.likeByMe) {
                    post.likes + 1
                } else {
                    post.likes - 1
                }

                likesAmount.text = WallService.countAmountFormat(post.likes)

                like.setImageResource(
                    if (post.likeByMe) {
                        R.drawable.baseline_favorite_24
                    } else {
                        R.drawable.baseline_favorite_border_24
                    }
                )
            }

            share.setOnClickListener {
                post.shares += 1
                shareAmount.text = WallService.countAmountFormat(post.shares)
            }

            views.setOnClickListener {
                post.views += 1
                viewsAmount.text = WallService.countAmountFormat(post.views)
            }
        }
    }
}