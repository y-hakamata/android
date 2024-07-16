package com.example.myapplication

interface R {
    interface layout {
        companion object {
            const val activity_main: Int = 0x7f040000
            // Define other layout resources here if needed
        }
    }

    interface string {
        companion object {
            const val button_equal: Int = 0x7f060001
            const val button_clear: Int = 0x7f060002
            const val app_name: Int = 0x7f060000
            // Define other string resources here if needed
        }
    }

    // Add similar interfaces for other resource types like drawable, dimen, etc.
}
