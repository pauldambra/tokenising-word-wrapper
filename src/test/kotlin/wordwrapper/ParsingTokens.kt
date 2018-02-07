package wordwrapper

import assertk.assert
import assertk.assertions.containsAll
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

object ParsingTokens : Spek(
{
    it("can split on 7 columns") {
        val tokenStream = listOf("the", "mad", "cat", "has", "no", "pajamas").asSequence()
        val rows = wrap(tokenStream)
        assert(rows).containsAll("the mad", "cat has", "no", "pajamas")
    }

    it("can split on 8 columns") {
        val tokenStream = listOf("the", "crazy", "cat", "has", "no", "pajamas").asSequence()
        val rows = wrap(tokenStream, 9)
        assert(rows).containsAll("the crazy", "cat has", "no", "pajamas")
    }
})

fun wrap(tokens: Sequence<String>, columns: Int = 7): List<String> {
    val wrapped = mutableListOf<String>()
    var currentRow = ""
    tokens.forEach {
        val candidate = "$currentRow $it".trim()
        currentRow = if (candidate.length > columns) {
            wrapped.add(currentRow)
            it
        } else {
            candidate
        }
    }
    wrapped.add(currentRow)
    return wrapped.toList()
}