package com.example.xmasapp

import kotlin.random.Random

// stworzylam obiekt w kotlinie ktory zawiera wszystkie typy
// ktore moga sie wyswietlic po kliknieciu w przycisk
// bo w poleceniu jest ze ma byc randomowe przywitanie, prezent i moze sie pojawic grinch co psoci
// nazwalam go santaself bo w sumie elf rozdaje prezenty in real life
object SantasElf {
    private val greetings = listOf(
        "Merry Christmas and Happy Holidays! 🎄",
        "Wishing you a joyful and festive season! ❄️",
        "May your days be merry and bright! ✨",
        "Ho Ho Ho! Merry Christmas! 🎅",
        "Warm wishes for a wonderful Christmas! 🎁"
    )

    private val gifts = listOf(
        "Socks 🧦", "Lego Set 🧱", "Teddy Bear 🧸", "Book 📚",
        "Scarf 🧣", "Perfume 🌸", "Headphones 🎧", "Board Game 🎲"
    )

    private val grinchMessages = listOf(
        "The Grinch stole your presents! 😈",
        "Oops! Wishes canceled, the Grinch was here first! 👀",
        "The Grinch took all the gifts away! 🎁❌"
    )

    fun getRandomGreeting(): String = greetings.random()

    fun getRandomGift(): String = gifts.random()

    fun getGrinchSurprise(): String = grinchMessages.random()

    fun isGrinchTime(): Boolean = Random.nextInt(0, 10) > 7  // 30% ze sie ten zielony stwor pojawi lol
}
