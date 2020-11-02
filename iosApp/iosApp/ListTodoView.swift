//
//  ListTodoView.swift
//  iosApp
//
//  Created by The Ant on 10/25/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import SwiftUI
import shared

var testLoginData =  LoginData(token: "oifofoijwojoejoiwjrowejowejowej", user: User(id: 0, userName: "Jack", displayName: "Jack Worthington"))

struct ListTodoView: View {
    var loginData: LoginData? = nil
    
    var body: some View {
        VStack(alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/, spacing: 20) {
            CircleImage()
            Text("Home")
                .font(.largeTitle)
                .fontWeight(.black)
            
            VStack(alignment: .center) {
                Text("\(loginData?.user.userName ?? "")")
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
                Text("\(loginData?.user.displayName ?? "")")
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
                Text("\(loginData?.token ?? "")")
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
            }.padding(.horizontal, 15)
            
            Spacer()
        }.padding(.all, 40.0)
    }
}

struct ListTodoView_Previews: PreviewProvider {
    static var previews: some View {
        ListTodoView(loginData: testLoginData)
    }
}
