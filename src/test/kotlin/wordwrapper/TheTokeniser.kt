package wordwrapper

import assertk.assert
import assertk.assertions.isEqualTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import wordwrapper.Tokenise.Companion.byWord

object TheTokeniser : Spek(
{
    context("tokenised by word boundary") {
        it("can tokenise to sequence by word boundary") {
            val input = "the mad cat has no pajamas"
            val tokenisedStream = tokenise(input)
            val expectedTokenStream = sequenceOf("the", "mad", "cat", "has", "no", "pajamas")
            assert(tokenisedStream.toList()).isEqualTo(expectedTokenStream.toList())
        }

        it("can explicitly tokenise by word boundary") {
            val input = "the mad cat has no pajamas"
            val tokenisedStream = tokenise(input, ::byWord)
            val expectedTokenStream = sequenceOf("the", "mad", "cat", "has", "no", "pajamas")
            assert(tokenisedStream.toList()).isEqualTo(expectedTokenStream.toList())
        }
    }

    context("tokenising by passing in a tokeniser") {
        it("can tokenise on numbers") {
            val input = "cat number 5 has 6 pajamas"

            val tokenisedStream = tokenise(
                    input,
                    fun(s) = s.split("\\d+".toRegex()))

            val expectedTokenStream = sequenceOf("cat number ", " has ", " pajamas")
            assert(tokenisedStream.toList()).isEqualTo(expectedTokenStream.toList())
        }
    }
})
