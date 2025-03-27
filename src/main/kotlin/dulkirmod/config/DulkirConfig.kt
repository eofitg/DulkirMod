package dulkirmod.config

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.*
import cc.polyfrost.oneconfig.config.data.Mod
import cc.polyfrost.oneconfig.config.data.ModType


object DulkirConfig : Config(Mod("DulkirMod", ModType.SKYBLOCK), "dulkirmod-config.json") {

    // CUSTOM ANIMATIONS
    @Switch(
        name = "Global Toggle",
        description = "Change the look of your held item",
        category = "Animations",
        subcategory = "Animations"
    )
    var customAnimations = false

    @Slider(
        name = "Size",
        description = "Scales the size of your currently held item. Default: 0",
        category = "Animations",
        subcategory = "Animations",
        min = -1.5f,
        max = 1.5f,
        step = 0
    )
    var customSize = 0f

    @Checkbox(
        name = "Scale Swing",
        description = "Also scale the size of the swing animation.",
        category = "Animations",
        subcategory = "Animations"
    )
    var doesScaleSwing = true

    @Slider(
        name = "X",
        description = "Moves the held item. Default: 0",
        category = "Animations",
        subcategory = "Animations",
        min = -1.5f,
        max = 1.5f,
        step = 0
    )
    var customX = 0f

    @Slider(
        name = "Y",
        description = "Moves the held item. Default: 0",
        category = "Animations",
        subcategory = "Animations",
        min = -1.5f,
        max = 1.5f,
        step = 0
    )
    var customY = 0f

    @Slider(
        name = "Z",
        description = "Moves the held item. Default: 0",
        category = "Animations",
        subcategory = "Animations",
        min = -1.5f,
        max = 1.5f,
        step = 0
    )
    var customZ = 0f

    @Slider(
        name = "Yaw",
        description = "Rotates your held item. Default: 0",
        category = "Animations",
        subcategory = "Animations",
        min = -180f,
        max = 180f,
        step = 1
    )
    var customYaw = 0f

    @Slider(
        name = "Pitch",
        description = "Rotates your held item. Default: 0",
        category = "Animations",
        subcategory = "Animations",
        min = -180f,
        max = 180f,
        step = 1
    )
    var customPitch = 0f

    @Slider(
        name = "Roll",
        description = "Rotates your held item. Default: 0",
        category = "Animations",
        subcategory = "Animations",
        min = -180f,
        max = 180f,
        step = 1
    )
    var customRoll = 0f

    @Slider(
        name = "Speed",
        description = "Speed of the swing animation.",
        category = "Animations",
        subcategory = "Animations",
        min = -2f,
        max = 1f,
        step = 0
    )
    var customSpeed = 0f

    @Checkbox(
        name = "Ignore Haste",
        description = "Makes the chosen speed override haste modifiers.",
        category = "Animations",
        subcategory = "Animations",
    )
    var ignoreHaste = true

    fun init() {
        initialize()
    }
}
