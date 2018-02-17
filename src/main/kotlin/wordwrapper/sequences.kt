package wordwrapper

import kotlin.coroutines.experimental.buildSequence

fun tokenise(input: String) = buildSequence {
    val splat = input.split("\\s+".toRegex())
    splat.forEach { yield(it) }
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