<img src="https://github.com/from-source/kiwi/blob/master/img/850x350_kiwi_SMALL.png">

# Kiwi: Fluent assertions for Kotlin projects

[![Build Status](https://travis-ci.com/from-source/kiwi.svg?branch=master)](https://travis-ci.com/from-source/kiwi)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://github.com/from-source/kiwi/blob/master/LICENSE.md)


## Kiwi, Hello World!

Below snippet demonstrates usage of Kiwi assertions for standard type like String
```kotlin
    @Test
    fun `should say hallo to kiwi()` {
        "Kiwi, Hello World!".should() startWith "Kiwi" contain "Hello" endWith "!"
    }
```

Kiwi supports collections
```kotlin
    @Test
    fun `should combine operator in infix chain`() {
        val animals = listOf("kiwi", "hedgehog", "flamingo", "humpback")

        animals.should() haveSize 4 contain "humpback" have1st "kiwi"
    }
```

as well collections with custom types
```kotlin
    data class Animal(val name: String, val weight: Int, val mammal: Boolean) {
       fun heavy(): Boolean = weight > 10
    }

    val kiwi     = Animal(name = "kiwi", weight = 1, mammal = true)
    val hedgehog = Animal(name = "hedgehog", weight = 2, mammal = true)
    val flamingo = Animal(name = "flamingo", weight = 5, mammal = false)
    val humpback = Animal(name = "humpback", weight = 5000, mammal = true)


    @Test
    fun `should apply collection operators for list of custom object`() {

        val animals = listOf(kiwi, hedgehog, flamingo, humpback)

        animals.should()
                .haveSize(4)
                .contain(flamingo)
                .beSorted { it.weight }
                .filtered { animal -> animal.mammal }
                .matchAny { animal -> animal.heavy() }

    }
```

Different types of operators e.g. `Collection`, `String`, `Numbers` can be used fluently

```kotlin
    @Test
    fun `should mix different types of operators`() {

        val animals = listOf(kiwi, hedgehog, flamingo, humpback)

        animals.should()
                .contain(hedgehog)                             // Collection operator
                .last().name.should()                          // extract
                .match(Regex("[a-z]+"))                        // String operator
    }
```

## Build
Run command below
```bash
$ git clone git@github.com/from-source/kiwi.git
$ cd kiwi/
$ ./gradlew clean build
```
