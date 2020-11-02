import SwiftUI
import shared

func greet() -> String {
    return Greeting().greeting()
}

func randomUUID() -> String {
    return Greeting().randomUUID()
}

struct IntroView: View {
    @State var flag = true
    
    var body: some View {
        NavigationView {
            VStack {
                Image("banner")
                    .resizable()
                    .frame(maxHeight: 300)
                    .edgesIgnoringSafeArea(.top)
                CircleImage()
                    .offset(y: -250)
                
                VStack(alignment: .center, spacing: 10.0) {
                    Text(greet())
                        .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
                        .foregroundColor(.orange)
                        .multilineTextAlignment(.center)
                    Text("\(randomUUID())")
                        .font(.headline)
                        .foregroundColor(.green)
                        .multilineTextAlignment(.center)
                    Text("Welcome to Kotlin Multiplatform")
                        .font(.headline)
                        .foregroundColor(.green)
                        .multilineTextAlignment(.center)
                }.padding()
                .offset(y: -250)
                
                Spacer()
                
                VStack(alignment: .center, spacing: 0.0) {
                    NavigationLink(destination: LoginView()) {
                        Text("Login")
                            .accentColor(Color.white)
                            .padding()
                            .background(Color(UIColor.orange))
                            .cornerRadius(5.0)
                    }
                    NavigationLink(destination: RegisterView()) {
                        Text("Register")
                            .accentColor(Color.white)
                            .padding()
                            .background(Color(UIColor.orange))
                            .cornerRadius(5.0)
                            .padding(Edge.Set.vertical, 8)
                    }
                }
            }
        }
    }
}

struct IntroView_Previews: PreviewProvider {
    static var previews: some View {
        IntroView()
    }
}
