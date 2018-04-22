# hangman-client
Hangman Client Written in Kotlin for Emerging Languages Final 
[Corresponding server written in go](https://github.com/codeWonderland/hangman-server)

## Usage
- Run the [corresponding go server](https://github.com/codeWonderland/hangman-server)
- Open the project in Android Studio
- in `GameActivity.kt`, change the variable `serverUrl` to be `ws://<ipOfServer>:3000/ws
  - Note: if you are running this in an emulator, you CANNOT use localhost in place of the ip address, instead get the interal ip address of your computer
- Run the project!
