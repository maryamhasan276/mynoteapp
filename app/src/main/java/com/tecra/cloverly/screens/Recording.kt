package com.tecra.cloverly.screens

import java.io.Serializable

class Recording(var uri: String, var fileName: String
                , var isPlaying: Boolean
) :Serializable {}