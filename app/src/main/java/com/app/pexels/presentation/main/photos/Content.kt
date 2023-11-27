package com.app.pexels.presentation.main.photos

sealed interface Content {

    object PhotoListContent : Content
    object StubEmptyDataContent : Content
    object NetworkStubContent : Content
}