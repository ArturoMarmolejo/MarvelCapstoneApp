package com.arturomarmolejo.marvelcapstoneapp.utils

class NullCharacterListResponse(message: String = "Character List response is null"): Exception(message)
class FailureResponse(message: String?): Exception(message)