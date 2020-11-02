//
//  CircleImage.swift
//  iosApp
//
//  Created by The Ant on 10/25/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import SwiftUI

struct CircleImage: View {
    var body: some View {
        Image("rose")
            .resizable()
            .scaledToFill()
            .frame(width: 200.0, height: 200.0, alignment: .center)
            .clipShape(Circle())
            .overlay(Circle().stroke(Color.white))
            .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
    }
}

struct CircleImage_Previews: PreviewProvider {
    static var previews: some View {
        CircleImage()
    }
}
