package com.yes.inmyfood.util

import com.squareup.otto.Bus

class GlobalBus {

    companion object {
        private val bus:Bus = Bus()

        fun getBus() : Bus {
            return bus
        }
    }
}