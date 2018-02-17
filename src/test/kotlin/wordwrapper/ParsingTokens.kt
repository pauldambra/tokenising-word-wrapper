package wordwrapper

import assertk.assert
import assertk.assertions.containsAll
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import kotlin.coroutines.experimental.buildSequence

object ParsingTokens : Spek(
{
    context("tokenised by and respecting word boundary") {
        it("can split on 7 columns") {
            val tokenStream = sequenceOf("the", "mad", "cat", "has", "no", "pajamas")
            val rows = wrap(tokenStream)
            assert(rows.toList()).containsAll("the mad", "cat has", "no", "pajamas")
        }

        it("can split on 8 columns") {
            val tokenStream = sequenceOf("the", "crazy", "cat", "has", "no", "pajamas")
            val rows = wrap(tokenStream, 9)
            assert(rows.toList()).containsAll("the crazy", "cat has", "no", "pajamas")
        }

        it("can wrap from the tokeniser") {
            val input = "the mad cat has no pajamas"
            val rows = wrap(tokenise(input))
            assert(rows.toList()).containsAll("the mad", "cat has", "no", "pajamas")
        }
    }
})
