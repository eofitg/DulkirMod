package dulkirmod.config

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.*
import cc.polyfrost.oneconfig.config.data.Mod
import cc.polyfrost.oneconfig.config.data.ModType


object DulkirConfig : Config(Mod("SwingSpeed", ModType.PVP), "swingspeed-config.json") {

    @Switch(
        name = "Global Toggle",
        description = "Change the look of your held item",
        category = "Animations",
        subcategory = "Animations"
    )
    var globalEnabled = true

    @Slider(
        name = "Speed",
        description = "Speed of the swing animation.",
        category = "Animations",
        subcategory = "Animations",
        min = -2f,
        max = 1f,
        step = 0
    )
    var speed = 0f

    @Slider(
            name = "Haste Speed",
            description = "Speed of the swing animation when player has haste.",
            category = "Animations",
            subcategory = "Animations",
            min = -2f,
            max = 1f,
            step = 0
    )
    var hasteSpeed = 0f

    @Slider(
            name = "Fatigue Speed",
            description = "Speed of the swing animation when player has mining fatigue.",
            category = "Animations",
            subcategory = "Animations",
            min = -2f,
            max = 1f,
            step = 0
    )
    var fatigueSpeed = 0f

    @Checkbox(
        name = "Ignore Haste",
        description = "Makes the chosen speed override haste modifiers.",
        category = "Animations",
        subcategory = "Animations",
    )
    var disregardHaste = true

    @Checkbox(
        name = "Ignore Mining Fatigue",
        description = "Makes the chosen speed override mining fatigue modifiers.",
        category = "Animations",
        subcategory = "Animations"
    )
    var disregardFatigue = true

    fun init() {
        initialize()
    }
}
