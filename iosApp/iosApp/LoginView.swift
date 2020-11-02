//
//  LoginView.swift
//  iosApp
//
//  Created by The Ant on 10/25/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LoginView: View {
    private var api: Api = Api()
    
    @State var username: String = ""
    @State var password: String = ""
    @State var loginData: LoginData? = nil
    
    @State var showListTodos: Bool = false
    
    var body: some View {
        VStack(alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/, spacing: 20) {
            CircleImage()
            Text("Login")
                .font(.largeTitle)
                .fontWeight(.black)
            
            VStack(alignment: .leading) {
                Text("Username").font(.headline)
                TextField("Enter your username", text: $username)
                    .accentColor(Color.black)
                    .padding()
                    .background(
                        Color(
                            red: 239.0/255.0,
                            green: 243.0/255.0,
                            blue: 244.0/255.0,
                            opacity: 1.0
                        )
                    )
                    .cornerRadius(5.0)
                    .autocapitalization(.none)
            }.padding(.horizontal, 15)
            
            VStack(alignment: .leading) {
                Section(header: Text("Password").font(.headline)) {
                    SecureField("Enter your password", text: $password)
                        .accentColor(Color.black)
                        .padding()
                        .background(
                            Color(
                                red: 239.0/255.0,
                                green: 243.0/255.0,
                                blue: 244.0/255.0,
                                opacity: 1.0
                            )
                        )
                        .cornerRadius(5.0)
                        .autocapitalization(.none)
                }
            }.padding(.horizontal, 15)
            
            NavigationLink(destination: ListTodoView(loginData: loginData), isActive: $showListTodos) {
                Button("Continue", action: {
                    api.login(username: username, password: password) { (data) in
                        if (data != nil) {
                            self.showListTodos = true
                            self.loginData = data
                        }
                    }
                })
            }
            .foregroundColor(.white)
            .padding()
            .background(Color(UIColor.orange))
            .cornerRadius(5.0)
            .padding(Edge.Set.vertical, 10)
            
            Spacer()
        }.padding(.all, 40.0)
    }
}

struct LoginView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView()
    }
}
