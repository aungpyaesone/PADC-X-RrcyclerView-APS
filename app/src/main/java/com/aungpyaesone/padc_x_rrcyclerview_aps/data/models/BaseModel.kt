package com.aungpyaesone.padc_x_rrcyclerview_aps.data.models

import com.aungpyaesone.padc_x_rrcyclerview_aps.network.data_agent.HttUrlConnectionDataAgentImpl
import com.aungpyaesone.padc_x_rrcyclerview_aps.network.data_agent.NewsDataAgent
import com.aungpyaesone.padc_x_rrcyclerview_aps.network.data_agent.OKHttpUrlConnectionDataAgentImpl
import com.aungpyaesone.padc_x_rrcyclerview_aps.network.data_agent.RetrofitDataAgentImpl

abstract class BaseModel(
   // val mDataAgent: NewsDataAgent = RetrofitDataAgentImpl
    val mDataAgent: NewsDataAgent = RetrofitDataAgentImpl
)