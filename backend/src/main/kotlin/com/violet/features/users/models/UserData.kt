package com.violet.features.users.models

import auth.data.UserData


fun UserData.toUpdatedUser(): UpdatedUser {
    return UpdatedUser(
        id = id,
        email = email,
        verified = verified
    )
}

fun ExistingUser.toUserData() = UserData(
    id = id,
    email = email,
    verified = verified
)