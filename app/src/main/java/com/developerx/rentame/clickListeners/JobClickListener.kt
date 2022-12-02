package com.developerx.rentame.clickListeners

import com.developerx.rentame.models.Jobs

interface JobClickListener {
    fun itemClickListener(jobs: Jobs)
}