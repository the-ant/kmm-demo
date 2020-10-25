import SwiftUI
import shared

func greet() -> String {
    return Greeting().greeting()
}

func login() {
    return Api().login(username: "test01", password: "1")
}

struct ContentView: View {
    var body: some View {
        Text(greet())
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
