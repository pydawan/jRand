package me.xdrop.jrand.generators.person

import me.xdrop.jrand.JRand
import me.xdrop.jrand.data.AssetLoader
import me.xdrop.jrand.data.Assets

class LastnameGeneratorTest extends GroovyTestCase {
    def instance = { -> JRand.lastname() }
    def names = Assets.NEUTRAL_SURNAMES.loadItems()

    void testGen() {
        assertTrue instance().gen() in names
    }

    void testPrint() {
        println instance().gen()
    }
}
