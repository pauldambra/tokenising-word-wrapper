package wordwrapper

import wordwrapper.Tokenise.Companion.byWord
import kotlin.coroutines.experimental.buildSequence

class Tokenise {
    companion object {
        fun byWord(s: String) = s.split("\\s+".toRegex())
    }
}

fun tokenise(input: String, splitter: (s: String) -> List<String> = ::byWord) = buildSequence {
    splitter(input).forEach { yield(it) }
}

fun wrap(tokens: Sequence<String>, columns: Int = 7) = buildSequence {
    var current = ""
    tokens.forEach {
        val candidate = "$current $it".trim()
        current = if (candidate.length > columns) {
            yield(current)
            it
        } else {
            candidate
        }
    }
    yield(current)
}