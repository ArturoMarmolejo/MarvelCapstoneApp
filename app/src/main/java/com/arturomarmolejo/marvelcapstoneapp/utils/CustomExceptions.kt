package com.arturomarmolejo.marvelcapstoneapp.utils

class NullCharacterListResponse(message: String = "Character List response is null"): Exception(message)
class NullCreatorListResponse(message: String = "Creator List response is null"): Exception(message)
class NullComicListResponse(message: String = "Comic List response is null"): Exception(message)
class FailureResponse(message: String?): Exception(message)