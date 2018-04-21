package edu.champlain.hangman_client.MODELS

class Word {
    private var word: String = ""
    var encrypt: String = ""

    fun newWord(newWord: String): String {
        word = newWord
        return getEncryptedWord()
    }

//  Pre: This function takes no parameters
//  Post: This function returns the encrypt variable
//  Purpose: This function will be called while the encrypt variable
//    has nothing in it, and it will set it to a string the size of
//    the word that contains a series of question marks
    fun getEncryptedWord(): String {
        if (encrypt == "") {
            encrypt = word
            encrypt = encrypt.map { _ -> '?' }.joinToString("")
        }
        return encrypt
    }


//  Pre: This function takes in a letter that should exist in the word
//  Post: This function returns the encrypt variable
//  Purpose: This function will be called with a letter that should be
//    in the word. Where it appears in the word we will reveal it in
//    the encrypted word and return said variable
    fun getEncryptedWord(letter: String): String {

        var splitWord = word.toCharArray()

        println("Word is $word, encrypted word is $encrypt")

        while (splitWord.contains(letter[0])) {
            splitWord.forEachIndexed({
                index, c ->
                    if (c == letter[0]) {
                        splitWord[index] = '?'
                        encrypt = if (index != encrypt.length - 1)
                            encrypt.replaceRange(index, index + 1, letter)
                        else
                            "${encrypt.dropLast(1)}${letter[0]}"
                    }
            })
        }

        println("New letter revealed $encrypt")

        return encrypt
    }
}